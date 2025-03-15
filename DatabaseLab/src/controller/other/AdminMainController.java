package controller.other;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminMainController {
	
	@FXML
	private Button logOutButton;
	
	@FXML
	private Button calendarButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private AnchorPane mainView;
	
	@FXML
	private void handleLogOut() {
		openLogInView();
	}
	
    private void openLogInView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/other/LogInView.fxml"));
            Stage stage = (Stage) logOutButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Log In View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể mở giao diện đăng nhập!");
        }
    }
    @FXML
    private void handleOpenCalendar() throws IOException {
    	Pane scene1 = FXMLLoader.load(getClass().getResource("/view/other/CalendarView.fxml"));
    	mainView.getChildren().setAll(scene1);
    }
    
    @FXML
    private void handleDelete() {
    	mainView.getChildren().clear();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
