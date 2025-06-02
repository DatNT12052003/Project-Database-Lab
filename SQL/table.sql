CREATE DATABASE project;
USE project;

CREATE TABLE users (
    UserID VARCHAR(10) PRIMARY KEY,
    UserName VARCHAR(20) NOT NULL UNIQUE,
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
    RoomID INT PRIMARY KEY AUTO_INCREMENT,
    Address VARCHAR(255) NOT NULL,
    Type ENUM('Offline', 'Online') NOT NULL,
    MaxStudents INTEGER CHECK (MaxStudents > 0) NOT NULL,
    Status ENUM('Empty', 'Using', 'Deleted') NOT NULL DEFAULT 'Empty'
);

CREATE TABLE subjects (
    SubjectID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(30) NOT NULL,
    Credit INTEGER CHECK (Credit > 0),
    Tuition INTEGER CHECK (Tuition >= 0),
    Status ENUM('Deleted', 'Using') NOT NULL DEFAULT 'Using'
);

CREATE TABLE schedules (
    ScheduleID INT PRIMARY KEY AUTO_INCREMENT,
    Day VARCHAR(10) NOT NULL,
    TimeStart TIME NOT NULL,
    TimeEnd TIME NOT NULL,
    Status ENUM('Deleted', 'Using') NOT NULL DEFAULT 'Using',
    CHECK (TimeEnd > TimeStart)
);

CREATE TABLE courses (
    CourseID INT PRIMARY KEY AUTO_INCREMENT,
    RoomID INT NOT NULL,
    TeacherID VARCHAR(10),
    SubjectID INT NOT NULL,
    RegisStartDate DATE NOT NULL,
    CourseStartDate DATE NOT NULL,
    Status ENUM('Registration', 'Canceled', 'Ongoing', 'Completed') DEFAULT 'Registration',
    FOREIGN KEY (RoomID) REFERENCES rooms(RoomID) ON UPDATE CASCADE,
    FOREIGN KEY (TeacherID) REFERENCES teachers(TeacherID) ON UPDATE CASCADE,
    FOREIGN KEY (SubjectID) REFERENCES subjects(SubjectID) ON UPDATE CASCADE
);

CREATE TABLE course_schedule (
    CourseScheduleID INT PRIMARY KEY AUTO_INCREMENT,
    CourseID INT NOT NULL,
    ScheduleID INT NOT NULL,
    FOREIGN KEY (CourseID) REFERENCES courses(CourseID) ON UPDATE CASCADE,
    FOREIGN KEY (ScheduleID) REFERENCES schedules(ScheduleID) ON UPDATE CASCADE
);

CREATE TABLE enrollments (
    EnrollmentID INT PRIMARY KEY AUTO_INCREMENT,
    StudentID VARCHAR(10) NOT NULL,
    CourseID INT NOT NULL,
    RegistrationDate DATETIME NOT NULL,
    TuitionPayment ENUM('Incomplete', 'Completed') NOT NULL DEFAULT 'Incomplete',
    Status ENUM('Studying', 'Completed', 'Registered', 'Canceled') NOT NULL DEFAULT 'Registered',
    FOREIGN KEY (StudentID) REFERENCES students(StudentID) ON UPDATE CASCADE,
    FOREIGN KEY (CourseID) REFERENCES courses(CourseID) ON UPDATE CASCADE
);

CREATE TABLE payments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    EnrollmentID INT,
    Amount INTEGER NOT NULL CHECK (Amount >= 0),
	Method ENUM('Cash', 'Bank Transfer', 'Momo', 'VNPay', 'ViettelMoney') NOT NULL,
    PaymentDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status ENUM('Deleted', 'Received', 'Refunded') NOT NULL DEFAULT 'Received',
    FOREIGN KEY (EnrollmentID) REFERENCES enrollments(EnrollmentID)
);

CREATE TABLE attendances (
    AttendanceID INT PRIMARY KEY AUTO_INCREMENT,
    EnrollmentID INT NOT NULL,
    AttendanceDate DATE NOT NULL,
    Status ENUM('Present', 'Absent') NOT NULL DEFAULT 'Present',
    FOREIGN KEY (EnrollmentID) REFERENCES enrollments(EnrollmentID) ON UPDATE CASCADE
);

CREATE TABLE scores (
    ScoreID INT PRIMARY KEY AUTO_INCREMENT,
    EnrollmentID INT NOT NULL,
    ExamNumber INT NOT NULL,
    Coefficient INT NOT NULL,
    Score FLOAT NOT NULL,
    FOREIGN KEY (EnrollmentID) REFERENCES enrollments(EnrollmentID) ON UPDATE CASCADE
);

