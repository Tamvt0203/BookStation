package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.Connector;

import model.bean.*;
import model.dao.*;

public class ReviewCardDAO {
	public List<ReviewCard> getAllReviewCardsByBookId(int currentPage, int itemsPerPage, int bookId){
		List<ReviewCard> listReviewCards = new ArrayList<>();
		ReviewDAO reviewDAO = new ReviewDAO();
		UserDAO userDAO = new UserDAO();
		List<Review> listReviewByBookId = reviewDAO.pageReviews(currentPage, itemsPerPage, bookId);
		for(Review review : listReviewByBookId) {
			User user = userDAO.findUserById(review.getUserId());
			ReviewCard reviewCard = new ReviewCard();
			reviewCard.setBookId(bookId);
			reviewCard.setDetail(review.getDetail());
			reviewCard.setAvataName(user.getAvataName());
			reviewCard.setDownVotes(review.getDownVotes());
			reviewCard.setPath(user.getPath());
			reviewCard.setRating(review.getRating());
			reviewCard.setReviewDate(review.getReviewDate());
			reviewCard.setReviewId(review.getReviewId());
			reviewCard.setTotalVotes(review.getTotalVotes());
			reviewCard.setUpVotes(review.getUpVotes());
			reviewCard.setUserId(user.getUserId());
			reviewCard.setUsername(user.getUsername());
			listReviewCards.add(reviewCard);
		}
		return listReviewCards;
	}
	public ReviewCard findReviewCard(int BookId, String UserId) {
		ReviewDAO reviewDAO = new ReviewDAO();
		UserDAO userDAO = new UserDAO();
		Review review = reviewDAO.getAllReviewsByBookIdAndUserId(BookId, UserId);
		if(review != null) {
			//System.out.println(review.getUserId());
			User user = userDAO.findUserById(review.getUserId());
			//System.out.println(user.getUsername());
			ReviewCard reviewCard = new ReviewCard();
			reviewCard.setBookId(BookId);
			reviewCard.setDetail(review.getDetail());
			reviewCard.setAvataName(user.getAvataName());
			reviewCard.setDownVotes(review.getDownVotes());
			reviewCard.setPath(user.getPath());
			reviewCard.setRating(review.getRating());
			reviewCard.setReviewDate(review.getReviewDate());
			reviewCard.setReviewId(review.getReviewId());
			reviewCard.setTotalVotes(review.getTotalVotes());
			reviewCard.setUpVotes(review.getUpVotes());
			reviewCard.setUserId(user.getUserId());
			reviewCard.setUsername(user.getUsername());
			return reviewCard;
		}
		return null;
	}
}
