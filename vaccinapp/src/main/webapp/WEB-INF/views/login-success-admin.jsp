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
<script>
window.onload = function(){
	$("#newVaccination").hide();
}
function showhide(){
	$("#newVaccination").show();
}

</script>
<style>
body {
	background-image: url("/resources/images/background.jpg");
	background-size: cover;
}

label, h1, p {
	color: white;
}

a {
	display: block;
}

#login, #logout{

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
				<h1>${message}</h1>
			</div>
			<div class="col-md-6" style="text-align: right">
			
				<br> 
				<a id="login" href="login"><input type="button"
					class="btn btn-primary btn-lg" value="Home" /></a>
				<a id="logout" href="logout"><input type="button"
					class="btn btn-primary btn-lg" value="Logout" /></a>
			</div>
		</div>
	</div>
	<br>

	<div class="row">
		<div class="col-md-6">
			<br> <a href="viewPendingApprovals"><input type="button" class="btn btn-primary btn-lg"
				value="Pending User Approvals" /></a>
		</div>
		<div id="newVaccination" class="col-md-4">
			<h1 class="text align-self-center p-2">Add New Vaccination...</h1>
			<form:form action="addNewUserVaccination" cssClass="form-horizontal"
				method="post" modelAttribute="UserVaccinationInfo">

				<div class="form-group" style="display: none;">
					<label for="fname" class="col-md-3 control-label">fname</label>
					<div class="col-md-12">
						<form:input path="fname" value="${userName}"
							cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="vaccinType" class="col-md-3 control-label">Vaccination</label>
					<div class="col-md-12">
						<form:input path="vaccinType" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="vaccinDate" class="col-md-6 control-label">Date (MM/DD/YYYY)</label>
					<div class="col-md-12">
						<form:input path="vaccinDate" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="notes" class="col-md-3 control-label">Notes</label>
					<div class="col-md-12">
						<form:input path="notes" cssClass="form-control" />
					</div>
				</div>

				<div class="form-group">

					<div class="col-md-offset-3 col-md-9">
						<form:button cssClass="btn btn-primary">Add Record</form:button>
						<br>
						<p>${approved}</p>

					</div>
				</div>

			</form:form>

		</div>
	</div>



</body>
</html>
