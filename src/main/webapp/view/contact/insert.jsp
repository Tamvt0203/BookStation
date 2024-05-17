<%@page import="context.SessionUtils"%>
<%@page import="model.bean.UserRole"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Send opinion</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/site.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin_page.css" />
<style type="text/css">
.input-content {
	background: rgb(2, 0, 36);
	background: linear-gradient(45deg, rgba(2, 0, 36, 0.5) 0%,
		rgba(9, 9, 121, 0.5) 35%, rgba(0, 212, 255, 0.5) 120%);
	border: 2px solid #fff;
	height: 400px;
	width: 800px;
	border-radius: 20px;
	transition: all 0.4s ease-in-out;
	color: #fff;
	padding: 20px 12px;
	margin-top: 30px;
}

.input-content:focus {
	scale: 1.05;
	border: 2px solid #fff;
}
</style>
</head>
<body>

	<jsp:include page="../shared/header.jsp" />
	<%
	UserRole currentUserRole = (UserRole) SessionUtils.getInstance().getValue(request, "userRole");
	%>
	<div class="row mt-5">
		<div class="col col-3"></div>
		<div class="col-md-4">
			<form action="ContactController">
				<input name="user-id" value="<%=currentUserRole.getUserId()%>"
					type="hidden" />
				<textarea class="input-content" placeholder="Write your opinion"
					style="white-space: pre-wrap;" name="content"></textarea>
				<input name="status" value="0" type="hidden" />
				<button class="btn btn-outline-primary mt-5 fw-bold" type="submit"
					name="action" value="handle-insert">Create</button>
				<a class="btn btn-outline-danger fw-bold mt-5"
					href="${pageContext.request.contextPath}/AuthorController?action=list">Back</a>
			</form>
		</div>

	</div>


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/popper.js"></script>

</body>
</html>