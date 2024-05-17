package model.bean;

public class Vote {
	private int voteId;
    private int reviewId;
    private String userId;
    private int voteValue;

    // Constructors
    public Vote() {
    }

    public Vote(int voteId, int reviewId, String userId, int voteValue) {
        this.voteId = voteId;
        this.reviewId = reviewId;
        this.userId = userId;
        this.voteValue = voteValue;
    }

    // Getters and Setters
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(int voteValue) {
        this.voteValue = voteValue;
    }
}
