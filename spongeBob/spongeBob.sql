drop database springBoot;

create database springBoot;

use springBoot;

create table user
(
    uid           int auto_increment primary key,
    name          varchar(255) not null,
    password      varchar(255) not null,
    birthday      date                  default null,
    role          int          not null default '2',
    email         varchar(255) not null,
    address       varchar(255)          default null,
    status        int          not null default '1',
    login_time    timestamp    null     default current_timestamp on update current_timestamp,
    register_time timestamp    null     default current_timestamp
);

insert into user
values (1, '张三', 'Ilby1314', null, 0, '3460380090@qq.com', null, 1, '2024-04-17 03:25:45', '2024-04-17 03:25:45'),
       (2, '李四', 'Ilby1314', null, 1, '3460380091@qq.com', null, 1, '2024-04-17 03:25:45', '2024-04-17 03:25:45'),
       (3, '王五', 'Ilby1314', null, 2, '3460380092@qq.com', null, 1, '2024-04-17 03:25:45', '2024-04-17 03:25:45');

select *
from user;