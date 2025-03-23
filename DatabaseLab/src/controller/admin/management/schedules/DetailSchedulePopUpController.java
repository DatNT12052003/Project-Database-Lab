package controller.admin.management.schedules;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.person.Teacher;
import model.room.Room;
import model.room.RoomDAO;
import model.schedule.Schedule;
import model.schedule.ScheduleDAO;

public class DetailSchedulePopUpController {
	
	@FXML
	private Text scheduleidText;
	
	@FXML
	private Text dayText;

	@FXML
	private Text timeStartText;

	@FXML
	private Text timeEndText;
	
	@FXML
	private Button okButton;
	
	private ScheduleDAO scheduleDAO = new ScheduleDAO();
	
    private SchedulesSceneController schedulesSceneController;

    public void setSchedulesSceneController(SchedulesSceneController schedulesSceneController) {
        this.schedulesSceneController = schedulesSceneController;
    }
	
	public SchedulesSceneController getSchedulesSceneController() {
		return schedulesSceneController;
	}
	
	public void setData(Schedule schedule) {
		
		scheduleidText.setText(schedule.getScheduleid());
		dayText.setText(schedule.getDay());
		timeStartText.setText(schedule.getTimeStart());
		timeEndText.setText(schedule.getTimeEnd());
	}
	
	@FXML
	private void handleOk() {
		 ((Stage) okButton.getScene().getWindow()).close();
	}

}
