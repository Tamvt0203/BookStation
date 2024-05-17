package model.bo;

import model.bean.User;
import model.dao.*;

public class UserBO {
	UserDAO userDAO = new UserDAO();

	public User findUserById(String userId) {
		return userDAO.findUserById(userId);
	}

	public String findUserId(String username, String password) {
		return userDAO.findUserId(username, password);
	}

	public void updateAva(String avaName, String path, String userId) {
		userDAO.updateAva(avaName, path, userId);
	}

	public void updatePassword(String password, String userId) {
		userDAO.updatePassword(password, userId);
	}

	public String addUser(String userId, String userName, String password, String avatarName, String path) {
		return userDAO.addUser(userId, userName, password, avatarName, path);
	}
}
