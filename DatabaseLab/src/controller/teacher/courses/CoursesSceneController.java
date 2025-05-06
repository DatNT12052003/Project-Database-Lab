package controller.teacher.courses;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CoursesSceneController {
	
	@FXML
	private Button allButton;
	
	@FXML
	private Button teachingButton;
	
	@FXML
	private Button registeredButton;
	
	@FXML
	private Button completedButton;
	
	@FXML
	private Button regisButton;
	
	@FXML
	private Button canceledButton;
	
	@FXML
	private AnchorPane mainScene;
	
	
	@FXML
	private void initialize() throws IOException {
//		Pane scene = FXMLLoader.load(getClass().getResource("/view/student/courses/all/AllScene.fxml"));
//    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	public void handleAll() throws IOException {
//		Pane scene = FXMLLoader.load(getClass().getResource("/view/student/courses/all/AllScene.fxml"));
//    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	public void handleTeaching() throws IOException {
		Pane scene = FXMLLoader.load(getClass().getResource("/view/teacher/courses/teaching/TeachingScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	public void handleRegistered() throws IOException {
//		Pane scene = FXMLLoader.load(getClass().getResource("/view/student/courses/registered/RegisteredScene.fxml"));
//    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	public void handleCompleted() {
		
	}
	
	@FXML
	public void handleRegis() throws IOException {
//		Pane scene = FXMLLoader.load(getClass().getResource("/view/student/courses/registration/RegistrationCourseScene.fxml"));
//    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	public void handleCanceled() throws IOException {
//		Pane scene = FXMLLoader.load(getClass().getResource("/view/student/courses/registration/RegistrationCourseScene.fxml"));
//    	mainScene.getChildren().setAll(scene);
	}
}
