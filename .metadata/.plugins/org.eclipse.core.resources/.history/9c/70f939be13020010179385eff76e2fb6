package controller.admin.management.users;

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
import model.user.User;
import model.user.UserDAO;

public class UsersSceneController {
	@FXML
	private TextField searchTF;
	
	@FXML
	private ComboBox<String> typeSearchCB;
	
	@FXML
	private TableView<User> usersTable;
	
	@FXML
	private TableColumn<User, String> useridColumn;
	
	@FXML
	private TableColumn<User, String> accountColumn;
	
	@FXML
	private TableColumn<User, String> roleColumn;
	
	@FXML
	private TableColumn<User, String> statusColumn;
	
	@FXML
	private TableColumn<User, String> createdDateColumn;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private Button detailButton;
	
	@FXML
	private Button ActiveOrLockedButton;
	
	private ObservableList<User> userList = FXCollections.observableArrayList();
	
	private UserDAO userDAO;
	
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
		userDAO = new UserDAO();
		userList = userDAO.getAllUsers();
		
		useridColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
		useridColumn.setStyle("-fx-alignment: CENTER;");
		accountColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
		accountColumn.setStyle("-fx-alignment: CENTER;");
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
		roleColumn.setStyle("-fx-alignment: CENTER;");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		statusColumn.setStyle("-fx-alignment: CENTER;");
		createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
		createdDateColumn.setStyle("-fx-alignment: CENTER;");
		
		usersTable.setItems(userList);
		
		typeSearchCB.getItems().addAll("User ID", "Account");
		typeSearchCB.setValue("User ID");
		
		searchTF.textProperty().addListener((observable, oldValue, newValue) -> filterUsers(newValue));
		
	}
	
	@FXML
	private void handleAddUser() {
		  try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/users/AddUserPopUp.fxml"));
		        Parent root = loader.load();

		        AddUserPopUpController popUpController = loader.getController();

		        Stage stage = new Stage();
		        stage.setTitle("Add User");
		        stage.setScene(new Scene(root));
		        stage.setResizable(false);
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.showAndWait();
		        
		        popUpController.setUsersSceneController(this);
		        
		        refreshUserList();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
	@FXML
	private void handleUpdateUser() {
	    User selectedUser = usersTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedUser == null) {
	    	showErrorAlert("Error", "Please select a user to update!");
	        return;
	    }
	    
	    if(selectedUser.getAccount().equals("admin")) {
	    	showErrorAlert("Error", "Can not update ADMIN!");
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/users/UpdateUserPopUp.fxml"));
	        Parent root = loader.load();

	        UpdateUserPopUpController popUpController = loader.getController();
	        popUpController.setUser(selectedUser); // Truyền dữ liệu user sang cửa sổ cập nhật
	        popUpController.setUsersSceneController(this); // Thiết lập lại controller chính

	        Stage stage = new Stage();
	        stage.setTitle("Update User");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	        refreshUserList(); // Làm mới danh sách sau khi cập nhật

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void handleDelete() {
	    User selectedUser = usersTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedUser == null) {
	    	showErrorAlert("Error", "Please select a user to update!");
	        return;
	    }else if(selectedUser.getAccount().equals("admin")) {
	    	showErrorAlert("Error", "Can not delete ADMIN!");
	        return;
	    } else {
			boolean confirmed = showConfirmation("Confirm", "Are you sure you want to delete?");
			if (confirmed) {
			    userDAO.deleteUser(selectedUser.getUserid());
			}
	    }
	    
	    refreshUserList(); 
	}

	
	@FXML
	private void handleDetail() {
	    User selectedUser = usersTable.getSelectionModel().getSelectedItem();
	    
	    if (selectedUser == null) {
	        showErrorAlert("Error", "Please select a user to update!");
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/users/DetailUserPopUp.fxml"));
	        Parent root = loader.load();

	        DetailUserPopUpController popUpController = loader.getController();
	        
	        // Lấy dữ liệu chi tiết dựa trên role
	        Student student = null;
	        Teacher teacher = null;
	        
	        if ("student".equals(selectedUser.getRole())) {
	            student = userJoinStudents(selectedUser.getUserid());
	        } else if ("teacher".equals(selectedUser.getRole())) {
	            teacher = userJoinTeachers(selectedUser.getUserid());
	        }

	        popUpController.setData(selectedUser, teacher, student);
	        popUpController.setUsersSceneController(this);

	        Stage stage = new Stage();
	        stage.setTitle("Detail User");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void handleActiveOrLocked() {
	    User selectedUser = usersTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedUser == null) {
	    	showErrorAlert("Error", "Please select a user to update!");
	        return;
	    } else if(selectedUser.getAccount().equals("admin")) {
	    	showErrorAlert("Error", "Can not lock ADMIN!");
	        return;
	    }else {
			boolean confirmed = showConfirmation("Confirm", "Are you sure you want to active/locked?");
			if (confirmed) {
				if(selectedUser.getStatus().equals("active")) {
					userDAO.updateStatusUser(selectedUser.getUserid(), "locked");
				}else {
					userDAO.updateStatusUser(selectedUser.getUserid(), "active");
				}
			}
	    }
	    
	    refreshUserList(); 
	}

	private void filterUsers(String searchText) {
	    if (searchText == null || searchText.isEmpty()) {
	        usersTable.setItems(userList); // Không lọc nếu ô tìm kiếm trống
	        return;
	    }

	    String selectedType = typeSearchCB.getValue(); // Lấy giá trị của ComboBox

	    ObservableList<User> filteredList = FXCollections.observableArrayList();

	    for (User user : userList) {
	        if (selectedType.equals("User ID") && user.getUserid().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(user);
	        } else if (selectedType.equals("Account") && user.getAccount().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(user);
	        }
	    }

	    usersTable.setItems(filteredList);
	}

	public void refreshUserList() {
		usersTable.setItems(FXCollections.observableArrayList(userDAO.getAllUsers()));
    }
	
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
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
