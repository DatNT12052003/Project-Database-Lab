package controller.admin.management.teachers;

import hash_password.HashPassword;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.person.TeacherDAO;
import model.user.UserDAO;

public class AddTeacherPopUpController {
	@FXML
	private TextField fullNameTF;
	
	@FXML
	private TextField accountTF;
	
	@FXML
	private TextField passwordTF;
	
	@FXML
	private DatePicker dobDP;
	
	@FXML
	private RadioButton maleRB;
	
	@FXML
	private RadioButton femaleRB;
	
	@FXML
	private ComboBox<String> addressCB;
	
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
	
	private ToggleGroup genderGroup = new ToggleGroup();
	
	private TeacherDAO teacherDAO = new TeacherDAO();
	
	private UserDAO userDAO = new UserDAO();
	
    private TeachersSceneController teachersSceneController;

    public void setTeachersSceneController(TeachersSceneController teachersSceneController) {
        this.teachersSceneController = teachersSceneController;
    }
	
	public TeachersSceneController getTeachersSceneController() {
		return teachersSceneController;
	}

	@FXML
	private void initialize() {
		maleRB.setToggleGroup(genderGroup);
		femaleRB.setToggleGroup(genderGroup);
//		String[] provinces = {
//	            "An Giang", "Bà Rịa - Vũng Tàu", "Bạc Liêu", "Bắc Giang", "Bắc Kạn", "Bắc Ninh", "Bến Tre", 
//	            "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cần Thơ", "Cao Bằng", "Đà Nẵng", 
//	            "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", 
//	            "Hà Nội", "Hà Tĩnh", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", 
//	            "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", 
//	            "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", 
//	            "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", 
//	            "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "TP Hồ Chí Minh", "Trà Vinh", 
//	            "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
//	        };
		String[] provinces = {
			    "An Giang", "Ba Ria - Vung Tau", "Bac Lieu", "Bac Giang", "Bac Kan", "Bac Ninh", "Ben Tre",
			    "Binh Dinh", "Binh Duong", "Binh Phuoc", "Binh Thuan", "Ca Mau", "Can Tho", "Cao Bang", "Da Nang",
			    "Dak Lak", "Dak Nong", "Dien Bien", "Dong Nai", "Dong Thap", "Gia Lai", "Ha Giang", "Ha Nam",
			    "Ha Noi", "Ha Tinh", "Hai Duong", "Hai Phong", "Hau Giang", "Hoa Binh", "Hung Yen", "Khanh Hoa",
			    "Kien Giang", "Kon Tum", "Lai Chau", "Lam Dong", "Lang Son", "Lao Cai", "Long An", "Nam Dinh",
			    "Nghe An", "Ninh Binh", "Ninh Thuan", "Phu Tho", "Phu Yen", "Quang Binh", "Quang Nam",
			    "Quang Ngai", "Quang Ninh", "Quang Tri", "Soc Trang", "Son La", "Tay Ninh", "Thai Binh",
			    "Thai Nguyen", "Thanh Hoa", "Thua Thien Hue", "Tien Giang", "TP Ho Chi Minh", "Tra Vinh",
			    "Tuyen Quang", "Vinh Long", "Vinh Phuc", "Yen Bai"
			};
		addressCB.getItems().addAll(provinces);
		addressCB.setValue("Ha Noi");
	}
	
	@FXML
	private void handleOk() {
		String fullName = fullNameTF.getText();
		String account = accountTF.getText();
		String password = passwordTF.getText();
		
		String dob = null;
		if(dobDP.getValue() != null) {
			dob = dobDP.getValue().toString();
		}
		
		RadioButton selectedRadio = (RadioButton) genderGroup.getSelectedToggle();
		String gender = null;
		if(selectedRadio != null) {
			gender = selectedRadio.getText();
		}
		
		String address = addressCB.getValue();
		
		String phone = phoneTF.getText();
		String email = emailTF.getText();
		
		String expertise = expertiseTF.getText();
		String level = levelTF.getText();
		int salary = 0;
		
		try {
		    salary = Integer.parseInt(salaryTF.getText());
		} catch (NumberFormatException e) {
			showErrorAlert("Error", "Salary must integer!");
			return;
		}

		
		if(fullName.isEmpty() || account.isEmpty() || password.isEmpty() || dob==null || gender==null || address==null||phone.isEmpty() || email.isEmpty() || expertise.isEmpty() || level.isEmpty()) {
			showErrorAlert("Error", "Not enough information has been entered!");
		}else if (!phone.matches("\\d{10}")) {
		    showErrorAlert("Error", "Phone number must have 10 digits!");
		} else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
		    showErrorAlert("Error", "Invalid email format!");
		} else if (salary<0) {
		    showErrorAlert("Error", "Can salary be negative?");
		}else {
			userDAO.insertUser(teacherDAO.generateTeacherID(), account, HashPassword.hashSHA256(password), "Teacher");
			teacherDAO.insertTeacher(fullName, dob, gender, address, phone, email, expertise, level, salary);
			String message = "Teacher ID: " + teacherDAO.generateTeacherID() + "\n"
					+ "Full Name: " + fullName + "\n"
					+ "Account: " + account + "\n"
					+ "Password: " + password + "\n"
                    + "Date Of Birth: " + dob + "\n"
                    + "Gender: " + gender + "\n"
                    + "Address: " + address + "\n"
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
