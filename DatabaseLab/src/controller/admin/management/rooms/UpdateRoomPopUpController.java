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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.room.Room;
import model.room.RoomDAO;
import model.user.User;
import model.user.UserDAO;

public class UpdateRoomPopUpController {
	
    private RoomsSceneController roomsSceneController;

    public void setRoomsSceneController(RoomsSceneController roomsSceneController) {
        this.roomsSceneController = roomsSceneController;
    }
	
	public RoomsSceneController getRoomsSceneController() {
		return roomsSceneController;
	}
	
	@FXML
	private TextField addressTF;
	
	@FXML
	private ComboBox<String> typeCB;
	
	@FXML
	private ComboBox<String> statusCB;
	
	@FXML
	private Button okButton;
	
	private RoomDAO roomDAO = new RoomDAO();
	
	private Room room;
	
	public void setRoom(Room room) {
		this.room = room;
		addressTF.setText(room.getAddress());
		if(room.getType().equals("offline")) {
			typeCB.setValue("Offline");
		}else {
			typeCB.setValue("Online");
		}
		if(room.getStatus().equals("empty")) {
			statusCB.setValue("Empty");
		}else {
			statusCB.setValue("Using");
		}
	}
	
	@FXML
	private void initialize() {
		typeCB.getItems().addAll("Offline", "Online");
		statusCB.getItems().addAll("Empty", "Using");
	}
	
	@FXML
	private void handleOk() {
		String address = addressTF.getText();
		String type = typeCB.getValue().toLowerCase();
		String status = statusCB.getValue().toLowerCase();
		
		String roomid = "";
		
		if(type.equals(room.getType())) {
			roomid = room.getRoomid();
		} else {
			if(type.equals("offline")) {
				roomid = roomDAO.generateRoomidOff();
			} else {
				roomid = roomDAO.generateRoomidOnl();
			}
		}
		
		if(address.isEmpty()) {
			showErrorAlert("Error", "Not enough information has been entered!");
		} else {
			roomDAO.updateRoom(roomid, address, type, status);
			String message = "Roomid: " + roomid + "\n"
					+ "Address: " + address + "\n"
					+ "Type: " + type + "\n"
                    + "Status: " + status + "\n";
			showCompletedAlert("Success", message);
			
	        ((Stage) okButton.getScene().getWindow()).close();
		}
	}
	
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showCompletedAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Account Updated Successfully!");
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}
