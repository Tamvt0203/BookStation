package model.bo;

import model.bean.Stat;
import model.dao.StatDAO;

public class StatBO {
	private StatDAO statDAO;

	public StatBO() {
		super();
		// TODO Auto-generated constructor stub
		this.statDAO = new StatDAO();
	}
	public Stat findStat() {
		return statDAO.findStat();
	}

}
