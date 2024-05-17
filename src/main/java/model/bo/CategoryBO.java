package model.bo;

import java.util.List;

import model.bean.Category;
import model.dao.CategoryDAO;

public class CategoryBO {
	private CategoryDAO dao;

	public List<Category> findAll() {
		return dao.findAll();
	}
	public Category findById(String id) {
		return dao.findById(id);
	}
	public CategoryBO() {
		super();
		this.dao = new CategoryDAO();
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
