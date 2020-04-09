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

```
