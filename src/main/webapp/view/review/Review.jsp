<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List" import="model.bean.*"
	import="java.util.ArrayList" import="java.util.Random"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%
String pageVar = request.getParameter("page");
int itemsPerPage = 5;
int totalItems = (int) request.getAttribute("countReview");
int currentPage = (pageVar == null ? 0 : Integer.parseInt(pageVar));
int pageCount = (totalItems - 1) / itemsPerPage + 1;
int firstPage = 0;
int lastPage = pageCount - 1;
currentPage = (currentPage > pageCount ? (pageCount - 1) : currentPage);

List<Integer> pageNumbers;

if (pageCount <= 3) {
	pageNumbers = new ArrayList<>();
	for (int i = 0; i < pageCount; i++) {
		pageNumbers.add(i);
	}
} else {
	if ((currentPage + 1) == pageCount) {
		Integer[] range = {currentPage - 2, currentPage - 1, currentPage};
		pageNumbers = new ArrayList<Integer>();
		for (int num : range) {
	pageNumbers.add(num);
		}
	} else {
		Integer[] range = {currentPage - 1, currentPage, currentPage + 1};
		pageNumbers = new ArrayList<Integer>();
		for (int num : range) {
	pageNumbers.add(num);
		}
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/book_card.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/review_style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/site.css">


<link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Font-Awesome-640/css/all.css" />
</head>
</head>
<body>
	<jsp:include page="../shared/header.jsp" />
	<div class="wrap-container">
		<div class="container py-4">
			<div class="row">
				<div class="col col-9">
					<%
					BookCard thisBookCard = (BookCard) request.getAttribute("thisBookCard");
					UserRole userRole = (UserRole) request.getAttribute("userRole");
					String stringFavouriteBook = "";
					String redirect = "";
					if (userRole == null) {
						redirect = "false";
					} else {
						stringFavouriteBook = (String) request.getAttribute("stringFavourite");
						redirect = "true";
					}
					%>
					<article class="postcard dark red book-detail">
						<div class="book-card-text">
							<h1 class="postcard__title blue"><%=thisBookCard.getBookName()%></h1>
							<div class="postcard__subtitle small">
								<a class="mr-2"><%=thisBookCard.getAuthorName()%></a>
							</div>
							<div class="postcard__bar"></div>
							<div class="postcard__preview-txt"><%=thisBookCard.getSummary()%></div>
							<ul class="postcard__tagbox">
								<li class="tag__item"><i class="mr-2"></i> <a
									href="/Review/Index?bookId=@thisBook.BookId&category_id=@thisBook.CategoryId1"><%=thisBookCard.getCategoryName1()%></a>
								</li>
								<li class="tag__item"><i class="mr-2"></i> <a
									href="/Review/Index?bookId=@thisBook.BookId&category_id=@thisBook.CategoryId2"><%=thisBookCard.getCategoryName2()%></a>
								<li class="tag__item"><i class="mr-2"></i>Average Rating: <%=thisBookCard.getAverageRating()%></li>
							</ul>
						</div>
					</article>
					<%
					String[] classes = new String[]{"blue", "red", "green", "yellow"};
					Random count = new Random();
					if (userRole != null) {
						ReviewCard reviewCardOfUser = (ReviewCard) request.getAttribute("reviewCardOfUser");
						if (reviewCardOfUser != null) {
					%>
					<div class="row review-content ">
						<article
							class="postcard dark @classes[count.Next(0,3)] user-review">
							<div class="book-card-text">
								<div class="user-info">
									<%
									String Avatar = reviewCardOfUser.getAvataName();
									String userName = reviewCardOfUser.getUsername();
									%>
									<img class="user-avatar" src="./img/avt_img/<%=Avatar%>"
										alt="Image Title" /> <a
										class="user-gmail postcard__title <%=classes[count.nextInt(4)]%>"
										href="/Reviewer/Index?userId=<%=reviewCardOfUser.getUserId()%>"><%=userName%></a>
								</div>
								<a class="btn btn-close delete-btn" href="javascript:void(0)"
									onclick="confirmDelete(<%=reviewCardOfUser.getReviewId()%>)"></a>
								<div class="postcard__subtitle small">
									<i class="mr-2"></i><%=reviewCardOfUser.getReviewDate()%>
								</div>
								<div class="postcard__bar"></div>
								<div class="postcard__preview-txt"><%=reviewCardOfUser.getDetail()%></div>
								<ul class="postcard__tagbox" style="list-style-type: none;">
									<li style="margin-right: 12px;">
										<%
										for (int i = 0; i < 5; i++) {
											if (i < reviewCardOfUser.getRating()) {
										%> <i class="fa-solid fa-star" style="color: #ecf000;"></i> <%
 } else {
 %><i class="fa-regular fa-star" style="color: #ecf000;"></i> <%
 }
 }
 %>
									</li>
									<li class="tag__item"><i class="mr-2"></i>Up</li>
									<h5 class="mr-2" style="margin-right: 6px;"><%=reviewCardOfUser.getTotalVotes()%></h5>
									<li class="tag__item"><i class="mr-2"></i>Down</li>
								</ul>
							</div>
						</article>
					</div>
					<%
					}
					}
					List<ReviewCard> listReviewCardsPage = (List<ReviewCard>) request.getAttribute("listReviewCardsPage");
					if (listReviewCardsPage != null) {
					for (ReviewCard reviewCard : listReviewCardsPage) {
					if (reviewCard.getBookId() == thisBookCard.getBookId()) {
					%>
					<div class="row review-content">
						<article class="postcard dark <%=classes[count.nextInt(4)]%>">
							<div class="book-card-text">
								<div class="user-info">
									<%
									String userIdInList = reviewCard.getUserId();
									//IdentityUser user = await UserManager.FindByIdAsync(userIdInList);
									String userName = reviewCard.getUsername();
									String avatar = reviewCard.getAvataName();
									%>
									<img class="user-avatar" src="./img/avt_img/<%=avatar%>"
										alt="Image Title" /> <a
										class="user-gmail postcard__title <%=classes[count.nextInt(4)]%>"
										href="/Reviewer/Index?userId=<%=userIdInList%>"><%=userName%></a>
								</div>
								<div class="postcard__subtitle small">
									<i class="mr-2"></i><%=reviewCard.getReviewDate()%>
								</div>
								<div class="postcard__bar"></div>
								<div class="postcard__preview-txt"><%=reviewCard.getDetail()%></div>
								<%
								String up = " ";
								String down = " ";
								String disableUp = " ";
								String disableDown = " ";
								List<Vote> listVote = (List<Vote>) request.getAttribute("listVote");
								if (userRole != null) {
									if (listVote != null) {
										for (Vote vote : listVote) {
									if (vote.getReviewId() == reviewCard.getReviewId()) {
										if (vote.getUserId().equals(userRole.getUserId())) {
											if (vote != null && vote.getVoteValue() > 0) {
												up = "up-voted";
												disableDown = "disable-vote";
											}

											if (vote != null && vote.getVoteValue() < 0) {
												down = "down-voted";
												disableUp = "disable-vote";
											}
										} else {
											continue;
										}
									}
										}
									}
								}
								%>
								<ul class="postcard__tagbox" style="list-style-type: none;">
									<li style="margin-right: 12px;">
										<%
										for (int i = 0; i < 5; i++) {
										%> <%
 if (i < reviewCard.getRating()) {
 %> <i class="fa-solid fa-star" style="color: #ecf000;"></i> <%
 } else {
 %> <i class="fa-regular fa-star" style="color: #ecf000;"></i> <%
 }
 %> <%
 }
 %>
									</li>
									<li class="tag__item <%=up%> <%=disableUp%>"
										onclick="upVote('<%=reviewCard.getReviewId()%>', <%=redirect%>)"
										id="btn_up_<%=reviewCard.getReviewId()%>"><i class="mr-2"></i>Up</li>

									<h5 class="mr-2" style="margin-right: 6px;"
										id="value_<%=reviewCard.getReviewId()%>">
										<%=reviewCard.getTotalVotes()%>
									</h5>

									<li class="tag__item <%=down%> <%=disableDown%>"
										onclick="downVote('<%=reviewCard.getReviewId()%>', <%=redirect%>)"
										id="btn_down_<%=reviewCard.getReviewId()%>"><i
										class="mr-2"></i>Down</li>
									<%
									if (userRole != null) {
										List<Report> listReports = (List<Report>) request.getAttribute("listReport");
										if (!listReports.isEmpty()) {
											int res = 0;
											for (Report report : listReports) {
										if (report.getReviewId() == reviewCard.getReviewId()) {
											res++;
									%>
									<%
									}
									}
									if (res == 0) {
									%>
									<a class="btn btn-info report-btn"
										id="Re_<%=reviewCard.getReviewId()%>"
										onclick="addReport('<%=reviewCard.getReviewId()%>','<%=userRole.getUserId()%>','<%=redirect%>'+'')"
										reference="true"> Report</a>
									<%
									} else {
									%>
									<a class="btn btn-danger report-btn"
										id="<%="Re_" + reviewCard.getReviewId()%>"
										onclick="addReport('<%=reviewCard.getReviewId()%>','<%=userRole.getUserId()%>','<%=redirect%>'+'')"
										reference="false">Reported</a>
									<%
									}
									} else {
									%>
									<a class="btn btn-info report-btn"
										id="Re_<%=reviewCard.getReviewId()%>"
										onclick="addReport('<%=reviewCard.getReviewId()%>','<%=userRole.getUserId()%>','<%=redirect%>'+'')"
										reference="true"> Report</a>
									<%
									}
									} else {
									%>
									<%
									String a = "NoUser";
									%>
									<a class="btn btn-info report-btn"
										id="Re_<%=reviewCard.getReviewId()%>"
										onclick="addReport('<%=reviewCard.getReviewId()%>','<%=a%>','<%=redirect%>'+'')"
										reference="true"> Report</a>
									<%
									}
									%>
								</ul>
							</div>
						</article>
					</div>
					<%
					}
					}
					}
					%>

					<div class="center">
						<nav aria-label="Page navigation example">
							<ul class="pagination">
								<li class="page-item"><a class="page-link"
									href="ReviewController?action=getReviews&bookId=<%=thisBookCard.getBookId()%>&page=<%=firstPage%>&size=<%=itemsPerPage%>"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Previous</span>
								</a></li>
								<%
								for (int item : pageNumbers) {
								%>
								<li class="page-item"><a class="page-link"
									href="ReviewController?action=getReviews&bookId=<%=thisBookCard.getBookId()%>&page=<%=item%>&size=<%=itemsPerPage%>">
										<%=item + 1%></a></li>
								<%
								}
								%>
								<li class="page-item"><a class="page-link"
									href="ReviewController?action=getReviews&bookId=<%=thisBookCard.getBookId()%>&page=<%=lastPage%>&size=<%=itemsPerPage%>"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Next</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
				<div class="col col-3 book-info">
					<div class="book-card-display">
						<img class="book-card-display-img"
							src="./img/book_img/<%=thisBookCard.getImageName()%>"
							alt="Image Title" />
						<%
						ReviewCard reviewCardOfUser = (ReviewCard) request.getAttribute("reviewCardOfUser");
						%>
						<%
						if (stringFavouriteBook.equals("true")) {
						%>
						<a class="btn btn-danger btn-add"
							id="<%="fav_" + thisBookCard.getBookId()%>"
							onclick="addFavourites('<%=thisBookCard.getBookId()%>', <%=redirect%>)"
							reference="false">Delete from favourite</a>
						<%
						} else {
						%>
						<a class="btn btn-info btn-add"
							id="<%="fav_" + thisBookCard.getBookId()%>"
							onclick="addFavourites('<%=thisBookCard.getBookId()%>', <%=redirect%>)"
							reference="true">Add to Favourites</a>
						<%
						}
						%>
						<h5>What do you think?</h5>
						<a class="btn btn-outline-success btn-edit d-none">Edit your
							Review</a> <a class="btn btn-outline-success btn-review d-none">Write
							a Review</a>
						<%
						if (userRole == null) {
						%>
						<script>
									document.querySelector('.btn-review').addEventListener("click", function () {
										window.location.href = './LoginController?action=get-form-login';
									});
								</script>
						<%
						} else {
						%>
						<script>
								document.querySelector('.btn-review').addEventListener("click", function () {
									document.querySelector('.write-model').style.display = "flex";
									document.querySelector('body').style.overflow = "hidden";
								});
							</script>
						<%
						}
						%>
						<%
						if (reviewCardOfUser != null) {
						%>
						<script>
									document.querySelector('.btn-edit').classList.remove("d-none");
									var userId = '<%=reviewCardOfUser.getUserId()%>';
								    var bookId = '<%=reviewCardOfUser.getBookId()%>';
								    var hrefValue = 'ReviewController?action=EditReview&UserId=' + userId + '&BookId=' + bookId;
									document.querySelector('.btn-edit').setAttribute('href', hrefValue);
								</script>
						<%
						} else {
						%>
						<script>
					    	 	document.querySelector('.btn-review').classList.remove("d-none");
					    	 </script>
						<%
						}
						%>

					</div>
				</div>
			</div>
		</div>
		<div class="write-model">
			<div class="write-container">
				<div class="write-btn-container">
					<button class="btn post-btn add-btn">Post</button>
					<button class="btn close-btn">Cancel</button>
				</div>
				<div class="write-zone">
					<div class="rating-zone">
						<form action="ReviewController?action=CreateReview" method="post"
							id="write-new-review" accept-charset="UTF-8">
							<div class="text-danger" id="validation-summary"></div>
							<input type="hidden" name="UserId" class="form-control"
								value="<%=(userRole != null) ? userRole.getUserId() : ""%>" />
							<input type="hidden" name="BookId" class="form-control"
								value="<%=thisBookCard.getBookId()%>" /> <input type="hidden"
								name="ReviewDate" class="form-control" id="current-date" />
							<div class="rating-zone-header form-group rating">
								<p>Your rating:</p>
								<i class="fa-regular fa-star star"
									style="color: #ecf000; -i: 0;"></i> <i
									class="fa-regular fa-star star" style="color: #ecf000; -i: 1;"></i>
								<i class="fa-regular fa-star star"
									style="color: #ecf000; -i: 2;"></i> <i
									class="fa-regular fa-star star" style="color: #ecf000; -i: 3;"></i>
								<i class="fa-regular fa-star star"
									style="color: #ecf000; -i: 4;"></i> <input type="hidden"
									name="Rating" class="form-control" required /> <span
									class="text-danger"></span>
							</div>
							<input type="hidden" name="UpVotes" class="form-control"
								value="0" /> <input type="hidden" name="DownVotes"
								class="form-control" value="0" /> <input type="hidden"
								name="TotalVotes" class="form-control" value="0" />
							<textarea name="Detail" class="review-text"
								placeholder="Write your review here"
								style="white-space: pre-wrap;"></textarea>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/review.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/jquery/dist/jquery.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">
function confirmDelete(reviewId) {
    var confirmDelete = confirm("Bạn có muốn xóa bài viết này?");
    if (confirmDelete) {       
       window.location.href = 'ReviewController?action=DeleteReview&ReviewId=' + reviewId;
    } else {
       // Người dùng đã nhấn Cancel, không làm gì cả
    }
 }
function upVote(reviewId, redirect) {
		console.log("UpVote");
	
	    var buttonUp = document.getElementById("btn_up_" + reviewId);
	    var up = buttonUp.classList.contains("up-voted") ? "up-voted" : "";
	    var disableUp = buttonUp.classList.contains("disableUp");
	    if (disableUp) {
	        console.log("disableUp");
	    	return;
	    }
	    if ("<%=redirect%>" === "false") {
	        window.location.assign("./LoginController?action=get-form-login");
	        return;
	    }
	    var dataUpVote = { "action": "upVote","reviewId": reviewId, "value": 1};
	    var urlUpVote = "/BookStation/APIController";
	    invokeApiForVote(urlUpVote, dataUpVote, up);
	}
function downVote(reviewId, redirect) {
		console.log("DownVote");
	
        var buttonDown = document.getElementById("btn_down_" + reviewId);
        var down = buttonDown.classList.contains("down-voted") ? "down-voted" : "";
        var disableDown = buttonDown.classList.contains("disableDown");

        if (disableDown) {
        	console.log("disableDown");
        	return;
        }
        if ("<%=redirect%>" === "false") {
	        window.location.assign("./LoginController?action=get-form-login");
	        return;
	    }
        var dataDownVote = { "action": "downVote","reviewId": reviewId, "value": -1};
        var urlDownVote = "/BookStation/APIController";
        invokeApiForVote(urlDownVote, dataDownVote, down);
    }
	function invokeApiForVote(url, data, action) {
        var empty = " ";
        $.ajax({
            type: 'POST',
            url: url,
            contentType: 'application/json; charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (result) {
                console.log(result);
                var totalVoteTag = document.getElementById("value_" + data.reviewId);
                var valueToDisplay = result.result;

                // Gán giá trị vào innerHTML
                totalVoteTag.innerHTML = valueToDisplay;
                //totalVoteTag.innerHTML = result;
                var buttonUp = document.getElementById("btn_up_" + data.reviewId);
                var buttonDown = document.getElementById("btn_down_" + data.reviewId);
                if (data.value > 0) {
                    if (action == "up-voted") {
                        // remove upvote
                        buttonUp.classList.remove("up-voted");
                        buttonDown.classList.remove("disable-vote");
                    } else {
                        buttonDown.classList.add("disable-vote");
                        buttonUp.classList.add("up-voted");
                    }
                } else {
                    if (action == "down-voted") {
                        // remove downvote
                        buttonDown.classList.remove("down-voted");
                        buttonUp.classList.remove("disable-vote");
                    } else {
                        buttonUp.classList.add("disable-vote");
                        buttonDown.classList.add("down-voted");
                    }
                }
            },
            error: function () {
                console.log('Invoke Api failed : ' + url);
            }
        });
    }
	
function addFavourites(BookId, redirect) {
	    <%-- Nếu cần redirect, thực hiện chuyển hướng --%>
	    console.log(BookId);
	    if ("<%=redirect%>" === "false") {
	        window.location.assign("./LoginController?action=get-form-login");
	        return;
	    }
	
	    <%-- Lấy thông tin về nút bấm --%>
	    let button = document.getElementById("fav_" + BookId);
	    let reference = button.getAttribute("reference");
	    let dataAddFavourites = { "action": "addFavouriteBook","BookId": BookId };
	    console.log(dataAddFavourites);
	    let urlAddFavourites = "/BookStation/APIController";
	    let isAdding = reference === "true" ? true : false;
		
	    <%-- Gọi hàm JSP để thực hiện gọi API --%>
	    invokeApiForFav(urlAddFavourites, dataAddFavourites, isAdding, reference);
	
    <%-- Hàm JSP thực hiện gọi API --%>
    function invokeApiForFav(url, data, isAdding, stringFavouriteBook) {
        // Sử dụng đối tượng để lưu trữ các thông số
        var params = {
            type: "POST",
            url: url,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(data),
            success: function (result) {
                console.log(result);
                if (isAdding) {
                    button.classList.remove("btn", "btn-info", "btn-add");
                    button.classList.add("btn", "btn-danger", "btn-add");
                    button.innerHTML = "Delete from favourite";
                    button.setAttribute("reference", "false");
                } else {
                    button.classList.remove("btn", "btn-danger", "btn-add");
                    button.classList.add("btn", "btn-info", "btn-add");
                    button.innerHTML = "Add to Favourites";
                    button.setAttribute("reference", "true");
                }
            },
            error: function () {
                console.log('Invoke Api failed : ' + url);
            }
        };

        // Gọi hàm thực hiện gọi API
        invokeApi(params);
    }

    <%-- Hàm JSP thực hiện gọi API --%>
    function invokeApi(params) {
        $.ajax({
            type: params.type,
            url: params.url,
            contentType: params.contentType,
            dataType: params.dataType,
            data: params.data,
            success: params.success,
            error: params.error
        });
    }
}
function addReport(ReviewId, UserId, redirect) {
	if ("<%=redirect%>" === "false") {
        window.location.assign("./LoginController?action=get-form-login");
        return;
    }
    let button = document.getElementById("Re_" + ReviewId);
    let reference = button.getAttribute("reference");
    let dataAddReports = { "action": "addReport", "ReviewId": ReviewId };
    let urlAddReports = "/BookStation/APIController";
    let isAdding = reference === "true";
    invokeApiForReport(urlAddReports, dataAddReports, isAdding);
	function invokeApiForReport(url, data, isAdding) {
	    var params = {
	        type: "POST",
	        url: url,
	        contentType: "application/json; charset=UTF-8",
	        dataType: "json",
	        data: JSON.stringify(data),
	        success: function (result) {
	            console.log(result);
	            let button = document.getElementById("Re_" + data.ReviewId);
	            if (isAdding) {
	                button.classList.remove("btn", "btn-info");
	                button.classList.add("btn", "btn-danger");
	                button.innerHTML = "Reported";
	                button.setAttribute("reference", "false");
	            } else {
	                button.classList.remove("btn", "btn-danger");
	                button.classList.add("btn", "btn-info");
	                button.innerHTML = "Report";
	                button.setAttribute("reference", "true");
	            }
	        },
	        error: function () {
	            console.log('Invoke Api failed : ' + url);
	        }
	    };
		invokeApi(params);
	}
	function invokeApi(params) {
	    $.ajax({
	        type: params.type,
	        url: params.url,
	        contentType: params.contentType,
	        dataType: params.dataType,
	        data: params.data,
	        success: params.success,
	        error: params.error
	    });
	}
}
</script>
</html>