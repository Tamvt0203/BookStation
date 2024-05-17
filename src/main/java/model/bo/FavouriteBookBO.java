package model.bo;

import java.util.List;

import model.bean.*;
import model.dao.*;

public class FavouriteBookBO {
	FavouriteBookDAO favouriteBookDAO = new FavouriteBookDAO();

	public List<FavouriteBook> findFavouriteBookByUserId(String UserId) {
		return favouriteBookDAO.findFavouriteBookByUserId(UserId);
	}

	public boolean isFavouriteBookExist(int bookId, String userId) {
		return favouriteBookDAO.isFavouriteBookExist(bookId, userId);
	}
	public void delete(int bookId, String userId) {
		favouriteBookDAO.delete(bookId, userId);
	}
	public boolean isFavouriteBookExists(int bookId, String userId) {
		FavouriteBook book = favouriteBookDAO.isFavouriteBookExists(bookId, userId);		
		if(book != null) {
			System.out.println("BO: "+ book.getBookId());
			return true;
		}else {
			return false;
		}
	}
	public boolean addFavouriteBook(int bookId, String userId) {
		System.out.println("BO: addFavouriteBook");
		return favouriteBookDAO.addFavouriteBook(bookId, userId);
	}
	public boolean removeFavouriteBook(int bookId, String userId) {
		return favouriteBookDAO.removeFavouriteBook(bookId, userId);
	}
}
