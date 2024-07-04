create database if not exists springBoot;

use springBoot;

create table student
(
    id   int auto_increment primary key,
    name varchar(255) not null,
    age  int          not null
);

insert into student (name, age)
values ('张三', '18'),
       ('李四', '18'),
       ('王五', '18');