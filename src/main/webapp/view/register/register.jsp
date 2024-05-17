<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login_style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/site.css">
</head>
<body>
	<jsp:include page="../shared/header.jsp" />
	<%
	String message = (String) request.getAttribute("message");
	%>
	<section>
		<div class="container form-box right-panel-active" id="container">
			<div class="form-container sign-up-container">
				<form method="post" action="RegisterController">
					<h1>Create Account</h1>
					<input type="email" name="email" autocomplete="username"
						aria-required="true" placeholder="name@example.com" /> <input
						type="password" name="password" autocomplete="current-password"
						aria-required="true" placeholder="password" type="password" /> <input
						type="password" name="confirmed-password"
						autocomplete="current-password" aria-required="true"
						placeholder="confirm password" type="password" />
					<!--  <a href="#">Forgot your
						password?</a> -->
					<button type="submit" name="action" value="handle-register">Create</button>
					<%
					if (message != null) {
					%>
					<div class="mt-2 bg-white text-danger"><%=message%></div>
					<%
					}
					%>
				</form>
			</div>
			<div class="overlay-container">
				<div class="overlay">
					<div class="overlay-panel overlay-right">
						<h1>Hello, Friend!</h1>
						<p>Enter your personal details and start journey with us</p>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>