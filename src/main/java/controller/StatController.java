package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.SessionUtils;
import model.bean.Stat;
import model.bean.UserRole;
import model.bo.StatBO;

/**
 * Servlet implementation class StatController
 */
@WebServlet("/StatController")
public class StatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		UserRole userRole =(UserRole) SessionUtils.getInstance().getValue(request, "userRole");
		if(userRole==null||!userRole.getUserRole().equalsIgnoreCase("admin")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/error/access-denied.jsp");
			dispatcher.forward(request, response);
		}
		if(action.equals("view")) {
			StatBO  bo = new StatBO();
			Stat stat = bo.findStat();
			request.setAttribute("stat", stat);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/stat/view.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
