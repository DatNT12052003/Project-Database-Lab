package controller.admin.management;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ManagementSceneController {
	
	@FXML
	private Button usersButton;
	
	@FXML
	private Button teachersButton;
	
	@FXML
	private Button studentsButton;
	
	@FXML
	private Button subjectsButton;
	
	@FXML
	private Button coursesButton;
	
	@FXML
	private Button roomsButton;
	
	@FXML
	private Button schedulesButton;
	
	@FXML
	private AnchorPane mainScene;
	
	@FXML
	private void initialize() throws Exception{
		handleUsers();
	}
	
	@FXML
	private void handleUsers() throws Exception {
    	Pane scene = FXMLLoader.load(getClass().getResource("/view/admin/management/users/UsersScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	private void handleTeachers() throws Exception {
    	Pane scene = FXMLLoader.load(getClass().getResource("/view/admin/management/teachers/TeachersScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	private void handleStudents() throws Exception {
    	Pane scene = FXMLLoader.load(getClass().getResource("/view/admin/management/students/StudentsScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	private void handleSubjects() throws Exception {
		Pane scene = FXMLLoader.load(getClass().getResource("/view/admin/management/subjects/SubjectsScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
	@FXML
	private void handleCourses() throws Exception {
		Pane scene = FXMLLoader.load(getClass().getResource("/view/admin/management/courses/CoursesScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
	@FXML
	private void handleRooms() throws Exception {
		Pane scene = FXMLLoader.load(getClass().getResource("/view/admin/management/rooms/RoomsScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
	@FXML
	private void handleSchedules() throws Exception {
		Pane scene = FXMLLoader.load(getClass().getResource("/view/admin/management/schedules/SchedulesScene.fxml"));
    	mainScene.getChildren().setAll(scene);
		
	}
}
