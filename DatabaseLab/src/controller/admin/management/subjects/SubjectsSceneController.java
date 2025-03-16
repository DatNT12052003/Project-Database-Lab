package controller.admin.management.subjects;

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
import model.subject.Subject;
import model.subject.SubjectDAO;
import model.user.User;
import model.user.UserDAO;

public class SubjectsSceneController {
	@FXML
	private TextField searchTF;
	
	@FXML
	private ComboBox<String> typeSearchCB;
	
	@FXML
	private TableView<Subject> subjectsTable;
	
	@FXML
	private TableColumn<Subject, String> subjectidColumn;
	
	@FXML
	private TableColumn<Subject, String> nameColumn;
	
	@FXML
	private TableColumn<Subject, Integer> massColumn;
	
	@FXML
	private TableColumn<Subject, Integer> tuitionColumn;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private Button detailButton;
	
	
	private ObservableList<Subject> subjectList = FXCollections.observableArrayList();
	
	private SubjectDAO subjectDAO;
	
//	private TeacherDAO teacherDAO;
	
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
		subjectDAO = new SubjectDAO();
		subjectList = subjectDAO.getAllSubjects();
		
		subjectidColumn.setCellValueFactory(new PropertyValueFactory<>("subjectid"));
		subjectidColumn.setStyle("-fx-alignment: CENTER;");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setStyle("-fx-alignment: CENTER;");
		massColumn.setCellValueFactory(new PropertyValueFactory<>("mass"));
		massColumn.setStyle("-fx-alignment: CENTER;");
		tuitionColumn.setCellValueFactory(new PropertyValueFactory<>("tuition"));
		tuitionColumn.setStyle("-fx-alignment: CENTER;");
		
		subjectsTable.setItems(subjectList);
		
		typeSearchCB.getItems().addAll("Subject ID", "Subject Name");
		typeSearchCB.setValue("Subject ID");
		
		searchTF.textProperty().addListener((observable, oldValue, newValue) -> filterSubjects(newValue));
		
	}
	
	@FXML
	private void handleAddSubject() {
		  try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/subjects/AddSubjectPopUp.fxml"));
		        Parent root = loader.load();

		        AddSubjectPopUpController popUpController = loader.getController();

		        Stage stage = new Stage();
		        stage.setTitle("Add Subject");
		        stage.setScene(new Scene(root));
		        stage.setResizable(false);
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.showAndWait();
		        
		        popUpController.setSubjectsSceneController(this);
		        
		        refreshSubjectList();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
	@FXML
	private void handleUpdateSubject() {
	    Subject selectedSubject = subjectsTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedSubject == null) {
	    	showErrorAlert("Error", "Please select a student to update!");
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/subjects/UpdateSubjectPopUp.fxml"));
	        Parent root = loader.load();

	        UpdateSubjectPopUpController popUpController = loader.getController();
	        popUpController.setSubject(selectedSubject); // Truyền dữ liệu user sang cửa sổ cập nhật
	        popUpController.setSubjectsSceneController(this); // Thiết lập lại controller chính

	        Stage stage = new Stage();
	        stage.setTitle("Update Student");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	        refreshSubjectList(); // Làm mới danh sách sau khi cập nhật

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void handleDelete() {
		Subject selectedSubject = subjectsTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedSubject == null) {
	    	showErrorAlert("Error", "Please select a user to delete!");
	        return;
	    }else {
			boolean confirmed = showConfirmation("Confirm", "Are you sure you want to delete?");
			if (confirmed) {
			    subjectDAO.deleteSubject(selectedSubject.getSubjectid());
			}
	    }
	    
	    refreshSubjectList(); 
	}

	
	@FXML
	private void handleDetail() {
	    Subject selectedSubject = subjectsTable.getSelectionModel().getSelectedItem();
	    
	    if (selectedSubject == null) {
	        showErrorAlert("Error", "Please select a subject to detail!");
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/subjects/DetailSubjectPopUp.fxml"));
	        Parent root = loader.load();

	        DetailSubjectPopUpController popUpController = loader.getController();

	        popUpController.setData(selectedSubject);
	        popUpController.setSubjectsSceneController(this);

	        Stage stage = new Stage();
	        stage.setTitle("Detail Subject");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void filterSubjects(String searchText) {
	    if (searchText == null || searchText.isEmpty()) {
	        subjectsTable.setItems(subjectList); // Không lọc nếu ô tìm kiếm trống
	        return;
	    }

	    String selectedType = typeSearchCB.getValue(); // Lấy giá trị của ComboBox

	    ObservableList<Subject> filteredList = FXCollections.observableArrayList();

	    for (Subject subject : subjectList) {
	        if (selectedType.equals("Subject ID") && subject.getSubjectid().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(subject);
	        } else if (selectedType.equals("Subject Name") && subject.getName().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(subject);
	        }
	    }

	    subjectsTable.setItems(filteredList);
	}

	public void refreshSubjectList() {
		subjectsTable.setItems(FXCollections.observableArrayList(subjectDAO.getAllSubjects()));
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
