package controller.admin.management.users;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.person.Student;
import model.person.Teacher;
import model.user.User;

public class DetailUserPopUpController {
	
	@FXML
	private Text useridTF;
	
	@FXML
	private Text accountTF;
	
	@FXML
	private Text roleTF;
	
	@FXML
	private Text statusTF;
	
	@FXML
	private Text createdDateTF;
	
	@FXML
	private Text fullNameTF;
	
	@FXML
	private Text dobTF;
	
	@FXML
	private Text genderTF;
	
	@FXML
	private Text addressTF;
	
	@FXML
	private Text phoneTF;
	
	@FXML
	private Text emailTF;
	
	@FXML
	private Button okButton;
	
    private UsersSceneController usersSceneController;

    public void setUsersSceneController(UsersSceneController usersSceneController) {
        this.usersSceneController = usersSceneController;
    }
	
	public UsersSceneController getUsersSceneController() {
		return usersSceneController;
	}
	
	public void setData(User user, Teacher teacher, Student student) {
	    
	    useridTF.setText(user.getUserid());
	    accountTF.setText(user.getAccount());
	    roleTF.setText(user.getRole());
	    statusTF.setText(user.getStatus());
	    createdDateTF.setText(user.getCreatedDate());

	    if ("Teacher".equals(user.getRole()) && teacher != null) {
	        fullNameTF.setText(teacher.getFullName());
	        dobTF.setText(teacher.getDateOfBirth());
	        genderTF.setText(teacher.getGender());
	        addressTF.setText(teacher.getAddress());
	        phoneTF.setText(teacher.getPhone()); 
	        emailTF.setText(teacher.getEmail());
	    } else if ("Student".equals(user.getRole()) && student != null) {
	        fullNameTF.setText(student.getFullName());
	        dobTF.setText(student.getDateOfBirth());
	        genderTF.setText(student.getGender());
	        addressTF.setText(student.getAddress());
	        phoneTF.setText(student.getPhone()); 
	        emailTF.setText(student.getEmail());
	    } else {
	        fullNameTF.setText("N/A");
	        dobTF.setText("N/A");
	        genderTF.setText("N/A");
	        addressTF.setText("N/A");
	        phoneTF.setText("N/A");
	        emailTF.setText("N/A");
	    }
	}

	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleOk() {
		 ((Stage) okButton.getScene().getWindow()).close();
	}
}
