package controller.admin.management.rooms;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import hash_password.HashPassword;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.room.RoomDAO;
import model.user.UserDAO;

public class AddRoomPopUpController {
	@FXML
	private TextField addressTF;
	
	@FXML
	private ComboBox<String> typeCB;
	
	@FXML
	private TextField maxStudentsTF;
	
	@FXML
	private Button okButton;
	
	private RoomDAO roomDAO = new RoomDAO();
	
    private RoomsSceneController roomsSceneController;

    public void setRoomsSceneController(RoomsSceneController roomsSceneController) {
        this.roomsSceneController = roomsSceneController;
    }
	
	public RoomsSceneController getRoomsSceneController() {
		return roomsSceneController;
	}

	@FXML
	private void initialize() {
		typeCB.getItems().addAll("Offline", "Online");
		typeCB.setValue("Offline");
	}
	
	@FXML
	private void handleOk() {
		String address = addressTF.getText();
		String type = typeCB.getValue();
		
		int maxStudents = 0;
		
		try {
			maxStudents = Integer.parseInt(maxStudentsTF.getText());
		} catch (NumberFormatException e) {
			showErrorAlert("Error", "Salary must integer!");
			return;
		}
		
		if(address.isEmpty() || maxStudents == 0) {
			showErrorAlert("Error", "Not enough information has been entered!");
		} else if(maxStudents<0) {
			 showErrorAlert("Error", "Can max students be negative?");
		}else {
			roomDAO.insertRoom(address, type, maxStudents);
			
			String message = "Address: " + address + "\n"
					+ "Type: " + type + "\n"
					+ "Max Students: " + maxStudents + "\n";
			
			showCompletedAlert("Success", message);
			
	        ((Stage) okButton.getScene().getWindow()).close();
		}
	}
	
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("ERROR");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showCompletedAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Account Created Successfully!");
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}
