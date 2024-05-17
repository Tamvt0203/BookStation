<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login </title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login_style.css">
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/site.css">
</head>
<body>
	<jsp:include page="../shared/header.jsp" />
	<section>
		<div class="container form-box" id="container">
			<div class="form-container sign-in-container">
				<form method="post" action="LoginController">
					<h1>Login</h1>
					<div class="text-danger"
						role="alert"></div>
					<input type="email" name="email"
						 placeholder="name@example.com" /> 
					<input type="password" name="password"
						autocomplete="current-password" aria-required="true"
						placeholder="password" />
					<button name="action" value="handle-login">Login</button>
				</form>
			</div>
			<div class="overlay-container">
				<div class="overlay">
					<div class="overlay-panel overlay-left">
						<h1>Welcome Back!</h1>
						<p>To keep connected with us please login with your personal
							info</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/popper.js"></script>
</body>
</html>