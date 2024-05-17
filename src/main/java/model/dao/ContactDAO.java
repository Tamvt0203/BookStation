package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.Connector;
import model.bean.Contact;

public class ContactDAO {
	public List<Contact> findAll() {
		List<Contact> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM contacts";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int contactId = rs.getInt("Id");
					String userId = rs.getString("UserId");
					String content = rs.getString("Content");
					int status = rs.getInt("Status");
					Contact contact = new Contact(contactId, userId, content, status);
					list.add(contact);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Contact findById(String id) {
		List<Contact> list = new ArrayList<>();
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM contacts where Id = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int contactId = rs.getInt("Id");
					String userId = rs.getString("UserId");
					String content = rs.getString("Content");
					int status = rs.getInt("Status");
					Contact contact = new Contact(contactId, userId, content, status);
					list.add(contact);
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

	public void updateStatus(String id, int status) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "UPDATE contacts SET Status = ?  where Id = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, status);
				stmt.setString(2, id);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String id) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "DELETE FROM contacts where Id = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String userId, String content, int status) {
		try (Connection conn = Connector.getConnection()) {
			String sql = "INSERT INTO  contacts(UserId, Content, Status) VALUES(?,?,?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, userId);
				stmt.setString(2, content);
				stmt.setInt(3, status);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
