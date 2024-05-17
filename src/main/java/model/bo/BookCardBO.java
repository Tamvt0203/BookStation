package model.bo;

import java.util.List;

import model.bean.BookCard;
import model.dao.BookCardDAO;

public class BookCardBO {
	BookCardDAO card = new BookCardDAO();
	public List<BookCard> getListBookCards(){
		return card.listBookCards(10);
	}
	public List<BookCard> getAllBookCards(){
		return card.getAllBookCards();
	}
	public BookCard findBookCardById(int bookId) {
		return card.findBookCardById(bookId);
	}
}
