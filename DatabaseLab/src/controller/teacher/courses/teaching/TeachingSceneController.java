package controller.teacher.courses.teaching;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.course.Course;
import model.course.CourseDAO;
import model.user_login.UserLogin;

public class TeachingSceneController {
	
	@FXML
	private TextField searchTF;
	
	@FXML
	private ComboBox<String> typeSearchCB;
	
	@FXML
	private Text prevPage;
	
	@FXML
	private Text lastPage;
	
	@FXML
	private GridPane grid;
	
	private CourseDAO courseDAO = new CourseDAO();
	
	private ObservableList<Course> courseList = FXCollections.observableArrayList();
	
	private static final int ITEMS_PER_PAGE = 12; // Số khóa học hiển thị trên mỗi trang
	private int currentPage = 1;
	private int totalPages = 1;
	private ObservableList<Course> filteredList = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		courseList = courseDAO.getAllTeachingCoursesByTeacherid(UserLogin.getUserid());
		filteredList.setAll(courseList); // Ban đầu hiển thị toàn bộ danh sách

        addCoursesToGrid(getCurrentPageItems());

		typeSearchCB.getItems().addAll("Course ID", "Subject Name", "Teacher", "Course Start Date");
		typeSearchCB.setValue("Course ID");
		
		searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
			filterCourses(newValue);
			updatePagination();
		});

		// Xử lý sự kiện khi nhấn nút chuyển trang
		prevPage.setOnMouseClicked(event -> goToPreviousPage());
		lastPage.setOnMouseClicked(event -> goToNextPage());

		updatePagination();
    }

	private void addCoursesToGrid(List<Course> courses) {
	    grid.getChildren().clear(); // Xóa dữ liệu cũ trước khi thêm mới

	    int row = 0, col = 0;
	    for (Course course : courses) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/teacher/courses/teaching/CourseItem.fxml"));
	            AnchorPane courseItem = loader.load();

	            CourseItemController controller = loader.getController();
	            controller.setData(course);

	            grid.add(courseItem, col, row);
	            col++;
	            if (col == 4) { // Mỗi hàng tối đa 4 cột
	                col = 0;
	                row++;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

	// Lọc khóa học theo từ khóa
    private void filterCourses(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            filteredList.setAll(courseList); // Hiển thị tất cả nếu ô tìm kiếm trống
        } else {
            String selectedType = typeSearchCB.getValue(); // Lấy giá trị của ComboBox
            filteredList.clear();

            for (Course course : courseList) {
                String searchLower = searchText.toLowerCase();

                if (selectedType.equals("Course ID") && course.getCourseid() != null 
                    && course.getCourseid().toLowerCase().contains(searchLower)) {
                    filteredList.add(course);
                } else if (selectedType.equals("Subject Name") && course.getSubject() != null 
                    && course.getSubject().getName().toLowerCase().contains(searchLower)) {
                    filteredList.add(course);
                } else if (selectedType.equals("Teacher") && course.getTeacher() != null 
                    && course.getTeacher().getFullName().toLowerCase().contains(searchLower)) {
                    filteredList.add(course);
                } else if (selectedType.equals("Course Start Date") && course.getCourseStartDate() != null 
                    && course.getCourseStartDate().toLowerCase().contains(searchLower)) {
                    filteredList.add(course);
                }
            }
        }
        
        // Reset về trang đầu tiên khi lọc
        currentPage = 1;
        updatePagination();
    }

	// Lấy danh sách khóa học của trang hiện tại
	private List<Course> getCurrentPageItems() {
		int fromIndex = (currentPage - 1) * ITEMS_PER_PAGE;
		int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, filteredList.size());
		return filteredList.subList(fromIndex, toIndex);
	}

	// Cập nhật tổng số trang và hiển thị dữ liệu
	private void updatePagination() {
		totalPages = (int) Math.ceil((double) filteredList.size() / ITEMS_PER_PAGE);
		if (totalPages == 0) totalPages = 1;
		if (currentPage > totalPages) currentPage = totalPages;

		addCoursesToGrid(getCurrentPageItems());

		// Cập nhật trạng thái nút
		prevPage.setDisable(currentPage == 1);
		lastPage.setDisable(currentPage == totalPages);

		prevPage.setOpacity(currentPage == 1 ? 0.5 : 1.0);
		lastPage.setOpacity(currentPage == totalPages ? 0.5 : 1.0);
	}

	// Chuyển về trang trước
	private void goToPreviousPage() {
		if (currentPage > 1) {
			currentPage--;
			updatePagination();
		}
	}

	// Chuyển về trang sau
	private void goToNextPage() {
		if (currentPage < totalPages) {
			currentPage++;
			updatePagination();
		}
	}
}
