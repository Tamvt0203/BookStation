package model.bo;

import model.bean.UserRole;
import model.dao.UserRoleDAO;
	
public class UserRoleBO {
	private UserRoleDAO dao;
	public UserRole findByUserId(String userId) {
		return dao.findByUserId(userId);
	}
	public UserRoleBO() {
		super();
		// TODO Auto-generated constructor stub
		this.dao = new UserRoleDAO();
	}
	public void addUserRole(String userId, String nameRole) {
		dao.addUserRole(userId, nameRole);
	}
	
}
