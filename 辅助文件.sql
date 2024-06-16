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

create table category(
	id int unsigned primary key auto_increment comment "分类id",
	category_name varchar(32) default '' comment '分类名称',
	category_alias varchar(32) default '' comment '分类别名',
	create_user int unsigned comment '创建人ID',
	create_time datetime not null comment '创建时间',
	update_time datetime not null comment '更新时间'
)comment '文章分类表';

create  table article(
    id int unsigned primary key auto_increment COMMENT '主键ID',
    title varchar(30) COMMENT '文章标题',
    content varchar(10000) COMMENT '文章内容',
    cover_img varchar(128) COMMENT '封面图像',
    state varchar(3) COMMENT '发布状态,发布/草稿',
    category_id int unsigned COMMENT '文章分类ID',
    create_user int unsigned COMMENT '创建人ID',
    create_time  datetime COMMENT '创建时间',
    update_time datetime COMMENT '更新时间'
) COMMENT '文章内容';