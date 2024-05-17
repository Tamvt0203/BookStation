package model.bo;
import java.util.List;

import model.bean.*;
import model.dao.*;
public class VoteBO {
	VoteDAO voteDAO = new VoteDAO();
	public Vote getVoteByReviewAndUser(int reviewId, String userId) {
		return voteDAO.getVoteByReviewAndUser(reviewId, userId);
	}
	public boolean deleteVote(int reviewId, String userId,int voteValue) {
		return voteDAO.deleteVote(reviewId, userId, voteValue);
	}
	public boolean addVote(int reviewId, String userId, int voteValue) {
		return voteDAO.addVote(reviewId, userId, voteValue);
	}
	public Vote findVoteByUserAndReview(String userId, int reviewId) {
		return voteDAO.findVoteByUserAndReview(userId, reviewId);
	}
	public List<Vote> getAllVotesByBookId(int bookId){
		return voteDAO.getAllVotesByBookId(bookId);
	}
	public boolean doesVoteExist(int reviewId, String userId, int voteValue) {
		return voteDAO.doesVoteExist(reviewId, userId, voteValue);
	}
}
