<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vaccinapp Login</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style>
body {
	background-image: url("/resources/images/background.jpg");
	background-size: cover;
}

label, h1, p {
	color: white;
}
</style>

</head>
<body>
	<section style="margin-top: 15%" class="h-100">
		<header class="container h-100">
			<div class="d-flex align-items-center justify-content-center h-100">
				<div class="d-flex flex-column">
					<h1 class="text align-self-center p-2">Vaccinapp Login</h1>
					<form:form action="login" cssClass="form-horizontal" method="post"
						modelAttribute="loginInfo">

						<div class="form-group">
							<label for="email" class="col-md-3 control-label">Email</label>
							<div class="col-md-12">
								<form:input path="email" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-md-3 control-label">Password</label>
							<div class="col-md-12">
								<form:password path="password" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								<form:button cssClass="btn btn-primary">Login</form:button>
								<a href="showSignUpForm"><input type="button"
									value="Sign Up" /></a><br>

							</div>
						</div>
						<p>${messageInvalid}</p>
					</form:form>

				</div>
			</div>

		</header>

	</section>
</body>
</html>