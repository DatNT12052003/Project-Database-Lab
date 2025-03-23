INSERT INTO users (userid, account, password, role) values
	('ADM0000001', 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Admin');
SELECT * FROM users;
desc subjects;

Select * from subjects
left join students on users.UserID = students.StudentID
left join teachers on users.UserID = teachers.TeacherID;

desc rooms;
select * from schedules;	
delete from rooms
where roomid like '%2';
SELECT * FROM rooms WHERE roomid LIKE '%2';
SET SQL_SAFE_UPDATES = 0;
DELETE FROM rooms WHERE roomid LIKE '%2';
SET SQL_SAFE_UPDATES = 1;


UPDATE rooms SET roomid = 'OFF0001', address = 'Teams - 1203oh', type = 'Offline', maxStudents = 30 WHERE roomid = ?;

desc rooms;

select * from teachers;

update teachers
set status = 'Teaching'
where TeacherID = 'T000000002';

update teachers
set status = 'Deleted'
where TeacherID = 'T000000002';

delete from teachers
where teacherid = 'T000000003';

select * from users;

INSERT INTO users (userid, account, password, role) values
	('T000000002', 'acb', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Teacher');

update users
set userid = 'T000000003'
where userid = 'T000000004';

delete from teachers
where TeacherID = 'T000000005';

select u.account from teachers as t
inner join users as u
on t.TeacherID = u.UserID and t.TeacherID = 'T000000005';

select * from users as u
inner join teachers as t
on u.UserID = t.TeacherID and u.UserID = 'T000000005';

update teachers
set status = 'Teaching'
where TeacherID = 'T000000002';

delete from users 
where userid = 'T000000005';

update teachers set status = 'Teaching' where TeacherID = 'T000000001';

select * from users;
select * from teachers;
select * from students;

update users set status = 'Active' where userid = 'T000000001';

delete from students
where StudentID = 'S000000001';

desc courses;

select * from courses;

insert into courses(courseid, roomid, teacherid, subjectid, currentstudents, regisstartdate, coursestartdate, status) values 
	('C00001', 'OFF00001', 'T000000001', 'ENG001', 10, '2025-03-25', '2025-04-25', null);
    
    
select * from schedules   
where scheduleid not in 
(select s.scheduleid from schedules as s
inner join course_schedule as cs on s.scheduleid = cs.scheduleid
inner join courses as c on cs.courseid = c.courseid
inner join rooms as r on c.roomid = r.roomid
where r.roomid = 'OFF00001'
union 
SELECT s.scheduleid FROM schedules AS s
INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid
INNER JOIN courses AS c ON cs.courseid = c.courseid
INNER JOIN teachers AS t ON c.teacherid = t.teacherid
WHERE t.teacherid = 'T000000001');

    SELECT * FROM schedules s  
    WHERE NOT EXISTS (
        SELECT 1 FROM course_schedule cs
        INNER JOIN courses c ON cs.courseid = c.courseid
        LEFT JOIN rooms r ON c.roomid = r.roomid
        LEFT JOIN teachers t ON c.teacherid = t.teacherid
        WHERE cs.scheduleid = s.scheduleid
        AND (r.roomid = 'OFF00001' OR t.teacherid = 'T000000001')
    );
    
    
    SELECT * FROM schedules where status = 'Using';
    
	SELECT s.* FROM schedules AS s
	INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid
	INNER JOIN courses AS c ON cs.courseid = c.courseid
	INNER JOIN teachers AS t ON c.teacherid = t.teacherid
	WHERE t.teacherid = 'T000000001';
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
select * from schedules    
where scheduleid not in 
(SELECT s.scheduleid FROM schedules AS s
INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid
INNER JOIN courses AS c ON cs.courseid = c.courseid
INNER JOIN teachers AS t ON c.teacherid = t.teacherid
WHERE t.teacherid = 'T000000002');

SELECT * FROM courses WHERE status NOT IN ('Canceled', 'Completed') OR status IS NULL;

alter table courses
modify column Status enum('Opening','Canceled','Ongoing','Completed');


select * from course_schedule;

delete from courses where courseid = 'C00002';

delete from course_schedule where coursescheduleid LIKE 'CS%';

desc course_schedule;

desc courses;

desc rooms;

    			SELECT * FROM schedules
    			WHERE scheduleid NOT IN
    		    (SELECT s.scheduleid FROM schedules AS s
    		    INNER JOIN course_schedule AS cs ON s.scheduleid = cs.scheduleid
    		    INNER JOIN courses AS c ON cs.courseid = c.courseid
    		    INNER JOIN rooms AS r ON c.roomid = r.roomid
    		    WHERE r.roomid = 'OFF00001' AND c.status NOT IN ('Canceled', 'Completed') OR c.status IS NULL);
