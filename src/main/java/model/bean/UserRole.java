package model.bean;

public class UserRole {
	private String userId;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String userRole;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public UserRole(String userId, String userName, String userRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userRole = userRole;
	}

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

}
