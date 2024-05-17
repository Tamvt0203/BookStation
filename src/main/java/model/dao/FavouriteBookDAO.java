package model.dao;

import model.bean.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import context.Connector;

public class FavouriteBookDAO {
	public List<FavouriteBook> findFavouriteBookByUserId(String UserId) {
		List<FavouriteBook> favouriteBooks = new ArrayList<>();
		String sql = "SELECT * FROM FavouriteBooks WHERE UserId = ?";
		try (Connection connection = Connector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, UserId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					FavouriteBook favouriteBook = new FavouriteBook();
					favouriteBook.setFavouriteBookId(resultSet.getInt("FavouriteBookId"));
					favouriteBook.setBookId(resultSet.getInt("BookId"));
					favouriteBook.setUserId(UserId);
					favouriteBooks.add(favouriteBook);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng của bạn
		}
		return favouriteBooks;
	}

	public void delete(int bookId, String userId) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "delete  from FavouriteBooks  where BookId = ? and UserId = ? ";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, bookId);
				stmt.setString(2, userId);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isFavouriteBookExist(int bookId, String userId) {
		String sql = "SELECT * FROM FavouriteBooks WHERE BookId = ? AND UserId = ?";
		try (Connection connection = Connector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, bookId);
			preparedStatement.setString(2, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean addFavouriteBook(int bookId, String userId) {
	    String sql = "INSERT INTO FavouriteBooks (BookId, UserId) VALUES (?, ?)";
	    boolean isSuccess = false;

	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        preparedStatement.setInt(1, bookId);
	        preparedStatement.setString(2, userId);

	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows > 0) {
	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                	isSuccess = true;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng của bạn
	    }

	    return isSuccess;
	}
	public boolean removeFavouriteBook(int bookId, String userId) {
	    String sql = "DELETE FROM FavouriteBooks WHERE BookId = ? AND UserId = ?";
	    boolean isSuccess = false;

	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);
	        preparedStatement.setString(2, userId);

	        int affectedRows = preparedStatement.executeUpdate();
	        if(affectedRows > 0) {
	        	isSuccess = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng của bạn
	    }

	    return isSuccess;
	}
	public FavouriteBook isFavouriteBookExists(int bookId, String userId) {
	    String sql = "SELECT * FROM FavouriteBooks WHERE BookId = ? AND UserId = ?";
	    System.out.println("DAO: " + bookId + "," + userId);
	    FavouriteBook favouriteBook = new FavouriteBook();
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);
	        preparedStatement.setString(2, userId);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	        	while (resultSet.next()) {
	                favouriteBook.setFavouriteBookId(resultSet.getInt("FavouriteBookId"));
	                favouriteBook.setBookId(resultSet.getInt("BookId"));
	                System.out.println("DAO1: " + favouriteBook.getBookId());
	                favouriteBook.setUserId(userId);
	                return favouriteBook;
	            }
	        	System.out.println("DAO: true");	        		        	
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("DAO: false");	         
	    }
	    return null;
	}
}
