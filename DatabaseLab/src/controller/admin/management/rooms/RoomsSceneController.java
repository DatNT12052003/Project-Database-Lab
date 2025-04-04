package controller.admin.management.rooms;

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
import model.user.User;
import model.user.UserDAO;

public class RoomsSceneController {
	@FXML
	private TextField searchTF;
	
	@FXML
	private ComboBox<String> typeSearchCB;
	
	@FXML
	private TableView<Room> roomsTable;
	
	@FXML
	private TableColumn<Room, String> roomidColumn;
	
	@FXML
	private TableColumn<Room, String> addressColumn;
	
	@FXML
	private TableColumn<Room, String> typeColumn;
	
	@FXML
	private TableColumn<Room, Integer> maxStudentsColumn;
	
	@FXML
	private TableColumn<Room, String> statusColumn;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private Button detailButton;
	
	
	private ObservableList<Room> roomList = FXCollections.observableArrayList();
	
	private RoomDAO roomDAO;
	
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
		roomDAO = new RoomDAO();
		roomList = roomDAO.getAllRooms();
		
		roomidColumn.setCellValueFactory(new PropertyValueFactory<>("roomid"));
		roomidColumn.setStyle("-fx-alignment: CENTER;");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		addressColumn.setStyle("-fx-alignment: CENTER;");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		typeColumn.setStyle("-fx-alignment: CENTER;");
		maxStudentsColumn.setCellValueFactory(new PropertyValueFactory<>("maxStudents"));
		maxStudentsColumn.setStyle("-fx-alignment: CENTER;");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		statusColumn.setStyle("-fx-alignment: CENTER;");
		
		roomsTable.setItems(roomList);
		
		typeSearchCB.getItems().addAll("Room ID", "Address");
		typeSearchCB.setValue("Room ID");
		
		searchTF.textProperty().addListener((observable, oldValue, newValue) -> filterRooms(newValue));
		
	}
	
	@FXML
	private void handleAddRoom() {
		  try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/rooms/AddRoomPopUp.fxml"));
		        Parent root = loader.load();

		        AddRoomPopUpController popUpController = loader.getController();

		        Stage stage = new Stage();
		        stage.setTitle("Add Teacher");
		        stage.setScene(new Scene(root));
		        stage.setResizable(false);
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.showAndWait();
		        
		        popUpController.setRoomsSceneController(this);
		        
		        refreshRoomList();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
	@FXML
	private void handleUpdateRoom() {
	    Room selectedRoom = roomsTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedRoom == null) {
	    	showErrorAlert("Error", "Please select a room to update!");
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/rooms/UpdateRoomPopUp.fxml"));
	        Parent root = loader.load();

	        UpdateRoomPopUpController popUpController = loader.getController();
	        popUpController.setRoom(selectedRoom); // Truyền dữ liệu user sang cửa sổ cập nhật
	        popUpController.setRoomsSceneController(this); // Thiết lập lại controller chính

	        Stage stage = new Stage();
	        stage.setTitle("Update Teacher");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	        refreshRoomList(); // Làm mới danh sách sau khi cập nhật

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void handleDelete() {
		Room selectedRoom = roomsTable.getSelectionModel().getSelectedItem();  // Lấy user được chọn

	    if (selectedRoom == null) {
	    	showErrorAlert("Error", "Please select a user to update!");
	        return;
	    }else {
			boolean confirmed = showConfirmation("Confirm", "Are you sure you want to delete?");
			if (confirmed) {
			    roomDAO.updateStatus(selectedRoom.getRoomid(), "Deleted");
			}
	    }
	    
	    refreshRoomList(); 
	}

	
	@FXML
	private void handleDetail() {
	    Room selectedRoom = roomsTable.getSelectionModel().getSelectedItem(); // Lấy user được chọn

	    if (selectedRoom == null) {
	    	showErrorAlert("Error", "Please select a room to update!");
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/management/rooms/DetailRoomPopUp.fxml"));
	        Parent root = loader.load();

	        DetailRoomPopUpController popUpController = loader.getController();

	        popUpController.setData(selectedRoom);
	        popUpController.setRoomsSceneController(this);

	        Stage stage = new Stage();
	        stage.setTitle("Detail Room");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void filterRooms(String searchText) {
	    if (searchText == null || searchText.isEmpty()) {
	        roomsTable.setItems(roomList); // Không lọc nếu ô tìm kiếm trống
	        return;
	    }

	    String selectedType = typeSearchCB.getValue(); // Lấy giá trị của ComboBox

	    ObservableList<Room> filteredList = FXCollections.observableArrayList();

	    for (Room room : roomList) {
	        if (selectedType.equals("Room ID") && room.getRoomid().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(room);
	        } else if (selectedType.equals("Address") && room.getAddress().toLowerCase().contains(searchText.toLowerCase())) {
	            filteredList.add(room);
	        } 
	    }

	    roomsTable.setItems(filteredList);
	}

	public void refreshRoomList() {
		roomsTable.setItems(FXCollections.observableArrayList(roomDAO.getAllRooms()));
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
