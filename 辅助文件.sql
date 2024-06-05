CREATE database learning;

use learning;

create table user(
 		id int unsigned primary key auto_increment comment 'ID',
 		username varchar(20) not null unique comment '用户名',
 		password varchar(32) comment '密码',
 		nickname varchar(10) default '' comment '昵称',
 		email varchar(128) default '' comment '邮箱',
 		user_pic varchar(128) default '' comment '头像',
  		create_time datetime not null comment '创建时间',
  		update_time datetime not null comment '更新时间'
 ) comment '用户表';