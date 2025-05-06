package controller.admin.management.courses;

import java.time.LocalDate;

import hash_password.HashPassword;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.schedule.Schedule;
import model.schedule.ScheduleDAO;
import model.subject.Subject;
import model.subject.SubjectDAO;
import model.course.Course;
import model.course.CourseDAO;
import model.course_schedule.CourseScheduleDAO;
import model.person.Teacher;
import model.person.TeacherDAO;
import model.room.Room;
import model.room.RoomDAO;
import model.user.UserDAO;

public class UpdateCoursePopUpController {
	
	@FXML
	private Text subjectNameText;
	
	@FXML
	private ComboBox<Teacher> teacherCB;
	
	@FXML
	private ComboBox<Room> roomCB;
	
	@FXML
	private DatePicker regisStartCourseDateDP;
	
	@FXML
	private DatePicker courseStartDateDP;
	
	@FXML
	private ComboBox<String> statusCB;
	
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
	private Button okButton;
	
	private CourseDAO courseDAO = new CourseDAO();
	
	private CourseScheduleDAO courseScheduleDAO = new CourseScheduleDAO();
	
    private CoursesSceneController coursesSceneController;

    public void setCoursesSceneController(CoursesSceneController coursesSceneController) {
        this.coursesSceneController = coursesSceneController;
    }
	
	public CoursesSceneController getCoursesSceneController() {
		return coursesSceneController;
	}
	
	private TeacherDAO teacherDAO;
	private ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
	private RoomDAO roomDAO;
	private ObservableList<Room> roomList = FXCollections.observableArrayList();
	private ScheduleDAO scheduleDAO;
	private ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
	
	private Course course;
	
	private ObservableList<Schedule> scheduleSelectedList = FXCollections.observableArrayList();
	
	public void setCourse(Course course) {
		this.course = course;
		
		subjectNameText.setText(course.getSubject().getName());
		teacherCB.setValue(course.getTeacher());
		roomCB.setValue(course.getRoom());
		
		String dateString = course.getRegisStartDate();
		LocalDate date = LocalDate.parse(dateString);
		regisStartCourseDateDP.setValue(date);
		
		dateString = course.getCourseStartDate();
		date = LocalDate.parse(dateString);
		courseStartDateDP.setValue(date);
		
		statusCB.setValue(course.getStatus());
		
		scheduleList = scheduleDAO.getScheduleByCourseid(course.getCourseid());
		
		scheduleidColumn.setCellValueFactory(new PropertyValueFactory<>("scheduleid"));
		scheduleidColumn.setStyle("-fx-alignment: CENTER;");
		dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
		dayColumn.setStyle("-fx-alignment: CENTER;");
		timeStartColumn.setCellValueFactory(new PropertyValueFactory<>("timeStart"));
		timeStartColumn.setStyle("-fx-alignment: CENTER;");
		timeEndColumn.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));
		timeEndColumn.setStyle("-fx-alignment: CENTER;");
		
		schedulesTable.setItems(scheduleList);
		
		ObservableList<Teacher> allTeacherList = FXCollections.observableArrayList();
		allTeacherList = teacherDAO.getTeachersBySubject(subjectName(course.getSubject().getName()));
		
		for(Schedule schedule : scheduleList) {
			TeacherDAO teacherDAO = new TeacherDAO();
			
			ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
			teacherList = teacherDAO.getTeachersByScheduleid(schedule.getScheduleid());
			
			allTeacherList.removeAll(teacherList);
		}
		
		allTeacherList.addFirst(course.getTeacher());
		teacherCB.setItems(allTeacherList);
		
		ObservableList<Room> allRoomList = FXCollections.observableArrayList();
		allRoomList = roomDAO.getAllRooms();
		
		for(Schedule schedule : scheduleList) {
			RoomDAO roomDAO = new RoomDAO();
			
			ObservableList<Room> roomList = FXCollections.observableArrayList();
			roomList = roomDAO.getRoomsByScheduleid(schedule.getScheduleid());
			
			allRoomList.removeAll(roomList);
		}
		
		allRoomList.addFirst(course.getRoom());
		roomCB.setItems(allRoomList);
	}

	@FXML
	private void initialize() {
		teacherDAO = new TeacherDAO();
		roomDAO = new RoomDAO();
		scheduleDAO = new ScheduleDAO();
		
	    teacherList = teacherDAO.getAllTeachers();
	    roomList = roomDAO.getAllRooms();
	    
	    roomCB.setItems(roomList);
	    statusCB.setItems(FXCollections.observableArrayList("Registration", "Ongoing", "Canceled", "Completed"));
	    
//	    teacherCB.setOnAction(e -> {
//            Teacher selectedTeacher = teacherCB.getValue();
//        });
//	    
//	    roomCB.setOnAction(e -> {
//            Room selectedRoom = roomCB.getValue();
//        });
	    

	    scheduleList = FXCollections.observableArrayList();
		
		scheduleidColumn.setCellValueFactory(new PropertyValueFactory<>("scheduleid"));
		scheduleidColumn.setStyle("-fx-alignment: CENTER;");
		dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
		dayColumn.setStyle("-fx-alignment: CENTER;");
		timeStartColumn.setCellValueFactory(new PropertyValueFactory<>("timeStart"));
		timeStartColumn.setStyle("-fx-alignment: CENTER;");
		timeEndColumn.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));
		timeEndColumn.setStyle("-fx-alignment: CENTER;");
		
		
		schedulesTable.setItems(scheduleList);
	    
	}

	
	@FXML
	private void handleOk() {
		
		String teacherid = null;
		String roomid = null;
		String status = null;
		
		if(teacherCB.getValue()!=null) {
			teacherid = teacherCB.getValue().getTeacherid();
		}
		
		if(roomCB.getValue()!=null) {
			roomid = roomCB.getValue().getRoomid();
		}
		
		if(statusCB.getValue()!=null) {
			status = statusCB.getValue();
		}
		
		String regisStartCourseDate = null;
		if (regisStartCourseDateDP.getValue() != null) {
		    regisStartCourseDate = regisStartCourseDateDP.getValue().toString();
		}

		String courseStartDate = null;
		if (courseStartDateDP.getValue() != null) {
		    courseStartDate = courseStartDateDP.getValue().toString();
		}
		
		String courseid = course.getCourseid();
		
		if(teacherid==null || roomid==null || regisStartCourseDate==null || courseStartDate==null) {
			showErrorAlert("Error", "Not enough information has been entered!");
		}else {
			courseDAO.updateCourse(courseid, teacherid, roomid, regisStartCourseDate, courseStartDate, status);
			String schedule = "";
			for(Schedule s : scheduleList) {
				schedule = schedule + s.toString() + ", ";
			}

			String message = "Course ID: " + courseid + "\n"
								+ "Subject Name: " + subjectNameText.getText() + "\n"
								+ "Teacher: " + teacherCB.getValue() + "\n"
								+ "Room: " + roomCB.getValue() + "\n"
					            + "Status: " + status + "\n"
					            + "Schedule: " + schedule + "\n";
	
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
        alert.setHeaderText("Teacher Created Successfully!");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private String subjectName(String name) {
        String prefix = "";
        
        if(name.contains("English")) {
        	prefix = "English";
        }else if(name.contains("Japanese")) {
        	prefix = "Japanese";
        }else if(name.contains("Chinese")) {
        	prefix = "Chinese";
        }else if(name.contains("Korean")) {
        	prefix = "Korean";
        }else if(name.contains("Russian")) {
        	prefix = "Russian";
        }else if(name.contains("French")) {
        	prefix = "French";
        }else if(name.contains("German")) {
        	prefix = "German";
        }
        
        return prefix;
    }
	
}
