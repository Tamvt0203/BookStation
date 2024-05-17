package model.bean;

public class Contact {
	private int id;
	private String userId;
	private String content;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Contact(int id, String userId, String content, int status) {
		super();
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.status = status;
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

}
