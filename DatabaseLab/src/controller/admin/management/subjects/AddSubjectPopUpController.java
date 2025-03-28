package controller.admin.management.subjects;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.person.TeacherDAO;
import model.subject.SubjectDAO;

public class AddSubjectPopUpController {
	
	@FXML
	private TextField nameTF;
	
	@FXML
	private TextField massTF;
	
	@FXML
	private TextField tuitionTF;
	
	@FXML
	private Button okButton;
	
	
	private SubjectDAO subjectDAO = new SubjectDAO();
	
    private SubjectsSceneController subjectsSceneController;

    public void setSubjectsSceneController(SubjectsSceneController subjectsSceneController) {
        this.subjectsSceneController = subjectsSceneController;
    }
	
	public SubjectsSceneController getSubjectsSceneController() {
		return subjectsSceneController;
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
			subjectDAO.insertSubject(name, mass, tuition);
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
        alert.setHeaderText("ERROR");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showCompletedAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Teacher Created Successfully!");
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}
