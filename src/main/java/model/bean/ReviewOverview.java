package model.bean;

public class ReviewOverview {
	private String bookImageName;
	private String bookImagePath;
	private String bookName;
	private String reviewDetail;
	private int reviewRating;
	private int reviewTotalVote;

	public String getBookImageName() {
		return bookImageName;
	}

	public void setBookImageName(String bookImageName) {
		this.bookImageName = bookImageName;
	}

	public String getBookImagePath() {
		return bookImagePath;
	}

	public void setBookImagePath(String bookImagePath) {
		this.bookImagePath = bookImagePath;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getReviewDetail() {
		return reviewDetail;
	}

	public void setReviewDetail(String reviewDetail) {
		this.reviewDetail = reviewDetail;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public int getReviewTotalVote() {
		return reviewTotalVote;
	}

	public void setReviewTotalVote(int reviewTotalVote) {
		this.reviewTotalVote = reviewTotalVote;
	}

	public ReviewOverview() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ReviewOverview [bookImageName=" + bookImageName + ", bookImagePath=" + bookImagePath + ", bookName="
				+ bookName + ", reviewDetail=" + reviewDetail + ", reviewRating=" + reviewRating + ", reviewTotalVote="
				+ reviewTotalVote + "]";
	}

}
