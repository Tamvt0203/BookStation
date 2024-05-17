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
import model.bean.UserRole;
import model.bo.AuthorBO;

/**
 * Servlet implementation class AuthorController
 */
@WebServlet("/AuthorController")
public class AuthorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthorController() {
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
			
			AuthorBO authorBO = new AuthorBO();
			List<Author> list = authorBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/author/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("get-form-edit")) {
			AuthorBO authorBO = new AuthorBO();
			String id = request.getParameter("id");
			Author author = authorBO.findById(id);
			request.setAttribute("author", author);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/author/edit.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-form-edit")) {
			AuthorBO authorBO = new AuthorBO();
			String id = request.getParameter("AuthorId");
			String name = request.getParameter("AuthorName");
			authorBO.update(id, name);
			List<Author> list = authorBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/author/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-delete")) {
			AuthorBO authorBO = new AuthorBO();
			String id = request.getParameter("id");
			authorBO.delete(id);
			List<Author> list = authorBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/author/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("get-form-insert")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/author/insert.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-insert")) {
			AuthorBO authorBO = new AuthorBO();
			String name = request.getParameter("name");
			authorBO.insert(name);
			List<Author> list = authorBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/author/list.jsp");
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
