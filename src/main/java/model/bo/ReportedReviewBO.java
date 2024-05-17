package model.bo;

import java.util.List;

import model.bean.ReportedReview;
import model.dao.ReportedReviewDAO;

public class ReportedReviewBO {
	private ReportedReviewDAO dao;

	public List<ReportedReview> findAll() {
		return dao.findAll();
	}

	public ReportedReviewBO() {
		super();
		this.dao = new ReportedReviewDAO();
		// TODO Auto-generated constructor stub
	}

}
