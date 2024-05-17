package model.bo;

import model.dao.ReportDAO;
import model.bean.*;
import java.util.List;
public class ReportBO {
	private ReportDAO reportDAO;

	public void delete(String id) {
		reportDAO.delete(id);
	}

	public ReportBO() {
		super();
		// TODO Auto-generated constructor stub
		this.reportDAO = new ReportDAO();
	}
	public List<Report> findReportsByBookId(int BookId, String UserId){
		return reportDAO.findReportsByBookId(BookId, UserId);
	}
	public Report findByReviewIdAndUserIdReport(int reviewId, String userIdReport) {
		return reportDAO.findByReviewIdAndUserIdReport(reviewId, userIdReport);
	}
	public boolean deleteReport(int reviewId, String userIdReport) {
		return reportDAO.deleteReport(reviewId, userIdReport);
	}
	public boolean addReport(int reviewId, String userIdReport) {
		return reportDAO.addReport(reviewId, userIdReport);
	}
}
