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

insert into `chapter` (id, course_id, name) values ('00000000', '00000000', 'Test Chapter 00');
insert into `chapter` (id, course_id, name) values ('00000001', '00000000', 'Test Chapter 01');
insert into `chapter` (id, course_id, name) values ('00000002', '00000000', 'Test Chapter 02');
insert into `chapter` (id, course_id, name) values ('00000003', '00000000', 'Test Chapter 03');
insert into `chapter` (id, course_id, name) values ('00000004', '00000000', 'Test Chapter 04');
insert into `chapter` (id, course_id, name) values ('00000005', '00000000', 'Test Chapter 05');
insert into `chapter` (id, course_id, name) values ('00000006', '00000000', 'Test Chapter 06');
insert into `chapter` (id, course_id, name) values ('00000007', '00000000', 'Test Chapter 07');
insert into `chapter` (id, course_id, name) values ('00000008', '00000000', 'Test Chapter 08');
insert into `chapter` (id, course_id, name) values ('00000009', '00000000', 'Test Chapter 09');
insert into `chapter` (id, course_id, name) values ('00000010', '00000000', 'Test Chapter 10');
insert into `chapter` (id, course_id, name) values ('00000011', '00000000', 'Test Chapter 11');
insert into `chapter` (id, course_id, name) values ('00000012', '00000000', 'Test Chapter 12');
insert into `chapter` (id, course_id, name) values ('00000013', '00000000', 'Test Chapter 13');
insert into `chapter` (id, course_id, name) values ('00000014', '00000000', 'Test Chapter 14');
insert into `chapter` (id, course_id, name) values ('00000015', '00000000', 'Test Chapter 15');
insert into `chapter` (id, course_id, name) values ('00000016', '00000000', 'Test Chapter 16');
insert into `chapter` (id, course_id, name) values ('00000017', '00000000', 'Test Chapter 17');
insert into `chapter` (id, course_id, name) values ('00000018', '00000000', 'Test Chapter 18');
insert into `chapter` (id, course_id, name) values ('00000019', '00000000', 'Test Chapter 19');

drop table if exists `section`;
create table `section` (
    `id` char(8) not null default '',
    `title` varchar(50) not null,
    `course_id` char(8),
    `chapter_id` char(8),
    `video` varchar(200),
    `time` int comment 'Video length in seconds',
    `charge` char(1) comment 'Charge or not: C means charge and F means free',
    `sort` int,
    `created_at` datetime(3) comment 'Create time',
    `updated_at` datetime(3) comment 'Update time',
    primary key (`id`)
) engine = InnoDB default charset = utf8mb4;

insert into `section` (id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at)
VALUES ('00000001', 'Test Section 01', '00000001', '00000000', '', 500, 'F', 1, now(), now());