package model.bean;

import java.util.ArrayList;
import java.util.List;

public class Personal {
	private String gmail;

	private String avaName;
	private String avaPath;
	private int totalBookReviewd;
	private int totalUpVotes;
	private int totalDownVotes;
	private int totalVotes;
	private String highestVotedReview;

	public String getAvaName() {
		return avaName;
	}

	public void setAvaName(String avaName) {
		this.avaName = avaName;
	}

	public String getAvaPath() {
		return avaPath;
	}

	public void setAvaPath(String avaPath) {
		this.avaPath = avaPath;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	private List<Book> listFavouriteBooks;

	public int getTotalBookReviewd() {
		return totalBookReviewd;
	}

	public void setTotalBookReviewd(int totalBookReviewd) {
		this.totalBookReviewd = totalBookReviewd;
	}

	public int getTotalUpVotes() {
		return totalUpVotes;
	}

	public void setTotalUpVotes(int totalUpVotes) {
		this.totalUpVotes = totalUpVotes;
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

	public String getHighestVotedReview() {
		return highestVotedReview;
	}

	public void setHighestVotedReview(String highestVotedReview) {
		this.highestVotedReview = highestVotedReview;
	}

	public List<Book> getListFavouriteBooks() {
		return listFavouriteBooks;
	}

	public void setListFavouriteBooks(List<Book> listFavouriteBooks) {
		this.listFavouriteBooks = listFavouriteBooks;
	}

	public Personal() {
		super();
		this.listFavouriteBooks = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Personal [gmail=" + gmail + ", avaName=" + avaName + ", avaPath=" + avaPath + ", totalBookReviewd="
				+ totalBookReviewd + ", totalUpVotes=" + totalUpVotes + ", totalDownVotes=" + totalDownVotes
				+ ", totalVotes=" + totalVotes + ", highestVotedReview=" + highestVotedReview + ", listFavouriteBooks="
				+ listFavouriteBooks + "]";
	}

}
