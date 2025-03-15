package model.person;

public class Student extends Person{
	private String studentid;
	
	public Student() {
		super();
	}
	
	public Student(String studentid) {
		super();
		this.studentid = studentid;
	}
	

	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
}
