package controller.admin.management.schedules;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import connection_database.DatabaseConnection;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.person.Student;
import model.person.Teacher;
import model.person.TeacherDAO;
import model.room.Room;
import model.room.RoomDAO;
import model.schedule.Schedule;
import model.schedule.ScheduleDAO;
import model.user.User;
import model.user.UserDAO;

public class SchedulesSceneController {
	@FXML
	private TextField searchTF;
	
	@FXML
	private ComboBox<String> typeSearchCB;
	
	@FXML
	private TableView<Schedule> schedulesTable;
	
	@FXML
	private TableColumn<Schedule, String> scheduleidColumn;
	
	@FXML
	private TableColumn<Schedule, String> dayColumn;
	
	@FXML
	private TableColumn<Schedule, String> timeStartColumn;
	
	@FXML
	private TableColumn<Schedule, String> timeEndColumn;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private Button detailButton;
	
	
	private ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
	
	private ScheduleDAO scheduleDAO;
	
//	private User user;
//	
//	private TeacherDAO teacherDAO;
//	
//	private Teacher teacher;
//	
//	private StudentDAO studentDAO;
//	
//	private Student student;
	
	@FXML
	private void initialize() {
		scheduleDAO = new ScheduleDAO();
		scheduleList = scheduleDAO.getAllSchedules();
		
		scheduleidColumn.setCellValueFactory(new PropertyValueFactory<>("scheduleid"));
		scheduleidColumn.setStyle("-fx-alignment: CENTER;");
		dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
		dayColumn.setStyle("-fx-alignment: CENTER;");
		timeStartColumn.setCellValueFactory(new PropertyValueFactory<>("timeStart"));
		timeStartColumn.setStyle("-fx-alignment: CENTER;");
		timeEndColumn.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));
		timeEndColumn.setStyle("-fx-alignment: CENTER;");
		
		schedulesTable.setItems(scheduleList);
		
		typeSearchCB.getItems().addAll("Schedule ID", "Day", "Time Start");
		typeSearchCB.setValue("Schedule ID");
		
		searchTF.textProperty().addListener((observable, oldValue, newValue) -> filterSchedules(newValue));
		
	}
	
	@FXML
	private void handleAddSchedule() {
		  try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/schedules/AddSchedulePopUp.fxml"));
		        Parent root = loader.load();

		        AddSchedulePopUpController popUpController = loader.getController();

		        Stage stage = new Stage();
		        stage.setTitle("Add Schedule");
		        stage.setScene(new Scene(root));
		        stage.setResizable(false);
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.showAndWait();
		        
		        popUpController.setSchedulesSceneController(this);
		        
		        refreshScheduleList();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
	@FXML
	private void handleUpdateSchedule() {
	    Schedule selectedSchedule = schedulesTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedSchedule == null) {
	    	showErrorAlert("Error", "Please select a room to update!");
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/schedules/UpdateSchedulePopUp.fxml"));
	        Parent root = loader.load();

	        UpdateSchedulePopUpController popUpController = loader.getController();
	        popUpController.setSchedule(selectedSchedule); // Truyền dữ liệu user sang cửa sổ cập nhật
	        popUpController.setSchedulesSceneController(this); // Thiết lập lại controller chính

	        Stage stage = new Stage();
	        stage.setTitle("Update Schedule");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	        refreshScheduleList(); // Làm mới danh sách sau khi cập nhật

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void handleDelete() {
	    Schedule selectedSchedule = schedulesTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedSchedule == null) {
	    	showErrorAlert("Error", "Please select a user to update!");
	        return;
	    }else {
			boolean confirmed = showConfirmation("Confirm", "Are you sure you want to delete?");
			if (confirmed) {
			    scheduleDAO.updateStatus(selectedSchedule.getScheduleid(), "Deleted");
			}
	    }
	    
	    refreshScheduleList(); 
	}

	
	@FXML
	private void handleDetail() {
	    Schedule selectedSchedule = schedulesTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedSchedule == null) {
	    	showErrorAlert("Error", "Please select a schedule to update!");
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/schedules/DetailSchedulePopUp.fxml"));
	        Parent root = loader.load();

	        DetailSchedulePopUpController popUpController = loader.getController();

	        popUpController.setData(selectedSchedule);
	        popUpController.setSchedulesSceneController(this);

	        Stage stage = new Stage();
	        stage.setTitle("Detail Schedule");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void filterSchedules(String searchText) {
	    if (searchText == null || searchText.isEmpty()) {
	        schedulesTable.setItems(scheduleList); // Không lọc nếu ô tìm kiếm trống
	        return;
	    }

	    String selectedType = typeSearchCB.getValue(); // Lấy giá trị của ComboBox

	    ObservableList<Schedule> filteredList = FXCollections.observableArrayList();

	    for (Schedule schedule : scheduleList) {
	        if (selectedType.equals("Schedule ID") && schedule.getScheduleid().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(schedule);
	        } else if (selectedType.equals("Day") && schedule.getDay().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(schedule);
	        } else if (selectedType.equals("Time Start") && schedule.getTimeStart().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(schedule);
	        } 
	    }

	    schedulesTable.setItems(filteredList);
	}

	public void refreshScheduleList() {
		schedulesTable.setItems(FXCollections.observableArrayList(scheduleDAO.getAllSchedules()));
    }
	
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("ERROR");
        alert.setContentText(message);
        alert.showAndWait();
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
    
    public Student userJoinStudents(String userid) {
        String sql = "SELECT * FROM users INNER JOIN students ON users.UserID = students.StudentID WHERE users.UserID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userid);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Student student = new Student();
                student.setFullName(resultSet.getString("fullName"));
                student.setDateOfBirth(resultSet.getString("dateofbirth"));
                student.setGender(resultSet.getString("gender"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Teacher userJoinTeachers(String userid) {
        String sql = "SELECT * FROM users INNER JOIN teachers ON users.UserID = teachers.TeacherID WHERE users.UserID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userid);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setFullName(resultSet.getString("fullName"));
                teacher.setDateOfBirth(resultSet.getString("dateofbirth"));
                teacher.setGender(resultSet.getString("gender"));
                teacher.setPhone(resultSet.getString("phone"));
                teacher.setEmail(resultSet.getString("email"));
                return teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
