package controller.admin.management.subjects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import hash_password.HashPassword;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.person.Teacher;
import model.person.TeacherDAO;
import model.subject.Subject;
import model.subject.SubjectDAO;
import model.user.User;
import model.user.UserDAO;

public class UpdateSubjectPopUpController {
	
	private SubjectDAO subjectDAO = new SubjectDAO();
	
    private SubjectsSceneController subjectsSceneController;

    public void setSubjectsSceneController(SubjectsSceneController subjectsSceneController) {
        this.subjectsSceneController = subjectsSceneController;
    }
	
	public SubjectsSceneController getSubjectsSceneController() {
		return subjectsSceneController;
	}
	
	@FXML
	private TextField nameTF;
	
	@FXML
	private TextField massTF;
	
	@FXML
	private TextField tuitionTF;
	
	@FXML
	private Button okButton;
	
	private Subject subject;
	
	public void setSubject(Subject subject) {
		this.subject = subject;
		nameTF.setText(subject.getName());
		massTF.setText(String.valueOf(subject.getMass()));
		tuitionTF.setText(String.valueOf(subject.getTuition()));
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleOk() {
		String name = nameTF.getText();
		int mass = Integer.parseInt(massTF.getText());
		int tuition = Integer.parseInt(tuitionTF.getText());
		
		if(name.isEmpty() || mass==0 || tuition==0) {
			showErrorAlert("Error", "Not enough information has been entered!");
		}else if (mass<0 || tuition<0) {
		    showErrorAlert("Error", "Can mass or tuition be negative?");
		}else {
			subjectDAO.updateSubject(subject.getSubjectid(), name, mass, tuition);
			String message = "Subject Name: " + name + "\n"
                    + "Mass: " + mass + "\n"
                    + "Tuition: " + tuition + "\n";
			
			showCompletedAlert("Success", message);
			
	        ((Stage) okButton.getScene().getWindow()).close();
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
        alert.setHeaderText("Account Updated Successfully!");
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}
