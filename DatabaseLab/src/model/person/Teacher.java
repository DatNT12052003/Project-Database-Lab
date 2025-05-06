package model.person;

import java.util.Objects;

public class Teacher extends Person{
	private String teacherid;
	private String expertise;
	private String level;
	private int salary;
	
	public Teacher() {
		super();
	}
	
	public Teacher(String teacherid, String fullName, String dateOfBirth, String gender, String address, String phone, String email, String expertise, String level, int salary, String status) {
		super(fullName, dateOfBirth, gender, address, phone, email, status);
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
        return getTeacherid() + " - " + super.getFullName() + " - " + getExpertise();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher teacher = (Teacher) obj;
        return this.teacherid.equals(teacher.teacherid); // So sánh theo ID giáo viên
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherid); // Đảm bảo hashCode phù hợp với equals
    }

	
}
