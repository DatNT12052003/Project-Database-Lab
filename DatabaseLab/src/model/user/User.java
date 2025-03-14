package model.user;

public class User {
	private String userid;
	private String account;
	private String password;
	private String role;
	private String status;
	
	public User() {
		super();
	}
	
	public User(String userid, String account, String password, String role, String status) {
		super();
		this.userid = userid;
		this.account = account;
		this.password = password;
		this.role = role;
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
