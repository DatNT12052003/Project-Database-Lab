-- Insert admin
Insert into users (userid, username, password, role, status) values ('ADMIN00001', 'admin', '123456', 'Admin', 'Active');

-- Insert users
Insert into users (userid, username, password, role) values 
('S000000001', 'thanhdat2003pt', '4nhs3ch30m', 'Student'),
('T000000001', 'oanhcute', 'y3u04nh', 'Teacher'),
('S000000002', 'vananh2007', 'va123123', 'Student'),
('T000000002', 'phanducjp', 'ducphanabc()', 'Teacher'),
('S000000003', 'leeuyen2005', 'uyenlee', 'Student'),
('T000000003', 'gioiduong2102', 'trongxuan1@3$', 'Teacher'),
('S000000004', 'quocvietpro', 'vietcong', 'Student'),
('T000000004', 'quynhmai1998', 'maipham!@#', 'Teacher'),
('S000000005', 'nguyenthuminh2004', 'minh^&*', 'Student'),
('T000000005', 'manhle99', 'ldm$%^', 'Teacher'),
('S000000006', 'duyhoang2003', 'hoangduy', 'Student'),
('T000000006', 'thainodoto', 'nguyenthai0504', 'Teacher'),
('S000000007', 'hathuongchu', 'thuongcute', 'Student'),
('T000000007', 'tungduong2001', 'duong!@#', 'Teacher'),
('S000000008', 'hieuduong2003', 'trunghieuduong', 'Student'),
('S000000009', 'hahangpt', 'h4ng123', 'Student'),
('S000000010', 'linhngoc', 'ngoclinh', 'Student'),
('S000000011', 'vangiap2006', 'giaplinux123$%^', 'Student'),
('S000000012', 'anhtuan2004', 'tuannguyet', 'Student'),
('S000000013', 'thaophuong123', 'phuongoanh123', 'Student');

-- Insert teachers
Insert into teachers (teacherid, fullname, dateofbirth, gender, address, phone, email, expertise, level, salary) values
('T000000001', 'Nguyen Thi Kim Oanh', '2003-05-23', 'Female', 'Phu Tho', '0396179235', 'oanhcute@gmail.com', 'English', 'IELTS 8.5', 25000000),
('T000000002', 'Phan Trung Duc', '2000-05-25', 'Male', 'Phu Tho', '0987654321', 'phanduc@gmail.com', 'Japanese', 'JLPT N1', 18000000),
('T000000003', 'Duong Van Gioi', '1999-05-21', 'Male', 'Thanh Hoa', '0123456789', 'trongxuan@gmail.com', 'Chinese', 'HSK 6', 22000000),
('T000000004', 'Pham Quynh Mai', '1998-09-08', 'Female', 'Ha Noi', '0999888777', 'maiquynhpham@gmail.com', 'English', 'TOEIC 960', 25000000),
('T000000005', 'Le Duc Manh', '1999-05-11', 'Male', 'Yen Bai', '0111222333', 'manhleyb@gmail.com', 'Chinese', 'HSK 5', 14000000),
('T000000006', 'Nguyen Duc Thai', '2002-04-05', 'Male', 'Hai Phong', '0555444333', 'nodoto@gmail.com', 'Japanese', 'JLPT N1', 18000000),
('T000000007', 'Nguyen Gia Tung Duong', '2001-11-11', 'Male', 'Ha Noi', '0987666555', 'nguyenduong2001@gmail.com', 'English', 'IELTS 9.0', 25000000);

-- Insert students
Insert into students (studentid, fullname, dateofbirth, gender, address, phone, email) values
('S000000001', 'Nguyen Thanh Dat', '2003-05-12', 'Male', 'Phu Tho', '0888014325', 'thanhdat2003pt@gmail.com'),
('S000000002', 'Tran Hoang Van Anh', '2007-10-07', 'Female', 'Ha Noi', '0111666777', 'vananh2007@gmail.com'),
('S000000003', 'Le Ngoc Uyen', '2006-01-01', 'Female', 'Ha Noi', '0956874123', 'leeuyen2006@gmail.com'),
('S000000004', 'Nguyen Quoc Viet', '2005-03-31', 'Male', 'Quang Ninh', '0156732911', 'vietpromax@gmail.com'),
('S000000005', 'Nguyen Thu Minh', '2004-07-20', 'Female', 'Hoa Binh', '0911876234', 'minhnguyen2007@gmail.com'),
('S000000006', 'Hoang Dinh Duy', '2003-06-16', 'Male', 'Ha Nam', '0234876555', 'duyhoang2003@gmail.com'),
('S000000007', 'Chu Thi Ha Thong', '2005-06-01', 'Female', 'Thai Binh', '0898762154', 'chuthuong2005@gmail.com'),
('S000000008', 'Duong Trung Hieu', '2003-04-25', 'Male', 'Lang Son', '0923871622', 'duonghieu123@gmail.com'),
('S000000009', 'Bui Thi Thu Hang', '2007-09-15', 'Female', 'Ha Noi', '0567890123', 'hahang0915@gmail.com'),
('S000000010', 'Nguyen Ngoc Linh', '2006-05-12', 'Female', 'Vinh Phuc', '0928156372', 'ngoclinh@gmail.com'),
('S000000011', 'Nguyen Van Giap', '2005-02-20', 'Male', 'Phu Tho', '0378987615', 'giaplinux@gmail.com'),
('S000000012', 'Nguyen Anh Tuan', '2004-09-10', 'Male', 'Hung Yen', '0399887665', 'tuannguyen2004@gmail.com'),
('S000000013', 'Hoang Thi Thao Phuong', '2006-05-12', 'Female', 'Ha Noi', '0345999888', 'phuongoanh987@gmail.com');

-- Insert subjects
Insert into subjects (name, credit, tuition) values
('IELTS 6.0', 24, 4800000),
('JLPT N4', 36, 3600000),
('HSK 2', 24, 3600000),
('TOEIC 650', 24, 4800000),
('JLPT N3', 36, 5400000),
('IELTS 7.0', 30, 6000000);

-- Insert rooms
Insert into rooms (address, type, maxstudents) values
('A1 - 101', 'Offline', 30),
('A1 - 102', 'Offline', 30),
('A1 - 103', 'Offline', 30),
('A1 - 201', 'Offline', 30),
('A1 - 202', 'Offline', 30),
('A1 - 203', 'Offline', 30),
('A1 - 301', 'Offline', 30),
('A1 - 302', 'Offline', 30),
('A1 - 303', 'Offline', 30),
('A1 - 401', 'Offline', 30),
('A1 - 402', 'Offline', 30),
('A1 - 403', 'Offline', 30),
('Microsoft Teams - a9u7gd', 'Online', 50),
('Microsoft Teams - b36yh8', 'Online', 50);

-- Insert schedules
Insert into schedules (day, timestart, timeend) VALUES
('Monday', '07:30:00', '09:00:00'),
('Monday', '09:30:00', '11:00:00'),
('Monday', '14:00:00', '15:30:00'),
('Monday', '16:00:00', '17:30:00'),
('Monday', '19:00:00', '20:30:00'),
('Tuesday', '07:30:00', '09:00:00'),
('Tuesday', '09:30:00', '11:00:00'),
('Tuesday', '14:00:00', '15:30:00'),
('Tuesday', '16:00:00', '17:30:00'),
('Tuesday', '19:00:00', '20:30:00'),
('Wednesday', '07:30:00', '09:00:00'),
('Wednesday', '09:30:00', '11:00:00'),
('Wednesday', '14:00:00', '15:30:00'),
('Wednesday', '16:00:00', '17:30:00'),
('Wednesday', '19:00:00', '20:30:00'),
('Thursday', '07:30:00', '09:00:00'),
('Thursday', '09:30:00', '11:00:00'),
('Thursday', '14:00:00', '15:30:00'),
('Thursday', '16:00:00', '17:30:00'),
('Thursday', '19:00:00', '20:30:00'),
('Friday', '07:30:00', '09:00:00'),
('Friday', '09:30:00', '11:00:00'),
('Friday', '14:00:00', '15:30:00'),
('Friday', '16:00:00', '17:30:00'),
('Friday', '19:00:00', '20:30:00'),
('Saturday', '07:30:00', '09:00:00'),
('Saturday', '09:30:00', '11:00:00'),
('Saturday', '14:00:00', '15:30:00'),
('Saturday', '16:00:00', '17:30:00'),
('Saturday', '19:00:00', '20:30:00'),
('Sunday', '07:30:00', '09:00:00'),
('Sunday', '09:30:00', '11:00:00'),
('Sunday', '14:00:00', '15:30:00'),
('Sunday', '16:00:00', '17:30:00'),
('Sunday', '19:00:00', '20:30:00');

-- Insert courses
Insert into courses (roomid, teacherid, subjectid, regisstartdate, coursestartdate, status) values
(1, 'T000000001', 1, '2024-05-12', '2024-06-12', 'Completed'),
(2, 'T000000004', 4, '2024-09-10', '2024-10-10', 'Canceled'),
(4, 'T000000002', 5, '2025-04-05', '2025-05-05', 'Ongoing'),
(1, 'T000000003', 3, '2025-03-25', '2025-04-25', 'Ongoing'),
(13, 'T000000007', 6, '2025-05-19', '2025-06-19', 'Registration');

-- Insert course_schedule
Insert into course_schedule (courseid, scheduleid) values
(1, 15), (1, 35),
(2, 5), (2, 20),
(3, 3), (3, 13), (3, 23),
(4, 15), (4, 35),
(5, 9), (5, 24);

-- Insert enrollments
Insert into enrollments (studentid, courseid, registrationdate, tuitionpayment, status) values
('S000000001', 1, '2024-05-12 12:05:03', 'Completed', 'Completed'),
('S000000003', 1, '2024-05-13 22:11:57', 'Completed', 'Completed'),
('S000000011', 1, '2024-05-15 19:02:11', 'Completed', 'Completed'),
('S000000008', 1, '2024-05-17 08:05:33', 'Completed', 'Completed'),
('S000000004', 1, '2024-05-17 12:33:44', 'Completed', 'Completed'),
('S000000007', 1, '2024-05-18 13:00:23', 'Completed', 'Completed'),
('S000000002', 2, '2024-09-12 09:10:11', 'Incomplete', 'Canceled'),
('S000000005', 2, '2024-09-19 20:34:19', 'Incomplete', 'Canceled'),
('S000000009', 2, '2024-10-08 17:30:45', 'Incomplete', 'Canceled'),
('S000000001', 3, '2025-04-07 15:17:39', 'Completed', 'Studying'),
('S000000002', 3, '2025-04-10 23:29:51', 'Completed', 'Studying'),
('S000000007', 3, '2025-04-15 05:23:11', 'Incomplete', 'Canceled'),
('S000000005', 3, '2025-04-18 12:16:30', 'Completed', 'Studying'),
('S000000010', 3, '2025-04-20 19:45:22', 'Completed', 'Studying'),
('S000000012', 3, '2025-04-24 10:58:40', 'Completed', 'Studying'),
('S000000007', 4, '2025-03-28 11:17:39', 'Completed', 'Studying'),
('S000000009', 4, '2025-03-30 15:18:31', 'Completed', 'Studying'),
('S000000008', 4, '2025-04-02 09:30:11', 'Completed', 'Studying'),
('S000000004', 4, '2025-04-05 20:40:59', 'Completed', 'Studying'),
('S000000011', 4, '2025-04-10 22:09:24', 'Completed', 'Studying'),
('S000000013', 5, '2025-05-21 08:00:49', 'Completed', 'Registered'),
('S000000006', 5, '2025-05-25 17:49:00', 'Incomplete', 'Registered');

-- Insert payments
Insert into payments (enrollmentid, amount, method, paymentdate, status) values
(1, 4800000, 'Cash', '2024-05-12 12:20:15', 'Received'),
(2, 4800000, 'Momo', '2024-05-13 22:15:09', 'Received'),
(3, 4800000, 'Bank Transfer', '2024-05-15 19:12:44', 'Received'),
(4, 4800000, 'Cash', '2024-05-17 08:17:58', 'Received'),
(5, 4800000, 'ViettelMoney', '2024-05-17 12:50:23', 'Received'),
(6, 4800000, 'Momo', '2024-05-18 13:04:38', 'Received'),
(7, 4800000, 'Cash', '2024-09-12 09:20:15', 'Refunded'),
(8, 4800000, 'Momo', '2024-09-19 20:38:25', 'Refunded'),
(9, 4800000, 'Bank Transfer', '2024-10-08 17:41:48', 'Refunded'),
(10, 5400000, 'Bank Transfer', '2025-04-07 15:23:39', 'Received'),
(11, 5400000, 'Bank Transfer', '2025-04-10 23:32:44', 'Received'),
(13, 5400000, 'VNPay', '2025-04-18 12:19:21', 'Received'),
(14, 2700000, 'Cash', '2025-04-20 19:55:33', 'Received'),
(15, 5400000, 'ViettelMoney', '2025-04-24 11:05:53', 'Received'),
(14, 2700000, 'Cash', '2025-04-07 16:28:01', 'Received'),
(16, 1800000, 'VNPay', '2025-03-28 11:28:39', 'Received'),
(17, 3600000, 'Cash', '2025-03-30 15:25:31', 'Received'),
(16, 1800000, 'VNPay', '2025-04-01 21:49:50', 'Received'),
(18, 3600000, 'Momo', '2025-04-02 09:40:19', 'Received'),
(19, 3600000, 'ViettelMoney', '2025-04-05 20:49:52', 'Received'),
(20, 1800000, 'Bank Transfer', '2025-04-10 22:19:24', 'Received'),
(20, 1800000, 'Bank Transfer', '2025-04-15 22:05:23', 'Received'),
(21, 6000000, 'Cash', '2025-05-21 08:07:35', 'Received'),
(22, 6000000, 'VNPay', '2025-05-25 17:58:01', 'Received');

-- Insert attendances
Insert into attendances (enrollmentid, attendancedate, status) values
(1, '2024-06-19', 'Present'), (2, '2024-06-19', 'Present'), (3, '2024-06-19', 'Present'), (4, '2024-06-19', 'Present'), (5, '2024-06-19', 'Present'), (6, '2024-06-19', 'Present'),
(1, '2024-06-23', 'Present'), (2, '2024-06-23', 'Absent'), (3, '2024-06-23', 'Present'), (4, '2024-06-23', 'Present'), (5, '2024-06-23', 'Present'), (6, '2024-06-23', 'Present'),
(1, '2024-06-26', 'Present'), (2, '2024-06-26', 'Present'), (3, '2024-06-26', 'Present'), (4, '2024-06-26', 'Present'), (5, '2024-06-26', 'Absent'), (6, '2024-06-26', 'Present'),
(1, '2024-06-30', 'Present'), (2, '2024-06-30', 'Present'), (3, '2024-06-30', 'Present'), (4, '2024-06-30', 'Absent'), (5, '2024-06-30', 'Present'), (6, '2024-06-30', 'Present'),
(1, '2024-07-03', 'Present'), (2, '2024-07-03', 'Present'), (3, '2024-07-03', 'Present'), (4, '2024-07-03', 'Present'), (5, '2024-07-03', 'Present'), (6, '2024-07-03', 'Absent'),
(1, '2024-07-07', 'Present'), (2, '2024-07-07', 'Present'), (3, '2024-07-07', 'Present'), (4, '2024-07-07', 'Present'), (5, '2024-07-07', 'Present'), (6, '2024-07-07', 'Present'),
(1, '2024-07-10', 'Present'), (2, '2024-07-10', 'Present'), (3, '2024-07-10', 'Present'), (4, '2024-07-10', 'Present'), (5, '2024-07-10', 'Present'), (6, '2024-07-10', 'Present'),
(1, '2024-07-14', 'Present'), (2, '2024-07-14', 'Present'), (3, '2024-07-14', 'Present'), (4, '2024-07-14', 'Present'), (5, '2024-07-14', 'Present'), (6, '2024-07-14', 'Present'),
(1, '2024-07-17', 'Present'), (2, '2024-07-17', 'Present'), (3, '2024-07-17', 'Present'), (4, '2024-07-17', 'Present'), (5, '2024-07-17', 'Present'), (6, '2024-07-17', 'Present'),
(1, '2024-07-21', 'Absent'), (2, '2024-07-21', 'Present'), (3, '2024-07-21', 'Present'), (4, '2024-07-21', 'Present'), (5, '2024-07-21', 'Present'), (6, '2024-07-21', 'Present'),
(1, '2024-07-24', 'Present'), (2, '2024-07-24', 'Present'), (3, '2024-07-24', 'Present'), (4, '2024-07-24', 'Present'), (5, '2024-07-24', 'Present'), (6, '2024-07-24', 'Present'),
(1, '2024-07-28', 'Present'), (2, '2024-07-28', 'Present'), (3, '2024-07-28', 'Present'), (4, '2024-07-28', 'Present'), (5, '2024-07-28', 'Present'), (6, '2024-07-28', 'Present'),
(1, '2024-07-31', 'Present'), (2, '2024-07-31', 'Present'), (3, '2024-07-31', 'Absent'), (4, '2024-07-31', 'Present'), (5, '2024-07-31', 'Present'), (6, '2024-07-31', 'Present'),
(1, '2024-08-04', 'Present'), (2, '2024-08-04', 'Present'), (3, '2024-08-04', 'Present'), (4, '2024-08-04', 'Present'), (5, '2024-08-04', 'Present'), (6, '2024-08-04', 'Absent'),
(1, '2024-08-07', 'Present'), (2, '2024-08-07', 'Present'), (3, '2024-08-07', 'Present'), (4, '2024-08-07', 'Present'), (5, '2024-08-07', 'Present'), (6, '2024-08-07', 'Present'),
(1, '2024-08-11', 'Present'), (2, '2024-08-11', 'Present'), (3, '2024-08-11', 'Present'), (4, '2024-08-11', 'Present'), (5, '2024-08-11', 'Present'), (6, '2024-08-11', 'Present'),
(1, '2024-08-14', 'Present'), (2, '2024-08-14', 'Present'), (3, '2024-08-14', 'Present'), (4, '2024-08-14', 'Absent'), (5, '2024-08-14', 'Present'), (6, '2024-08-14', 'Present'),
(1, '2024-08-18', 'Present'), (2, '2024-08-18', 'Present'), (3, '2024-08-18', 'Present'), (4, '2024-08-18', 'Present'), (5, '2024-08-18', 'Absent'), (6, '2024-08-18', 'Present'),
(1, '2024-08-21', 'Present'), (2, '2024-08-21', 'Present'), (3, '2024-08-21', 'Present'), (4, '2024-08-21', 'Present'), (5, '2024-08-21', 'Present'), (6, '2024-08-21', 'Present'),
(1, '2024-08-25', 'Absent'), (2, '2024-08-25', 'Present'), (3, '2024-08-25', 'Present'), (4, '2024-08-25', 'Present'), (5, '2024-08-25', 'Present'), (6, '2024-08-25', 'Present'),
(1, '2024-08-28', 'Present'), (2, '2024-08-28', 'Present'), (3, '2024-08-28', 'Present'), (4, '2024-08-28', 'Present'), (5, '2024-08-28', 'Absent'), (6, '2024-08-28', 'Absent'),
(1, '2024-09-01', 'Present'), (2, '2024-09-01', 'Present'), (3, '2024-09-01', 'Present'), (4, '2024-09-01', 'Present'), (5, '2024-09-01', 'Present'), (6, '2024-09-01', 'Present'),
(1, '2024-09-04', 'Present'), (2, '2024-09-04', 'Present'), (3, '2024-09-04', 'Absent'), (4, '2024-09-04', 'Present'), (5, '2024-09-04', 'Present'), (6, '2024-09-04', 'Present'),
(1, '2024-09-08', 'Present'), (2, '2024-09-08', 'Present'), (3, '2024-09-08', 'Present'), (4, '2024-09-08', 'Present'), (5, '2024-09-08', 'Present'), (6, '2024-09-08', 'Present'),
(10, '2025-05-12', 'Present'), (11, '2025-05-12', 'Present'), (13, '2025-05-12', 'Present'), (14, '2025-05-12', 'Present'), (15, '2025-05-12', 'Present'),
(10, '2025-05-14', 'Present'), (11, '2025-05-14', 'Present'), (13, '2025-05-14', 'Present'), (14, '2025-05-14', 'Present'), (15, '2025-05-14', 'Present'),
(10, '2025-05-16', 'Present'), (11, '2025-05-16', 'Present'), (13, '2025-05-16', 'Present'), (14, '2025-05-16', 'Present'), (15, '2025-05-16', 'Present'),
(10, '2025-05-19', 'Present'), (11, '2025-05-19', 'Present'), (13, '2025-05-19', 'Present'), (14, '2025-05-19', 'Present'), (15, '2025-05-19', 'Present'),
(10, '2025-05-21', 'Present'), (11, '2025-05-21', 'Present'), (13, '2025-05-21', 'Present'), (14, '2025-05-21', 'Present'), (15, '2025-05-21', 'Present'),
(10, '2025-05-23', 'Present'), (11, '2025-05-23', 'Present'), (13, '2025-05-23', 'Present'), (14, '2025-05-23', 'Present'), (15, '2025-05-23', 'Present'),
(10, '2025-05-26', 'Present'), (11, '2025-05-26', 'Present'), (13, '2025-05-26', 'Present'), (14, '2025-05-26', 'Present'), (15, '2025-05-26', 'Present'),
(10, '2025-05-28', 'Present'), (11, '2025-05-28', 'Present'), (13, '2025-05-28', 'Present'), (14, '2025-05-28', 'Present'), (15, '2025-05-28', 'Present'),
(10, '2025-05-30', 'Present'), (11, '2025-05-30', 'Present'), (13, '2025-05-30', 'Present'), (14, '2025-05-30', 'Present'), (15, '2025-05-30', 'Present'),
(16, '2025-05-05', 'Present'), (17, '2025-05-05', 'Present'), (18, '2025-05-05', 'Present'), (19, '2025-05-05', 'Present'), (20, '2025-05-05', 'Present'),
(16, '2025-05-08', 'Present'), (17, '2025-05-08', 'Present'), (18, '2025-05-08', 'Present'), (19, '2025-05-08', 'Present'), (20, '2025-05-08', 'Present'),
(16, '2025-05-12', 'Present'), (17, '2025-05-12', 'Present'), (18, '2025-05-12', 'Present'), (19, '2025-05-12', 'Present'), (20, '2025-05-12', 'Present'),
(16, '2025-05-15', 'Present'), (17, '2025-05-15', 'Present'), (18, '2025-05-15', 'Present'), (19, '2025-05-15', 'Present'), (20, '2025-05-15', 'Present'),
(16, '2025-05-19', 'Present'), (17, '2025-05-19', 'Present'), (18, '2025-05-19', 'Present'), (19, '2025-05-19', 'Present'), (20, '2025-05-19', 'Present'),
(16, '2025-05-22', 'Present'), (17, '2025-05-22', 'Present'), (18, '2025-05-22', 'Present'), (19, '2025-05-22', 'Present'), (20, '2025-05-22', 'Present'),
(16, '2025-05-26', 'Present'), (17, '2025-05-26', 'Present'), (18, '2025-05-26', 'Present'), (19, '2025-05-26', 'Present'), (20, '2025-05-26', 'Present'),
(16, '2025-05-29', 'Present'), (17, '2025-05-29', 'Present'), (18, '2025-05-29', 'Present'), (19, '2025-05-29', 'Present'), (20, '2025-05-29', 'Present');

-- Insert scores
Insert into scores (enrollmentid, examnumber, coefficient, score) values
(1, 1, 1, 8.0), (2, 1, 1, 7.0), (3, 1, 1, 8.5), (4, 1, 1, 9.5), (5, 1, 1, 9.0), (6, 1, 1, 7.5),
(1, 2, 1, 8.5), (2, 2, 1, 6.5), (3, 2, 1, 9.0), (4, 2, 1, 8.0), (5, 2, 1, 9.0), (6, 1, 1, 6.5),
(1, 3, 2, 9.0), (2, 3, 2, 8.0), (3, 3, 2, 7.0), (4, 3, 2, 7.5), (5, 3, 2, 8.5), (6, 3, 2, 8.0),
(10, 1, 1, 9.5), (11, 1, 1, 8.5), (13, 1, 1, 9.0), (14, 1, 1, 7.5), (15, 1, 1, 8.0),
(16, 1, 1, 7.5), (17, 1, 1, 8.0), (18, 1, 1, 6.0), (19, 1, 1, 6.5), (20, 1, 1, 7.0);
