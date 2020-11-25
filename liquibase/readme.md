
## 使用Liquibase目的
    便于版本控制和项目部署升级!
    实际的项目很多都是分期开发部署的，随着项目的发展，数据库表会经过很多修改，变得会错综复杂。
    如果我们都是通过手动创建表或者修改表字段的情况下，会导致很多问题，如：
        - v1.0.0版本部署的代码需要升级到v1.0.4，中间几个版本的数据库修改都没有记录，可能会导致结构等各种问题
    开发人员规范的使用Liquibase操作数据的情况下，修改记录及备注原因都可以很清晰的找到，

## Liquibase容易出现的问题
- ChangeSet文件同时多人在修改，自己的ChangeSet被改掉，甚至被删除掉。
- 开发人员将ChangeSet添加到已经执行过的文件中，导致执行顺序出问题。
- 开发人员擅自添加对业务数据的修改，其它环境无法执行并报错。
- ChangeSet中SQL包含schema名称，导致其它环境schema名称变化时，ChangeSet报错。
- 开发人员不小心改动了已经执行过的ChangeSet，在启动时会报错。
 
## Liquibase基本规范
- ChangeSet id使用[日期]-[序号]，xml用-,sql用--，如xml的 20201009-001 ，sql文件的20201009--001
- ChangeSet必须填写author
- Liquibase禁止对业务数据进行sql操作
- 使用<sql>时，禁止包含schema名称
- Liquibase禁止使用存储过程
- 所有表，列要加remarks进行注释
- 已经执行过的ChangeSet严禁修改。
- 不要随便升级项目liquibase版本，特别是大版本升级。不同版本ChangeSet MD5SUM的算法不一样。

### springboot + Liquibase 碰到的问题 
   前提版本（springboot 2.2.0，liquibase 3.8.0）
- .sql 文件一定要在最前面添加--liquibase formatted sql 否则会有异常，执行不了
-  .xml的标签<databaseChangeLog>要完善点，参考[changelog.xml](./src/main/resources/liquibase/app/changelog.xml)
- relativeToChangelogFile如果为true，则表示file属性表示的文件路径是相对于根changelog  而不是CLASSPATH的，默认为false

####参考文章
[liquibase.sql官方说明](https://docs.liquibase.com/concepts/basic/sql-format.html)

[Liquibase注意事项](https://www.cnblogs.com/zhaoyanhaoBlog/p/11327039.html)

