package model.bean;

public class FavouriteBook {
	private int FavouriteBookId;
	private String UserId;
	private int BookId;

	public int getFavouriteBookId() {
		return FavouriteBookId;
	}

	public void setFavouriteBookId(int favouriteBookId) {
		FavouriteBookId = favouriteBookId;
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

	public FavouriteBook(int favouriteBookId, String userId, int bookId) {
		super();
		FavouriteBookId = favouriteBookId;
		UserId = userId;
		BookId = bookId;
	}

	public FavouriteBook() {
		super();
	}

	@Override
	public String toString() {
		return "FavouriteBook [FavouriteBookId=" + FavouriteBookId + ", UserId=" + UserId + ", BookId=" + BookId + "]";
	}

}
