package model.bean;

public class BookCard {
	private int BookId;
    private String BookName;
    private String AuthorName;
    private String CategoryName1;
    private String CategoryName2;
    private String Summary;
    private String Path;
    private String ImageName;
    private double AverageRating;
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getAuthorName() {
		return AuthorName;
	}
	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}
	public String getCategoryName1() {
		return CategoryName1;
	}
	public void setCategoryName1(String categoryName1) {
		CategoryName1 = categoryName1;
	}
	public String getCategoryName2() {
		return CategoryName2;
	}
	public void setCategoryName2(String categoryName2) {
		CategoryName2 = categoryName2;
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
	public double getAverageRating() {
		return AverageRating;
	}
	public void setAverageRating(double averageRating) {
		AverageRating = averageRating;
	}
	public int getBookId() {
		return BookId;
	}
	public void setBookId(int bookId) {
		BookId = bookId;
	}
	public BookCard(int bookId, String bookName, String authorName, String categoryName1, String categoryName2,
			String summary, String path, String imageName, double averageRating) {
		super();
		BookId = bookId;
		BookName = bookName;
		AuthorName = authorName;
		CategoryName1 = categoryName1;
		CategoryName2 = categoryName2;
		Summary = summary;
		Path = path;
		ImageName = imageName;
		AverageRating = averageRating;
	}
	public BookCard() {
		super();
	}
	
}
