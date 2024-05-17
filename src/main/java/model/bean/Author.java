package model.bean;

public class Author {
	private int AuthorId;
	private String AuthorName;
	public int getAuthorId() {
		return AuthorId;
	}
	public void setAuthorId(int authorId) {
		AuthorId = authorId;
	}
	public String getAuthorName() {
		return AuthorName;
	}
	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}
	public Author(int authorId, String authorName) {
		super();
		AuthorId = authorId;
		AuthorName = authorName;
	}
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
