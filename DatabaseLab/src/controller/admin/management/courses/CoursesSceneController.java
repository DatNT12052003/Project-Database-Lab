package controller.admin.management.courses;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import connection_database.DatabaseConnection;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import model.course.Course;
import model.course.CourseDAO;
import model.person.Student;
import model.person.Teacher;
import model.person.TeacherDAO;
import model.user.User;
import model.user.UserDAO;

public class CoursesSceneController {
	@FXML
	private TextField searchTF;
	
	@FXML
	private ComboBox<String> typeSearchCB;
	
	@FXML
	private TableView<Course> coursesTable;
	
	@FXML
	private TableColumn<Course, String> courseidColumn;
	
	@FXML
	private TableColumn<Course, String> subjectNameColumn;
	
	@FXML
	private TableColumn<Course, String> teacherColumn;
	
	@FXML
	private TableColumn<Course, String> roomColumn;
	
	@FXML
	private TableColumn<Course, Integer> maxStudentsColumn;
	
	@FXML
	private TableColumn<Course, Integer> currentStudentsColumn;
	
	@FXML
	private TableColumn<Course, String> courseStartDateColumn;
	
	@FXML
	private TableColumn<Course, String> statusColumn;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private Button detailButton;
	
	
	private ObservableList<Course> courseList = FXCollections.observableArrayList();
	
	private TeacherDAO teacherDAO;
	
	private CourseDAO courseDAO;
	
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
		courseDAO = new CourseDAO();
		courseList = courseDAO.getAllCourses();
		
		courseidColumn.setCellValueFactory(new PropertyValueFactory<>("courseid"));
		courseidColumn.setStyle("-fx-alignment: CENTER;");
		subjectNameColumn.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getSubject().getName()));
		subjectNameColumn.setStyle("-fx-alignment: CENTER;");
		teacherColumn.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getTeacher().getFullName()));
		teacherColumn.setStyle("-fx-alignment: CENTER;");
		
		roomColumn.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getRoom().getAddress()));
		roomColumn.setStyle("-fx-alignment: CENTER;");
		
		maxStudentsColumn.setCellValueFactory(cellData -> 
	    new SimpleIntegerProperty(cellData.getValue().getRoom().getMaxStudents()).asObject());
		maxStudentsColumn.setStyle("-fx-alignment: CENTER;");
//		currentStudentsColumn.setCellValueFactory(cellData -> 
//	    new SimpleIntegerProperty(cellData.getValue().getRoom().getMaxStudents()).asObject());
//		currentStudentsColumn.setStyle("-fx-alignment: CENTER;");
		currentStudentsColumn.setCellValueFactory(new PropertyValueFactory<>("currentStudents"));
		currentStudentsColumn.setStyle("-fx-alignment: CENTER;");
		courseStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("courseStartDate"));
		courseStartDateColumn.setStyle("-fx-alignment: CENTER;");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		statusColumn.setStyle("-fx-alignment: CENTER;");
		
		coursesTable.setItems(courseList);
		
		typeSearchCB.getItems().addAll("Course ID", "Subject Name", "Teacher", "Course Start Date");
		typeSearchCB.setValue("Course ID");
		
		searchTF.textProperty().addListener((observable, oldValue, newValue) -> filterCourses(newValue));
		
	}
	
	@FXML
	private void handleAddCourse() {
		  try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/courses/AddCoursePopUp.fxml"));
		        Parent root = loader.load();

		        AddCoursePopUpController popUpController = loader.getController();

		        Stage stage = new Stage();
		        stage.setTitle("Add Teacher");
		        stage.setScene(new Scene(root));
		        stage.setResizable(false);
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.showAndWait();
		        
		        popUpController.setCoursesSceneController(this);
		        
		        refreshCourseList();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
	@FXML
	private void handleUpdateCourse() {
	    Course selectedCourse = coursesTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedCourse == null) {
	    	showErrorAlert("Error", "Please select a teacher to update!");
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/courses/UpdateCoursePopUp.fxml"));
	        Parent root = loader.load();

	        UpdateCoursePopUpController popUpController = loader.getController();
	        popUpController.setCourse(selectedCourse); // Truyền dữ liệu user sang cửa sổ cập nhật
	        popUpController.setCoursesSceneController(this); // Thiết lập lại controller chính

	        Stage stage = new Stage();
	        stage.setTitle("Update Course");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	        refreshCourseList(); // Làm mới danh sách sau khi cập nhật

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
//	
//	@FXML
//	private void handleDelete() {
//	    Teacher selectedTeacher = teachersTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn
//
//	    if (selectedTeacher == null) {
//	    	showErrorAlert("Error", "Please select a user to update!");
//	        return;
//	    }else {
//			boolean confirmed = showConfirmation("Confirm", "Are you sure you want to delete?");
//			if (confirmed) {
//			    teacherDAO.deleteTeacher(selectedTeacher.getTeacherid());
//			}
//	    }
//	    
//	    refreshTeacherList(); 
//	}
//
//	
//	@FXML
//	private void handleDetail() {
//	    Teacher selectedTeacher = teachersTable.getSelectionModel().getSelectedItem();
//	    User userJoinTeacher = new User();
//	    
//	    if (selectedTeacher == null) {
//	        showErrorAlert("Error", "Please select a user to update!");
//	        return;
//	    }else {
//	    	userJoinTeacher =  teacherDAO.teachersJoinUsers(selectedTeacher.getTeacherid());
//	    }
//
//	    try {
//	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/teachers/DetailTeacherPopUp.fxml"));
//	        Parent root = loader.load();
//
//	        DetailTeacherPopUpController popUpController = loader.getController();
//
//	        popUpController.setData(selectedTeacher, userJoinTeacher);
//	        popUpController.setTeachersSceneController(this); 
//
//	        Stage stage = new Stage();
//	        stage.setTitle("Detail Teacher");
//	        stage.setScene(new Scene(root));
//	        stage.setResizable(false);
//	        stage.initModality(Modality.APPLICATION_MODAL);
//	        stage.showAndWait();
//
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}

	private void filterCourses(String searchText) {
	    if (searchText == null || searchText.isEmpty()) {
	        coursesTable.setItems(courseList); // Không lọc nếu ô tìm kiếm trống
	        return;
	    }

	    String selectedType = typeSearchCB.getValue(); // Lấy giá trị của ComboBox

	    ObservableList<Course> filteredList = FXCollections.observableArrayList();

	    for (Course course : courseList) {
	        if (selectedType.equals("Course ID") && course.getCourseid().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(course);
	        } else if (selectedType.equals("Subject Name") && course.getSubject().getName().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(course);
	        } else if (selectedType.equals("Teacher") && course.getTeacher().getFullName().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(course);
	        } else if (selectedType.equals("Course Start Date") && course.getCourseStartDate().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(course);
	        }
	    }

	    coursesTable.setItems(filteredList);
	}

	public void refreshCourseList() {
		coursesTable.setItems(FXCollections.observableArrayList(courseDAO.getAllCourses()));
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
