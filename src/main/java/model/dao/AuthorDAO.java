package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.Connector;
import model.bean.Author;

public class AuthorDAO {
	public List<Author> findAll() {
		List<Author> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM authors";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String authorId = rs.getString("AuthorId");
					String authorName = rs.getString("AuthorName");
					Author author = new Author(Integer.parseInt(authorId), authorName);
					list.add(author);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Author findById(String id) {
		List<Author> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM authors where AuthorId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String authorId = rs.getString("AuthorId");
					String authorName = rs.getString("AuthorName");
					Author author = new Author(Integer.parseInt(authorId), authorName);
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

	public void update(String id, String name) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "UPDATE authors SET AuthorName = ?  where AuthorId = ?";
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
			String sql = "DELETE FROM authors where AuthorId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void insert(String name) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "INSERT INTO  authors(AuthorName) VALUES(?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, name);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String findAuthorNameByBookId(int bookId) {
	    String authorName = null;
	    String sql = "SELECT a.AuthorName " +
	                 "FROM Authors a " +
	                 "JOIN Books b ON a.AuthorId = b.AuthorId " +
	                 "WHERE b.BookId = ?";
	    
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                authorName = resultSet.getString("AuthorName");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle the exception according to your application's needs
	    }
	    
	    return authorName;
	}
}
