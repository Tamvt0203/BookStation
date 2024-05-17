package model.dao;

import model.bean.Stat;

public class StatDAO {
	private BookDAO bookDAO;
	private UserDAO userDAO;
	private ReviewDAO reviewDAO;
	private CategoryDAO categoryDAO;

	public StatDAO() {
		super();
		// TODO Auto-generated constructor stub
		this.bookDAO = new BookDAO();
		this.reviewDAO = new ReviewDAO();
		this.userDAO = new UserDAO();
		this.categoryDAO = new CategoryDAO();
	}

	public Stat findStat() {
		Stat stat = new Stat();
		int totalBooks = bookDAO.countAll();
		int totalUsers = userDAO.countAll();
		int totalReviews = reviewDAO.countAll();
		stat.setTotalBooks(totalBooks);
		stat.setTotalReviews(totalReviews);
		stat.setTotalUsers(totalUsers);
		stat.setBooksCategory(categoryDAO.countByBook());
		stat.setReviewsCategory(categoryDAO.countByReview());
		System.out.println(stat.toString());
		return stat;
	}

}
