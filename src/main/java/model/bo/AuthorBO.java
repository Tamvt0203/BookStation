package model.bo;

import java.util.List;

import model.bean.Author;
import model.dao.AuthorDAO;

public class AuthorBO {
	private AuthorDAO dao;

	public List<Author> findAll() {
		return dao.findAll();
	}
	public Author findById(String id) {
		return dao.findById(id);
	}
	public AuthorBO() {
		super();
		this.dao = new AuthorDAO();
	}
	public void update(String id, String name) {
		dao.update(id, name);
	}
	public void delete(String id) {
		dao.delete(id);
	}
	public void insert(String name) {
		dao.insert(name);
	}
	
}
