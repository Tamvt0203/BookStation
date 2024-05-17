package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import model.bean.Book;
import model.bean.BookCard;
import model.bean.Category;
import model.bo.CategoryBO;
import model.bo.BookBO;
import model.bo.BookCardBO;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryBO categoryBO;
	private BookBO bookBO;
	private BookCardBO bookCardBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
		categoryBO = new CategoryBO();
		bookBO = new BookBO();
		bookCardBO = new BookCardBO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Lấy dữ liệu danh sách danh mục từ database hoặc từ nguồn dữ liệu khác
		List<Category> listCategories = categoryBO.findAll();
		List<Book> listBooks = bookBO.findAll();
		List<BookCard> listCardBooks = bookCardBO.getListBookCards();
		List<BookCard> listAllBookCard = bookCardBO.getAllBookCards();
		// Đặt danh sách danh mục vào request attribute
		request.setAttribute("listBooks", listBooks);
		request.setAttribute("listCardBooks", listCardBooks);
		request.setAttribute("listAllBookCard", listAllBookCard);
		request.setAttribute("listCategories", listCategories);
		// Chuyển hướng tới trang JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/home/Home.jsp");
		dispatcher.forward(request, response);
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
