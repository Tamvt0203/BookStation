package model.bo;

import java.util.List;

import model.bean.Review;
import model.dao.ReviewDAO;

public class ReviewBO {
	private ReviewDAO dao;

	public List<Review> findAll() {
		return dao.findAll();
	}
	public Review findById(String id) {
		return dao.findById(id);
	}
	public ReviewBO() {
		super();
		this.dao = new ReviewDAO();
	}
	public void update(String id, String name) {
		dao.update(id, name);
	}
	public void delete(String id) {
		dao.delete(id);
	}
	public int getTotalReviewsByBookId(int bookId) {
		return dao.getTotalReviewsByBookId(bookId);
	}
	public List<Review> pageReviews(int currentPage, int itemsPerPage, int bookId){
		return dao.pageReviews(currentPage, itemsPerPage, bookId); 
	}
	public void insertReview(Review newReview) {
		dao.insertReview(newReview);
	}
	public void updateReview(Review newReview) {
		dao.updateReview(newReview);
	}
	public Review getReviewByReviewId(int reviewId) {
		return dao.getReviewByReviewId(reviewId);
	}
	public void deleteReview(int reviewId) {
		dao.deleteReview(reviewId);
	}
//	public void insert(String name) {
//		dao.insert(name);
//	}
	
}
