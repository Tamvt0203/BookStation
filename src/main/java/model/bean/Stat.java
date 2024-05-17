package model.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class Stat {
	private int totalBooks;
	private int totalUsers;
	private int totalReviews;
	private Map<String, Integer> booksCategory;
	private Map<String, Integer> reviewsCategory;

	public int getTotalBooks() {
		return totalBooks;
	}

	public void setTotalBooks(int totalBooks) {
		this.totalBooks = totalBooks;
	}

	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}

	public int getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}

	public Map<String, Integer> getBooksCategory() {
		return booksCategory;
	}

	public void setBooksCategory(Map<String, Integer> booksCategory) {
		this.booksCategory = booksCategory;
	}

	public Map<String, Integer> getReviewsCategory() {
		return reviewsCategory;
	}

	public void setReviewsCategory(Map<String, Integer> reviewsCategory) {
		this.reviewsCategory = reviewsCategory;
	}

	public Stat() {
		super();
		// TODO Auto-generated constructor stub
		this.booksCategory = new LinkedHashMap<>();
		this.reviewsCategory = new LinkedHashMap<>();
	}

	@Override
	public String toString() {
		String res = "Stat [totalBooks=" + totalBooks + ", totalUsers=" + totalUsers + ", totalReviews=" + totalReviews
				+ ", booksCategory=" + booksCategory + ", reviewsCategory=" + reviewsCategory + "]";
		return res;
	}

}
