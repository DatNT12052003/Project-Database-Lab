package controller.admin.management.teachers;

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
import model.user.User;
import model.user.UserDAO;

public class UpdateTeacherPopUpController {
	
    private TeachersSceneController teachersSceneController;

    public void setTeachersSceneController(TeachersSceneController teachersSceneController) {
        this.teachersSceneController = teachersSceneController;
    }
	
	public TeachersSceneController getTeachersSceneController() {
		return teachersSceneController;
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
	private TextField expertiseTF;
	
	@FXML
	private TextField levelTF;
	
	@FXML
	private TextField salaryTF;

	@FXML
	private Button okButton;
	
	private Teacher teacher;
	
	private TeacherDAO teacherDAO = new TeacherDAO();
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
		fullNameTF.setText(teacher.getFullName());
		
		String dateString = teacher.getDateOfBirth();
		LocalDate date = LocalDate.parse(dateString);
		dobDP.setValue(date);
		
		if(teacher.getGender().equals("male")) {
			maleRB.setSelected(true);
		}else {
			femaleRB.setSelected(true);
		}
		
		phoneTF.setText(teacher.getPhone());
		emailTF.setText(teacher.getEmail());
		expertiseTF.setText(teacher.getExpertise());
		levelTF.setText(teacher.getLevel());
		salaryTF.setText(String.valueOf(teacher.getSalary())); // Chuyển đổi số sang chuỗi
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
		String expertise = expertiseTF.getText();
		String level = levelTF.getText();
		int salary = Integer.parseInt(salaryTF.getText());

		
		String teacherid = teacher.getTeacherid();
		
		if(fullName.isEmpty() || dob==null || gender==null || phone.isEmpty() || email.isEmpty() || expertise.isEmpty() || level.isEmpty() || salary==0) {
			showErrorAlert("Error", "Not enough information has been entered!");
		} else {
			teacherDAO.updateTeacher(teacherid, fullName, dob, gender, phone, email, expertise, level, salary);
			String message = "Teacher ID: " + teacherid + "\n"
					+ "Full Name: " + fullName + "\n"
                    + "Date Of Birth: " + dob + "\n"
                    + "Gender: " + gender + "\n"
                    + "Phone: " + phone + "\n"
                    + "Email: " + email + "\n"
                    + "Expertise: " + expertise + "\n"
                    + "Level: " + level + "\n"
                    + "Salary: " + salary;
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
