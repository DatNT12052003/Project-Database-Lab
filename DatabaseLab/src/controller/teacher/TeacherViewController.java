package controller.teacher;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TeacherViewController {
	
	@FXML
	private Button homeButton;
	
	@FXML
	private Button coursesButton;
	
	@FXML
	private Button scheduleButton;
	
	@FXML
	private Button logOutButton;
	
	@FXML
	private AnchorPane mainScene;
	
	@FXML
	private void initialize() throws Exception {
		handleOpenHome();
	}
	
	@FXML
	private void handleOpenHome() throws Exception {
    	Pane scene = FXMLLoader.load(getClass().getResource("/view/teacher/home/HomeScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	private void handleOpenCourses() throws Exception {
    	Pane scene = FXMLLoader.load(getClass().getResource("/view/teacher/courses/CoursesScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	private void handleOpenSchedule() throws Exception {
    	Pane scene = FXMLLoader.load(getClass().getResource("/view/admin/schedule/ScheduleScene.fxml"));
    	mainScene.getChildren().setAll(scene);
	}
	
	@FXML
	private void handleLogout() throws Exception {
		boolean comfirm = showConfirmation("Comfirm", "Do you want to exit?");
		if(comfirm) {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/other/LogInView.fxml"));
	        Stage stage = (Stage) logOutButton.getScene().getWindow();
	        stage.setScene(new Scene(loader.load()));
	        stage.setTitle("Log In");
	        stage.show();
		}
	}
	
    public boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.YES;
    }
	
}
