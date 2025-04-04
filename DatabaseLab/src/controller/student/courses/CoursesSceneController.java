package controller.student.courses;

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
	private Button studyingButton;
	
	@FXML
	private Button registeredButton;
	
	@FXML
	private Button completedButton;
	
	@FXML
	private Button regisButton;
	
	@FXML
	private AnchorPane mainScene;
	
	
	@FXML
	public void handleAll() {
		
	}
	
	@FXML
	public void handleStudying() {
		
	}
	
	@FXML
	public void handleRegistered() {
		
	}
	
	@FXML
	public void handleCompleted() {
		
	}
	
	@FXML
	public void handleRegis() throws IOException {
		Pane scene = FXMLLoader.load(getClass().getResource("/view/student/courses/all/AllScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
}
