package model.bo;

import java.util.List;

import model.bean.*;
import model.dao.*;

public class ReviewCardBO {
	ReviewCardDAO reviewCardDAO = new ReviewCardDAO();
	public List<ReviewCard> getAllReviewCardsByBookId(int currentPage, int itemsPerPage, int bookId){
		return reviewCardDAO.getAllReviewCardsByBookId(currentPage, itemsPerPage,bookId);
	}
	public ReviewCard findReviewCard(int BookId, String UserId) {
		return reviewCardDAO.findReviewCard(BookId, UserId);
	}
}
