package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.Connector;
import model.bean.*;

public class ReportDAO {
	public List<Report> findAll() {
		List<Report> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM reports";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int reportId = rs.getInt("ReportId");
					int reviewId = rs.getInt("ReviewId");
					String userIdReport = rs.getString("UserIdReport");
					String userIdReported = rs.getString("UserIdReported");
					Report report = new Report(reportId, reviewId, userIdReport, userIdReported);
					list.add(report);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Report findById(String id) {
		List<Report> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM reports where ReportId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int reportId = rs.getInt("ReportId");
					int reviewId = rs.getInt("ReviewId");
					String userIdReport = rs.getString("UserIdReport");
					String userIdReported = rs.getString("UserIdReported");
					Report author = new Report(reportId, reviewId, userIdReport, userIdReported);
					list.add(author);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() > 0) {
			return list.get(0);
		} else
			return null;
	}

	public void delete(String id) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "DELETE FROM reports where ReviewId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Report findByReviewIdAndUserIdReport(int reviewId, String userIdReport) {
		Report report = new Report();
		//System.out.println(reviewId + userIdReport);
	    try (Connection conn = Connector.getConnection()) {
	        String sql = "SELECT * FROM Reports WHERE ReviewId = ? AND UserIdReport = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, reviewId);
	            stmt.setString(2, userIdReport);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int reportId = rs.getInt("ReportId");
	                String userIdReported = rs.getString("UserIdReported");
	                report = new Report(reportId, reviewId, userIdReport, userIdReported);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return report;
	}
	public List<Report> findReportsByBookId(int BookId, String UserId){
		List<Report> list = new ArrayList<>();
		ReviewDAO reviewDAO = new ReviewDAO();
		List<Review> listReview = reviewDAO.getAllReviewsByBookId(BookId);
		for(Review review : listReview) {
			 Report report = findByReviewIdAndUserIdReport(review.getReviewId(), UserId);
			if(report.getReviewId() != 0) {
				list.add(report);
				System.out.println("dao: " + report.getReportId());
			}
		}
		return list;
	}
	public boolean deleteReport(int reviewId, String userIdReport) {
		System.out.println(reviewId + "," + userIdReport);
	    String sql = "DELETE FROM Reports WHERE ReviewId = ? AND UserIdReport = ?";
	    try (Connection conn = Connector.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, reviewId);
	        stmt.setString(2, userIdReport);
	        int rowsAffected = stmt.executeUpdate();
	        if(rowsAffected > 0) {
	        	return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public boolean addReport(int reviewId, String userIdReport) {
		ReviewDAO reviewDAO = new ReviewDAO();
		Review review  = reviewDAO.getReviewByReviewId(reviewId);
		String UserIdReported = review.getUserId();
        String sql = "INSERT INTO Reports (ReviewId, UserIdReport ,UserIdReported) VALUES (?, ?, ?)";
        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            stmt.setString(2, userIdReport);
            stmt.setString(3, UserIdReported);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0) {
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
