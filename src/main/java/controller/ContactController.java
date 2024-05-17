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
import model.bean.Author;
import model.bean.Book;
import model.bean.BookCard;
import model.bean.Category;
import model.bean.Contact;
import model.bean.UserRole;
import model.bo.AuthorBO;
import model.bo.BookBO;
import model.bo.BookCardBO;
import model.bo.CategoryBO;
import model.bo.ContactBO;
import model.bo.UserBO;
import model.bo.UserRoleBO;

/**
 * Servlet implementation class ContactController
 */
@WebServlet("/ContactController")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserBO userBO;
	private CategoryBO categoryBO;
	private BookBO bookBO;
	private BookCardBO bookCardBO;
	private UserRoleBO userRoleBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactController() {
		super();
		userBO = new UserBO();
		userRoleBO = new UserRoleBO();
		categoryBO = new CategoryBO();
		bookBO = new BookBO();
		bookCardBO = new BookCardBO();
		// TODO Auto-generated constructor stub
	}

	private void checkAccess(String role, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserRole userRole = (UserRole) SessionUtils.getInstance().getValue(request, "userRole");
		if (userRole == null || !userRole.getUserRole().equalsIgnoreCase(role)) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/error/access-denied.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ContactBO bo = new ContactBO();
		String action = request.getParameter("action");
		if (action.equals("get-form-insert")) {
			checkAccess("user", request, response);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/contact/insert.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-insert")) {
			checkAccess("user", request, response);
			String userId = request.getParameter("user-id");
			String content = request.getParameter("content");
			String status = request.getParameter("status");
			int statusInt = Integer.parseInt(status);
			bo.insert(userId, content, statusInt);

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
		} else if (action.equals("list")) {
			checkAccess("admin", request, response);
			List<Contact> list = bo.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/contact/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("process")) {
			checkAccess("admin", request, response);
			String id = request.getParameter("id");
			bo.updateStatus(id, 1);
			List<Contact> list = bo.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/contact/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("undo-process")) {
			checkAccess("admin", request, response);
			String id = request.getParameter("id");
			bo.updateStatus(id, 0);
			List<Contact> list = bo.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/contact/list.jsp");
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
