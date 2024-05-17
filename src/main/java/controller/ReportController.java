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
import model.bean.ReportedReview;
import model.bean.UserRole;
import model.bo.ReportedReviewBO;

/**
 * Servlet implementation class ReportController
 */
@WebServlet("/ReportController")
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportController() {
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
		ReportedReviewBO bo = new ReportedReviewBO();
		if (action.equals("list")) {
			List<ReportedReview> list = bo.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/report/list.jsp");
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
