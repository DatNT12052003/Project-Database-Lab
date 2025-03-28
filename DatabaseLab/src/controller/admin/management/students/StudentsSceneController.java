package controller.admin.management.students;

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
import model.person.StudentDAO;
import model.person.Teacher;
import model.person.TeacherDAO;
import model.user.User;
import model.user.UserDAO;

public class StudentsSceneController {
	@FXML
	private TextField searchTF;
	
	@FXML
	private ComboBox<String> typeSearchCB;
	
	@FXML
	private TableView<Student> studentsTable;
	
	@FXML
	private TableColumn<Student, String> studentidColumn;
	
	@FXML
	private TableColumn<Student, String> fullNameColumn;
	
	@FXML
	private TableColumn<Student, String> dateOfBirthColumn;
	
	@FXML
	private TableColumn<Student, String> genderColumn;
	
	@FXML
	private TableColumn<Student, String> addressColumn;
	
	@FXML
	private TableColumn<Student, String> phoneColumn;
	
	@FXML
	private TableColumn<Student, String> emailColumn;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private Button detailButton;
	
	
	private ObservableList<Student> studentList = FXCollections.observableArrayList();
	
	private StudentDAO studentDAO;
	
//	private TeacherDAO teacherDAO;
	
//	private UserDAO userDAO;
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
		studentDAO = new StudentDAO();
		studentList = studentDAO.getAllStudents();
		
		studentidColumn.setCellValueFactory(new PropertyValueFactory<>("studentid"));
		studentidColumn.setStyle("-fx-alignment: CENTER;");
		fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		fullNameColumn.setStyle("-fx-alignment: CENTER;");
		dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		dateOfBirthColumn.setStyle("-fx-alignment: CENTER;");
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
		genderColumn.setStyle("-fx-alignment: CENTER;");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		addressColumn.setStyle("-fx-alignment: CENTER;");
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		phoneColumn.setStyle("-fx-alignment: CENTER;");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailColumn.setStyle("-fx-alignment: CENTER;");
		
		studentsTable.setItems(studentList);
		
		typeSearchCB.getItems().addAll("Student ID", "Full Name", "Address", "Phone", "Email");
		typeSearchCB.setValue("Student ID");
		
		searchTF.textProperty().addListener((observable, oldValue, newValue) -> filterStudents(newValue));
		
	}
	
	@FXML
	private void handleAddStudent() {
		  try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/students/AddStudentPopUp.fxml"));
		        Parent root = loader.load();

		        AddStudentPopUpController popUpController = loader.getController();

		        Stage stage = new Stage();
		        stage.setTitle("Add Student");
		        stage.setScene(new Scene(root));
		        stage.setResizable(false);
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.showAndWait();
		        
		        popUpController.setStudentsSceneController(this);
		        
		        refreshStudentList();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
	@FXML
	private void handleUpdateStudent() {
	    Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedStudent == null) {
	    	showErrorAlert("Error", "Please select a student to update!");
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/students/UpdateStudentPopUp.fxml"));
	        Parent root = loader.load();

	        UpdateStudentPopUpController popUpController = loader.getController();
	        popUpController.setStudent(selectedStudent); // Truyền dữ liệu user sang cửa sổ cập nhật
	        popUpController.setStudentsSceneController(this); // Thiết lập lại controller chính

	        Stage stage = new Stage();
	        stage.setTitle("Update Student");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	        refreshStudentList(); // Làm mới danh sách sau khi cập nhật

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void handleDelete() {
	    Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedStudent == null) {
	    	showErrorAlert("Error", "Please select a user to delete!");
	        return;
	    }else {
			boolean confirmed = showConfirmation("Confirm", "Are you sure you want to delete?");
			if (confirmed) {
			    studentDAO.deleteStudent(selectedStudent.getStudentid());
			}
	    }
	    
	    refreshStudentList(); 
	}

	
	@FXML
	private void handleDetail() {
	    Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem();
	    User userJoinStudent = new User();
	    
	    if (selectedStudent == null) {
	        showErrorAlert("Error", "Please select a student to update!");
	        return;
	    } else {
	    	userJoinStudent = studentDAO.studentsJoinUsers(selectedStudent.getStudentid());
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/students/DetailStudentPopUp.fxml"));
	        Parent root = loader.load();

	        DetailStudentPopUpController popUpController = loader.getController();

	        popUpController.setData(selectedStudent,  userJoinStudent);
	        popUpController.setStudentsSceneController(this);

	        Stage stage = new Stage();
	        stage.setTitle("Detail Teacher");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void filterStudents(String searchText) {
	    if (searchText == null || searchText.isEmpty()) {
	        studentsTable.setItems(studentList); // Không lọc nếu ô tìm kiếm trống
	        return;
	    }

	    String selectedType = typeSearchCB.getValue(); // Lấy giá trị của ComboBox

	    ObservableList<Student> filteredList = FXCollections.observableArrayList();

	    for (Student student : studentList) {
	        if (selectedType.equals("Student ID") && student.getStudentid().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(student);
	        } else if (selectedType.equals("Full Name") && student.getFullName().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(student);
	        } else if (selectedType.equals("Phone") && student.getPhone().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(student);
	        } else if (selectedType.equals("Address") && student.getAddress().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(student);
	        } else if (selectedType.equals("Email") && student.getEmail().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(student);
	        }
	    }

	    studentsTable.setItems(filteredList);
	}

	public void refreshStudentList() {
		studentsTable.setItems(FXCollections.observableArrayList(studentDAO.getAllStudents()));
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
