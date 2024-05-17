package model.bo;

import java.util.List;

import model.bean.FavouriteBook;
import model.bean.Personal;
import model.bean.User;
import model.dao.FavouriteBookDAO;
import model.dao.ReviewDAO;
import model.dao.UserDAO;

public class PersonalBO {
	private ReviewDAO reviewDAO;
	private FavouriteBookDAO favouriteBookDAO;
	private UserDAO userDAO;
	private BookBO bookBO;

	public Personal getInfor(String userId) {
		Personal res = new Personal();
		res.setTotalBookReviewd(reviewDAO.countBooksReviewed(userId));
		res.setTotalDownVotes(reviewDAO.countDownVotes(userId));
		res.setTotalUpVotes(reviewDAO.countUpVotes(userId));
		List<FavouriteBook> list = favouriteBookDAO.findFavouriteBookByUserId(userId);
		res.setListFavouriteBooks(bookBO.findFavouriteBook(list));
		res.setTotalVotes(reviewDAO.countTotalVotes(userId));
		String review = reviewDAO.findHighestVotedReview(userId);
		if (review.length() > 500) {
			review = review.substring(0, 500);
			review = review + "...";
		}
		res.setHighestVotedReview(review);
		User user = userDAO.findUserById(userId);
		res.setAvaName(user.getAvataName());
		res.setGmail(user.getUsername());
		res.setAvaPath(user.getPath());
		System.out.println(res.toString());
		return res;
	}

	public PersonalBO() {
		super();
		// TODO Auto-generated constructor stub
		this.reviewDAO = new ReviewDAO();
		this.favouriteBookDAO = new FavouriteBookDAO();
		this.userDAO = new UserDAO();
		this.bookBO = new BookBO();
	}

}
