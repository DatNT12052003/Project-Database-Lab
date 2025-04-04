package controller.admin.management.students;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.person.Student;
import model.person.Teacher;
import model.user.User;

public class DetailStudentPopUpController {
	
	@FXML
	private Text studentidText;
	
	@FXML
	private Text fullNameText;
	

	@FXML
	private Text dobText;

	@FXML
	private Text genderText;
	
	@FXML
	private Text addressText;

	@FXML
	private Text phoneText;

	@FXML
	private Text emailText;
	
	@FXML
	private Text accountText;
	
	@FXML
	private Button okButton;
	
    private StudentsSceneController studentsSceneController;

    public void setStudentsSceneController(StudentsSceneController studentsSceneController) {
        this.studentsSceneController = studentsSceneController;
    }
	
	public StudentsSceneController getStudentsSceneController() {
		return studentsSceneController;
	}

	public void setData(Student student,  User user) {
		
		studentidText.setText(student.getStudentid());
		fullNameText.setText(student.getFullName());
		dobText.setText(student.getDateOfBirth());
		genderText.setText(student.getGender());
		addressText.setText(student.getAddress());
		phoneText.setText(student.getPhone());
		emailText.setText(student.getEmail());
		accountText.setText(user.getAccount());
		
	}
	
	@FXML
	private void handleOk() {
		 ((Stage) okButton.getScene().getWindow()).close();
	}

}
