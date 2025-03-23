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

desc subjects;

alter table teachers
change column name FullName VARCHAR(50) NOT NULL; 

alter table teachers
change column name TeacherID VARCHAR(6) NOT NULL; 

desc students;

select * from students;

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

INSERT INTO subjects (subjectid, name, mass, tuition) VALUES
('ENG01', 'English IELTS 5.0 - 6.5', 50, 12500000);

INSERT INTO rooms (roomid, address, type, status) VALUES
('OFF00001', 'A2 - 301', 'offline', 'empty');

select * from rooms;

select * from schedules;

desc rooms;

INSERT INTO schedules (scheduleid, day, timestart, timeend) VALUES
('MON03', 'Monday', '18', '19');

delete from rooms
where roomid = 'OFF00001';

alter table rooms
modify column type enum('Offline', 'Online') not null;
alter table students
modify column gender enum('Male', 'Female') not null;

alter table rooms
modify column status enum('Empty', 'Using') not null;

alter table schedules
modify column Day varchar(10) not null;

DROP TABLE rooms CASCADE;

desc schedules;

select * from users;

select * from students;

select * from teachers;

select * from subjects;

desc rooms;

insert into teachers (teacherid, fullname, dateofbirth, gender, phone, email, expertise, level, salary) values
	('T000000001', 'Nguyen Van Giap', '1997-02-20', 'male', '0397123456', 'giaplinux@gmail.com', 'English IELTS', '8.0', '15000000');

update users
set role = 'admin'
where userid = 'S000000001';

DELETE FROM students
WHERE studentid like 'S%';

DELETE FROM users
WHERE userid like 'S%';

DELETE FROM students
WHERE studentid = 'S000000002';

update users
set userid = 'S000000002'
where userid = 'S000000003';

ALTER TABLE users ADD COLUMN CreatedDate datetime not null;

ALTER TABLE rooms DROP COLUMN status;


desc subjects;

select * from users
inner join teachers on (users.UserID = teachers.teacherID) and (users.UserID = 'T000000001');

desc teachers;

drop table backups;

desc courses;

drop database projectdatabase;

select * from courses;

update courses set courseid = 'C00001' where courseid = 'CS0001';

desc course_schedule;

insert into course_schedule ()


