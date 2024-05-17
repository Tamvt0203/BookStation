package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import context.Connector;
import model.bean.Review;

public class ReviewDAO {
	public List<Review> findAll() {
		List<Review> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM reviews";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int reviewId = rs.getInt("ReviewId");
					String userId = rs.getString("UserId");
					int bookId = rs.getInt("BookId");
					String reviewDate = rs.getString("ReviewDate");
					int rating = rs.getInt("Rating");
					int upVotes = rs.getInt("UpVotes");
					int downVotes = rs.getInt("DownVotes");
					int totalVotes = rs.getInt("TotalVotes");
					String detai = rs.getString("Detail");
					Review review = new Review(reviewId, userId, bookId, reviewDate, rating, upVotes, downVotes,
							totalVotes, detai);
					list.add(review);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int countAll() {
		int res = 0;
		String sql = "SELECT COUNT(*) AS count FROM Reviews";
		try (Connection connection = Connector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				res = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception according to your application's needs
		}
		return res;
	}

	public int countReports(String reviewId) {
		int result = 0;
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT COUNT(ReviewId) AS count FROM Reports WHERE ReviewId = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, reviewId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt("count");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public String findHighestVotedReview(String userId) {
		String res = new String();
		try (Connection conn = Connector.getConnection()) {
			String sql = "select top(1) Detail\r\n" + "from Reviews\r\n" + "where UserId = ?\r\n"
					+ "order by TotalVotes desc";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, userId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					res = rs.getString("Detail");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;

	}

	public int countVotes(String reviewId) {
		int result = 0;
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT COUNT(DISTINCT UserId) AS count FROM Votes WHERE ReviewId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, reviewId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt("count");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countBooksReviewed(String userId) {
		int result = 0;
		try (Connection conn = Connector.getConnection()) {
			String sql = "select count(BookId) as count\r\n" + "from Reviews\r\n" + "where UserId = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, userId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt("count");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countUpVotes(String userId) {
		int result = 0;
		try (Connection conn = Connector.getConnection()) {
			String sql = "select sum(UpVotes) as count\r\n" + "from Reviews\r\n" + "where UserId =?;";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, userId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt("count");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countDownVotes(String userId) {
		int result = 0;
		try (Connection conn = Connector.getConnection()) {
			String sql = "select sum(DownVotes) as count\r\n" + "from Reviews\r\n" + "where UserId =?;";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, userId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt("count");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countTotalVotes(String userId) {
		int result = 0;
		try (Connection conn = Connector.getConnection()) {
			String sql = "select sum(TotalVotes) as count\r\n" + "from Reviews\r\n" + "where UserId =?;";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, userId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt("count");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Review> findByBookId(String id) {
		List<Review> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM reviews where BookId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int reviewId = rs.getInt("ReviewId");
					String userId = rs.getString("UserId");
					int bookId = rs.getInt("BookId");
					String reviewDate = rs.getString("ReviewDate");
					int rating = rs.getInt("Rating");
					int upVotes = rs.getInt("UpVotes");
					int downVotes = rs.getInt("DownVotes");
					int totalVotes = rs.getInt("TotalVotes");
					String detai = rs.getString("Detail");
					Review review = new Review(reviewId, userId, bookId, reviewDate, rating, upVotes, downVotes,
							totalVotes, detai);
					list.add(review);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Review findById(String id) {
		List<Review> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM reviews where ReviewId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int reviewId = rs.getInt("ReviewId");
					String userId = rs.getString("UserId");
					int bookId = rs.getInt("BookId");
					String reviewDate = rs.getString("ReviewDate");
					int rating = rs.getInt("Rating");
					int upVotes = rs.getInt("UpVotes");
					int downVotes = rs.getInt("DownVotes");
					int totalVotes = rs.getInt("TotalVotes");
					String detai = rs.getString("Detail");
					Review review = new Review(reviewId, userId, bookId, reviewDate, rating, upVotes, downVotes,
							totalVotes, detai);
					list.add(review);
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

	public void update(String id, String name) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "UPDATE reviews SET ReviewName = ?  where ReviewId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, name);
				stmt.setString(2, id);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String id) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "DELETE FROM reviews where ReviewId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Review> getAllReviewsByBookId(int bookId) {
	    List<Review> reviews = new ArrayList<>();
	    String sql = "SELECT * FROM Reviews WHERE BookId = ?";
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                Review review = new Review();
	                review.setReviewId(resultSet.getInt("ReviewId"));
	                review.setUserId(resultSet.getString("UserId"));
	                review.setBookId(resultSet.getInt("BookId"));
	                review.setReviewDate(resultSet.getString("ReviewDate"));
	                review.setRating(resultSet.getInt("Rating"));
	                review.setUpVotes(resultSet.getInt("UpVotes"));
	                review.setDownVotes(resultSet.getInt("DownVotes"));
	                review.setTotalVotes(resultSet.getInt("TotalVotes"));
	                review.setDetail(resultSet.getString("Detail"));
	                reviews.add(review);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng của bạn
	    }
	    return reviews;
	}
	public int getTotalReviewsByBookId(int bookId) {
	    int count = 0;
	    String sql = "SELECT * FROM Reviews WHERE BookId = ?";
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                count++;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng của bạn
	    }
	    return count;
	}
	public List<Review> pageReviews(int currentPage, int itemsPerPage, int bookId) {
	    System.out.println("currentPage = " + currentPage);

	    List<Review> reviews = new ArrayList<>();
	    String sql = "SELECT * FROM Reviews WHERE BookId = ? ORDER BY ReviewId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        preparedStatement.setInt(1, bookId);
	        preparedStatement.setInt(2, currentPage * itemsPerPage);
	        preparedStatement.setInt(3, itemsPerPage);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                Review review = new Review();
	                review.setUserId(resultSet.getString("UserId"));
	                review.setReviewId(resultSet.getInt("ReviewId"));
	                review.setBookId(resultSet.getInt("BookId"));
	                review.setReviewDate(resultSet.getString("ReviewDate"));
	                review.setRating(resultSet.getInt("Rating"));
	                review.setUpVotes(resultSet.getInt("UpVotes"));
	                review.setDownVotes(resultSet.getInt("DownVotes"));
	                review.setTotalVotes(resultSet.getInt("TotalVotes"));
	                review.setDetail(resultSet.getString("Detail"));
	                reviews.add(review);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle the exception according to your application's needs
	    }

	    System.out.println("size=" + reviews.size());
	    return reviews;
	}
	public Review getAllReviewsByBookIdAndUserId(int bookId, String UserId) {		
	    Review review = null;
	    String sql = "SELECT * FROM Reviews WHERE BookId = ? AND UserId = ?";
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);
	        preparedStatement.setString(2, UserId);	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	            	review = new Review();
	                review.setReviewId(resultSet.getInt("ReviewId"));
	                review.setUserId(resultSet.getString("UserId"));
	                review.setBookId(resultSet.getInt("BookId"));
	                review.setReviewDate(resultSet.getString("ReviewDate"));
	                review.setRating(resultSet.getInt("Rating"));
	                review.setUpVotes(resultSet.getInt("UpVotes"));
	                review.setDownVotes(resultSet.getInt("DownVotes"));
	                review.setTotalVotes(resultSet.getInt("TotalVotes"));
	                review.setDetail(resultSet.getString("Detail"));	                
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng của bạn
	    }
	    //System.out.println(review.getUserId());
	    return review;
	}
	public void insertReview(Review newReview) {
	    // Kiểm tra và xử lý review.Detail nếu nó là null hoặc chuỗi trắng
	    if (newReview.getDetail() == null || newReview.getDetail().trim().isEmpty()) {
	        newReview.setDetail("I have no idea...");
	    }
	    String sql = "INSERT INTO Reviews (UserId, BookId, ReviewDate, Rating, UpVotes, DownVotes, TotalVotes, Detail) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        preparedStatement.setString(1, newReview.getUserId());
	        preparedStatement.setInt(2, newReview.getBookId());
	        preparedStatement.setString(3, newReview.getReviewDate());
	        preparedStatement.setInt(4, newReview.getRating());
	        preparedStatement.setInt(5, newReview.getUpVotes());
	        preparedStatement.setInt(6, newReview.getDownVotes());
	        preparedStatement.setInt(7, newReview.getTotalVotes());
	        preparedStatement.setString(8, newReview.getDetail());
	        preparedStatement.executeUpdate();
	        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int generatedReviewId = generatedKeys.getInt(1);
	                newReview.setReviewId(generatedReviewId);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng của bạn
	    }
	}
	public void updateReview(Review updatedReview) {
	    // Kiểm tra và xử lý updatedReview.getDetail() nếu nó là null hoặc chuỗi trắng
	    if (updatedReview.getDetail() == null || updatedReview.getDetail().trim().isEmpty()) {
	        updatedReview.setDetail("I have no idea...");
	    }
	    
	    String sql = "UPDATE Reviews SET UserId=?, BookId=?, ReviewDate=?, Rating=?, UpVotes=?, DownVotes=?, TotalVotes=?, Detail=? WHERE ReviewId=?";
	    
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setString(1, updatedReview.getUserId());
	        preparedStatement.setInt(2, updatedReview.getBookId());
	        preparedStatement.setString(3, updatedReview.getReviewDate());
	        preparedStatement.setInt(4, updatedReview.getRating());
	        preparedStatement.setInt(5, updatedReview.getUpVotes());
	        preparedStatement.setInt(6, updatedReview.getDownVotes());
	        preparedStatement.setInt(7, updatedReview.getTotalVotes());
	        preparedStatement.setString(8, updatedReview.getDetail());
	        preparedStatement.setInt(9, updatedReview.getReviewId());
	        
	        preparedStatement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng của bạn
	    }
	}
	public Review getReviewByReviewId(int reviewId) {
	    Review review = null;
	    String sql = "SELECT * FROM Reviews WHERE ReviewId = ?";
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, reviewId);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                review = new Review();
	                review.setReviewId(resultSet.getInt("ReviewId"));
	                review.setUserId(resultSet.getString("UserId"));
	                review.setBookId(resultSet.getInt("BookId"));
	                review.setReviewDate(resultSet.getString("ReviewDate"));
	                review.setRating(resultSet.getInt("Rating"));
	                review.setUpVotes(resultSet.getInt("UpVotes"));
	                review.setDownVotes(resultSet.getInt("DownVotes"));
	                review.setTotalVotes(resultSet.getInt("TotalVotes"));
	                review.setDetail(resultSet.getString("Detail"));                
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng của bạn
	    }
	    return review;
	}
	public void deleteReview(int reviewId) {
	    String sql = "DELETE FROM Reviews WHERE ReviewId = ?";
	    
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, reviewId);
	        
	        // Thực hiện truy vấn xóa
	        int rowsAffected = preparedStatement.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Review with ID " + reviewId + " has been deleted successfully.");
	        } else {
	            System.out.println("No review found with ID " + reviewId + ". Nothing deleted.");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace(); // Xử lý ngoại lệ theo nhu cầu của ứng dụng của bạn
	    }
	}
//	public void insert(String name) {
//		try (Connection conn = Connector.getConnection()) {
//			String sql = "INSERT INTO  reviews(ReviewName) VALUES(?)";
//			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//				stmt.setString(1, name);
//				stmt.executeUpdate();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
