
<%@page import="model.bo.ReviewerBO"%>
<%@page import="model.bo.PersonalBO"%>
<%@ page import="java.io.*,java.util.*,javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%
// Set the URL to which you want to redirect
String url = "http://localhost:8080/BookStation/HomeController";

// Redirecting to the specified URL
response.sendRedirect(url);

%>