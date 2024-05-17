package model.dao;

import java.util.List;

import model.bean.FavouriteBook;
import model.bean.Personal;
import model.bean.User;

public class PersonalDAO {
	private ReviewDAO reviewDAO;
	private FavouriteBookDAO favouriteBookDAO;
	private UserDAO userDAO;

	public PersonalDAO() {
		super();
		// TODO Auto-generated constructor stub
		this.reviewDAO = new ReviewDAO();
		this.favouriteBookDAO = new FavouriteBookDAO();
		this.userDAO = new UserDAO();
	}

	public Personal getInfor(String userId) {
		Personal res = new Personal();
		res.setTotalBookReviewd(reviewDAO.countBooksReviewed(userId));
		res.setTotalDownVotes(reviewDAO.countDownVotes(userId));
		res.setTotalUpVotes(reviewDAO.countUpVotes(userId));
		List<FavouriteBook> list = favouriteBookDAO.findFavouriteBookByUserId(userId);
//		res.setListFavouriteBooks();
		res.setTotalVotes(reviewDAO.countTotalVotes(userId));
		res.setHighestVotedReview(reviewDAO.findHighestVotedReview(userId));
		User user = userDAO.findUserById(userId);
		res.setAvaName(user.getAvataName());
		res.setAvaPath(user.getPath());
		System.out.println(res.toString());
		return res;
	}

}
