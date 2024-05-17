<%@page import="model.bean.Personal"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.Author"%>
<%@page import="model.bean.Book"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Avatar</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/site.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/personal_style.css" />
</head>
<body>
	<%
	Personal personal = (Personal) request.getAttribute("personal");
	%>
	<div class="container holder">
		<form action="PersonalController" enctype="multipart/form-data"
			method="post">
			<div class="row">
				<div class="form-group mb-2">
					<button class="btn btn-submit-ava" type="submit" name="action"
						value="handle-edit-ava">Save</button>
				</div>
				<div class="mb-3">
					<input name="img" class="form-control mb-3" type="file" required="required"
						onchange="preview()"> <img id="frame"
						src="img/avt_img/<%=personal.getAvaName()%>" class="img-avt" />
				</div>
			</div>
		</form>
		<div>
			<a
				href="${pageContext.request.contextPath}/PersonalController?action=view">Back
				to List</a>
		</div>
	</div>
	<script>
		function preview() {
			frame.src = URL.createObjectURL(event.target.files[0]);

		}
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/popper.js"></script>
</body>
</html>