package model.dao;

import context.Connector;
import model.bean.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserDAO {
	public User findUserById(String userId) {
		User user = null;
		String sql = "SELECT * FROM Users WHERE UserId = ?";
		try (Connection connection = Connector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					user = new User();
					user.setUserId(resultSet.getString("UserId"));
					user.setUsername(resultSet.getString("Username"));
					user.setPassword(resultSet.getString("Password"));
					user.setAvataName(resultSet.getString("AvatarName"));
					user.setPath(resultSet.getString("Path"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public void updatePassword(String password, String userId) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "update Users set Password=? where UserId=?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, password);
				stmt.setString(2, userId);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String addUser(String userId, String userName, String password, String avatarName, String path) {
		String existedUser = findUserId(userName);
		if (existedUser == null) {
			try (Connection conn = Connector.getConnection()) {
				String sql = "insert into Users(UserName, Password, AvatarName, Path, UserId) values(?,?,?,?,?)";
				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setString(1, userName);
					stmt.setString(2, password);
					stmt.setString(3, avatarName);
					stmt.setString(4, path);
					stmt.setString(5, userId);
					stmt.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			String mess = "This username already existed! Please try again";
			return mess;

		}

	}

	public Map<String, Integer> countVotes() {
		Map<String, Integer> res = new LinkedHashMap<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "select top(10) u.UserName, (select sum(TotalVotes)\r\n"
					+ "					from Reviews as r\r\n"
					+ "					where r.UserId = u.UserId ) as total\r\n" + "from  users as u\r\n"
					+ "order by total desc";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String categoryName = rs.getString("UserName");
					Integer count = rs.getInt("total");
					res.put(categoryName, count);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public Map<String, Integer> countReviews() {
		Map<String, Integer> res = new LinkedHashMap<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "select top(10) u.UserName, (select count(r.ReviewId)\r\n"
					+ "					from Reviews as r\r\n"
					+ "					where r.UserId = u.UserId ) as total\r\n" + "from  users as u\r\n"
					+ "order by total desc";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String categoryName = rs.getString("UserName");
					Integer count = rs.getInt("total");
					res.put(categoryName, count);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int countAll() {
		int res = 0;
		String sql = "SELECT COUNT(*) AS count FROM Users";
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

	public String findUserId(String username, String password) {
		String result = null;
		String sql = "SELECT UserId FROM Users WHERE UserName = ? AND Password = ?";
		try (Connection connection = Connector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					result = resultSet.getString("UserId");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public String findUserId(String username) {
		String result = null;
		String sql = "SELECT UserId FROM Users WHERE UserName = ?";
		try (Connection connection = Connector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					result = resultSet.getString("UserId");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public void updateAva(String avaName, String path, String userId) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "update Users set AvatarName = ?, Path = ? where UserId = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, avaName);
				stmt.setString(2, path);
				stmt.setString(3, userId);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
