DELIMITER $$
CREATE TRIGGER after_delete_student
AFTER UPDATE ON students
FOR EACH ROW
BEGIN
    IF NEW.status = 'Deleted' THEN
        DELETE FROM users WHERE userid = NEW.studentid;
    END IF;
END $$

DELIMITER ;

DELIMITER $$
CREATE TRIGGER after_delete_teacher
AFTER UPDATE ON teachers
FOR EACH ROW
BEGIN
    IF NEW.status = 'Deleted' THEN
        DELETE FROM users WHERE userid = NEW.teacherid;
    END IF;
END $$

DELIMITER ;














DELIMITER $$
CREATE TRIGGER after_delete_student
AFTER UPDATE ON students
FOR EACH ROW
BEGIN
    IF NEW.status = 'Deleted' THEN
        UPDATE users SET userid = NEW.studentid, status = 'Locked' WHERE userid = NEW.studentid;
    END IF;
END $$

DELIMITER ;

DELIMITER $$
CREATE TRIGGER after_delete_teacher
AFTER UPDATE ON teachers
FOR EACH ROW
BEGIN
    IF NEW.status = 'Deleted' THEN
        UPDATE users SET userid = NEW.teacherid, status = 'Locked' WHERE userid = NEW.teacherid;
    END IF;
END $$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER after_unlock_user
AFTER UPDATE ON users
FOR EACH ROW
BEGIN
    IF NEW.status = 'Active' AND SUBSTRING(NEW.userid, 1, 1) = 'T' THEN
        UPDATE teachers SET status = 'Teaching' WHERE teacherid = NEW.userid;
    END IF;
    IF NEW.status = 'Active' AND SUBSTRING(NEW.userid, 1, 1) = 'S' THEN
        UPDATE students SET status = 'Studying' WHERE studentid = NEW.userid;
    END IF;
END $$

DELIMITER ;

DELIMITER $$
CREATE TRIGGER after_studies_insert
AFTER INSERT ON studies
FOR EACH ROW
BEGIN
	UPDATE courses SET currentstudents = (SELECT COUNT(*) FROM studies WHERE courseid = NEW.courseid) WHERE courseid = NEW.courseid;
END $$

DELIMITER ;

DELIMITER $$
CREATE TRIGGER affter_courses_update_ongoing
AFTER UPDATE ON courses
FOR EACH ROW
BEGIN
    IF NEW.status = 'Ongoing' THEN
		UPDATE studies s
		JOIN (SELECT studyid FROM studies WHERE courseid = NEW.courseid) sub
		ON s.studyid = sub.studyid
		SET s.status = 'Studying';
    END IF;
END $$

DELIMITER ;


SHOW TRIGGERS;



