<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style>
body {
	background-image: url("/resources/images/background.jpg");
	background-size: cover;
}

label, h1 {
	color: white;
}
</style>
<%@ page isELIgnored="false"%>
</head>
<body>


	<div class="page-header">
		<div class="row">
			<div class="col-md-6">
				<br>
				<h1>${message}</h1>
			</div>
			<div class="col-md-6" style="text-align: right">
				<br> <a href="logout"><input type="button"
					class="btn btn-primary btn-lg" value="Logout" /></a>
			</div>
		</div>
	</div>
	<br>

	<div class="page-header">
		<div class="row">
			<div class="col-md-6">
				<br> <a><input type="button" class="btn btn-primary btn-lg"
					value="Add a new Record" /></a>
			</div>

		</div>
	</div>
	<br>

</body>
</html>
