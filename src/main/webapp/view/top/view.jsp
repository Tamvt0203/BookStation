<%@page import="model.bean.Top"%>
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
	href="${pageContext.request.contextPath}/css/top_style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Font-Awesome-640/css/all.css" />
</head>
<body>
	<jsp:include page="../shared/header.jsp" />

	<%
	Top top = (Top) request.getAttribute("top");
	Map<String, Integer> enthuReviewers = top.getEnthusiasticReviewers();
	Map<String, Integer> highestVotedReviewers = top.getHighestVotedReviewers();
	Map<String, Integer> popularBooks = top.getPopularBooks();
	Map<String, Double> highestRatedBooks = top.getHighestRatedBooks();
	%>
	<div class="container wrap-container">
		<div class="row">
			<div class="col col-6">
				<h2 class="top-title">Top 10 Most Popular Books</h2>
				<table class="table top-table">
					<thead>
						<tr>
							<th>Rank</th>
							<th style="width: 70%">Book Name</th>
							<th style="width: 20%;">Total reviews</th>
						</tr>
					</thead>
					<tbody>
						<%
						int i = 0;
						for (Map.Entry<String, Integer> mapElement : popularBooks.entrySet()) {
							i++;
							String key = mapElement.getKey();
							Integer value = mapElement.getValue();
						%>
						<tr>
							<td>
								<%
								if (i == 1) {
								%> <i class="fas fa-medal" style="color: yellow;"></i> <%
 } else if (i == 2) {
 %> <i class="fas fa-medal" style="color: white;"></i> <%
 } else if (i == 3) {
 %> <i class="fas fa-medal" style="color: brown;"></i> <%
 } else {
 %><i class="fas fa-medal" style="color: transparent;"></i> <%
 }
 %>
							</td>
							<td><a class="link" href="/Review/Index?bookId=@book.BookId">
									<%=key%>
							</a></td>
							<td><%=value%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div class="col col-6">
				<h2 class="top-title">Top 10 Highest Rated Books</h2>
				<table class="table top-table">
					<thead>
						<tr>
							<th>Rank</th>
							<th style="width: 80%;">Book Name</th>
							<th style="width: 10%;">Rating</th>
						</tr>
					</thead>
					<tbody>
						<%
						i = 0;
						for (Map.Entry<String, Double> mapElement : highestRatedBooks.entrySet()) {
							i++;
							String key = mapElement.getKey();
							Double value = mapElement.getValue();
						%>
						<tr>
							<td>
								<%
								if (i == 1) {
								%> <i class="fas fa-medal" style="color: yellow;"></i> <%
 } else if (i == 2) {
 %> <i class="fas fa-medal" style="color: white;"></i> <%
 } else if (i == 3) {
 %> <i class="fas fa-medal" style="color: brown;"></i> <%
 } else {
 %><i class="fas fa-medal" style="color: transparent;"></i> <%
 }
 %>
							</td>
							<td><a class="link" href="/Review/Index?bookId=@book.BookId">
									<%=key%>
							</a></td>
							<td><%=value%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row mt-5">
			<div class="col col-6">
				<h2 class="top-title">Top 10 Highest Voted Reviewers</h2>
				<table class="table top-table">
					<thead>
						<tr>
							<th>Rank</th>
							<th style="width: 70%">Reviewer</th>
							<th style="width: 20%;">Vote</th>
						</tr>
					</thead>
					<tbody>
						<%
						i = 0;
						for (Map.Entry<String, Integer> mapElement : highestVotedReviewers.entrySet()) {
							i++;
							String key = mapElement.getKey();
							Integer value = mapElement.getValue();
						%>
						<tr>
							<td>
								<%
								if (i == 1) {
								%> <i class="fas fa-medal" style="color: yellow;"></i> <%
 } else if (i == 2) {
 %> <i class="fas fa-medal" style="color: white;"></i> <%
 } else if (i == 3) {
 %> <i class="fas fa-medal" style="color: brown;"></i> <%
 } else {
 %><i class="fas fa-medal" style="color: transparent;"></i> <%
 }
 %>
							</td>
							<td><a class="link"
								href="ReviewerController?action=view&username=<%=key%>"> <%=key%>
							</a></td>
							<td><%=value%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div class="col col-6">
				<h2 class="top-title">Top 10 Enthusiastic Reviewers</h2>
				<table class="table top-table">
					<thead>
						<tr>
							<th>Rank</th>
							<th style="width: 60%">Reviewer</th>
							<th>Number Of Reviews</th>
						</tr>
					</thead>
					<tbody>
						<%
						i = 0;
						for (Map.Entry<String, Integer> mapElement : enthuReviewers.entrySet()) {
							i++;
							String key = mapElement.getKey();
							Integer value = mapElement.getValue();
						%>
						<tr>
							<td>
								<%
								if (i == 1) {
								%> <i class="fas fa-medal" style="color: yellow;"></i> <%
 } else if (i == 2) {
 %> <i class="fas fa-medal" style="color: white;"></i> <%
 } else if (i == 3) {
 %> <i class="fas fa-medal" style="color: brown;"></i> <%
 } else {
 %><i class="fas fa-medal" style="color: transparent;"></i> <%
 }
 %>
							</td>
							<td><a class="link"
								href="ReviewerController?action=view&username=<%=key%>"> <%=key%>
							</a></td>
							<td><%=value%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
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
