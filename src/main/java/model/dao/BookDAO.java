package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import context.Connector;
import model.bean.Book;

public class BookDAO {
	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM Books";
		try (Connection connection = Connector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				Book book = new Book();
				book.setBookId(resultSet.getInt("BookId"));
				book.setBookName(resultSet.getString("BookName"));
				book.setAuthorId(resultSet.getInt("AuthorId"));
				book.setCategoryId1(resultSet.getInt("CategoryId1"));
				book.setCategoryId2(resultSet.getInt("CategoryId2"));
				book.setCategoryId3(resultSet.getInt("CategoryId3"));
				book.setSummary(resultSet.getString("Summary"));
				book.setImageName(resultSet.getString("ImageName"));
				book.setPath(resultSet.getString("Path"));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception according to your application's needs
		}
		return books;
	}

	public int countAll() {
		int res = 0;
		String sql = "SELECT COUNT(*) AS count FROM Books";
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

	public Book findById(int bookId) {
		Book book = null;
		String sql = "SELECT * FROM Books WHERE BookId = ?";
		try (Connection connection = Connector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, bookId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					book = new Book();
					book.setBookId(resultSet.getInt("BookId"));
					book.setBookName(resultSet.getString("BookName"));
					book.setAuthorId(resultSet.getInt("AuthorId"));
					book.setCategoryId1(resultSet.getInt("CategoryId1"));
					book.setCategoryId2(resultSet.getInt("CategoryId2"));
					book.setCategoryId3(resultSet.getInt("CategoryId3"));
					book.setSummary(resultSet.getString("Summary"));
					book.setImageName(resultSet.getString("ImageName"));
					book.setPath(resultSet.getString("Path"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	public void update(String bookId, String bookName, String authorId, String category1, String category2,
			String category3, String summary, String imageName, String path) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "UPDATE Books SET BookName = ?, AuthorId = ?, "
					+ "CategoryId1 = ?, CategoryId2 = ?, CategoryId3 = ?, "
					+ "Summary = ?, ImageName = ? , Path = ?  where BookId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, bookName);
				stmt.setString(2, authorId);
				stmt.setString(3, category1);
				stmt.setString(4, category2);
				stmt.setString(5, category3);
				stmt.setString(6, summary);
				stmt.setString(7, imageName);
				stmt.setString(8, path);
				stmt.setString(9, bookId);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateImage(String bookId, String imageName, String path) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "UPDATE Books SET ImageName = ? , Path = ?  where BookId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, imageName);
				stmt.setString(2, path);
				stmt.setString(3, bookId);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Integer> countByReview() {
		Map<String, Integer> res = new LinkedHashMap<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "select top (10) b.BookName, (select	COUNT(r.ReviewId)\r\n"
					+ "							from Reviews as r\r\n"
					+ "							where r.BookId = b.BookID) as count_review\r\n" + "from Books as b\r\n"
					+ "order by count_review desc";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String categoryName = rs.getString("BookName");
					Integer count = rs.getInt("count_review");
					res.put(categoryName, count);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public Map<String, Double> findAvgRating() {
		Map<String, Double> res = new LinkedHashMap<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "select top(10) b.BookName, cast(round((select AVG(r.Rating*1.0) \r\n"
					+ "							from Reviews as r\r\n"
					+ "							where r.BookId = b.BookId),2)as numeric(36,1)) as avg_book\r\n"
					+ "from Books as b\r\n"
					+ "order by  avg_book desc\r\n"
					+ "";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String categoryName = rs.getString("BookName");
					Double count = rs.getDouble("avg_book");
					res.put(categoryName, count);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int findMaxId() {
		int result = 0;
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT MAX(BookId) AS maxId FROM Books";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt("maxId");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public void insert(String bookName, String authorId, String category1, String category2, String category3,
			String summary, String imageName, String path) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "INSERT INTO Books(BookName,AuthorId,CategoryId1,CategoryId2,"
					+ "CategoryId3,Summary,ImageName, Path" + " ) VALUES(?,?,?,?,?,?,?,?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, bookName);
				stmt.setString(2, authorId);
				stmt.setString(3, category1);
				stmt.setString(4, category2);
				stmt.setString(5, category3);
				stmt.setString(6, summary);
				stmt.setString(7, imageName);
				stmt.setString(8, path);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String id) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "DELETE FROM  Books  WHERE BookId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Book findBookById(int bookId) {
	    Book book = null;
	    String sql = "SELECT * FROM Books WHERE BookId = ?";
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                book = new Book();
	                book.setBookId(resultSet.getInt("BookId"));
	                book.setBookName(resultSet.getString("BookName"));
	                book.setAuthorId(resultSet.getInt("AuthorId"));
	                book.setCategoryId1(resultSet.getInt("CategoryId1"));
	                book.setCategoryId2(resultSet.getInt("CategoryId2"));
	                book.setCategoryId3(resultSet.getInt("CategoryId3"));
	                book.setSummary(resultSet.getString("Summary"));
	                book.setImageName(resultSet.getString("ImageName"));
	                book.setPath(resultSet.getString("Path"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle the exception according to your application's needs
	    }

	    return book;
	}
}
