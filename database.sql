create database project3;
use project3;
create table users(
  username varchar(100) PRIMARY KEY,
  name varchar(100),
  email varchar(100),
  password varchar(100),
  mobile varchar(100)
);create table groups(
  groupId varchar(100) PRIMARY KEY,
  groupName varchar(100),
  groupOwner varchar(100),
  groupDescription varchar(500),
  groupDate varchar(100)
);
