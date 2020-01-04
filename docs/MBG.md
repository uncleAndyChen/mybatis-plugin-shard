# 在项目根目录下执行
## 删除生成的所有文件
del/f/s/q biz\biz-service-dal\src\main\java\biz\service\dal\mapper\original\*.*
del/f/s/q biz\biz-service-dal\src\main\java\biz\service\dal\mapper\xml\original\*.*
del/f/s/q biz\biz-model\src\main\java\biz\model\entity\*.*

## 按不同配置文件，生成 entity 和 mapper
java -Dfile.encoding=UTF-8 -cp mybatis-generator-1.3.7.jar;mybatis-generator-enhance-mysql-v5.7.x.jar org.mybatis.generator.api.ShellRunner -configfile generator-config-system.xml -overwrite
java -Dfile.encoding=UTF-8 -cp mybatis-generator-1.3.7.jar;mybatis-generator-enhance-mysql-v5.7.x.jar org.mybatis.generator.api.ShellRunner -configfile generator-config-student.xml -overwrite
java -Dfile.encoding=UTF-8 -cp mybatis-generator-1.3.7.jar;mybatis-generator-enhance-mysql-v5.7.x.jar org.mybatis.generator.api.ShellRunner -configfile generator-config-finance.xml -overwrite

# 注意
- 如果有新的 tinyint 字段，需要重新生成 table 属性。
    - 生成 table 属性，请参考：https://github.com/uncleAndyChen/mybatis-generator
- *Mapper.xml 文件，每次重新生成都需要先删除，否则部分内容会重复生成，导致错误，版本 1.3.5 以及现在使用的 1.3.7 版均有此问题。
- 执行之前请确保文件路径是正确的。

# 以下语句生成 mapper，分别针对 MySQL 的两个版本，执行时请注意 MySQL 的 driverClass 值。
## 针对 MySQL v5.7.x（理论上，v5.7 以前的版本也是支持的，没测试），执行前，请确保本文件中 driverClass 的值为：com.mysql.jdbc.Driver
java -Dfile.encoding=UTF-8 -cp mybatis-generator-1.3.7.jar;mybatis-generator-enhance-mysql-v5.7.x.jar org.mybatis.generator.api.ShellRunner -configfile generator-config-finance.xml -overwrite

## 针对 MySQL v8.x，执行前，请确保本文件中 driverClass 的值为：com.mysql.cj.jdbc.Driver
java -Dfile.encoding=UTF-8 -cp mybatis-generator-1.3.7.jar;mybatis-generator-enhance-mysql-v8.x.jar org.mybatis.generator.api.ShellRunner -configfile generator-config-finance.xml -overwrite
