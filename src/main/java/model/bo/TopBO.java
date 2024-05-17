package model.bo;

import model.bean.Top;
import model.dao.TopDAO;

public class TopBO {
	private TopDAO dao;

	public Top findTop() {
		return dao.findTop();
	}

	public TopBO() {
		super();
		// TODO Auto-generated constructor stub
		this.dao = new TopDAO();
	}

}
