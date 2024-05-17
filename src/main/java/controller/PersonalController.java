package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import context.SessionUtils;
import model.bean.Personal;
import model.bean.UserRole;
import model.bo.FavouriteBookBO;
import model.bo.PersonalBO;
import model.bo.UserBO;

/**
 * Servlet implementation class PersonalController
 */
@MultipartConfig
@WebServlet("/PersonalController")
public class PersonalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonalController() {
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
		if (userRole == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/error/access-denied.jsp");
			dispatcher.forward(request, response);
		}
		String action = request.getParameter("action");
		if (action.equals("view")) {
			PersonalBO personalBO = new PersonalBO();
			Personal personal = personalBO.getInfor(userRole.getUserId());
			request.setAttribute("personal", personal);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/personal/view.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("delete-fav-book")) {
			String bookId = request.getParameter("id");
			String userId = userRole.getUserId();
			FavouriteBookBO bo = new FavouriteBookBO();
			bo.delete(Integer.parseInt(bookId), userId);

			PersonalBO personalBO = new PersonalBO();
			Personal personal = personalBO.getInfor(userRole.getUserId());
			request.setAttribute("personal", personal);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/personal/view.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("get-edit-ava-form")) {
			PersonalBO personalBO = new PersonalBO();
			Personal personal = personalBO.getInfor(userRole.getUserId());
			request.setAttribute("personal", personal);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/personal/edit-ava.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-edit-ava")) {
			UserBO bo = new UserBO();
			try {
				Part img = request.getPart("img");
				String realPath = request.getServletContext().getRealPath("/img/avt_img");
				String filename = Path.of(img.getSubmittedFileName()).getFileName().toString();
				if (!Files.exists(Path.of(realPath))) {
					Files.createDirectories(Path.of(realPath));
				}
				String extension = "";
				int i = filename.lastIndexOf('.');
				if (i > 0) {
					extension = filename.substring(i + 1);
				}
				filename = userRole.getUserId() + "." + extension;
				img.write(realPath + "/" + filename);
				bo.updateAva(filename, realPath, userRole.getUserId());
			} catch (Exception e) {
			}
			PersonalBO personalBO = new PersonalBO();
			Personal personal = personalBO.getInfor(userRole.getUserId());
			request.setAttribute("personal", personal);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/personal/view.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("get-edit-password-form")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/personal/edit-password.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("handle-edit-password")) {
			String password = request.getParameter("new-password");
			String userId = userRole.getUserId();
			UserBO bo = new UserBO();
			bo.updatePassword(password, userId);
			String message = "Change password successfully!";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/personal/edit-password.jsp");
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
