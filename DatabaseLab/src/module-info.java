
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
    exports controller.admin.schedule;
    exports app; 
    
    opens model.user to javafx.base;
    opens model.person to javafx.base;
    opens model.subject to javafx.base;
    opens model.room to javafx.base;
    opens controller.other to javafx.fxml; 
    opens controller.admin to javafx.fxml;
    opens controller.admin.home to javafx.fxml;
    opens controller.admin.management to javafx.fxml;
    opens controller.admin.management.users to javafx.fxml;
    opens controller.admin.management.teachers to javafx.fxml;
    opens controller.admin.management.students to javafx.fxml;
    opens controller.admin.management.subjects to javafx.fxml;
    opens controller.admin.management.rooms to javafx.fxml;
    opens controller.admin.schedule to javafx.fxml;
    opens app to javafx.fxml;
}