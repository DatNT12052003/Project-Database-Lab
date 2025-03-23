package controller.admin.management.schedules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

import hash_password.HashPassword;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.room.RoomDAO;
import model.schedule.ScheduleDAO;
import model.user.UserDAO;

public class AddSchedulePopUpController {
	@FXML
	private ComboBox<String> dayCB;
	
	@FXML
	private ComboBox<String> timeStartHourCB;
	
	@FXML
	private ComboBox<String> timeStartMinuteCB;
	
	@FXML
	private ComboBox<String> timeEndHourCB;
	
	@FXML
	private ComboBox<String> timeEndMinuteCB;
	
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

	@FXML
	private void initialize() {
		dayCB.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
		dayCB.setValue("Monday");
		
		IntStream.range(0, 24)
	    .mapToObj(i -> String.format("%02d", i))
	    .forEach(timeStartHourCB.getItems()::add);
		
		timeStartHourCB.setValue("00");

		IntStream.range(0, 60)
		    .mapToObj(i -> String.format("%02d", i))
		    .forEach(timeStartMinuteCB.getItems()::add);
		
		timeStartMinuteCB.setValue("00");
	
		IntStream.range(0, 24)
		    .mapToObj(i -> String.format("%02d", i))
		    .forEach(timeEndHourCB.getItems()::add);
		
		timeEndHourCB.setValue("00");
	
		IntStream.range(0, 60)
		    .mapToObj(i -> String.format("%02d", i))
		    .forEach(timeEndMinuteCB.getItems()::add);
		
		timeEndMinuteCB.setValue("00");

	}
	
	@FXML
	private void handleOk() {
	    String day = dayCB.getValue();
	    
	    int startHour = Integer.parseInt(timeStartHourCB.getValue());
	    int startMinute = Integer.parseInt(timeStartMinuteCB.getValue());
	    int endHour = Integer.parseInt(timeEndHourCB.getValue());
	    int endMinute = Integer.parseInt(timeEndMinuteCB.getValue());

	    int startTotalMinutes = startHour * 60 + startMinute;
	    int endTotalMinutes = endHour * 60 + endMinute;

	    if (endTotalMinutes <= startTotalMinutes) {
	        showErrorAlert("Invalid Time", "The end time must be later than the start time.");
	        return; 
	    }

	    String timeStart = String.format("%02d:%02d", startHour, startMinute);
	    String timeEnd = String.format("%02d:%02d", endHour, endMinute);

	    scheduleDAO.insertSchedule(day, timeStart, timeEnd);

	    String message = "Day: " + day + "\n"
	            + "Time Start: " + timeStart + "\n"
	            + "Time End: " + timeEnd + "\n";

	    showCompletedAlert("Success", message);

	    ((Stage) okButton.getScene().getWindow()).close();
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
