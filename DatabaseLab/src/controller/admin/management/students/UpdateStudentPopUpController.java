package controller.admin.management.students;

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
import model.person.Student;
import model.person.StudentDAO;
import model.person.Teacher;
import model.person.TeacherDAO;
import model.user.User;
import model.user.UserDAO;

public class UpdateStudentPopUpController {
	
    private StudentsSceneController studentsSceneController;

    public void setStudentsSceneController(StudentsSceneController studentsSceneController) {
        this.studentsSceneController = studentsSceneController;
    }
	
	public StudentsSceneController getStudentsSceneController() {
		return studentsSceneController;
	}
	
	@FXML
	private TextField fullNameTF;
	
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
	private Button okButton;
	
	private Student student;
	
	private StudentDAO studentDAO = new StudentDAO();
	
	public void setStudent(Student student) {
		this.student = student;
		fullNameTF.setText(student.getFullName());
		
		String dateString = student.getDateOfBirth();
		LocalDate date = LocalDate.parse(dateString);
		dobDP.setValue(date);
		
		if(student.getGender().equals("male")) {
			maleRB.setSelected(true);
		}else {
			femaleRB.setSelected(true);
		}
		
		phoneTF.setText(student.getPhone());
		emailTF.setText(student.getEmail());
	}
	
	private ToggleGroup genderGroup = new ToggleGroup();
	
	@FXML
	private void initialize() {
		maleRB.setToggleGroup(genderGroup);
		femaleRB.setToggleGroup(genderGroup);
	}
	
	@FXML
	private void handleOk() {
		String fullName = fullNameTF.getText();
		
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

		
		String studentid = student.getStudentid();
		
		if(fullName.isEmpty() || dob==null || gender==null || phone.isEmpty() || email.isEmpty()) {
			showErrorAlert("Error", "Not enough information has been entered!");
		} else {
			studentDAO.updateStudent(studentid, fullName, dob, gender, phone, email);
			String message = "Student ID: " + studentid + "\n"
					+ "Full Name: " + fullName + "\n"
                    + "Date Of Birth: " + dob + "\n"
                    + "Gender: " + gender + "\n"
                    + "Phone: " + phone + "\n"
                    + "Email: " + email + "\n";
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
