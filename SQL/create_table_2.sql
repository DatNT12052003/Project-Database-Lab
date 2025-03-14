CREATE TABLE students (
    StudentID VARCHAR(10) PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('male', 'female') NOT NULL,
    Phone VARCHAR(15) UNIQUE,
    Email VARCHAR(50) UNIQUE
);

CREATE TABLE teachers (
    TeacherID VARCHAR(10) PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('male', 'female') NOT NULL,
    Phone VARCHAR(15) UNIQUE,
    Email VARCHAR(50) UNIQUE,
    Expertise VARCHAR(20),
    Level VARCHAR(10),
    Salary INTEGER CHECK (Salary >= 0)
);

CREATE TABLE users (
    UserID VARCHAR(10) PRIMARY KEY,
    Account VARCHAR(20) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role ENUM('admin', 'teacher', 'student') NOT NULL,
    Status ENUM('active', 'locked') NOT NULL
);

CREATE TABLE rooms (
    RoomID VARCHAR(8) PRIMARY KEY,
    Address VARCHAR(255) NOT NULL,
    Status ENUM('empty', 'using') NOT NULL
);

CREATE TABLE subjects (
    SubjectID VARCHAR(5) PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    Mass INTEGER NOT NULL,
    Tuition INTEGER NOT NULL
);

CREATE TABLE schedules (
    ScheduleID VARCHAR(4) PRIMARY KEY,
    Day VARCHAR(3) NOT NULL,
    TimeStart TIME NOT NULL,
    TimeEnd TIME NOT NULL
);

CREATE TABLE courses (
    CourseID VARCHAR(6) PRIMARY KEY,
    RoomID VARCHAR(5),
    TeacherID VARCHAR(10),
    SubjectID VARCHAR(5),
    MaxStudents INTEGER NOT NULL,
    CurrentStudents INTEGER DEFAULT 0,
    OpeningDate DATE NOT NULL,
    TypeCourse ENUM('offline', 'online') NOT NULL,
    FOREIGN KEY (RoomID) REFERENCES rooms(RoomID),
    FOREIGN KEY (TeacherID) REFERENCES teachers(TeacherID),
    FOREIGN KEY (SubjectID) REFERENCES subjects(SubjectID)
);

CREATE TABLE course_schedule (
    CourseScheduleID VARCHAR(10) PRIMARY KEY,
    CourseID VARCHAR(6),
    ScheduleID VARCHAR(4),
    FOREIGN KEY (CourseID) REFERENCES courses(CourseID),
    FOREIGN KEY (ScheduleID) REFERENCES schedules(ScheduleID)
);

CREATE TABLE studies (
    StudyID VARCHAR(10) PRIMARY KEY,
    StudentID VARCHAR(10),
    CourseID VARCHAR(6),
    FOREIGN KEY (StudentID) REFERENCES users(UserID),
    FOREIGN KEY (CourseID) REFERENCES courses(CourseID)
);

CREATE TABLE scores (
    ScoreID INTEGER PRIMARY KEY AUTO_INCREMENT,
    StudyID VARCHAR(10),
    ExamNumber INTEGER NOT NULL,
    ExamDate DATE NOT NULL,
    Coefficient INTEGER NOT NULL,
    Score FLOAT NOT NULL,
    FOREIGN KEY (StudyID) REFERENCES studies(StudyID)
);

CREATE TABLE attendances (
    AttendanceID INTEGER PRIMARY KEY AUTO_INCREMENT,
    StudyID VARCHAR(10) NOT NULL,
    AttendanceDate DATE NOT NULL,
    Status ENUM('present', 'absent') NOT NULL,
    FOREIGN KEY (StudyID) REFERENCES studies(StudyID)
);

