CREATE DATABASE projectdatabase;
USE projectdatabase;

CREATE TABLE users (
    UserID VARCHAR(10) PRIMARY KEY,
    Account VARCHAR(20) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Role ENUM('Admin', 'Teacher', 'Student') NOT NULL,
    Status ENUM('Active', 'Locked') NOT NULL DEFAULT 'Active',
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE students (
    StudentID VARCHAR(10) PRIMARY KEY,
    FullName VARCHAR(50) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female') NOT NULL,
    Address VARCHAR(50),
    Phone VARCHAR(15) UNIQUE,
    Email VARCHAR(50) UNIQUE,
    Status ENUM('Deleted', 'Studying') NOT NULL DEFAULT 'Studying'
);

CREATE TABLE teachers (
    TeacherID VARCHAR(10) PRIMARY KEY,
    FullName VARCHAR(50) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female') NOT NULL,
    Address VARCHAR(50),
    Phone VARCHAR(15),
    Email VARCHAR(50) UNIQUE,
    Expertise VARCHAR(20), 
    Level VARCHAR(20),
    Salary INTEGER CHECK (Salary >= 0),
    Status ENUM('Deleted', 'Teaching') NOT NULL DEFAULT 'Teaching'
);

CREATE TABLE rooms (
    RoomID VARCHAR(8) PRIMARY KEY,
    Address VARCHAR(255) NOT NULL,
    Type ENUM('Offline', 'Online') NOT NULL,
    MaxStudents INTEGER CHECK (MaxStudents > 0) NOT NULL,
    Status ENUM('Empty', 'Using', 'Deleted') NOT NULL DEFAULT 'Empty'
);

CREATE TABLE subjects (
    SubjectID VARCHAR(6) PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    Mass DOUBLE CHECK (Mass > 0),
    Tuition INTEGER CHECK (Tuition >= 0),
    Status ENUM('Deleted', 'Using') NOT NULL DEFAULT 'Using'
);

CREATE TABLE schedules (
    ScheduleID VARCHAR(5) PRIMARY KEY,
    Day VARCHAR(10) NOT NULL,
    TimeStart TIME NOT NULL,
    TimeEnd TIME NOT NULL,
    Status ENUM('Deleted', 'Using') NOT NULL DEFAULT 'Using',
    CHECK (TimeEnd > TimeStart)
);

CREATE TABLE courses (
    CourseID VARCHAR(6) PRIMARY KEY,
    RoomID VARCHAR(8),
    TeacherID VARCHAR(10),
    SubjectID VARCHAR(6),
    CurrentStudents INTEGER CHECK (CurrentStudents >= 0),
    RegisStartDate DATE NOT NULL,
    CourseStartDate DATE NOT NULL,
    Status ENUM('Registration', 'Canceled', 'Ongoing', 'Completed'),
    FOREIGN KEY (RoomID) REFERENCES rooms(RoomID) ON UPDATE CASCADE,
    FOREIGN KEY (TeacherID) REFERENCES teachers(TeacherID) ON UPDATE CASCADE,
    FOREIGN KEY (SubjectID) REFERENCES subjects(SubjectID) ON UPDATE CASCADE
);

CREATE TABLE course_schedule (
    CourseScheduleID VARCHAR(10) PRIMARY KEY,
    CourseID VARCHAR(6) NOT NULL,
    ScheduleID VARCHAR(5) NOT NULL,
    FOREIGN KEY (CourseID) REFERENCES courses(CourseID) ON UPDATE CASCADE,
    FOREIGN KEY (ScheduleID) REFERENCES schedules(ScheduleID) ON UPDATE CASCADE
);

CREATE TABLE studies (
    StudyID VARCHAR(10) PRIMARY KEY,
    StudentID VARCHAR(10) NOT NULL,
    CourseID VARCHAR(6) NOT NULL,
    RegistrationDate DATETIME NOT NULL,
    TuitionPayment ENUM('Completed', 'Incomplete') NOT NULL,
    Status ENUM('Studying', 'Completed', 'Registered', 'Canceled') NOT NULL DEFAULT 'Registered',
    FOREIGN KEY (StudentID) REFERENCES students(StudentID) ON UPDATE CASCADE,
    FOREIGN KEY (CourseID) REFERENCES courses(CourseID) ON UPDATE CASCADE
);

CREATE TABLE attendances (
    AttendanceID INT PRIMARY KEY AUTO_INCREMENT,
    StudyID VARCHAR(10) NOT NULL,
    AttendanceDate DATE NOT NULL,
    Status ENUM('Present', 'Absent') NOT NULL DEFAULT 'Present',
    FOREIGN KEY (StudyID) REFERENCES studies(StudyID) ON UPDATE CASCADE
);

CREATE TABLE scores (
    ScoreID INT PRIMARY KEY AUTO_INCREMENT,
    StudyID VARCHAR(10) NOT NULL,
    ExamNumber INT NOT NULL,
    ExamDate DATE NOT NULL,
    Coefficient INT NOT NULL,
    Score FLOAT NOT NULL,
    FOREIGN KEY (StudyID) REFERENCES studies(StudyID) ON UPDATE CASCADE
);
