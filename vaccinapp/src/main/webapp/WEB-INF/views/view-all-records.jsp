<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Vaccinations</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style>
body {
	background-image: url("/resources/images/background.jpg");
	background-size: cover;
}

label, h1, p, td, th {
	color: white;
}

a {
	display: block;
}

#login, #logout {
	display: inline;
}
</style>
<%@ page isELIgnored="false"%>
</head>
<body>


	<div class="page-header">
		<div class="row">
			<div class="col-md-6">
				<br>
				<h1>All Vaccinations</h1>
			</div>
			<div class="col-md-6" style="text-align: right">

				<br> <a id="login" href="login"><input type="button"
					class="btn btn-primary btn-lg" value="Home" /></a> <a id="logout"
					href="logout"><input type="button"
					class="btn btn-primary btn-lg" value="Logout" /></a>
			</div>
		</div>
	</div>
	<br>


	<section style="margin-top: 5%" class="h-100">
		<header class="container h-100">
			<div class="d-flex align-items-center justify-content-center h-100">
				<div class="d-flex flex-column">
					<table class="table table-striped table-bordered">
						<tr><th>Vaccination Type</th>
						<th>Vaccination Date</th>
						<th>Remarks</th>
						<th>Remove?</th></tr>
						<c:forEach var="userVaccinationInfo"
							items="${userVaccinationInfo}">
							<tr>
								<td>${userVaccinationInfo.vaccinType}</td>
								<td>${userVaccinationInfo.vaccinDate}</td>
								<td>${userVaccinationInfo.notes}</td>
								<td><a href="deleteUserVaccination?vaccinId=${userVaccinationInfo.vaccinId}"><input type="button" value="Delete"/> </a>
							</tr>
						</c:forEach>


					</table>


				</div>
			</div>
		</header>
	</section>



</body>
</html>
