package controller.other;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import hash_password.HashPassword;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.person.StudentDAO;
import model.user.UserDAO;

public class RegisterController {
	
	 private UserDAO userDAO = new UserDAO();
	 
	 private StudentDAO studentDAO = new StudentDAO();
	
	@FXML
	private Button comeBackButton;
	
	@FXML
	private TextField fullNameTF;
	
	@FXML
	private TextField accountTF;
	
	@FXML
	private PasswordField passwordTF;
	
	@FXML
	private PasswordField rePasswordTF;
	
	@FXML
	private DatePicker dobDP;
	
	@FXML
	private RadioButton maleRB;
	
	@FXML
	private RadioButton femaleRB;
	
	@FXML
	private TextField phoneTF;
	
	@FXML
	private TextField emailTF;
	
	@FXML
	private Button registerButton;
	
	private ToggleGroup genderGroup = new ToggleGroup();
	
	@FXML
	private void initialize() {
		maleRB.setToggleGroup(genderGroup);
		femaleRB.setToggleGroup(genderGroup);
	}
	
	
	@FXML
	private void handleComeBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/other/LoginView.fxml"));
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Login View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorAlert("Eror", "Can not open Login View");
        }
	}
	
	@FXML
	private void handleRegister() {
		String fullName = fullNameTF.getText();
		String account = accountTF.getText();
		String password = passwordTF.getText();
		String rePassword = rePasswordTF.getText();
		
		String dob = null;
		if(dobDP.getValue() != null) {
			dob = dobDP.getValue().toString();
		}
		
		RadioButton selectedRadio = (RadioButton) genderGroup.getSelectedToggle();
		String gender = null;
		if(selectedRadio != null) {
			gender = selectedRadio.getText();
		}
		
		String phone = phoneTF.getText();
		String email = emailTF.getText();
		
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
		
		if(fullName.isEmpty() || account.isEmpty() || password.isEmpty() || rePassword.isEmpty() || dob==null || gender==null || phone.isEmpty() || email.isEmpty()) {
			showErrorAlert("Error", "Not enough information has been entered!");
		}else if(!passwordTF.getText().equals(rePasswordTF.getText())) {
			showErrorAlert("Error", "Password and re-password are different!");
		}else if(userDAO.getUserByAccount(account)!=null) {
			showErrorAlert("Error", "Account already exists!");
		}else if (!phone.matches("\\d{10}")) {
		    showErrorAlert("Error", "Phone number must have 10 digits!");
		} else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
		    showErrorAlert("Error", "Invalid email format!");
		}else {
			userDAO.insertUser(account, HashPassword.hashSHA256(password), "student", formattedDateTime);
			studentDAO.insertStudent(fullName, dob, gender, phone, email);
			showCompletedAlert("Completed", "Registration successful!");
	        
	        handleComeBack();
		}
	}
	
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showCompletedAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}
