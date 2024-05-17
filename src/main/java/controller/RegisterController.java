package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.UserBO;
import model.bo.UserRoleBO;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
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
		String action = request.getParameter("action");
		if (action.equals("get-form-register")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/register/register.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-register")) {
			String username = request.getParameter("email");
			String password = request.getParameter("password");
			UserBO bo = new UserBO();
			long random = Calendar.getInstance().getTimeInMillis();
			System.out.println(random);
			Random rand = new Random();

	        // Generates a random number between 0 (inclusive) and 5 (exclusive)
	        int randomNumber = rand.nextInt(5);
			String avatar = "AV" + randomNumber + ".jpg";
			System.out.println(avatar);
			String realPath = request.getServletContext().getRealPath("/img/book_img");
			UserRoleBO userRoleBO = new UserRoleBO();
			String message = bo.addUser(random + "", username, password, avatar, realPath);
			userRoleBO.addUserRole(random + "", "user");
			if (message == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login/Login.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/register/register.jsp");
				dispatcher.forward(request, response);
			}
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
