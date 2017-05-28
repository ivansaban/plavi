create schema if not exists plavi default character set utf8;
use plavi;

create table if not exists roles(
  id int primary key auto_increment,
  name varchar(45) unique not null
)
default character set = utf8;

create table if not exists users(
  id int primary key auto_increment,
  username varchar(45) unique not null,
  password varchar(45) not null,
  role_id int,
  foreign key(role_id) references roles(id)
)
default character set = utf8;