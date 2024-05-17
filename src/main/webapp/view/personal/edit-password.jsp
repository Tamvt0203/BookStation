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
<title>Edit password</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/site.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/personal_style.css" />
</head>
<body>
	<%
	String message = (String) request.getAttribute("message");
	%>

	<div class="container holder">

		<div class="row">

			<div class="col-md-3"></div>
			<div class="col-md-5">
				<form action="PersonalController" method="post">
					<div class="form-floating mb-3">
						<label id="label-1"> New password:</label> <input type="password"
							name="new-password" class="form-control" aria-required="true"
							placeholder="Please enter your new password." />
					</div>
					<div class="form-floating mb-3">
						<label id="label-1"> Confirmed password:</label> <input
							type="password" name="re-password" class="form-control"
							aria-required="true"
							placeholder="Please enter your new password again." />
					</div>
					<button type="submit" name="action" value="handle-edit-password"
						class="w-100 btn btn-lg btn-primary">Update password</button>
					<%
					if (message != null) {
					%>
					<div class="p-3 mt-3 mb-2 bg-success text-white"><%=message%></div>
					<%
					}
					%>
				</form>
				<div>
					<a
						href="${pageContext.request.contextPath}/PersonalController?action=view">Back
						to List</a>
				</div>
			</div>

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