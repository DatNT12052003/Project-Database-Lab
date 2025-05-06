package test;

import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.person.Teacher;
import model.person.TeacherDAO;

public class Test {
	public static void main(String[] args) {
		
		TeacherDAO teacherDAO = new TeacherDAO();
		
		ObservableList<Teacher> allTeacherList = FXCollections.observableArrayList();
		
		allTeacherList = teacherDAO.getAllTeachers();
		
		for(Teacher t : allTeacherList) {
			System.out.println(t.toString());
		}
		
		ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
		
		teacherList = teacherDAO.getTeachersByScheduleid("SUN01");
		
		allTeacherList.removeAll(teacherList);
		
		for(Teacher t : allTeacherList) {
			System.out.println(t.toString());
		}
		
	}
}
