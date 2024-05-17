package model.bean;

public class ReviewCard {
	private int ReviewId;
    private String UserId;
    private int BookId;
    private String ReviewDate;
    private int Rating;
    private int UpVotes;
    private int DownVotes;
    private int TotalVotes;
    private String Detail;
    private String Username;
    private String AvataName;
	private String Path;
	public int getReviewId() {
		return ReviewId;
	}
	public void setReviewId(int reviewId) {
		ReviewId = reviewId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public int getBookId() {
		return BookId;
	}
	public void setBookId(int bookId) {
		BookId = bookId;
	}
	public String getReviewDate() {
		return ReviewDate;
	}
	public void setReviewDate(String reviewDate) {
		ReviewDate = reviewDate;
	}
	public int getRating() {
		return Rating;
	}
	public void setRating(int rating) {
		Rating = rating;
	}
	public int getUpVotes() {
		return UpVotes;
	}
	public void setUpVotes(int upVotes) {
		UpVotes = upVotes;
	}
	public int getDownVotes() {
		return DownVotes;
	}
	public void setDownVotes(int downVotes) {
		DownVotes = downVotes;
	}
	public int getTotalVotes() {
		return TotalVotes;
	}
	public void setTotalVotes(int totalVotes) {
		TotalVotes = totalVotes;
	}
	public String getDetail() {
		return Detail;
	}
	public void setDetail(String detail) {
		Detail = detail;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
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
	public ReviewCard(int reviewId, String userId, int bookId, String reviewDate, int rating, int upVotes,
			int downVotes, int totalVotes, String detail, String username, String avataName, String path) {
		super();
		ReviewId = reviewId;
		UserId = userId;
		BookId = bookId;
		ReviewDate = reviewDate;
		Rating = rating;
		UpVotes = upVotes;
		DownVotes = downVotes;
		TotalVotes = totalVotes;
		Detail = detail;
		Username = username;
		AvataName = avataName;
		Path = path;
	}
	public ReviewCard() {
		super();
	}
	
}
