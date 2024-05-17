package model.bean;

import java.util.ArrayList;
import java.util.List;

public class Reviewer {
	private String username;
	private int totalUpvotes;
	private int totalDownVotes;
	private int totalVotes;
	private List<ReviewOverview> list;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTotalUpvotes() {
		return totalUpvotes;
	}

	public void setTotalUpvotes(int totalUpvotes) {
		this.totalUpvotes = totalUpvotes;
	}

	public int getTotalDownVotes() {
		return totalDownVotes;
	}

	public void setTotalDownVotes(int totalDownVotes) {
		this.totalDownVotes = totalDownVotes;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	public List<ReviewOverview> getList() {
		return list;
	}

	public void setList(List<ReviewOverview> list) {
		this.list = list;
	}

	public Reviewer() {
		super();
		this.list = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Reviewer [username=" + username + ", totalUpvotes=" + totalUpvotes + ", totalDownVotes="
				+ totalDownVotes + ", totalVotes=" + totalVotes + "]";
	}

}
