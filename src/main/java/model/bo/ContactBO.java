package model.bo;

import java.util.List;

import model.bean.Contact;
import model.dao.ContactDAO;

public class ContactBO {
	private ContactDAO dao;

	public ContactBO() {
		super();
		// TODO Auto-generated constructor stub
		this.dao = new ContactDAO();
	}
	
	public List<Contact> findAll() {
		return dao.findAll();
	}
	public Contact findById(String id) {
		return dao.findById(id);
	}
	public void updateStatus(String id, int status) {
		dao.updateStatus(id, status);
	}
	public void insert(String userId, String content, int status) {
		dao.insert(userId, content, status);
	}

}
