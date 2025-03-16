package controller.admin.management.rooms;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.person.Teacher;
import model.room.Room;
import model.room.RoomDAO;

public class DetailRoomPopUpController {
	
	@FXML
	private Text roomidText;
	
	@FXML
	private Text addressText;

	@FXML
	private Text typeText;

	@FXML
	private Text statusText;
	
	@FXML
	private Button okButton;
	
    private RoomsSceneController roomsSceneController;

    public void setRoomsSceneController(RoomsSceneController roomsSceneController) {
        this.roomsSceneController = roomsSceneController;
    }
	
	public RoomsSceneController getRoomsSceneController() {
		return roomsSceneController;
	}
	
	public void setData(Room room) {
		
		roomidText.setText(room.getRoomid());
		addressText.setText(room.getAddress());
		typeText.setText(room.getType());
		statusText.setText(room.getStatus());
	}
	
	@FXML
	private void handleOk() {
		 ((Stage) okButton.getScene().getWindow()).close();
	}

}
