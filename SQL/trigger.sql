-- Automatic tuition status update
DELIMITER $$
CREATE TRIGGER trg_UpdateTuitionPayment
AFTER INSERT ON payments
FOR EACH ROW
BEGIN
    DECLARE totalPaid INT DEFAULT 0;
    DECLARE courseTuition INT DEFAULT 0;

    -- Lấy tổng số tiền đã thanh toán
    SELECT SUM(Amount)
    INTO totalPaid
    FROM payments
    WHERE EnrollmentID = NEW.EnrollmentID AND Status = 'Received';

    -- Lấy học phí khoá học
    SELECT Tuition
    INTO courseTuition
    FROM enrollments e
    JOIN courses c ON e.CourseID = c.CourseID
    WHERE e.EnrollmentID = NEW.EnrollmentID;

    -- Nếu thanh toán đủ thì cập nhật
    IF totalPaid >= courseTuition THEN
        UPDATE enrollments
        SET TuitionPayment = 'Completed'
        WHERE EnrollmentID = NEW.EnrollmentID;
    END IF;
END $$
DELIMITER ;

-- 
