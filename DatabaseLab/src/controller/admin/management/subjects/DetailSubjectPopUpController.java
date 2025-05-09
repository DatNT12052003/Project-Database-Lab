package controller.admin.management.subjects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.person.Teacher;
import model.subject.Subject;
import model.subject.SubjectDAO;

public class DetailSubjectPopUpController {
	
	@FXML
	private Text subjectidText;
	
	@FXML
	private Text nameText;

	@FXML
	private Text massText;

	@FXML
	private Text tuitionText;
	
	@FXML
	private Button okButton;
	
    private SubjectsSceneController subjectsSceneController;

    public void setSubjectsSceneController(SubjectsSceneController subjectsSceneController) {
        this.subjectsSceneController = subjectsSceneController;
    }
	
	public SubjectsSceneController getSubjectsSceneController() {
		return subjectsSceneController;
	}
	
	private Subject subject;

	
	public void setData(Subject subject) {
		this.subject = subject;
		subjectidText.setText(subject.getSubjectid());
		nameText.setText(subject.getName());
		massText.setText(String.valueOf(subject.getMass()));
		tuitionText.setText(String.valueOf(subject.getTuition()));
	}
	
	@FXML
	private void handleOk() {
		 ((Stage) okButton.getScene().getWindow()).close();
	}

}
