package model.bean;

public class Report {
	private int reportId;
	private int reviewId;
	private String userIdReport;
	private String userIdReported;
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getUserIdReport() {
		return userIdReport;
	}
	public void setUserIdReport(String userIdReport) {
		this.userIdReport = userIdReport;
	}
	public String getUserIdReported() {
		return userIdReported;
	}
	public void setUserIdReported(String userIdReported) {
		this.userIdReported = userIdReported;
	}
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Report(int reportId, int reviewId, String userIdReport, String userIdReported) {
		super();
		this.reportId = reportId;
		this.reviewId = reviewId;
		this.userIdReport = userIdReport;
		this.userIdReported = userIdReported;
	}
	
	
	
}
