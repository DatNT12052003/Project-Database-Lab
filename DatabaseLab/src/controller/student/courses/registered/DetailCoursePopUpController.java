package controller.student.courses.registered;

import java.util.Optional;

import controller.admin.management.schedules.SchedulesSceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.course.Course;
import model.course.CourseDAO;
import model.person.Student;
import model.person.StudentDAO;
import model.person.TeacherDAO;
import model.room.RoomDAO;
import model.schedule.Schedule;
import model.schedule.ScheduleDAO;
import model.study.StudyDAO;
import model.subject.SubjectDAO;
import model.user_login.UserLogin;

public class DetailCoursePopUpController {
	
	private Course courseItem;
	
	@FXML
	private Text courseidText;
	
	@FXML
	private Text subjectNameText;
	
	@FXML
	private Text massText;
	
	@FXML
	private Text tuitionText;
	
	@FXML
	private Text teacherNameText;
	
	@FXML
	private Text levelText;
	
	@FXML
	private Text phoneText;
	
	@FXML
	private Text emailText;
	
	@FXML
	private Text teacherAddressText;
	
	@FXML
	private Text currentStudentsText;
	
	@FXML
	private Text maxStudentsText;
	
	@FXML
	private Text startDateText;
	
	@FXML
	private Text regisDateText;
	
	@FXML
	private Text tuitionPaymentText;
	
	@FXML
	private Text roomAddressText;
	
	@FXML
	private Text typeText;
	
	
	@FXML
	private TableView<Schedule> schedulesTable;
	
	@FXML
	private TableColumn<Schedule, String> dayColumn;
	
	@FXML
	private TableColumn<Schedule, String> timeStartColumn;
	
	@FXML
	private TableColumn<Schedule, String> timeEndColumn;
	
	
	@FXML
	private TableView<Student> studentsRegistedTable;
	
	@FXML
	private TableColumn<Schedule, String> fullNameColumn;
	
	@FXML
	private TableColumn<Schedule, String> addressColumn;
	
	@FXML
	private Button registerButton;
	
	private CourseDAO courseDAO = new CourseDAO();
	
	private TeacherDAO teacherDAO = new TeacherDAO();
	
	private SubjectDAO subjectDAO = new SubjectDAO();
	
	private RoomDAO roomDAO = new RoomDAO();
	
	private ScheduleDAO scheduleDAO = new ScheduleDAO();
	
	private StudyDAO studyDAO = new StudyDAO();
	
	private StudentDAO studentDAO = new StudentDAO();
	
	private ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
	
	private ObservableList<Student> studentList = FXCollections.observableArrayList();
	
    private CourseItemController courseItemController;

    public void setCourseItemController(CourseItemController courseItemController) {
        this.courseItemController = courseItemController;
    }
	
	public CourseItemController getCourseItemController() {
		return courseItemController;
	}
	
	
	public void setData(Course courseItem) {
		this.courseItem = courseItem;
		
		courseidText.setText(courseItem.getCourseid());
		currentStudentsText.setText(String.valueOf(courseItem.getCurrentStudents()));
		maxStudentsText.setText(String.valueOf(roomDAO.getRoomById(courseItem.getRoom().getRoomid()).getMaxStudents()));
		startDateText.setText(courseItem.getCourseStartDate());
		regisDateText.setText(studyDAO.getStudyByStudentidAndCourseid(UserLogin.getUserid(), courseItem.getCourseid()).getRegistrationDate());
		tuitionPaymentText.setText(studyDAO.getStudyByStudentidAndCourseid(UserLogin.getUserid(), courseItem.getCourseid()).getTuitionPayment());
		
		subjectNameText.setText(subjectDAO.getSubjectById(courseItem.getSubject().getSubjectid()).getName());
		massText.setText(String.valueOf(subjectDAO.getSubjectById(courseItem.getSubject().getSubjectid()).getMass()));
		tuitionText.setText(String.valueOf(subjectDAO.getSubjectById(courseItem.getSubject().getSubjectid()).getTuition()));
		
		teacherNameText.setText(teacherDAO.getTeacherById(courseItem.getTeacher().getTeacherid()).getFullName());
		levelText.setText(teacherDAO.getTeacherById(courseItem.getTeacher().getTeacherid()).getLevel());
		phoneText.setText(teacherDAO.getTeacherById(courseItem.getTeacher().getTeacherid()).getPhone());
		emailText.setText(teacherDAO.getTeacherById(courseItem.getTeacher().getTeacherid()).getEmail());
		teacherAddressText.setText(teacherDAO.getTeacherById(courseItem.getTeacher().getTeacherid()).getAddress());
		
		roomAddressText.setText(roomDAO.getRoomById(courseItem.getRoom().getRoomid()).getAddress());
		typeText.setText(roomDAO.getRoomById(courseItem.getRoom().getRoomid()).getType());
		
		scheduleList = scheduleDAO.getScheduleByCourseid(courseItem.getCourseid());
		studentList = studentDAO.getAllStudentByCourseid(courseItem.getCourseid());
		
		dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
		dayColumn.setStyle("-fx-alignment: CENTER;");
		timeStartColumn.setCellValueFactory(new PropertyValueFactory<>("timeStart"));
		timeStartColumn.setStyle("-fx-alignment: CENTER;");
		timeEndColumn.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));
		timeEndColumn.setStyle("-fx-alignment: CENTER;");
		
		schedulesTable.setItems(scheduleList);
		
		fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		fullNameColumn.setStyle("-fx-alignment: CENTER;");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		addressColumn.setStyle("-fx-alignment: CENTER;");
		
		studentsRegistedTable.setItems(studentList);
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleRegister() {
//	    boolean confirmed = showConfirmation("Confirm", "Are you sure you want to register?");
//	    if (confirmed) {
//	        if (studyDAO.checkStudentRegisterdCourse(UserLogin.getUserid(), courseItem.getCourseid())) {
//	            studyDAO.insertStudy(UserLogin.getUserid(), courseItem.getCourseid());
//	            showCompletedAlert("Complete", "Register course completed!");
//	            
//	            if (courseItemController != null) {
//	                courseItemController.setData(courseDAO.getCourseById(courseItem.getCourseid()));
//	            }
//	            
//	            ((Stage) registerButton.getScene().getWindow()).close();
//	        } else {
//	            showErrorAlert("Error", "You already registered for this course!");
//	            ((Stage) registerButton.getScene().getWindow()).close();
//	        }
//	    }
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
