-- View for Admin
CREATE VIEW admin_view_students AS
SELECT 
    u.UserID, u.UserName, u.CreatedDate, s.FullName, s.Address, s.Phone, s.Email, s.Status AS Status
FROM users u
JOIN students s ON u.UserID = s.StudentID
WHERE u.Role = 'Student';

CREATE VIEW admin_view_teachers AS
SELECT 
    u.UserID, u.UserName, u.CreatedDate, t.FullName, t.Address, t.Phone, t.Email, t.Expertise, t.Salary, t.Status AS Status
FROM users u
JOIN teachers t ON u.UserID = t.TeacherID
WHERE u.Role = 'Teacher';

CREATE VIEW admin_view_courses AS
SELECT 
    c.CourseID, c.TeacherID, t.FullName AS TeacherName, s.Name AS SubjectName,
    s.Credit, s.Tuition, r.Address AS RoomAddress, c.RegisStartDate, c.CourseStartDate, c.Status
FROM 
    courses c
JOIN rooms r ON c.RoomID = r.RoomID
JOIN teachers t ON c.TeacherID = t.TeacherID
JOIN subjects s ON c.SubjectID = s.SubjectID;

CREATE VIEW admin_view_payments AS
SELECT 
    p.PaymentID, s.StudentID, s.FullName AS StudentName,  sub.Name AS SubjectName, p.Amount, p.Method, p.PaymentDate, p.Status
FROM 
    payments p
JOIN enrollments e ON p.EnrollmentID = e.EnrollmentID
JOIN students s ON e.StudentID = s.StudentID
JOIN courses c ON e.CourseID = c.CourseID
JOIN subjects sub ON c.SubjectID = sub.SubjectID;

-- View for Teacher
CREATE VIEW teacher_view_courses AS
SELECT 
    c.CourseID, c.TeacherID, t.FullName AS TeacherName, s.Name AS SubjectName,
    s.Credit, r.Address AS RoomAddress, c.CourseStartDate, c.Status
FROM 
    courses c
JOIN rooms r ON c.RoomID = r.RoomID
JOIN teachers t ON c.TeacherID = t.TeacherID
JOIN subjects s ON c.SubjectID = s.SubjectID;

CREATE VIEW teacher_view_students_in_courses AS
SELECT 
    c.CourseID, c.TeacherID, st.StudentID, st.FullName, st.Email, st.Phone,
    e.Status AS EnrollmentStatus
FROM courses c
JOIN enrollments e ON c.CourseID = e.CourseID
JOIN students st ON e.StudentID = st.StudentID;

CREATE VIEW teacher_view_students_in_courses AS
SELECT 
    c.CourseID, c.TeacherID, st.StudentID, st.FullName, st.Email, st.Phone,e.Status AS EnrollmentStatus,
	ROUND(
		IFNULL((
			SELECT AVG(s.Score)
			FROM scores s
			WHERE s.EnrollmentID = e.EnrollmentID
		), 0), 2) AS AverageScore,
    IFNULL((
        SELECT COUNT(*)
        FROM attendances a
        WHERE a.EnrollmentID = e.EnrollmentID AND a.Status = 'Absent'
    ), 0) AS Absences
FROM courses c
JOIN enrollments e ON c.CourseID = e.CourseID
JOIN students st ON e.StudentID = st.StudentID;

CREATE OR REPLACE VIEW teacher_view_schedule AS
SELECT 
    c.CourseID, c.TeacherID, sj.Name AS SubjectName, r.Address,
    sch.Day, sch.TimeStart, sch.TimeEnd, c.Status AS CourseStatus
FROM courses c
JOIN subjects sj ON c.SubjectID = sj.SubjectID
JOIN rooms r ON c.RoomID = r.RoomID
JOIN course_schedule cs ON c.CourseID = cs.CourseID
JOIN schedules sch ON cs.ScheduleID = sch.ScheduleID;

-- View for Student
CREATE OR REPLACE VIEW student_view_courses AS
SELECT 
    e.StudentID, c.CourseID, sj.Name AS SubjectName, c.CourseStartDate, t.FullName AS TeacherName, 
    r.Address AS RoomAddress, c.Status AS CourseStatus, e.Status AS EnrollmentStatus
FROM enrollments e
JOIN courses c ON e.CourseID = c.CourseID
JOIN subjects sj ON c.SubjectID = sj.SubjectID
JOIN teachers t ON c.TeacherID = t.TeacherID
JOIN rooms r ON c.RoomID = r.RoomID;

CREATE OR REPLACE VIEW student_view_schedule AS
SELECT 
    e.StudentID, c.CourseID, sj.Name AS SubjectName, r.Address,
    s.Day, s.TimeStart, s.TimeEnd
FROM enrollments e
JOIN courses c ON e.CourseID = c.CourseID
JOIN subjects sj ON c.SubjectID = sj.SubjectID
JOIN rooms r ON c.RoomID = r.RoomID
JOIN course_schedule cs ON c.CourseID = cs.CourseID
JOIN schedules s ON cs.ScheduleID = s.ScheduleID;

CREATE OR REPLACE VIEW student_view_scores_attendance AS
SELECT 
    e.StudentID,
    c.CourseID,
    sj.Name AS CourseName,
    c.Status AS CourseStatus,
    ROUND(IFNULL((
        SELECT AVG(sc.Score)
        FROM scores sc
        WHERE sc.EnrollmentID = e.EnrollmentID
    ), 0), 2) AS AverageScore,
    IFNULL((
        SELECT COUNT(*)
        FROM attendances a
        WHERE a.EnrollmentID = e.EnrollmentID AND a.Status = 'Absent'
    ), 0) AS Absences
FROM enrollments e
JOIN courses c ON e.CourseID = c.CourseID
JOIN subjects sj ON c.SubjectID = sj.SubjectID;
