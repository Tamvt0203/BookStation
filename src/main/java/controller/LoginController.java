package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.SessionUtils;
import model.bean.*;
import model.bo.*;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserBO userBO;
	private CategoryBO categoryBO;
	private BookBO bookBO;
	private BookCardBO bookCardBO;
	private UserRoleBO userRoleBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		userBO = new UserBO();
		userRoleBO = new UserRoleBO();
		categoryBO = new CategoryBO();
		bookBO = new BookBO();
		bookCardBO = new BookCardBO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action.equals("handle-login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			UserBO bo = new UserBO();
			String userId = bo.findUserId(email, password);
			System.out.println(userId);
			if (userId != null) {
				UserRole userRole = userRoleBO.findByUserId(userId);
				SessionUtils.getInstance().putValue(request, "userRole", userRole);// save session user login
				if (userRole == null) {
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher("/view/error/access-denied.jsp");
					dispatcher.forward(request, response);
				}
				// Chuyển hướng tới trang JSP
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
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login/Login.jsp");
				dispatcher.forward(request, response);
			}
		} else if (action.equals("get-form-login")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login/Login.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-logout")) {
			SessionUtils.getInstance().removeValue(request, "userRole");
			response.sendRedirect(request.getContextPath() + "/LoginController?action=get-form-login");
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
