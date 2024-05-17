package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.bean.ReportedReview;
import model.bean.Report;
import model.bean.Review;

public class ReportedReviewDAO {
	public List<ReportedReview> findAll() {
		List<ReportedReview> result = new ArrayList<>();
		ReportDAO reportDAO = new ReportDAO();
		ReviewDAO reviewDAO = new ReviewDAO();
		List<Report> reports = reportDAO.findAll();
		for (Report report : reports) {
			System.out.println("id: " + report.getReviewId()+"");
			ReportedReview dto = new ReportedReview();
			Review currentReview = reviewDAO.findById(report.getReviewId()+"");
			dto.setDetail(currentReview.getDetail());

			int countReport = reviewDAO.countReports(Integer.toString(report.getReviewId()));
			System.out.println("count  " + countReport);
			dto.setReportedNumber(countReport);
			int bookId = reviewDAO.findById(Integer.toString(report.getReviewId())).getBookId();
			List<Review> reviews = reviewDAO.findByBookId(Integer.toString(bookId));
			double totalVotes = 0;
			for (Review current : reviews) {
//				totalVotes += current.getUpVotes() + current.getDownVotes();
//				totalVotes += reviewDAO.countVotes(current.getReviewId()+"");
				totalVotes += current.getTotalVotes();
			}
			

			System.out.println("Total " + totalVotes);
			double percent = (double) countReport / totalVotes * 100.0;
			percent = Math.ceil(percent * 100) / 100;
			dto.setPercent(percent);
			dto.setReviewId(report.getReviewId());
			result.add(dto);
		}
		return result;
	}

}
