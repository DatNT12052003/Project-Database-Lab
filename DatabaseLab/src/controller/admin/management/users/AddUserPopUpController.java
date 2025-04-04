package controller.admin.management.users;

import hash_password.HashPassword;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.user.UserDAO;

public class AddUserPopUpController {
	@FXML
	private TextField accountTF;
	
	@FXML
	private PasswordField passwordTF;
	
	@FXML
	private ComboBox<String> roleCB;
	
	@FXML
	private Button okButton;
	
	private UserDAO userDAO = new UserDAO();
	
    private UsersSceneController usersSceneController;

    public void setUsersSceneController(UsersSceneController usersSceneController) {
        this.usersSceneController = usersSceneController;
    }
	
	public UsersSceneController getUsersSceneController() {
		return usersSceneController;
	}

	@FXML
	private void initialize() {
		roleCB.getItems().addAll("Teacher", "Student");
		roleCB.setValue("Teacher");
	}
	
	@FXML
	private void handleOk() {
		String account = accountTF.getText();
		String password = passwordTF.getText();
		String role = roleCB.getValue();
		
		if(account.isEmpty() || password.isEmpty()) {
			showErrorAlert("Error", "Not enough information has been entered!");
		} else if(userDAO.isUserExists(account)) {
			showErrorAlert("Error", "Account already exists!");
		}else {
			//userDAO.insertUser(account, HashPassword.hashSHA256(password), role);
			
			String message = "Account: " + account + "\n"
					+ "Password: " + password + "\n"
                    + "Role: " + role + "\n";
			
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
        alert.setHeaderText("Account Created Successfully!");
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}
