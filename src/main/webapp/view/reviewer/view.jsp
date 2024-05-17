
<%@page import="java.util.Random"%>
<%@page import="model.bean.ReviewOverview"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.Reviewer"%>
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
	href="${pageContext.request.contextPath}/css/admin_page.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/reviewer_style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/book_card.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Font-Awesome-640/css/all.css" />
</head>
<body>
	<jsp:include page="../shared/header.jsp" />

	<%
	Reviewer reviewer = (Reviewer) request.getAttribute("reviewer");
	List<ReviewOverview> list = reviewer.getList();
	%>
	<div class="container wrap-container">
		<h2 class="reviewer-wrap reviewer-title">
			All reviews of Reviewer:
			<%=reviewer.getUsername()%></h2>
		<h2 class="reviewer-wrap reviewer-up">
			Total Up votes:
			<%=reviewer.getTotalUpvotes()%></h2>
		<h2 class="reviewer-wrap reviewer-down">
			Total Down votes:
			<%=reviewer.getTotalDownVotes()%></h2>
		<h2 class="reviewer-wrap reviewer-total">
			Total votes:
			<%=reviewer.getTotalVotes()%></h2>
		<%
		String[] classes = new String[] { "blue", "red", "green", "yellow" };
		Random count = new Random();
		%>
		<%
		for (int i = 0; i < list.size(); i++) {
		%>
		<article class="postcard <%=classes[i % 4]%>">
			<a class="postcard__img_link" href="#"> <img
				class="postcard__img"
				src="img/book_img/<%=list.get(i).getBookImageName()%>"
				alt="Image Title" />
			</a>
			<div class="postcard__text">
				<h1 class="postcard__title @classes[count.Next(0,3)]">
					<a href="/Review/Index?bookId=@rev.BookId"><%=list.get(i).getBookName()%></a>
				</h1>
				<div class="postcard__bar"></div>
				<div class="postcard__preview-txt"><%=list.get(i).getReviewDetail()%></div>
				<ul class="postcard__tagbox" style="list-style-type: none;">
					<li style="margin-right: 12px;">
						<%
						for (int j = 0; j < 5; j++) {
							if (j < list.get(i).getReviewRating()) {
						%> <i class="fa-solid fa-star" style="color: #ecf000;"></i> <%
 } else {
 %><i class="fa-regular fa-star" style="color: #ecf000;"></i> <%
 }
 }
 %>
					</li>
					<li class="tag__item">Total Votes: <%=list.get(i).getReviewTotalVote()%></li>
				</ul>

			</div>
		</article>
		<%
		}
		%>

	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/popper.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/chart.umd.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.min.js"></script>

</body>
