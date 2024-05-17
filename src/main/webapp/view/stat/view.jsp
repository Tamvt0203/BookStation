
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
	href="${pageContext.request.contextPath}/css/admin_statictis.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Font-Awesome-640/css/all.css" />
</head>
<body>
	<jsp:include page="../shared/header.jsp" />

	<%
	Stat stat = (Stat) request.getAttribute("stat");
	Map<String, Integer> categoryBook = stat.getBooksCategory();
	Map<String, Integer> categoryReview = stat.getReviewsCategory();
	%>
	<div class="container wrap-container">

		<div class="row">
			<div class="col col-3">
				<h1>Welcome to the Statistics Page for Admin</h1>
			</div>
			<div class="col col-3">
				<div class="stat-card big-card">
					<i class="big-icon fa-sharp fa-solid fa-book-open-cover"></i>
					<h2>Total Books</h2>
					<h1><%=stat.getTotalBooks()%></h1>
				</div>
			</div>
			<div class="col col-3">
				<div class="stat-card big-card">
					<i class="big-icon fa-sharp fa-solid fa-users"></i>
					<h2>Total Users</h2>
					<h1><%=stat.getTotalUsers()%></h1>
				</div>
			</div>
			<div class="col col-3">
				<div class="stat-card big-card">
					<i class="big-icon fa-sharp fa-solid fa-sigma"></i>
					<h2>Total Reviews</h2>
					<h1><%=stat.getTotalReviews()%></h1>
				</div>
			</div>
		</div>
		<div class="row mt-4">
			<div>
				<%
				for (Map.Entry<String, Integer> mapElement : categoryBook.entrySet()) {

					String key = mapElement.getKey();
					Integer value = mapElement.getValue();
				%>

				<div class="d-none" id="cate-name"><%=key%></div>
				<div class="d-none" id="book-count">
					<%=value%>
				</div>

				<%
				}
				%>

				<%
				for (Map.Entry<String, Integer> mapElement : categoryReview.entrySet()) {

					String key = mapElement.getKey();
					Integer value = mapElement.getValue();
				%>

				<div class="d-none" id="cate-name1"><%=key%></div>
				<div class="d-none" id="review-count"><%=value%></div>
				<%
				}
				%>
			</div>
			<div class="col col-7 text-center">
				<h3>Number of books in each category</h3>
				<div class="chart">
					<canvas id="barchart"></canvas>
				</div>
			</div>
			<div class="col col-5">
				<h3>Number of reviews in each category</h3>
				<div class="chart">
					<canvas id="doughnut"></canvas>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/popper.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/chart.umd.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/statictis.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.min.js"></script>

</body>
