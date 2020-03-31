<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vaccinapp</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style>
body {
	background-image: url("/resources/images/background.jpg");
	background-size: cover;
}

h1 {
	color: white;
}
</style>

<script>
	window.onload = function() {

		setTimeout(function() {
			document.getElementById("main").innerHTML = "V";
		}, 500)

		setTimeout(function() {
			document.getElementById("main").innerHTML = "VA";
		}, 800)

		setTimeout(function() {
			document.getElementById("main").innerHTML = "VAC";
		}, 1100)

		setTimeout(function() {
			document.getElementById("main").innerHTML = "VACC";
		}, 1400)

		setTimeout(function() {
			document.getElementById("main").innerHTML = "VACCI";
		}, 1700)

		setTimeout(function() {
			document.getElementById("main").innerHTML = "VACCIN";
		}, 2000)

		setTimeout(function() {
			document.getElementById("main").innerHTML = "VACCINA";
		}, 2300)

		setTimeout(function() {
			document.getElementById("main").innerHTML = "VACCINAP";
		}, 2600)
		
		setTimeout(function() {
			document.getElementById("main").innerHTML = "VACCINAPP";
		}, 2900)

		setTimeout(function() {
			document.getElementById("main").innerHTML = "VACCINAPP!";
		}, 3200)

		setTimeout(function() {
			location.replace("http://localhost:8080/login");
		}, 3500)

	}
</script>

</head>
<body>
	<section style="margin-top: 15%" class="h-100">
		<header class="container h-100">
			<div class="d-flex align-items-center justify-content-center h-100">
				<div class="d-flex flex-column">
					<h1 id="main" class="text align-self-center p-2"></h1>
				</div>
			</div>
		</header>
	</section>
</body>
</html>