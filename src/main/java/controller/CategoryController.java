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
import model.bean.Category;
import model.bean.UserRole;
import model.bo.CategoryBO;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryController() {
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
			CategoryBO categoryBO = new CategoryBO();
			List<Category> list = categoryBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/category/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("get-form-edit")) {
			CategoryBO categoryBO = new CategoryBO();
			String id = request.getParameter("id");
			Category category = categoryBO.findById(id);
			request.setAttribute("category", category);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/category/edit.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-form-edit")) {
			CategoryBO categoryBO = new CategoryBO();
			String id = request.getParameter("CategoryId");
			String name = request.getParameter("CategoryName");
			categoryBO.update(id, name);
			List<Category> list = categoryBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/category/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-delete")) {
			CategoryBO categoryBO = new CategoryBO();
			String id = request.getParameter("id");
			categoryBO.delete(id);
			List<Category> list = categoryBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/category/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("get-form-insert")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/category/insert.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-insert")) {
			CategoryBO categoryBO = new CategoryBO();
			String name = request.getParameter("name");
			categoryBO.insert(name);
			List<Category> list = categoryBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/category/list.jsp");
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
