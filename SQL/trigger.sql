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

SHOW TRIGGERS;



