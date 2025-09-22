# 在项目根目录下执行
## 删除生成的所有文件
```
del/f/s/q biz\biz-service-dal\src\main\java\biz\mapper\original\*.*
del/f/s/q biz\biz-service-dal\src\main\java\biz\mapper\xml\original\*.*
del/f/s/q biz\biz-model\src\main\java\biz\model\entity\*.*
```

## 按不同配置文件，生成 entity 和 mapper
- 以下为 MySQL 5.7 版本，如果 MySQL 为 8.x 版本，参考后面的提示
- mybatis-generator-enhance-mysql-v5.7.x.jar，已包含 mybatis-generator 1.3.7 版本和 MySQL 5.7 驱动
- 查看/获取包含 mybatis-generator 1.4.2 版本和 MySQL 8 驱动的版本，请见：https://gitee.com/uncleAndyChen/mybatis-generator-enhance
  - 在根目录下，有打包好所有依赖的版本
```
java -Dfile.encoding=UTF-8 -cp mbg/mybatis-generator-enhance-mysql-v5.7.x.jar org.mybatis.generator.api.ShellRunner -configfile mbg/generator-config-system.xml -overwrite
java -Dfile.encoding=UTF-8 -cp mbg/mybatis-generator-enhance-mysql-v5.7.x.jar org.mybatis.generator.api.ShellRunner -configfile mbg/generator-config-student.xml -overwrite
java -Dfile.encoding=UTF-8 -cp mbg/mybatis-generator-enhance-mysql-v5.7.x.jar org.mybatis.generator.api.ShellRunner -configfile mbg/generator-config-finance.xml -overwrite
java -Dfile.encoding=UTF-8 -cp mbg/mybatis-generator-enhance-mysql-v5.7.x.jar org.mybatis.generator.api.ShellRunner -configfile mbg/generator-config-biz.xml -overwrite
```

# 生成 table 属性、生成 mapper 与 entity
本项目 `generator-config-xxx.xml` 中的 table 属性，以及使用的 `mybatis-generator-enhance-mysql-v5.7.x.jar` 和 `mybatis-generator-enhance-mysql-v8.x.jar`，均用到了：https://gitee.com/uncleAndyChen/mybatis-generator-enhance

# 注意
- 如果有新的 tinyint 字段，需要重新生成 table 属性。
- `*Mapper.xml` 文件，每次重新生成都需要先删除，否则部分内容会重复生成，导致错误，版本 1.3.5 以及现在使用的 1.3.7 版均有此问题。
- 执行之前请确保文件路径是正确的。

# 针对 MySQL 当前两个流行的版本
以下语句生成 mapper，分别针对 MySQL 的两个版本，执行时请注意 MySQL 的 driverClass 值。
## 针对 MySQL v5.7.x
理论上，v5.7 以前的版本也是支持的，没测试，执行前，请确保本文件 `generator-config-xxx.xml` 中 driverClass 的值为：com.mysql.jdbc.Driver
```
java -Dfile.encoding=UTF-8 -cp mbg/mybatis-generator-enhance-mysql-v5.7.x.jar org.mybatis.generator.api.ShellRunner -configfile mbg/generator-config-finance.xml -overwrite
```

## 针对 MySQL v8.x
- 执行前，请确保本文件 `generator-config-xxx.xml` 中 driverClass 的值为：com.mysql.cj.jdbc.Driver
- mybatis-generator-enhance-mysql-v8.x.jar，已包含 mybatis-generator 1.3.7 版本和 MySQL 8 驱动
- 查看/获取包含 mybatis-generator 1.4.2 版本和 MySQL 8 驱动的版本，请见：https://gitee.com/uncleAndyChen/mybatis-generator-enhance
    - 在根目录下，有打包好所有依赖的版本
```
java -Dfile.encoding=UTF-8 -cp mbg/mybatis-generator-enhance-mysql-v8.x.jar org.mybatis.generator.api.ShellRunner -configfile mbg/generator-config-finance.xml -overwrite
```
