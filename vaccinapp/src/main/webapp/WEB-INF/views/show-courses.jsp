<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<%@ page isELIgnored="false"%>
</head>
<body>
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h1>Course List</h1>
			${message}
			<hr />
			<form:form action="registerCourse" cssClass="form-horizontal"
				method="post" modelAttribute="course" id="courseRegisterForm">
				<form:hidden path="code" cssClass="form-control" />
				<form:hidden path="name" cssClass="form-control" />

				<table class="table table-striped table-bordered">
					<c:forEach var="course" items="${courses}">
						<tr>
							<td>${course.code} ${course.name}</td> <td><a href="#" onclick="document.getElementById('code').value='${course.code}';document.getElementById('name').value='${course.name}';document.getElementById('courseRegisterForm').submit();">Register</a></td>
						</tr>
					</c:forEach>

				</table>
			</form:form>
		</div>
	</div>
</body>
</html>
