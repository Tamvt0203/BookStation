package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import context.SessionUtils;
import model.bean.Author;
import model.bean.Book;
import model.bean.Category;
import model.bean.UserRole;
import model.bo.AuthorBO;
import model.bo.BookBO;
import model.bo.CategoryBO;

/**
 * Servlet implementation class BookController
 */
@MultipartConfig
@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookController() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserRole userRole = (UserRole) SessionUtils.getInstance().getValue(request, "userRole");
		if (userRole == null || !userRole.getUserRole().equalsIgnoreCase("admin")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/error/access-denied.jsp");
			dispatcher.forward(request, response);
		}
		String action = request.getParameter("action");
		if (action.equals("list")) {
			BookBO bookBO = new BookBO();
			List<Book> list = bookBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/book/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("get-form-edit")) {
			BookBO bookBO = new BookBO();
			String id = request.getParameter("id");
			Book book = bookBO.findById(id);
			request.setAttribute("book", book);
			CategoryBO categoryBO = new CategoryBO();
			AuthorBO authorBO = new AuthorBO();
			List<Author> authors = authorBO.findAll();
			List<Category> categories = categoryBO.findAll();
			Category category1 = categoryBO.findById(book.getCategoryId1() + "");
			Category category2 = categoryBO.findById(book.getCategoryId2() + "");
			Category category3 = categoryBO.findById(book.getCategoryId3() + "");
			if (category1 == null) {
				request.setAttribute("category1", "null");
			} else {
				request.setAttribute("category1", category1.getCategoryName());
			}
			if (category2 == null) {
				request.setAttribute("category2", "null");
			} else {
				request.setAttribute("category2", category2.getCategoryName());

			}
			if (category3 == null) {
				request.setAttribute("category3", "null");
			} else {
				request.setAttribute("category3", category3.getCategoryName());

			}
			Author author = authorBO.findById(Integer.toString(book.getAuthorId()));
			if (author != null) {
				request.setAttribute("bookAuthor", author);
			} else {
				request.setAttribute("bookAuthor", new Author());
			}
			request.setAttribute("authorList", authors);
			request.setAttribute("authorList", authors);
			request.setAttribute("categoryList", categories);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/book/edit.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-form-edit")) {
			BookBO bookBO = new BookBO();
			String bookName = request.getParameter("book-name");
			String bookId = request.getParameter("book-id");
			String authorId = request.getParameter("author-id");
			String category1 = request.getParameter("category1");
			String category2 = request.getParameter("category2");
			String category3 = request.getParameter("category3");
			String summary = request.getParameter("summary");
			try {
				Part img = request.getPart("img");
				String realPath = request.getServletContext().getRealPath("/img/book_img");
				String filename = Path.of(img.getSubmittedFileName()).getFileName().toString();
				if (!Files.exists(Path.of(realPath))) {
					Files.createDirectories(Path.of(realPath));
				}
				String extension = "";
				int i = filename.lastIndexOf('.');
				if (i > 0) {
					extension = filename.substring(i + 1);
				}
				filename = bookId + "." + extension;
				img.write(realPath + "/" + filename);
				bookBO.update(bookId, bookName, authorId, category1, category2, category3, summary, filename, realPath);
			} catch (Exception e) {
				e.printStackTrace();
				String imgName = request.getParameter("backup-img-name");
				String path = request.getParameter("backup-path");
				bookBO.update(bookId, bookName, authorId, category1, category2, category3, summary, imgName, path);
			}
			List<Book> list = bookBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/book/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-delete")) {
			BookBO bookBO = new BookBO();
			String id = request.getParameter("id");
			bookBO.delete(id);
			List<Book> list = bookBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/book/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("get-form-insert")) {
			CategoryBO categoryBO = new CategoryBO();
			AuthorBO authorBO = new AuthorBO();
			List<Author> authors = authorBO.findAll();
			List<Category> categories = categoryBO.findAll();
			request.setAttribute("authorList", authors);
			request.setAttribute("categoryList", categories);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/book/insert.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-insert")) {
			BookBO bookBO = new BookBO();
			String bookName = request.getParameter("book-name");
			String authorId = request.getParameter("author-id");
			String category1 = request.getParameter("category1");
			String category2 = request.getParameter("category2");
			String category3 = request.getParameter("category3");
			String summary = request.getParameter("summary");
			bookBO.insert(bookName, authorId, category1, category2, category3, summary, "", "");
			int maxId = bookBO.findMaxId();
			Part img = request.getPart("img");

			String realPath = request.getServletContext().getRealPath("/img/book_img");
			String filename = Path.of(img.getSubmittedFileName()).getFileName().toString();

			if (!Files.exists(Path.of(realPath))) {
				Files.createDirectories(Path.of(realPath));
			}
			String extension = "";

			int i = filename.lastIndexOf('.');
			if (i > 0) {
				extension = filename.substring(i + 1);
			}
			filename = maxId + "." + extension;
			img.write(realPath + "/" + filename);
			bookBO.updateImage(maxId + "", filename, realPath);
//			bookBO.insert(name);
			List<Book> list = bookBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/book/list.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
