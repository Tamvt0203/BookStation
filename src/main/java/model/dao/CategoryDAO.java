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
import model.bean.Category;

public class CategoryDAO {
	public List<Category> findAll() {
		List<Category> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM categories";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String categoryId = rs.getString("CategoryId");
					String categoryName = rs.getString("CategoryName");
					Category category = new Category(Integer.parseInt(categoryId), categoryName);
					list.add(category);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Map<String, Integer> countByBook() {
		Map<String, Integer> res = new LinkedHashMap<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "select  top(20) c.CategoryName, (select COUNT(b.BookId)\r\n"
					+ "						FROM Books as b\r\n"
					+ "						WHERE b.CategoryId1  = c.CategoryId\r\n"
					+ "							or b.CategoryId2 = c.CategoryId\r\n"
					+ "							or b.CategoryId3 = c.CategoryId\r\n"
					+ "						) as count_category\r\n" + "from Categories as c\r\n"
					+ "order by count_category desc\r\n";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String categoryName = rs.getString("CategoryName");
					Integer count = rs.getInt("count_category");
					res.put(categoryName, count);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	public Map<String, Integer> countByReview() {
		Map<String, Integer> res = new LinkedHashMap<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "select top(5) c.CategoryName, (select COUNT(r.ReviewId)\r\n"
					+ "						FROM Books as b\r\n"
					+ "						INNER JOIN Reviews AS r\r\n"
					+ "						ON b.BookId = r.BookId\r\n"
					+ "						WHERE b.CategoryId1  = c.CategoryId\r\n"
					+ "							or b.CategoryId2 = c.CategoryId\r\n"
					+ "							or b.CategoryId3 = c.CategoryId\r\n"
					+ "						) as count_category\r\n"
					+ "from Categories as c\r\n"
					+ "order by count_category desc\r\n";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String categoryName = rs.getString("CategoryName");
					Integer count = rs.getInt("count_category");
					res.put(categoryName, count);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	

	public Category findById(String id) {
		if (id == null || id.equals("") || id.trim().length() == 0 || id.equals("0")) {
			return null;
		}
		List<Category> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM categories where CategoryId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String categoryId = rs.getString("CategoryId");
					String categoryName = rs.getString("CategoryName");
					Category category = new Category(Integer.parseInt(categoryId), categoryName);
					list.add(category);
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
			String sql = "UPDATE categories SET CategoryName = ?  where CategoryId = ?";
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
			String sql = "DELETE FROM categories where CategoryId = ?";
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
			String sql = "INSERT INTO  categories(CategoryName) VALUES(?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, name);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String findCategoryNameByBookId(int bookId, int num) {
	    String categoryName = null;
	    int id;
	    Category category = new Category();
	    String sql = null;
	    if(num == 1) {
	    	sql = "SELECT a.CategoryId " +
	                 "FROM Categories a " +
	                 "JOIN Books b ON a.CategoryId = b.CategoryId1 " +
	                 "WHERE b.BookId = ?"; 
	    }else if(num == 2) {
	    	sql = "SELECT a.CategoryId " +
	                 "FROM Categories a " +
	                 "JOIN Books b ON a.CategoryId = b.CategoryId2 " +
	                 "WHERE b.BookId = ?"; 
	    }else {
	    	sql = "SELECT a.CategoryId " +
	                 "FROM Categories a " +
	                 "JOIN Books b ON a.CategoryId = b.CategoryId3 " +
	                 "WHERE b.BookId = ?"; 
	    }
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	            	id = resultSet.getInt("CategoryId");
            		category = findCategoryById(id);
            		categoryName = category.getCategoryName();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle the exception according to your application's needs
	    }
	    
	    return categoryName;
	}
	public Category findCategoryById(int categoryId) {
	    Category category = null;
	    String sql = "SELECT * FROM Categories WHERE CategoryId = ?";
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        preparedStatement.setInt(1, categoryId);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                category = new Category();
	                category.setCategoryId(resultSet.getInt("CategoryId"));
	                category.setCategoryName(resultSet.getString("CategoryName"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle the exception according to your application's needs
	    }

	    return category;
	}
}
