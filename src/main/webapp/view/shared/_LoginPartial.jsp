<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.regex.Pattern"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <%
        //SignInManager<IdentityUser> signInManager = new SignInManager<>();
        // UserManager<IdentityUser> userManager = new UserManager<>();
        /*
            String getUserNameFromGmail(String gmail) {
                int index = gmail.indexOf('@');
                return gmail.substring(0, index);
            }
        */

        //HttpSession session = request.getSession(false);
    %>

    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link fw-bold text-white fs-6" id="register"
                href="<%= request.getContextPath() %>/RegisterController?action=get-form-register">Register</a>
        </li>
        <li class="nav-item">
            <a class="nav-link fw-bold text-white fs-6" id="login"
                href="<%= request.getContextPath() %>/LoginController?action=get-form-login">Login</a>
        </li>
    </ul>
</body>
</html>
