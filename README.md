# mybatis-plugin-shard
- 基于spring 切面（AOP）实现动态多数据源切换。
- 基于 MyBatis 插件方式实现动态分表查询。
- 来源于多个已上线项目实践。
- 本项目有完整的测试示例。

以后会出详细的文档，敬请期待。

# todo
- [x] 将分表需要的参数添加到插件属性而不是配置文件
- [ ] 完善分表逻辑
- [ ] 完善文档

# 项目地址
- github: [https://github.com/uncleAndyChen/mybatis-plugin-shard](https://github.com/uncleAndyChen/mybatis-plugin-shard)
- gitee: &nbsp;&nbsp;[https://gitee.com/uncleAndyChen/mybatis-plugin-shard](https://gitee.com/uncleAndyChen/mybatis-plugin-shard)

# 配套 MBG 增强插件
查看 MBG 增强插件请移步：[mybatis-generator](https://github.com/uncleAndyChen/mybatis-generator)
- 用该 MBG 增强插件生成的 {xxx}Mapper.xml，会把表名用[\`]（不包括中括号）引起来，这样做的目的是分表时，动态给表名添加后缀后替换原始表名时不会“添乱”。
- 注意 [\`] 并非单引号，是在ESC 键下面、Q 键左上角的数字键 1 的左边那个键对应的“单引号”。
- 比如有两张表：biz_trade、biz_trade_order，现在需要动态将 biz_trade 替换成 biz_trade_9，如果表名前后没有[\`]，则 biz_trade_order 也会被替换，替换后为：biz_trade_9_order，这显然不是我们希望发生的。

# 功能概述
- 分库：简单的分库功能，更确切的讲，是多数据源管理，可根据业务动态切换，基于切面（AOP）。
- 分表：对于同一数据源或不同数据源下的相同表结构的表，通过简单配置，实现分表查询功能。
    - 适用数据量增加迅速的业务场景。
    - 底层实现：基于 MyBatis 插件，拦截最终执行的 SQL 语句并且根据分表配置对 SQL 语句中的表名进行修改之后再执行。
        - 要求表名必须用 [\`]（不包括中括号）引起来。请使用增强插件（[mybatis-generator](https://github.com/uncleAndyChen/mybatis-generator)）生成 Mapper 和 entity model。

# 动态切换数据源的三种方式
- 通过参数 [ShardView.java](https://github.com/uncleAndyChen/mybatis-plugin-shard/blob/master/common/common-shard/src/main/java/common/shard/ShardView.java) 指定：优先级最高，也最灵活。
    - 可以根据具体业务场景决定要连接哪个数据源，无论是分表还是分库都可以灵活应对。
- 注解：可用在类和方法上，方法注解优先于类注解。
- biz service 配置
    - 以上两种方式均没有的情况下，会读取 biz.service.{shardKeySchema} 配置信息。
    - 事先在配置文件 db-shard.properties 中配置好 biz.service.{shardKeySchema} 对应的服务类接口，在运行过程中，通过 AOP 拦截 biz.service，从而识别应该使用哪个数据源，达到分库/多数据源动态切换的目的。
    - 这种方式的优点：可以由专人统一管理，同时生产环境与开发、测试环境可以用不同的配置信息，开发人员与测试人员不用关注分库的细节。

如果以上三种方式都没有找到数据源，则使用默认的数据源。

# 分库分表思路
- 分库思路：
    - 每个库有一个唯一的标志，起名叫 shardKeySchema，每个数据库的 shardKeySchema 与 [db-source.xml](https://github.com/uncleAndyChen/mybatis-plugin-shard/blob/master/biz/biz-config/src/main/resources/db-source.xml) 定义的数据源 dataSource -> targetDataSources -> map -> key 一一对应。
    - 用户在初始化时根据业务规则分配到某一个库，将该库的 shardKeySchema 保存到用户表。
- 分表思路：
    - 每个用户分配一个用于分表的数字编号 shardKeyTableNumber，同样保存到用户表。
- 用户表：
    - 集中在一个库用于统一登录验证，登录时获取用户 shardKeySchema 和 shardKeyTableNumber 并将用户登录信息缓存于非关系型数据库，业界常用的如 redis、memcached。
- 业务操作请求：
    - 在请求数据时，就可以根据 shardKeySchema 动态切换数据源，根据 shardKeyTableNumber 决定查哪张表了（分表操作通过 MyBatis 插件实现）。

# 分表分库场景
- 场景一：
    - SaaS 平台，用户量成千上万，交易表 biz_trade 每天100万级增长，如果只用一个库的一张表，写入和读取压力会非常大，会成为瓶颈，所以需要分库分表。
    - 请求数据时，需要通过 [ShardView.java](https://github.com/uncleAndyChen/mybatis-plugin-shard/blob/master/common/common-shard/src/main/java/common/shard/ShardView.java) 传 shardKeySchema 和 shardKeyTableNumber 参数。
    - 业务场景之：平均分配
        - 每个数据库实例最多分配 10 万用户，超过 10 万的用户，再分配到新库。
        - 交易记录平均分到 10 张表，这就意味着用于分表的 shardKeyTableNumber，一个数字编号最多同时分配给一万个用户。
        - 用户请求数据时，将用户的 shardKeyTableNumber 除以 10，将余数作为分表后缀，比如用户的 shardKeyTableNumber=8888，那么，8888%10=8，则用户的交易表是 biz_trade_8。
        - 同理，如果要平均分配到 100 张表，那么就除以 100 再取余作为分表后缀，8888%100=88，则用户的交易表是 biz_trade_88。
    - 业务场景之：区别对待
        - 在平均分配的基础上，由于运营需要，现在有 vip 客户，要保证 vip 客户的用户体验，vip 客户的数据库读写速度要快，那怎么办呢？
        - 其实只要针对这部分用户再制定一套规则就可以了，因为 shardKeySchema 和 shardKeyTableNumber 都是可以指定的。
        - 如果用户由一般用户变为了 vip 用户，那么在重新指定 shardKeySchema 和 shardKeyTableNumber 之后，用户原来的数据做相应的迁移即可。
- 场景二：
    - 不同于场景一，在某一些业务场景，需要与其它业务系统做对接，在其它系统不能提供 api 的情况下，直接操作数据库无疑是最快也最直接的方式。
    - 这种情况，不同业务数据保存在不同的数据库，请求数据的时候，对于从哪个数据库请求数据是明确的，那么最直接的方式就是使用注解，或者配置 biz.service.{shardKeySchema} 对应的服务接口全路径名列表。
    - 在不需要分表的情况下，用注解和配置 biz.service.{shardKeySchema} 就够了，这种情况下请求数据时，不需要通过 ShardView（[ShardView.java](https://github.com/uncleAndyChen/mybatis-plugin-shard/blob/master/common/common-shard/src/main/java/common/shard/ShardView.java)）传 shardKeySchema 和 shardKeyTableNumber 参数。
    - 当然，也可以不用注解也不用配置 biz.service.{shardKeySchema}，还是通过 ShardView 传递参数也行，怎么灵活怎么来。
- 场景三：
    - 分表是确定的，不是动态分配的，那么 [ShardView.java](https://github.com/uncleAndyChen/mybatis-plugin-shard/blob/master/common/common-shard/src/main/java/common/shard/ShardView.java) 只传 shardKeyTable 即可。

# 分表插件配置
在 mybatis-config.xml 中配置。
```xml
<plugins>
    <!-- shardKeyTableNumber 通过 ShardView 传递 -->
    <plugin interceptor="common.shard.ShardTableInterceptor">
        <!-- 直接将 shardKeyTableNumber 作为分表后缀的表，以逗号隔开 -->
        <property name="shardTableByKeyDirectlyTables" value="edu_student"/>
        <!-- 针对下面参数 shardTableByKeyDivideByTables 的除数 -->
        <property name="divideByValue" value="10"/>
        <!-- shardKeyTableNumber 除以 divideByValue 得到的值作为分表后缀的表，以逗号隔开 -->
        <property name="shardTableByKeyDivideByTables" value="biz_trade"/>
        <!-- 测试期间用于打印分表分库的 sql 语句，默认为 false 即不打印 -->
        <property name="isPrintShardSqlInfo" value="true"/>
    </plugin>
</plugins>
```

# 运行
- `git clone https://github.com/uncleAndyChen/mybatis-plugin-shard.git`
- 因为依赖统一管理，添加了一个父模块：dependencies，只有一个 pom.xml 文件，需要先把这个 model 安装到本地仓库，否则会去 maven 配置的仓库下载。打开 cmd 窗口，在项目根目录下操作：
```
cd dependencies
mvn clean
mvn compile
mvn install
```
- 强烈建议：maven 远程仓库添加阿里云镜像。
    - 修改 maven 根目录下 `config/settings.xml`，在 `<mirrors>` 下添加：
```
<mirror> 
    <id>alimaven</id> 
    <name>aliyun maven</name> 
    <url>https://maven.aliyun.com/repository/jcenter</url> 
    <mirrorOf>central</mirrorOf> 
</mirror>
```
- 用你喜欢的 IDE 导入项目，如果你要我推荐一款 IDE，那么我强烈推荐 IntelliJ IDEA，官网：http://www.jetbrains.com/
- IDE 安装 Lombok 插件。
- MySQL 数据库，导入 `docs/schemas.sql`
- 修改 `biz/biz-config/src/main/resources/jdbc.properties` 中连接数据库的参数
- 启动
- 访问：`http://localhost:81/api`，可以测试以三种不同方式切换数据源来查询数据。具体细节请看源代码，以后会出详细的文档，敬请期待。
![](https://www.lovesofttech.com/img/java/mybatis-shard-api-test.png)

# 重新生成 mapper 和 entity
请参考 [生成 Mapper 操作](https://github.com/uncleAndyChen/mybatis-plugin-shard/tree/master/docs)

# 有关 {xxx}Mapper.xml 文件
我是直接把 MBG 生成的 {xxx}Mapper.xml 文件放到了 biz-service-dal 模块下与 {xxx}Mapper.java 平级的目录下了，包名为：`biz.mapper.xml.original` 和 `biz.mapper.xml.extend`

默认情况下，xml 文件不会被打包，所以，运行的时候会出现类似这样的错误：
```
Invalid bound statement (not found): biz.service.dal.mapper.original.EduStudentMapper.selectByExample
```

解决：需要在 pom.xml 里设置为需要将 xml 一起打包，如下：
```
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```
> directory 配置到 xml 的父目录 `src/main/java/biz/mapper/xml` 不会生效，配置成 `src/main/java` 就好。

# 技术清单
- JDK 1.8，理论上支持 1.8 以上的版本，如需升级，比如要改为 JDK 11，将文件 `./dependencies/pom.xml` 中 `<java.version>1.8</java.version>` 改为 `<java.version>11</java.version>`
- MySQL 5.6.46、MySQL 5.7，用这两个版本作的测试，理论上支持 5.6 及以上版本。
- maven 依赖库
    - maven 依赖版本在 `./dependencies/pom.xml` 维护，如果要升级某一框架的版本，只需要修改这个文件就行，模块 dependencies 被作为其它模块的 parent，目的就是统一管理版本，同样的依赖库只定义一次版本号。
    - 以下依赖为当前（2020-01-06）最新版本
        - Spring Boot 2.2.2.RELEASE
        - Spring Framework 5.2.2.RELEASE （common-shard 模块直接依赖了 spring framework 下的 spring-aspects）
        - MyBatis 3.5.3
        - druid 1.1.21
        - lombok 1.18.10
        - jackson 2.10.1

# 支持
如果有疑问，请提 [Issue](https://github.com/uncleAndyChen/mybatis-plugin-shard/issues)，可能不会立即回复，尤其在上班期间，我会尽量抽业余时间回复的。

# 如果帮到了你
请 Star 一下以表肯定，同时让我更加有动力继续完善和优化。