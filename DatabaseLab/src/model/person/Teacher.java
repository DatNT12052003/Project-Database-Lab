package model.person;

public class Teacher extends Person{
	private String teacherid;
	private String expertise;
	private String level;
	private int salary;
	
	public Teacher() {
		super();
	}
	
	public Teacher(String teacherid, String fullName, String dateOfBirth, String gender, String phone, String email,String expertise, String level, int salary) {
		super(fullName, dateOfBirth, gender, phone, email);
		this.teacherid = teacherid;
		this.expertise = expertise;
		this.level = level;
		this.salary = salary;
	}
	
	public String getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
	    return super.toString() + ", Teacher ID: " + teacherid + 
	           ", Expertise: " + expertise + ", Level: " + level + 
	           ", Salary: " + salary;
	}
	
}
