<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.bean.*" %>
<%
    String title = "Edit";
    Date date = new Date();
    int year = date.getYear() + 1900;
    int month = date.getMonth() + 1;
    int day = date.getDate();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    ReviewCard reviewCard = (ReviewCard) request.getAttribute("reviewCard");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Review</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/review_style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/site.css">
<link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
	<div class="edit-model">
        <div class="write-container">
            <div class="write-btn-container">
                <button class="btn post-btn upd-btn">Post</button>
                <a class="btn close-btn">Cancel</a>
            </div>
            <div class="write-zone">
                <div class="rating-zone">
                    <form action="ReviewController?action=EditForm" method="post" id="update-review">
                        <div class="text-danger"></div>
                        <input type="hidden" name="reviewId" value="<%= reviewCard.getReviewId() %>" />
                        <input type="hidden" name="userId" value="<%= reviewCard.getUserId() %>" />
                        <input type="hidden" name="bookId" value="<%= reviewCard.getBookId() %>" />
                        <input type="hidden" name="reviewDate" id="current-date" value="<%= dateFormat.format(date) %>" />
                        <div class="rating-zone-header form-group rating">
                            <p>Your rating: </p>
                            <%
                                int rating = (int) reviewCard.getRating();
                                for (int i = 0; i < 5; i++) {
                                    if (i < rating) {
                            %>
                                        <label class='bx bxs-star star active' style="--i: <%= i %>;"></label>
                            <%
                                    } else {
                            %>
                                        <label class='bx bx-star star' style="--i: <%= i %>;"></label>
                            <%
                                    }
                                }
                            %>
                            <input hidden name="rating" value="<%= rating %>" class="form-control" type="text" required />
                            <span class="text-danger"></span>
                        </div>
                        <input type="hidden" name="upVotes" value="<%= reviewCard.getUpVotes() %>" />
                        <input type="hidden" name="downVotes" value="<%= reviewCard.getDownVotes() %>" />
                        <input type="hidden" name="totalVotes" value="<%= reviewCard.getTotalVotes() %>" />
                        <textarea name="detail" class="review-text"><%= reviewCard.getDetail() %></textarea>
                    </form>
                </div>
            </div>
        </div>
    </div>    
   <script>
        date = new Date();
        year = date.getFullYear();
        month = date.getMonth() + 1;
        day = date.getDate();
        var currentDateElements = document.querySelectorAll('#current-date');
        for (var i = 0; i < currentDateElements.length; i++) {
            currentDateElements[i].value = day + "-" + month + "-" + year;
        }

        const allStar = document.querySelectorAll('.edit-model .rating .star');
        const ratingValue = document.querySelector('.edit-model .rating input');

        allStar.forEach((item, idx) => {
            item.addEventListener('click', function () {
                let click = 0;
                ratingValue.value = idx + 1;

                allStar.forEach(i => {
                    i.classList.replace('bxs-star', 'bx-star');
                    i.classList.remove('active');
                });
                for (let i = 0; i < allStar.length; i++) {
                    if (i <= idx) {
                        allStar[i].classList.replace('bx-star', 'bxs-star');
                        allStar[i].classList.add('active');
                    } else {
                        allStar[i].style.setProperty('--i', click);
                        click++;
                    }
                }
            });
        });

        document.querySelector('.upd-btn').addEventListener('click', function () {
            document.querySelector('#update-review').submit();
        });

        document.querySelector('.close-btn').addEventListener('click', function () {
            history.back();
        });
    </script>
</body>
</html>