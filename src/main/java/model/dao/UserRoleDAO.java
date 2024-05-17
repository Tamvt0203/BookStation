package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import context.Connector;
import model.bean.UserRole;

public class UserRoleDAO {
	public UserRole findByUserId(String userId) {
		UserRole userRole = null;
		String sql = "SELECT r.Name, ur.UserId, LEFT(u.UserName, CHARINDEX('@', u.UserName + '@') - 1) AS UserName FROM UserRoles "
				+ "AS ur INNER JOIN Roles r ON  r.Id = ur.RoleId INNER JOIN Users u ON  u.UserId = ur.UserId WHERE u.UserId = ?";
		try (Connection connection = Connector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					userRole = new UserRole();
					userRole.setUserId(resultSet.getString("UserId"));
					userRole.setUserRole(resultSet.getString("Name"));
					userRole.setUserName(resultSet.getString("UserName"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userRole;
	}

	public String findRoleId(String name) {
		String res = null;
		String sql = "select id from Roles where name = ?";
		try (Connection connection = Connector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, name);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					res = resultSet.getString("id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public void addUserRole(String userId, String nameRole) {
		String roleId = findRoleId(nameRole);
		try (Connection conn = Connector.getConnection()) {
			String sql = "insert into UserRoles(UserId, RoleId) values(?,?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, userId);
				stmt.setString(2, roleId);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
