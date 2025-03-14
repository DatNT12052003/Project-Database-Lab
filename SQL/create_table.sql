create table users (
	userid varchar(10),
    account varchar(20),
    password varchar(255),
    role enum('admin', 'teacher', 'student'),
    status enum('active', 'locked'),
    primary key (userid)
);

drop table users;

create table teachers(
	teacherid varchar(10),
    name varchar(50),
    dateofbirth date,
    gender enum('male', 'female'),
    phone varchar(15),
    email varchar(50),
    expertise varchar(20),
    level varchar(10),
    salary integer,
    primary key (teacherid)
);

create table students(
	studentid varchar(10),
    name varchar(50),
    dateofbirth date,
    gender enum('male', 'female'),
    phone varchar(15),
    email varchar(50),
    primary key (studentid)
);