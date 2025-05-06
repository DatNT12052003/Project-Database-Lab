package controller.student.courses.all;

import java.io.IOException;

import controller.admin.management.schedules.AddSchedulePopUpController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.course.Course;
import model.course.CourseDAO;
import model.person.TeacherDAO;
import model.room.RoomDAO;
import model.study.StudyDAO;
import model.subject.SubjectDAO;
import model.user_login.UserLogin;

public class CourseItemController {
	
	private Course courseItem;
	
	@FXML
	private Text courseidText;
	
	@FXML
	private Text subjectNameText;
	
	@FXML
	private Text teacherNameText;
	
	@FXML
	private Text currentStudentsText;
	
	@FXML
	private Text maxStudentsText;
	
	@FXML
	private Text courseStartDateText;
	
	@FXML
	private Text statusText;
	
	@FXML
	private Button detailButton;
	
	private CourseDAO courseDAO = new CourseDAO();
	
	private TeacherDAO teacherDAO = new TeacherDAO();
	
	private SubjectDAO subjectDAO = new SubjectDAO();
	
	private RoomDAO roomDAO = new RoomDAO();
	
	private StudyDAO studyDAO = new StudyDAO();
	
	
	public void setData(Course courseItem) {
		this.courseItem = courseItem;
		
		courseidText.setText(courseItem.getCourseid());
		subjectNameText.setText(subjectDAO.getSubjectById(courseItem.getSubject().getSubjectid()).getName());
		teacherNameText.setText(teacherDAO.getTeacherById(courseItem.getTeacher().getTeacherid()).getFullName());
		currentStudentsText.setText(String.valueOf(courseItem.getCurrentStudents()));
		maxStudentsText.setText(String.valueOf(roomDAO.getRoomById(courseItem.getRoom().getRoomid()).getMaxStudents()));
		courseStartDateText.setText(courseItem.getCourseStartDate());
		statusText.setText(studyDAO.getStatusByStudentidAndCourseid(UserLogin.getUserid(), courseItem.getCourseid()));
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleDetail() {
		
//		  try {
//		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/student/courses/registration/DetailCoursePopUp.fxml"));
//		        Parent root = loader.load();
//
//		        DetailCoursePopUpController popUpController = loader.getController();
//		        
//		        
//		        popUpController.setData(courseItem);
//		        popUpController.setCourseItemController(this);
//
//		        Stage stage = new Stage();
//		        stage.setTitle("Detail Course");
//		        stage.setScene(new Scene(root));
//		        stage.setResizable(false);
//		        stage.initModality(Modality.APPLICATION_MODAL);
//		        stage.showAndWait();
//
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
		
	}
}
