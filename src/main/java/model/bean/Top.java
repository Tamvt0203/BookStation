package model.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class Top {
	private Map<String, Integer> popularBooks;
	private Map<String, Double> highestRatedBooks;
	private Map<String, Integer> highestVotedReviewers;
	private Map<String, Integer> enthusiasticReviewers;

	public Top() {
		super();
		// TODO Auto-generated constructor stub
		this.popularBooks = new LinkedHashMap<>();
		this.highestRatedBooks = new LinkedHashMap<>();
		this.highestVotedReviewers = new LinkedHashMap<>();
		this.enthusiasticReviewers = new LinkedHashMap<>();
	}

	public Map<String, Integer> getPopularBooks() {
		return popularBooks;
	}

	public void setPopularBooks(Map<String, Integer> popularBooks) {
		this.popularBooks = popularBooks;
	}

	public Map<String, Double> getHighestRatedBooks() {
		return highestRatedBooks;
	}

	public void setHighestRatedBooks(Map<String, Double> highestRatedBooks) {
		this.highestRatedBooks = highestRatedBooks;
	}

	public Map<String, Integer> getHighestVotedReviewers() {
		return highestVotedReviewers;
	}

	public void setHighestVotedReviewers(Map<String, Integer> highestVotedReviewers) {
		this.highestVotedReviewers = highestVotedReviewers;
	}

	public Map<String, Integer> getEnthusiasticReviewers() {
		return enthusiasticReviewers;
	}

	public void setEnthusiasticReviewers(Map<String, Integer> enthusiasticReviewers) {
		this.enthusiasticReviewers = enthusiasticReviewers;
	}

	@Override
	public String toString() {
		return "Top [popularBooks=" + popularBooks + ", highestRatedBooks=" + highestRatedBooks
				+ ", highestVotedReviewers=" + highestVotedReviewers + ", enthusiasticReviewers="
				+ enthusiasticReviewers + "]";
	}

}
