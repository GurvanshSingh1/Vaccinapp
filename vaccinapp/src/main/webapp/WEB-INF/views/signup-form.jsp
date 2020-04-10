<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style>
body {
	background-image: url("/resources/images/background.jpg");
	background-size: cover;
}

label, h2, p {
	color: white;
}

div{
	display: inline;
}

</style>
</head>
<body>
	<section class="h-100">
		<header class="container h-100">
			<div class="d-flex align-items-center justify-content-center h-100">
				<div class="d-flex flex-column">
					<h2>Register User...</h2>
					<form:form action="saveSignUpForm" cssClass="form-horizontal"
						method="post" modelAttribute="user">

						<div class="form-group">
							<label for="firstname" class="col-md-7 control-label">First
								Name</label>
							<div class="col-md-12">
								<form:input path="firstName" cssClass="form-control" />
							</div>
						</div><br>
						<div class="form-group">
							<label for="lastname" class="col-md-7 control-label">Last
								Name</label>
							<div class="col-md-12">
								<form:input path="lastName" cssClass="form-control" />
							</div>
						</div><br>

						<div class="form-group">
							<label for="email" class="col-md-7 control-label">Email </label>
							<div class="col-md-12">
								<form:input path="email" cssClass="form-control" />
							</div>
						</div><br>

						<div class="form-group">
							<label for="password" class="col-md-7 control-label">Password</label>
							<div class="col-md-12">
								<form:password path="password" cssClass="form-control" />
							</div>
						</div><br>
						<div class="form-group">
							<label for="gender" class="col-md-7 control-label">Gender</label>
							<div >
								<form:select path="gender">
									<form:option value="Male">Male</form:option>
									<form:option value="Female">Female</form:option>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<label for="dob" class="col-md-7 control-label">Date of Birth (MM/DD/YYYY)</label>
							<div class="col-md-12">
								<form:input path="dob" cssClass="form-control" />
							</div>
						</div><br>
						<div class="form-group">
							<label for="address" class="col-md-7 control-label">Address</label>
							<div class="col-md-12">
								<form:input path="address" cssClass="form-control" />
							</div>
						</div><br>
						<div class="form-group">
							<label for="postal" class="col-md-7 control-label">Postal Code</label>
							<div class="col-md-12">
								<form:input path="postal" cssClass="form-control" />
							</div>
							<div class="col-md-12" style="display:none">
								<form:input path="userType" value="USER" cssClass="form-control" />
							</div>
								<div class="col-md-12" style="display:none">
								<form:input path="isApproved" value="0" cssClass="form-control" />
							</div>
						</div><br>

						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								<form:button cssClass="btn btn-primary">Submit</form:button>
							</div>
						</div>
						<p>${alreadyExist}</p>
					</form:form>

				</div>
			</div>
		</header>
	</section>
</body>
</html>