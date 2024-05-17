package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.Connector;
import model.bean.Book;
import model.bean.BookCard;
import model.dao.AuthorDAO;
import model.dao.CategoryDAO;

public class BookCardDAO {
	public Book findBookById(int bookId) {
	    Book book = null;
	    String sql = "SELECT * FROM Books WHERE BookId = ?";
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                book = new Book();
	                book.setBookId(resultSet.getInt("BookId"));
	                book.setBookName(resultSet.getString("BookName"));
	                book.setAuthorId(resultSet.getInt("AuthorId"));
	                book.setCategoryId1(resultSet.getInt("CategoryId1"));
	                book.setCategoryId2(resultSet.getInt("CategoryId2"));
	                book.setCategoryId3(resultSet.getInt("CategoryId3"));
	                book.setSummary(resultSet.getString("Summary"));
	                book.setImageName(resultSet.getString("ImageName"));
	                book.setPath(resultSet.getString("Path"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle the exception according to your application's needs
	    }

	    return book;
	}
	public BookCard findBookCardById(int bookId) {
	    Book book = findBookById(bookId);
	    AuthorDAO author = new AuthorDAO();
		CategoryDAO category = new CategoryDAO();
	    BookCard bookCard = new BookCard(book.getBookId(),book.getBookName(),
	    						author.findAuthorNameByBookId(book.getBookId()),category.findCategoryNameByBookId(book.getBookId(), 1),
	    						category.findCategoryNameByBookId(book.getBookId(), 2),book.getSummary(),book.getPath(),book.getImageName()
	    						,calculateAverageRatingByBookId(book.getBookId()));
	    	    
	    return bookCard;
	}
	public List<Book> listPopularBooks(int num) {
        List<Book> listPopularBooks = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT TOP (?) BookId, COUNT(*) AS ReviewCount " +
                             "FROM Reviews GROUP BY BookId ORDER BY ReviewCount DESC;")) {
            preparedStatement.setInt(1, num);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("BookId");
                    Book book = findBookById(bookId);
                    if (book != null) {
                        listPopularBooks.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's needs
        }
        return listPopularBooks;
    }
	public String selectText(String fullText, int limit) {
	    if (fullText.length() > limit) {
	        return fullText.substring(0, limit);
	    } else {
	        return fullText;
	    }
	}
	public List<BookCard> listBookCards(int num){
		List<BookCard> listBookCards = new ArrayList<>();		
		AuthorDAO author = new AuthorDAO();
		CategoryDAO category = new CategoryDAO();
		List<Book> listPopularBooks = listPopularBooks(num);
		for(Book book : listPopularBooks) {
			BookCard bookCard = new BookCard();
			bookCard.setBookId(book.getBookId());
			bookCard.setBookName(book.getBookName());			
			bookCard.setAuthorName(author.findAuthorNameByBookId(book.getBookId()));			 
			bookCard.setCategoryName1(category.findCategoryNameByBookId(book.getBookId(), 1));
			bookCard.setCategoryName2(category.findCategoryNameByBookId(book.getBookId(), 2));
			bookCard.setSummary(selectText(book.getSummary(), 350));
			bookCard.setPath(book.getPath());
			bookCard.setImageName(book.getImageName());
			bookCard.setAverageRating(calculateAverageRatingByBookId(book.getBookId()));
			listBookCards.add(bookCard);
		}
		return listBookCards;
	}
	public List<BookCard> getAllBookCards(){
		List<BookCard> listBookCards = new ArrayList<>();		
		AuthorDAO author = new AuthorDAO();
		CategoryDAO category = new CategoryDAO();
		BookDAO allBooks = new BookDAO();
		List<Book> listBooks = allBooks.findAll();
		for(Book book : listBooks) {
			BookCard bookCard = new BookCard();
			bookCard.setBookId(book.getBookId());
			bookCard.setBookName(book.getBookName());			
			bookCard.setAuthorName(author.findAuthorNameByBookId(book.getBookId()));			 
			bookCard.setCategoryName1(category.findCategoryNameByBookId(book.getBookId(), 1));
			bookCard.setCategoryName2(category.findCategoryNameByBookId(book.getBookId(), 2));
			bookCard.setSummary(selectText(book.getSummary(), 350));
			bookCard.setPath(book.getPath());
			bookCard.setImageName(book.getImageName());
			bookCard.setAverageRating(calculateAverageRatingByBookId(book.getBookId()));
			listBookCards.add(bookCard);
		}
		return listBookCards;
	}
	public double calculateAverageRatingByBookId(int bookId) {
	    double averageRating = 0.0;
	    String sql = "SELECT AVG(CAST(Rating AS FLOAT)) AS AverageRating FROM Reviews WHERE BookId = ?";	    
	    try (Connection connection = Connector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, bookId);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                averageRating = resultSet.getDouble("AverageRating");
	                averageRating = Math.round(averageRating * 10.0) / 10.0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }	    
	    return averageRating;
	}
}
