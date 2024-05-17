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
//import model.bean.ReportedReview;
//import model.bean.Review;
//import model.bean.UserRole;
//import model.bo.ReportedReviewBO;
//import model.bo.ReportBO;
//import model.bo.ReviewBO;
import model.bean.*;
import model.bo.*;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/ReviewController")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookBO bookBO;
	private ReviewBO reviewBO;
	private BookCardBO bookCardBO;
	private ReviewCardBO reviewCardBO;
	private FavouriteBookBO favouriteBookBO;
	private VoteBO voteBO;
	private ReportBO reportBO;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewController() {
		super();
		// TODO Auto-generated constructor stub
		bookBO = new BookBO();
		reviewBO = new ReviewBO();
		bookCardBO = new BookCardBO();
		reviewCardBO = new ReviewCardBO();
		favouriteBookBO = new FavouriteBookBO();
		voteBO = new VoteBO();
		reportBO = new ReportBO();
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private void checkAccess(String role, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserRole userRole = (UserRole) SessionUtils.getInstance().getValue(request, "userRole");
		if (userRole == null || !userRole.getUserRole().equalsIgnoreCase(role)) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/error/access-denied.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action.equals("list")) {
			checkAccess("admin", request, response);
			ReviewBO reviewBO = new ReviewBO();
			List<Review> list = reviewBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/review/list.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("handle-delete")) {
			checkAccess("admin", request, response);
			ReviewBO bo = new ReviewBO();
			String id = request.getParameter("id");
			bo.delete(id);
			ReportedReviewBO dtoReportedReviewBO = new ReportedReviewBO();
			ReportBO reportBO = new ReportBO();
			reportBO.delete(id);
			List<ReportedReview> list = dtoReportedReviewBO.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/report/list.jsp");
			dispatcher.forward(request, response);
		}
		UserRole userRole = null;
		userRole = (UserRole) SessionUtils.getInstance().getValue(request, "userRole");
		if (action != null && action.equals("getReviews")) {
			String Id = request.getParameter("bookId");
			String crpage = request.getParameter("page");
			String num = request.getParameter("size");
			int bookId = Integer.parseInt(Id);
			int currentPage = Integer.parseInt(crpage);
			int itemsPerPage = Integer.parseInt(num);
			Book thisBook = bookBO.findBookById(bookId);
			BookCard thisBookCard = bookCardBO.findBookCardById(bookId);
			int countReview = reviewBO.getTotalReviewsByBookId(bookId);
			List<ReviewCard> listReviewCardsPage = reviewCardBO.getAllReviewCardsByBookId(currentPage, itemsPerPage,
					bookId);
			List<Vote> listVote = voteBO.getAllVotesByBookId(bookId);
			request.setAttribute("thisBook", thisBook);
			request.setAttribute("thisBookCard", thisBookCard);
			request.setAttribute("countReview", countReview);
			request.setAttribute("listReviewCardsPage", listReviewCardsPage);
			request.setAttribute("listVote", listVote);
			if(userRole == null) {
				if(request != null && response != null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/review/Review.jsp");
					dispatcher.forward(request, response);
				}else {
					System.out.println("Dữ liệu chưa được chuyển qua");
				}
			}else {
				List<Report> listReport = reportBO.findReportsByBookId(bookId, userRole.getUserId());
				request.setAttribute("listReport", listReport);
				String stringFavourite = "false";
				if(favouriteBookBO.isFavouriteBookExists(bookId, userRole.getUserId())) {
					stringFavourite = "true";					
				}
				request.setAttribute("stringFavourite", stringFavourite);
				if(reviewCardBO.findReviewCard(bookId, userRole.getUserId()) != null) {
					ReviewCard reviewCardOfUser = reviewCardBO.findReviewCard(bookId, userRole.getUserId());
					request.setAttribute("reviewCardOfUser", reviewCardOfUser);
				}
					request.setAttribute("userRole", userRole);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/review/Review.jsp");
					dispatcher.forward(request, response);				
				//System.out.println("user role: " + userRole.getUserId());
			}
			
			
		} else if (action.equals("EditReview")) {
			String UserId = request.getParameter("UserId");
			String bookId = request.getParameter("BookId");
			int BookId = Integer.parseInt(bookId);
			ReviewCard reviewCard = reviewCardBO.findReviewCard(BookId, UserId);
			request.setAttribute("reviewCard", reviewCard);
			System.out.println("Reviewcontroller tra ve: " + reviewCard.getReviewId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/review/EditReview.jsp");
			dispatcher.forward(request, response);
		}else if (action.equals("DeleteReview")) {
			String ReviewId = request.getParameter("ReviewId");
			int id = Integer.parseInt(ReviewId);
			reviewBO.deleteReview(id);
			String currentUrl = request.getHeader("Referer"); // Lấy đường dẫn của trang trước đó
	        response.getWriter().println("<script>window.location.href='" + currentUrl + "';</script>");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action != null && action.equals("CreateReview")) {
			String userId = request.getParameter("UserId");
	        String bookId = request.getParameter("BookId");
	        int BookId = Integer.parseInt(bookId);
	        String reviewDate = request.getParameter("ReviewDate");
	        String rating = request.getParameter("Rating");
	        int Rating = Integer.parseInt(rating);
	        String upVotes = request.getParameter("UpVotes");
	        int UpVotes = Integer.parseInt(upVotes);
	        String downVotes = request.getParameter("DownVotes");
	        int DownVotes = Integer.parseInt(downVotes);
	        String totalVotes = request.getParameter("TotalVotes");
	        int TotalVotes = Integer.parseInt(totalVotes);
	        String detail = request.getParameter("Detail");
	        Review newReview = new Review(0, userId, BookId, reviewDate, Rating, UpVotes, DownVotes, TotalVotes, detail);
	        reviewBO.insertReview(newReview);
	        String currentUrl = request.getHeader("Referer"); // Lấy đường dẫn của trang trước đó
	        response.getWriter().println("<script>window.location.href='" + currentUrl + "';</script>");
		}else if(action.equals("EditForm")) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
	        String userId = request.getParameter("userId");
	        int bookId = Integer.parseInt(request.getParameter("bookId"));
	        String reviewDate = request.getParameter("reviewDate");
	        int rating = Integer.parseInt(request.getParameter("rating"));
	        int upVotes = Integer.parseInt(request.getParameter("upVotes"));
	        int downVotes = Integer.parseInt(request.getParameter("downVotes"));
	        int totalVotes = Integer.parseInt(request.getParameter("totalVotes"));
	        String detail = request.getParameter("detail");
	        Review updateReview = new Review(reviewId, userId, bookId, reviewDate, rating, upVotes, downVotes, totalVotes, detail);
	        reviewBO.updateReview(updateReview);
	        response.sendRedirect(request.getContextPath() + "/ReviewController?action=getReviews&bookId=" + bookId + "&page=0&size=5");
		}
	}

}
