--liquibase formatted sql

--changeset huangzd:20201119-0
--comment '表project_info没有数据的时候插入测试数据'
--preconditions onFail:MARK_RAN onError:HALT validCheckSum:ANY
--precondition-sql-check expectedResult:0  select count(1) from project_info
insert into project_info (project_id,project_name) values("1","admin");
