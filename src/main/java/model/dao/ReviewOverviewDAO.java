package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.Connector;
import model.bean.ReviewOverview;

public class ReviewOverviewDAO {
	public List<ReviewOverview> findByUsername(String username) {
		List<ReviewOverview> res = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "select b.BookName, b.ImageName, b.Path, r.Detail, r.Rating, r.TotalVotes\r\n"
					+ "from Reviews as r\r\n" + "inner join Books as b\r\n" + "on r.BookId = b.BookId\r\n"
					+ "inner join Users as u\r\n" + "on u.UserId = r.UserId\r\n" + "where u.UserName= ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ReviewOverview reviewOverview = new ReviewOverview();
					String bookName = rs.getString("BookName");
					String bookImageName = rs.getString("ImageName");
					String path = rs.getString("Path");
					String detail = rs.getString("Detail");
					int rating = rs.getInt("Rating");
					int totalVotes = rs.getInt("TotalVotes");
					reviewOverview.setBookImageName(bookImageName);
					reviewOverview.setBookImagePath(path);
					reviewOverview.setBookName(bookName);
					reviewOverview.setReviewDetail(detail);
					reviewOverview.setReviewRating(rating);
					reviewOverview.setReviewTotalVote(totalVotes);
					res.add(reviewOverview);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
