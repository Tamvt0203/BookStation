package model.bean;

public class ReportedReview {
	private int reviewId;
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public ReportedReview(int reviewId, String detail, int reportedNumber, double percent) {
		super();
		this.reviewId = reviewId;
		this.detail = detail;
		this.reportedNumber = reportedNumber;
		this.percent = percent;
	}
	private String detail;
	private int reportedNumber;
	private double percent;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getReportedNumber() {
		return reportedNumber;
	}
	public void setReportedNumber(int reportedNumber) {
		this.reportedNumber = reportedNumber;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	public ReportedReview() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
