<%@page import="model.bean.ReportedReview"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<head>
<title>Admin Zone - Reported ReportedReviews</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/site.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin_page.css" />
</head>
<body>
	<jsp:include page="../shared/header.jsp" />

	<%
	

		List<ReportedReview> list = (List<ReportedReview>) request.getAttribute("list");
	%>
	<div class="admin_container container">
		<table class="table">
			<thead>
				<tr>
					<th>Detail</th>
					<th>Report Views</th>
					<th>Percent</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < list.size(); i++) {
				%>
				<tr>
					<td><%=list.get(i).getDetail()%></td>
					<td><%=list.get(i).getReportedNumber()%></td>
					<td><%=list.get(i).getPercent()%>%</td>
					<td><a
						href="${pageContext.request.contextPath}/ReviewController?action=handle-delete&id=<%=list.get(i).getReviewId()%>">Delete</a>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<div>
			<a
				href="${pageContext.request.contextPath}/ReviewController?action=list">Back
				to List</a>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.min.js"></script>
