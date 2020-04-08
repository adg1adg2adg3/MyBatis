# 本章节描述了如何构建一个最基本的MyBatis项目
数据库：
```mysql
CREATE DATABASE mybatis;
USE mybatis;
CREATE TABLE country(
	id INT NOT NULL AUTO_INCREMENT,
	countryname VARCHAR(32) NULL,
	countrycode VARCHAR(32) NULL,
	PRIMARY KEY (id)
);

INSERT INTO country(countryname,countrycode) VALUES
	('中国','CN'),
	('美国','USA'),
	('俄罗斯','RU'),
	('英国','GB'),
	('法国','FR');

CREATE TABLE sys_user
(
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
	user_name VARCHAR(50) COMMENT '用户名',
	user_password VARCHAR(50) COMMENT '密码',
	user_email VARCHAR(50) COMMENT '邮箱',
	user_info TEXT COMMENT '简介',
	head_img BLOB COMMENT '头像',
	create_time DATETIME COMMENT '创建时间',
	PRIMARY KEY (id)
);
ALTER TABLE sys_user COMMENT '用户表';

CREATE TABLE sys_role(
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT '角色ID',
	role_name VARCHAR(50) COMMENT '角色名',
	enabled INT COMMENT '有效标志',
	create_by BIGINT COMMENT '创建人',
	create_time DATETIME COMMENT '创建时间',
	PRIMARY KEY (id)
);
ALTER TABLE sys_role COMMENT '角色表';

CREATE TABLE sys_privilege(
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT '权限ID',
	privilege_name VARCHAR(50) COMMENT '权限名称',
	privilege_url VARCHAR(200) COMMENT '权限URL',
	PRIMARY KEY (id)
);
ALTER TABLE sys_privilege COMMENT '权限表';

CREATE TABLE sys_user_role(
	user_id BIGINT COMMENT '角色ID',
	role_id BIGINT COMMENT '角色ID'
);
ALTER TABLE sys_user_role COMMENT '用户角色关联表';

CREATE TABLE sys_role_privilege(
	role_id BIGINT COMMENT '角色ID',
	privilege_id BIGINT COMMENT '权限ID'
);
ALTER TABLE sys_role_privilege COMMENT '角色权限关联表';

INSERT INTO sys_user VALUES ('1','admin','123456','admin@mybatis.tk','管理员',NULL,'2016-04-01 17:00:58');
INSERT INTO sys_user VALUES ('1001','test','123456','test@mybatis.tk','测试用户',NULL,'2016-04-01 17:00:58');

INSERT INTO sys_role VALUES ('1','管理员','1','1','2016-04-01 17:02:14'),
			('2','普通用户','1','1','2016-04-01 17:02:34');
			
INSERT INTO sys_user_role VALUES ('1','1'),
				('1','2'),
				('1001','2');

INSERT INTO sys_privilege VALUES ('1','用户管理','/users'),
				 ('2','角色管理','/roles'),
				 ('3','系统日志','/logs'),
				 ('4','人员维护','/persons'),
				 ('5','单位维护','/companies');

INSERT INTO sys_role_privilege VALUES ('1','1'),
				      ('1','3'),
				      ('1','2'),
				      ('2','4'),
				      ('2','5');

```
