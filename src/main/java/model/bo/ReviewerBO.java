package model.bo;

import java.util.List;

import model.bean.ReviewOverview;
import model.bean.Reviewer;
import model.bean.User;
import model.dao.ReviewDAO;
import model.dao.ReviewOverviewDAO;
import model.dao.UserDAO;

public class ReviewerBO {
	private ReviewOverviewDAO reviewOverviewDAO;
	private ReviewDAO reviewDAO;
	private UserDAO userDAO;

	public Reviewer findInfor(String username) {
		Reviewer res = new Reviewer();
		String userId = userDAO.findUserId(username);
		res.setTotalDownVotes(reviewDAO.countDownVotes(userId));
		res.setTotalUpvotes(reviewDAO.countUpVotes(userId));
		res.setTotalVotes(reviewDAO.countTotalVotes(userId));
		res.setUsername(username);
		List<ReviewOverview> list = reviewOverviewDAO.findByUsername(username);
		res.setList(list);
		System.out.println(res.toString());
		System.out.println(res.getList().get(1));
		return res;
	}

	public ReviewerBO() {
		super();
		// TODO Auto-generated constructor stub
		this.reviewDAO = new ReviewDAO();
		this.reviewOverviewDAO = new ReviewOverviewDAO();
		this.userDAO = new UserDAO();
	}

}
