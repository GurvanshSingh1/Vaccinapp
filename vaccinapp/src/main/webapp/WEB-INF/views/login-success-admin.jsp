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

		$("#postNews").hide();
		$("#newVaccination").hide();
		$("#newClinic").hide();

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

		$("#postNews").hide();
		$("#result").hide();
		$("#newClinic").hide();
		$("#myimage").hide();
	}

	function showhideNews() {
		$("#postNews").show();

		$("#newVaccination").hide();
		$("#result").hide();
		$("#newClinic").hide();
		$("#myimage").hide();
	}

	function showhideClinic() {
		$("#newClinic").show();

		$("#newVaccination").hide();
		$("#result").hide();
		$("#postNews").hide();
		$("#myimage").hide();
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
	<br>

	<div class="row">

		<div class="col-md-6">
			<br> <a><input type="button"
				onclick="showhideNewVaccination();" class="btn btn-primary btn-lg"
				value="Add New Vaccination" /></a><br> <a
				href="viewAllAdminRecords"><input type="button"
				class="btn btn-primary btn-lg" value="View/Remove Vaccinations" /></a><br>
			<a href="viewPendingApprovals"><input type="button"
				class="btn btn-primary btn-lg" value="Pending User Approvals" /></a> <br>
			<a href="#"><input type="button" onclick="showhideClinic();"
				class="btn btn-primary btn-lg" value="Add Clinics" /></a> <br> <a
				href="viewClinics"><input type="button"
				class="btn btn-primary btn-lg" value="View/Remove Clinics" /></a> <br>
			<a href="viewAdminEnquiry"><input type="button"
				class="btn btn-primary btn-lg" value="View/Reply User Enquires" /></a>
			<br> <a href="#"><input type="button"
				onclick="showhideNews();" class="btn btn-primary btn-lg"
				value="Post News" /></a>
		</div>


		<!--  Result Section -->
		<div id="result" class="col-md-4">
			<h1 class="text align-self-center p-2">${approvedNews}</h1>
			<h1 class="text align-self-center p-2">${approved}</h1>
			<h1 class="text align-self-center p-2">${approvedClinic}</h1>

			<div id="notification" style="display: none;">
				${adminNotifications} <span class="dismiss"><a
					title="dismiss this notification">x</a></span>
			</div>
		</div>


		<!--  NEW Vaccination Form -->
		<div id="newVaccination" class="col-md-4">
			<h1 class="text align-self-center p-2">Add New Vaccination</h1>
			<form:form action="addNewAdminVaccination" cssClass="form-horizontal"
				method="post" modelAttribute="AdminVaccinationInfo">
				<div class="form-group">
					<label for="vaccinType" class="col-md-3 control-label">Vaccination</label>
					<div class="col-md-12">
						<form:input path="vaccinType" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="notes" class="col-md-6 control-label">Notes</label>
					<div class="col-md-12">
						<form:input path="notes" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="vaccinEffective" class="col-md-3 control-label">Validity Period
						(Months)</label>
					<div class="col-md-12">
						<form:input path="vaccinEffective" cssClass="form-control" />
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




		<!--  Daily News Form -->
		<div id="postNews" class="col-md-4">
			<h1 class="text align-self-center p-2">Post News</h1>
			<form:form action="postNews" cssClass="form-horizontal" method="post"
				modelAttribute="DailyNews">
				<div class="form-group">
					<label for="news" class="col-md-3 control-label">News</label>
					<div class="col-md-12">
						<form:textarea style="height:150px" path="news"
							cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-3 col-md-9">
						<form:button cssClass="btn btn-primary">Post</form:button>
						<br>
					</div>
				</div>
			</form:form>
		</div>


		<!--  NEW Clinic Form -->
		<div id="newClinic" class="col-md-4">
			<h1 class="text align-self-center p-2">New Clinic Detail</h1>
			<form:form action="addNewClinic" cssClass="form-horizontal"
				method="post" modelAttribute="ClinicInfo">
				<div class="form-group">
					<label for="clinicName" class="col-md-3 control-label">Clinic
						Name</label>
					<div class="col-md-12">
						<form:input path="clinicName" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="clinicAddress" class="col-md-6 control-label">Clinic
						Address</label>
					<div class="col-md-12">
						<form:input path="clinicAddress" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="clinicContact" class="col-md-3 control-label">Clinic
						Contact</label>
					<div class="col-md-12">
						<form:input path="clinicContact" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="clinicEmail" class="col-md-3 control-label">Clinic
						Email</label>
					<div class="col-md-12">
						<form:input path="clinicEmail" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-3 col-md-9">
						<form:button cssClass="btn btn-primary">Add Clinic</form:button>
						<br>
					</div>
				</div>
			</form:form>
		</div>
	</div>

</body>
</html>
