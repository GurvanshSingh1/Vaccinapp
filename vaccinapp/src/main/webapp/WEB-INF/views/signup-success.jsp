<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>javaguides.net</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style>
body {
	background-image: url("/resources/images/background.jpg");
	background-size: cover;
}

h5 {
	color: white;
}
</style>
<%@ page isELIgnored="false"%>
</head>
<body>
	<div class="container">
			<br><br>
			<h5>${message}</h5>
			<br>
			<a href="logout"><input type="button" value="Login" /></a>

	</div>
</body>
</html>
