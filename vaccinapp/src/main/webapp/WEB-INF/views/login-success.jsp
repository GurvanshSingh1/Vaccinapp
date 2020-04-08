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
	window.onload = function() {
		$("#result").show();

		$("#enquiry").hide();
		$("#newVaccination").hide();
		$("#clinic").hide();

		$("#myimage").click(function() {

			$("#result h1").hide();
			$("#postNews").hide();
			$("#newVaccination").hide();
			$("#newClinic").hide();

			$("#notification").fadeIn("slow");
			$(".dismiss").click(function() {
				$("#notification").fadeOut("slow");
			});

		});
	}

	function showhideNewVaccination() {
		$("#newVaccination").show();

		$("#enquiry").hide();
		$("#result").hide();
		$("#clinic").hide();
		$("#myimage").hide();
	}

	function showhideEnquiry() {
		$("#enquiry").show();

		$("#newVaccination").hide();
		$("#result").hide();
		$("#clinic").hide();
		$("#myimage").hide();
	}

	function showhideClinic() {
		$("#clinic").show();

		$("#newVaccination").hide();
		$("#result").hide();
		$("#enquiry").hide();
		$("#myimage").hide();
	}

	function getClinicDetail(clinicId) {
		location.replace("http://localhost:8080/viewClinicDetails?clinicId="
				+ clinicId);
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

#login, #logout {
	display: inline;
}

#notification {
	top: 0px;
	width: 100%;
	z-index: 105;
	text-align: center;
	font-weight: normal;
	font-size: 14px;
	font-weight: bold;
	color: white;
	padding: 5px;
}

#notification span.dismiss {
	border: 2px solid #FFF;
	padding: 0 5px;
	cursor: pointer;
	float: right;
	margin-right: 10px;
}

#notification a {
	color: white;
	text-decoration: none;
	font-weight: bold
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

				<br> <input type="image" id="myimage"
					style="height: 30px; width: 30px; margin-right: 10px;"
					src="https://img.icons8.com/nolan/64/bell.png" /> <a id="login"
					href="login"><input type="button"
					class="btn btn-primary btn-lg" value="Home" /></a> <a id="logout"
					href="logout"><input type="button"
					class="btn btn-primary btn-lg" value="Logout" /></a>
			</div>
		</div>
	</div>
	<marquee>
		<p>${todayNews}</p>
	</marquee>
	<br>

	<div class="row">
		<div class="col-md-6">
			<br> <a><input type="button"
				onclick="showhideNewVaccination();" class="btn btn-primary btn-lg"
				value="Add New Vaccination" /></a><br> <a href="viewAllRecords"><input
				type="button" class="btn btn-primary btn-lg"
				value="View/Remove Vaccinations" /></a> <br> <a
				href="viewDailyNews"><input type="button"
				class="btn btn-primary btn-lg" value="View Daily News" /></a> <br>
			<a href="#"><input type="button" onclick="showhideClinic();"
				class="btn btn-primary btn-lg" value="View Clinics" /></a> <br> <a
				href="#"><input type="button" onclick="showhideEnquiry();"
				class="btn btn-primary btn-lg" value="Enquire Us" /></a> <br> <a
				href="viewEnquiry"><input type="button"
				class="btn btn-primary btn-lg" value="View Enquiry Status" /></a>
		</div>

		<!--  Result Section -->
		<div id="result" class="col-md-4">
			<h1 class="text align-self-center p-2">${approvedEnquiry}</h1>
			<h1 class="text align-self-center p-2">${approved}</h1>

			<div id="notification" style="display: none;">
				${userNotifications} <span class="dismiss"><a
					title="dismiss this notification">x</a></span>
			</div>
		</div>

		<!--  New Vaccination Section -->
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
					<label for="vaccinType" class="col-md-3 control-label">Vaccination
					</label>
					<div class="col-md-12">
						<form:select path="vaccinType" cssClass="form-control">
							<c:forEach var="adminVaccinationInfo"
								items="${adminVaccinationInfo}">
								<form:option value="${adminVaccinationInfo.vaccinType}">${adminVaccinationInfo.vaccinType} (${adminVaccinationInfo.notes})</form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label for="vaccinEffective" class="col-md-3 control-label">Vaccination
						Effective (MONTHS) </label>
					<div class="col-md-12">
						<form:select path="vaccinEffective" cssClass="form-control">
							<c:forEach var="adminVaccinationInfo"
								items="${adminVaccinationInfo}">
								<form:option value="${adminVaccinationInfo.vaccinEffective}">${adminVaccinationInfo.vaccinType} (${adminVaccinationInfo.vaccinEffective} Months)</form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label for="vaccinDate" class="col-md-6 control-label">Date
						(MM/DD/YYYY)</label>
					<div class="col-md-12">
						<form:input path="vaccinDate" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="notes" class="col-md-3 control-label">Remarks</label>
					<div class="col-md-12">
						<form:input path="notes" cssClass="form-control" />
					</div>
				</div>

				<div class="form-group">

					<div class="col-md-offset-3 col-md-9">
						<form:button cssClass="btn btn-primary">Add Record</form:button>
						<br>

					</div>
				</div>

			</form:form>

		</div>




		<!--  Enquiry Form -->
		<div id="enquiry" class="col-md-4">
			<h1 class="text align-self-center p-2">Enquire Us..</h1>
			<form:form action="addEnquiry" cssClass="form-horizontal"
				method="post" modelAttribute="Enquiry">
				<div class="form-group">
					<label for="news" class="col-md-3 control-label">Enquiry</label>
					<div class="col-md-12">
						<form:textarea style="height:150px" path="enquiry"
							cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-3 col-md-9">
						<form:button cssClass="btn btn-primary">Send</form:button>
						<br>
					</div>
				</div>
			</form:form>
		</div>



		<!--  Clinic Form -->
		<div id="clinic" class="col-md-4">
			<h1 class="text align-self-center p-2">Clinic Details..</h1>
			<form:form modelAttribute="ClinicInfo">
				<div class="form-group">
					<label for="vaccinType" class="col-md-4 control-label">Select
						a Clinic </label>
					<div class="col-md-12">
						<form:select path="clinicName"
							onchange="getClinicDetail(this.value);" cssClass="form-control">
							<form:option value="">Select a Clinic</form:option>
							<c:forEach var="clinicInfo" items="${clinicInfo}">
								<form:option value="${clinicInfo.clinicId}">${clinicInfo.clinicName}</form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
			</form:form>
		</div>

	</div>



</body>
</html>
