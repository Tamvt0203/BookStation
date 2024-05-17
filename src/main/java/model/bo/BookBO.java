package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.bean.Book;
import model.bean.FavouriteBook;
import model.dao.BookDAO;

public class BookBO {
	private BookDAO dao;

	public List<Book> findAll() {
		return dao.findAll();
	}

	public Book findById(String id) {
		return dao.findById(Integer.parseInt(id));
	}

	public List<Book> findFavouriteBook(List<FavouriteBook> list) {
		List<Book> res = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Book book = dao.findById(list.get(i).getBookId());
			res.add(book);
		}
		return res;
	}

	public BookBO() {
		super();
		this.dao = new BookDAO();
	}

	public void update(String bookId, String bookName, String authorId, String category1, String category2,
			String category3, String summary, String imageName, String path) {
		dao.update(bookId, bookName, authorId, category1, category2, category3, summary, imageName, path);
	}

	public void delete(String id) {
		dao.delete(id);
	}

	public int findMaxId() {
		return dao.findMaxId();
	}

	public void updateImage(String bookId, String imageName, String path) {
		dao.updateImage(bookId, imageName, path);
	}

	public void insert(String bookName, String authorId, String category1, String category2, String category3,
			String summary, String imageName, String path) {
		dao.insert(bookName, authorId, category1, category2, category3, summary, imageName, path);
	}
	public Book findBookById(int id) {
		return dao.findBookById(id);
	}
}
