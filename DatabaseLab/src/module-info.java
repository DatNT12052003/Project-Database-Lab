
module DatabaseLab {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;

    exports controller.other;
    exports controller.admin;
    exports controller.admin.home;
    exports controller.admin.management;
    exports controller.student;
    exports controller.student.home;
    exports controller.student.courses;
    exports controller.teacher;
    exports controller.teacher.home;
    exports controller.teacher.courses;
    exports app; 
    
    opens model.user to javafx.base;
    opens model.person to javafx.base;
    opens model.subject to javafx.base;
    opens model.room to javafx.base;
    opens model.schedule to javafx.base;
    opens model.course to javafx.base;
    opens model.study to javafx.base;
    opens controller.other to javafx.fxml; 
    opens controller.admin to javafx.fxml;
    opens controller.admin.home to javafx.fxml;
    opens controller.admin.management to javafx.fxml;
    opens controller.admin.management.users to javafx.fxml;
    opens controller.admin.management.teachers to javafx.fxml;
    opens controller.admin.management.students to javafx.fxml;
    opens controller.admin.management.subjects to javafx.fxml;
    opens controller.admin.management.rooms to javafx.fxml;
    opens controller.admin.management.schedules to javafx.fxml;
    opens controller.admin.management.courses to javafx.fxml;
    
    opens controller.student to javafx.fxml;
    opens controller.student.home to javafx.fxml;
    opens controller.student.courses to javafx.fxml;
    opens controller.student.courses.registration to javafx.fxml;
    opens controller.student.courses.all to javafx.fxml;
    opens controller.student.courses.registered to javafx.fxml;
    opens controller.student.courses.studying to javafx.fxml;
    
    opens controller.teacher to javafx.fxml;
    opens controller.teacher.home to javafx.fxml;
    opens controller.teacher.courses to javafx.fxml;
    opens controller.teacher.courses.teaching to javafx.fxml;
    
    opens app to javafx.fxml;
}