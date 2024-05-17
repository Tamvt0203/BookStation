package model.dao;

import model.bean.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.Connector;
public class VoteDAO {
	public Vote getVoteByReviewAndUser(int reviewId, String userId) {
	    String sql = "SELECT * FROM Votes WHERE ReviewId = ? AND UserId = ?";
	    System.out.println("DAO: " + reviewId + "," + userId);
	    Vote vote = new Vote();
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, reviewId);
	        preparedStatement.setString(2, userId);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                vote.setVoteId(resultSet.getInt("VoteId"));
	                vote.setReviewId(resultSet.getInt("ReviewId"));
	                vote.setUserId(userId);
	                vote.setVoteValue(resultSet.getInt("VoteValue"));
	                System.out.println("DAO1: " + vote.getVoteId());
	                return vote;
	            }
	            System.out.println("DAO: true");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("DAO: false");
	    }
	    return null;
	}
	public boolean deleteVote(int reviewId, String userId, int voteValue) {
	    String sql = "DELETE FROM Votes WHERE ReviewId = ? AND UserId = ?";
	    String updateReviewSql1 = "UPDATE Reviews SET UpVotes = UpVotes - 1, TotalVotes = TotalVotes - 1 WHERE ReviewId = ?";
	    String updateReviewSql2 = "UPDATE Reviews SET DownVotes = DownVotes - 1, TotalVotes = TotalVotes + 1 WHERE ReviewId = ?";
	    if(voteValue > 0) {
	    	try (Connection connection = Connector.getConnection();
	   	         PreparedStatement insertVoteStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	   	         PreparedStatement updateReviewStatement = connection.prepareStatement(updateReviewSql1)) {
	   	        connection.setAutoCommit(false);
	   	        // Insert vote
	   	        insertVoteStatement.setInt(1, reviewId);
	   	        insertVoteStatement.setString(2, userId);
	   	        int rowsAffected = insertVoteStatement.executeUpdate();
	   	        if (rowsAffected <= 0) {
	   	            connection.rollback();
	   	            return false;
	   	        }
	   	        updateReviewStatement.setInt(1, reviewId);
	            updateReviewStatement.executeUpdate();
	   	        // Commit transaction
	   	        connection.commit();
	   	        return true;
	    	} catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
	    }else {
	    	try (Connection connection = Connector.getConnection();
		   	         PreparedStatement insertVoteStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		   	         PreparedStatement updateReviewStatement = connection.prepareStatement(updateReviewSql2)) {
		   	        connection.setAutoCommit(false);
		   	        // Insert vote
		   	        insertVoteStatement.setInt(1, reviewId);
		   	        insertVoteStatement.setString(2, userId);
		   	        int rowsAffected = insertVoteStatement.executeUpdate();
		   	        if (rowsAffected <= 0) {
		   	            connection.rollback();
		   	            return false;
		   	        }
		   	        updateReviewStatement.setInt(1, reviewId);
		            updateReviewStatement.executeUpdate();
		   	        // Commit transaction
		   	        connection.commit();
		   	        return true;
		    	} catch (SQLException e) {
			        e.printStackTrace();
			        return false;
		    	}
	    }
	}
	public boolean addVote(int reviewId, String userId, int voteValue) {
	    String insertVoteSql = "INSERT INTO Votes (ReviewId, UserId, VoteValue) VALUES (?, ?, ?)";
	    String updateReviewSql1 = "UPDATE Reviews SET UpVotes = UpVotes + 1, TotalVotes = TotalVotes + 1 WHERE ReviewId = ?";
	    String updateReviewSql2 = "UPDATE Reviews SET DownVotes = DownVotes + 1, TotalVotes = TotalVotes - 1 WHERE ReviewId = ?";
	    if(voteValue > 0) {
	    	try (Connection connection = Connector.getConnection();
	   	         PreparedStatement insertVoteStatement = connection.prepareStatement(insertVoteSql, Statement.RETURN_GENERATED_KEYS);
	   	         PreparedStatement updateReviewStatement = connection.prepareStatement(updateReviewSql1)) {
	   	        connection.setAutoCommit(false);
	   	        // Insert vote
	   	        insertVoteStatement.setInt(1, reviewId);
	   	        insertVoteStatement.setString(2, userId);
	   	        insertVoteStatement.setInt(3, voteValue);
	   	        int rowsAffected = insertVoteStatement.executeUpdate();
	   	        if (rowsAffected <= 0) {
	   	            connection.rollback();
	   	            return false;
	   	        }
	   	        updateReviewStatement.setInt(1, reviewId);
	            updateReviewStatement.executeUpdate();
	   	        // Commit transaction
	   	        connection.commit();
	   	        return true;
	    	} catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
	    }else {
	    	try (Connection connection = Connector.getConnection();
		   	         PreparedStatement insertVoteStatement = connection.prepareStatement(insertVoteSql, Statement.RETURN_GENERATED_KEYS);
		   	         PreparedStatement updateReviewStatement = connection.prepareStatement(updateReviewSql2)) {
		   	        connection.setAutoCommit(false);
		   	        // Insert vote
		   	        insertVoteStatement.setInt(1, reviewId);
		   	        insertVoteStatement.setString(2, userId);
		   	        insertVoteStatement.setInt(3, voteValue);
		   	        int rowsAffected = insertVoteStatement.executeUpdate();
		   	        if (rowsAffected <= 0) {
		   	            connection.rollback();
		   	            return false;
		   	        }
		   	        updateReviewStatement.setInt(1, reviewId);
		            updateReviewStatement.executeUpdate();
		   	        // Commit transaction
		   	        connection.commit();
		   	        return true;
		    	} catch (SQLException e) {
			        e.printStackTrace();
			        return false;
		    	}
	    }	    
	}

	public boolean updateVote(int reviewId, String userId, int voteValue) {
	    String sql = "UPDATE Votes SET VoteValue = ? WHERE ReviewId = ? AND UserId = ?";
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, voteValue);
	        preparedStatement.setInt(2, reviewId);
	        preparedStatement.setString(3, userId);

	        int rowsAffected = preparedStatement.executeUpdate();
	        if(rowsAffected > 0) {
	        	return true;
	        }else {
	        	return false;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public Vote findVoteByUserAndReview(String userId, int reviewId) {
	    String sql = "SELECT * FROM Votes WHERE UserId = ? AND ReviewId = ?";
	    Vote vote = new Vote();

	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setString(1, userId);
	        preparedStatement.setInt(2, reviewId);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                vote.setVoteId(resultSet.getInt("VoteId"));
	                vote.setReviewId(resultSet.getInt("ReviewId"));
	                vote.setUserId(resultSet.getString("UserId"));
	                vote.setVoteValue(resultSet.getInt("VoteValue"));
	                return vote;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null; // Return null if no vote is found
	}
	public List<Vote> getAllVotesByBookId(int bookId) {
	    String sql = "SELECT v.* FROM Votes v " +
	                 "JOIN Reviews r ON v.ReviewId = r.ReviewId " +
	                 "WHERE r.BookId = ?";
	    List<Vote> votes = new ArrayList<>();

	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                Vote vote = new Vote();
	                vote.setVoteId(resultSet.getInt("VoteId"));
	                vote.setReviewId(resultSet.getInt("ReviewId"));
	                vote.setUserId(resultSet.getString("UserId"));
	                vote.setVoteValue(resultSet.getInt("VoteValue"));
	                votes.add(vote);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return votes;
	}
	public boolean doesVoteExist(int reviewId, String userId, int voteValue) {
	    String sql = "SELECT COUNT(*) FROM Votes WHERE ReviewId = ? AND UserId = ? AND VoteValue = ?";
	    
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, reviewId);
	        preparedStatement.setString(2, userId);
	        preparedStatement.setInt(3, voteValue);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                if(count > 0) {
	                	return true;
	                }else {
	                	return false;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false; // Trả về false nếu có lỗi xảy ra
	}

}
