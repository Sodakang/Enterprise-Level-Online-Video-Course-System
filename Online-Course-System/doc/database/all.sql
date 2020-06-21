# drop table if exists test;
# create table test (
#  id char(8) not null default '' comment 'id',
#  name varchar(50) comment 'name',
#  primary key (id)
# ) engine=innodb default charset=utf8mb4 comment='test';
#
# insert into test (id, name) values (1, 'test');

drop table if exists `chapter`;
create table `chapter` (
    `id` char(8) not null,
    `course_id` char(8),
    `name` varchar(50),
    primary key (`id`)
) engine = innodb default charset = utf8mb4;

insert into `chapter` (id, course_id, name) values ('00000000', '00000000', 'Test Chapter 1');
insert into `chapter` (id, course_id, name) values ('00000001', '00000000', 'Test Chapter 2');