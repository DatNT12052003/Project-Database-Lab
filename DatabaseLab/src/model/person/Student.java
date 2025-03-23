package model.person;

public class Student extends Person{
	private String studentid;
	
	public Student() {
		super();
	}
	
	public Student(String studentid, String fullName, String dateOfBirth, String gender, String address, String phone, String email, String status) {
		super(fullName, dateOfBirth, gender, address, phone, email, status);
		this.studentid = studentid;
	}
	

	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
}
