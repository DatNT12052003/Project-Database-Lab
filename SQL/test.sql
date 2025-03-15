create database databaseproject;

use databaseproject;

create table users(
	id int auto_increment,
    account varchar(15) not null unique,
    password varchar(20) not null,
    email varchar(30) not null,
    phone varchar(10) not null,
    role enum('admin', 'teacher', 'student') not null,
    status int,
    primary key (id)
);

drop table users;

select * from users;

alter table users 
modify column status tinyint(1) check (status in (0,1)) not null;

desc users;

desc teachers;

alter table teachers
change column name FullName VARCHAR(50) NOT NULL; 

alter table teachers
change column name TeacherID VARCHAR(6) NOT NULL; 

desc students;

INSERT INTO users (account, password, email, phone, role, status) VALUES
('user001', 'pass12345', 'user001@example.com', '0901234567', 'admin', 1),
('user002', 'pass23456', 'user002@example.com', '0912345678', 'teacher', 1),
('user003', 'pass34567', 'user003@example.com', '0923456789', 'student', 1),
('user004', 'pass45678', 'user004@example.com', '0934567890', 'teacher', 1),
('user005', 'pass56789', 'user005@example.com', '0945678901', 'student', 1),
('user006', 'pass67890', 'user006@example.com', '0956789012', 'admin', 1),
('user007', 'pass78901', 'user007@example.com', '0967890123', 'student', 1),
('user008', 'pass89012', 'user008@example.com', '0978901234', 'teacher', 1),
('user009', 'pass90123', 'user009@example.com', '0989012345', 'student', 1),
('user010', 'pass01234', 'user010@example.com', '0990123456', 'admin', 1);

INSERT INTO users (userid, account, password, role, status, createddate) VALUES
('ADMIN00001', 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'admin', 'active', '2025-03-10 12:05:03');

INSERT INTO users (account, password, email, phone, role, status) VALUES
('ptd', 'a', 'ptd@example.com', '0123456789', 'teacher', 0);

select * from users;

select * from students;

update users
set role = 'admin'
where userid = 'S000000001';

DELETE FROM students
WHERE studentid like 'S%';

DELETE FROM users
WHERE userid like 'S%';

DELETE FROM students
WHERE studentid = 'S000000002';

ALTER TABLE users ADD COLUMN CreatedDate datetime not null;

desc schedules;

select * from users
inner join students on users.UserID = students.StudentID and users.UserID = 'S000000001';
