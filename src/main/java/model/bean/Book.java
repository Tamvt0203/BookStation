package model.bean;

public class Book {
	private int BookId;
    private String BookName;
    private int AuthorId;
    private int CategoryId1;
    private int CategoryId2;
    private int CategoryId3;
    private String Summary;
    private String Path;
    private String ImageName;
    //private IFormFile fileUpload;
	public int getBookId() {
		return BookId;
	}
	public void setBookId(int bookId) {
		BookId = bookId;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public int getAuthorId() {
		return AuthorId;
	}
	public void setAuthorId(int authorId) {
		AuthorId = authorId;
	}
	public int getCategoryId1() {
		return CategoryId1;
	}
	public void setCategoryId1(int categoryId1) {
		CategoryId1 = categoryId1;
	}
	public int getCategoryId2() {
		return CategoryId2;
	}
	public void setCategoryId2(int categoryId2) {
		CategoryId2 = categoryId2;
	}
	public int getCategoryId3() {
		return CategoryId3;
	}
	public void setCategoryId3(int categoryId3) {
		CategoryId3 = categoryId3;
	}
	public String getSummary() {
		return Summary;
	}
	public void setSummary(String summary) {
		Summary = summary;
	}
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getImageName() {
		return ImageName;
	}
	public void setImageName(String imageName) {
		ImageName = imageName;
	}
    
    
}
