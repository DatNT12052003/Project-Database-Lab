package controller.other;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class CalendarController {
    @FXML
    private Text monthYearText;
    @FXML
    private Button lastMonthButton;
    @FXML
    private Button nextMonthButton;
    @FXML
    private GridPane calendarGrid;
    
    private YearMonth currentMonth;
    
    private Map<LocalDate, String> notes = new HashMap<>(); // Lưu ghi chú cho từng ngày
    
    @FXML
    private void initialize() {
        currentMonth = YearMonth.now();
        updateCalendar();
    }

    @FXML
    private void handleLastMonth() {
        currentMonth = currentMonth.minusMonths(1);
        updateCalendar();
    }

    @FXML
    private void handleNextMonth() {
        currentMonth = currentMonth.plusMonths(1);
        updateCalendar();
    }
    
    private void updateCalendar() {
    	calendarGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);
        //monthYearText.setText(convertMonth(currentMonth.getMonth().toString()) + " - " + currentMonth.getYear());
        monthYearText.setText(currentMonth.getMonth().toString() + " - " + currentMonth.getYear());

        LocalDate firstDayOfMonth = currentMonth.atDay(1);
        int daysInMonth = currentMonth.lengthOfMonth();
        int firstDayColumn = (firstDayOfMonth.getDayOfWeek().getValue()) - 1;
        LocalDate today = LocalDate.now();

        int row = 1; 
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDate = firstDayOfMonth.plusDays(day - 1);
            Button dayButton = new Button(String.valueOf(day));
            dayButton.setPrefSize(30, 30);
            GridPane.setHalignment(dayButton, HPos.CENTER);
            GridPane.setValignment(dayButton, VPos.CENTER);
            
            // Định dạng màu cho Chủ Nhật và ngày hiện tại
            if (currentDate.getDayOfWeek().getValue() == 7) {
                dayButton.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-weight: bold;");
            }
            if (currentDate.equals(today)) {
                dayButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
            }
            
            dayButton.setOnAction(event -> showDayInfo(currentDate));
            calendarGrid.add(dayButton, firstDayColumn, row);
            
            firstDayColumn++;
            if (firstDayColumn == 7) {
                firstDayColumn = 0;
                row++;
            }
        }
    }
    
    private void showDayInfo(LocalDate date) {
        String note = notes.getOrDefault(date, "Không có ghi chú.");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông Tin Ngày");
        alert.setHeaderText("Ngày bạn chọn: " + date);
        alert.setContentText("Ghi chú: " + note);
        alert.showAndWait();
        
        // Mở hộp thoại nhập ghi chú
        TextInputDialog dialog = new TextInputDialog(note);
        dialog.setTitle("Nhập Ghi Chú");
        dialog.setHeaderText("Thêm hoặc sửa ghi chú cho ngày: " + date);
        dialog.setContentText("Nhập ghi chú:");
        
        dialog.showAndWait().ifPresent(input -> {
            notes.put(date, input);
        });
    }
    
//    private String convertMonth(String month) {
//    	String vnMonth = new String();
//    	switch(month) {
//    	case "JANUARY":
//    		vnMonth = "Tháng 1";
//    		break;
//    	case "FEBRUARY":
//    		vnMonth = "Tháng 2";
//    		break;
//    	case "MARCH":
//    		vnMonth = "Tháng 3";
//    		break;
//    	case "APRIL":
//    		vnMonth = "Tháng 4";
//    		break;
//    	case "MAY":
//    		vnMonth = "Tháng 5";
//    		break;
//    	case "JUNE":
//    		vnMonth = "Tháng 6";
//    		break;
//    	case "JULY":
//    		vnMonth = "Tháng 7";
//    		break;
//    	case "AUGUST":
//    		vnMonth = "Tháng 8";
//    		break;
//    	case "SEPTEMBER":
//    		vnMonth = "Tháng 9";
//    		break;
//    	case "OCTOBER":
//    		vnMonth = "Tháng 10";
//    		break;
//    	case "NOVEMBER":
//    		vnMonth = "Tháng 11";
//    		break;
//    	case "DECEMBER":
//    		vnMonth = "Tháng 12";
//    		break;
//    	}
//    	
//    	return vnMonth;
//    }
}