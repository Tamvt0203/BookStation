
<%@page import="model.bean.Book"%>
<%@page import="model.bean.FavouriteBook"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.Personal"%>
<%@page import="java.util.Map"%>
<%@page import="model.bean.Stat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<head>
<title>Statistics</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/site.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/personal_style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Font-Awesome-640/css/all.css" />
</head>
<body>
	<jsp:include page="../shared/header.jsp" />

	<%
	Personal personal = (Personal) request.getAttribute("personal");
	List<Book> list = personal.getListFavouriteBooks();
	%>

	<div class="container-fluid holder">
		<div class="row">
			<div class="col col-3">
				<div class="wrap-profile">
					<img src="img/avt_img/<%=personal.getAvaName()%>" class="user-img" />
					<h3 class="user-gmail">
						<%=personal.getGmail()%>
					</h3>
					<div class="user-book-count">
						<h5>
							Books Reviewed:
							<%=personal.getTotalBookReviewd()%></h5>
						<h5>
							Your total Up votes:
							<%=personal.getTotalUpVotes()%></h5>
						<h5>
							Your total Down votes:
							<%=personal.getTotalDownVotes()%></h5>

						<h5>
							Your total votes:
							<%=personal.getTotalVotes()%></h5>
					</div>
					<div class="user-highest-review">
						<h5>From your highest votes review</h5>
						<p class="user-highest-review-content"><%=personal.getHighestVotedReview()%></p>
					</div>
				</div>
			</div>
			<div class="col col-9">
				<div class="container">
					<div class="row">
						<h2 class="shelf-title">My Book Shelfs</h2>
						<%
						for (int i = 0; i < list.size(); i++) {
						%>
						<div class="col col-3 wrap-book">
							<a href="/Review/Index?bookId=@obj.BookId"> <img
								class="book-img"
								src="img/book_img/<%=list.get(i).getImageName()%>"
								alt="Image Title" />
							</a> <a class="btn btn-danger remove-btn"
								href="PersonalController?action=delete-fav-book&id=<%=list.get(i).getBookId()%>">Remove</a>
						</div>
						<%
						if (i % 4 == 0 && i != 0) {
						%>

						<div class="col col-12 shelf"></div>
						<%
						}
						}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/popper.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/personal.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.min.js"></script>

</body>
