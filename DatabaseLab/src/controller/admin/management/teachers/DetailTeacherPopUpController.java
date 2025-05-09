package controller.admin.management.teachers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.person.Teacher;
import model.user.User;

public class DetailTeacherPopUpController {
	
	@FXML
	private Text teacheridText;
	
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
	private Text expertiseText;

	@FXML
	private Text levelText;

	@FXML
	private Text salaryText;
	
	@FXML
	private Text accountText;
	
	@FXML
	private Button okButton;
	
    private TeachersSceneController teachersSceneController;

    public void setTeachersSceneController(TeachersSceneController teachersSceneController) {
        this.teachersSceneController = teachersSceneController;
    }
	
	public TeachersSceneController getTeachersSceneController() {
		return teachersSceneController;
	}

	
	public void setData(Teacher teacher, User user) {
		
		teacheridText.setText(teacher.getTeacherid());
		fullNameText.setText(teacher.getFullName());
		dobText.setText(teacher.getDateOfBirth());
		genderText.setText(teacher.getGender());
		addressText.setText(teacher.getAddress());
		phoneText.setText(teacher.getPhone());
		emailText.setText(teacher.getEmail());
		expertiseText.setText(teacher.getExpertise());
		levelText.setText(teacher.getLevel());
		salaryText.setText(String.valueOf(teacher.getSalary()));
		accountText.setText(user.getAccount());
	}
	
	@FXML
	private void handleOk() {
		 ((Stage) okButton.getScene().getWindow()).close();
	}

}
