package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import context.SessionUtils;
import model.bean.UserRole;
import model.bean.Report;
import model.bean.Review;
import model.bo.*;
/**
 * Servlet implementation class APIController
 */
@WebServlet("/APIController")
public class APIController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FavouriteBookBO favouriteBookBO;
	VoteBO voteBO;
	ReviewBO reviewBO;
	ReportBO reportBO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APIController() {
        super();
        // TODO Auto-generated constructor stub
        favouriteBookBO = new FavouriteBookBO();
        voteBO = new VoteBO();
        reviewBO = new ReviewBO();
        reportBO = new ReportBO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserRole userRole = null;
		userRole = (UserRole) SessionUtils.getInstance().getValue(request, "userRole");
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JsonObject jsonResponse = new JsonObject();
        try {
            JsonObject requestBody = new JsonParser().parse(request.getReader()).getAsJsonObject();
            String action = requestBody.get("action").getAsString();

            switch (action) {
                case "addFavouriteBook":
	                  int bookId = requestBody.get("BookId").getAsInt();
	                  boolean isAdding;
	                  if(favouriteBookBO.isFavouriteBookExists(bookId, userRole.getUserId())) {
	                  	 isAdding = favouriteBookBO.removeFavouriteBook(bookId, userRole.getUserId());
	                  }else {
	                  	isAdding = favouriteBookBO.addFavouriteBook(bookId, userRole.getUserId());
	                  }
	                  jsonResponse.addProperty("success", true);
	                  jsonResponse.addProperty("isAdding", isAdding);
                    break;
                case "upVote":
                	int reviewId = requestBody.get("reviewId").getAsInt();
                	int value = requestBody.get("value").getAsInt();
                	if(voteBO.doesVoteExist(reviewId, userRole.getUserId(), value)) {
                		System.out.println("Có tồn tại upVote gọi hàm delete");
                		if(voteBO.deleteVote(reviewId, userRole.getUserId(), value)) {
                			//jsonResponse.addProperty("success", true);
                			Review review = reviewBO.getReviewByReviewId(reviewId);
                			String result = String.valueOf(review.getTotalVotes());
                			System.out.println("result upVotes: " + result);
                	        jsonResponse.addProperty("result", result);
                		}else {
                			jsonResponse.addProperty("success", false);
                			jsonResponse.addProperty("result", "0");
                		}
                	}else {
                		System.out.println("Ko tồn tại upVote gọi hàm add");
                		if(voteBO.addVote(reviewId, userRole.getUserId(), value)) {
                			//jsonResponse.addProperty("success", true);
                			Review review = reviewBO.getReviewByReviewId(reviewId);
                			String result = String.valueOf(review.getTotalVotes());
                			System.out.println("result upVotes: " + result);
                	        jsonResponse.addProperty("result", result);
                		}else {
                			jsonResponse.addProperty("success", false);
                			jsonResponse.addProperty("result", "0");
                		}
                	}
                    break;
                case "downVote":
                	int ReviewId = requestBody.get("reviewId").getAsInt();
                	int Value = requestBody.get("value").getAsInt();
                	if(voteBO.doesVoteExist(ReviewId, userRole.getUserId(), Value)) {
                		System.out.println("Có tồn tại downVote gọi hàm delete");
                		if(voteBO.deleteVote(ReviewId, userRole.getUserId(), Value)) {
                			//jsonResponse.addProperty("success", true);
                			Review review = reviewBO.getReviewByReviewId(ReviewId);
                			String result = String.valueOf(review.getTotalVotes());
                			System.out.println("result downVotes: " + result);
                	        jsonResponse.addProperty("result", result);
                		}else {
                			jsonResponse.addProperty("success", false);
                			jsonResponse.addProperty("result", "0");
                		}
                	}else {
                		System.out.println("Không tồn tại downVote gọi hàm add");
                		if(voteBO.addVote(ReviewId, userRole.getUserId(), Value)) {
                			//jsonResponse.addProperty("success", true);
                			Review review = reviewBO.getReviewByReviewId(ReviewId);
                			String result = String.valueOf(review.getTotalVotes());
                			System.out.println("result downVotes: " + result);
                	        jsonResponse.addProperty("result", result);
                		}else {
                			jsonResponse.addProperty("success", false);
                			jsonResponse.addProperty("result", "0");
                		}
                	}
                    break;
                case "addReport":
                	int ReportedReviewId = requestBody.get("ReviewId").getAsInt();
                	boolean IsAdding = false;
                	Report report = reportBO.findByReviewIdAndUserIdReport(ReportedReviewId, userRole.getUserId());
                	if(report.getReviewId() == 0) {
                		System.out.println("API: add");
                		IsAdding = reportBO.addReport(ReportedReviewId, userRole.getUserId());
                	}else {
                		System.out.println("API: delete");
                		IsAdding = reportBO.deleteReport(ReportedReviewId, userRole.getUserId());
                	}
                	jsonResponse.addProperty("success", true);
	                jsonResponse.addProperty("isAdding", IsAdding);
                	break;
                default:
                    // Xử lý trường hợp không xác định hoặc không hỗ trợ action
                    jsonResponse.addProperty("success", false);
                    jsonResponse.addProperty("error", "Invalid action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("error", "An error occurred.");
        }

        out.println(jsonResponse.toString());
        out.close();
        
    }
}
//        try {
//        	JsonObject requestBody = new JsonParser().parse(request.getReader()).getAsJsonObject();
//            int bookId = requestBody.get("BookId").getAsInt();
//            boolean isAdding;
//            if(favouriteBookBO.isFavouriteBookExists(bookId, userRole.getUserId())) {
//            	 isAdding = favouriteBookBO.removeFavouriteBook(bookId, userRole.getUserId());
//            }else {
//            	isAdding = favouriteBookBO.addFavouriteBook(bookId, userRole.getUserId());
//            }
//            jsonResponse.addProperty("success", true);
//            jsonResponse.addProperty("isAdding", isAdding);
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsonResponse.addProperty("success", false);
//            jsonResponse.addProperty("error", "An error occurred.");
//        }
//        out.println(jsonResponse.toString());
//        out.close();
	
//	}

//}
