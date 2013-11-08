CREATE TABLE user (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(20) NOT NULL UNIQUE COMMENT '注册用户名',
	password varchar(32) NOT NULL COMMENT '注册用户密码',
	email varchar(32) NOT NULL UNIQUE default '' COMMENT '用户的email地址',
	PRIMARY KEY (id)
);

CREATE TABLE favorite (
	id int(11) NOT NULL AUTO_INCREMENT,
	userId int(11) NOT NULL COMMENT '所属的用户id',
	title varchar(128) NOT NULL DEFAULT '' COMMENT '被收藏页面的标题',
	description varchar(256) NOT NULL DEFAULT '' COMMENT '被收藏页面的描述',
	keyword varchar(256) NOT NULL DEFAULT '' COMMENT '被收藏页面的关键字',
	url varchar(128) NOT NULL COMMENT '被收藏页面的URL',
	PRIMARY KEY(id)
);