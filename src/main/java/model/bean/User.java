package model.bean;

public class User {
	private String UserId;
	private String Username;
	private String Password;
	private String AvataName;
	private String Path;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassWord() {
		return Password;
	}
	public void setPassword(String passWord) {
		Password = passWord;
	}
	public String getAvataName() {
		return AvataName;
	}
	public void setAvataName(String avataName) {
		AvataName = avataName;
	}
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public User(String userId, String username, String passWord, String avataName, String path) {
		super();
		UserId = userId;
		Username = username;
		Password = passWord;
		AvataName = avataName;
		Path = path;
	}
	public User() {
		super();
	}
	
}
