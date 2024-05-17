package model.dao;

import model.bean.Top;

public class TopDAO {
	public Top findTop() {
		BookDAO bookDAO = new BookDAO();
		UserDAO userDAO = new UserDAO();
		Top res = new Top();
		res.setEnthusiasticReviewers(userDAO.countReviews());
		res.setHighestRatedBooks(bookDAO.findAvgRating());
		res.setHighestVotedReviewers(userDAO.countVotes());
		res.setPopularBooks(bookDAO.countByReview());
		System.out.println(res.toString());
		return res;
	}
}
