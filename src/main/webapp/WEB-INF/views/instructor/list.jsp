<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Instructors</title>
</head>
<body>
<style>
	.error {
		color: red;
	}
	.success {
		color: green;
	}
</style>

	<h2>Instructors</h2>

    <p>
        <c:forEach var="message" items="${messages}">
            <span class="${message.key}">${message.value}</span><br/>
        </c:forEach>
    </p>

	<!-- Create url for showing instructor form -->
	<c:url var="updateLink" value="/instructor/instructor-form" />

	<div class="button">
		<a href="${updateLink}">
			<button>Add Instructor</button>
		</a>
	</div>

	<div class="table">
		<table border="1">
			<tr>
				<th>First name</th>
				<th>Last name</th>
				<th>Email</th>
				<th>Hobby</th>
				<th>Channel</th>
				<th>Action</th>
			</tr>
			<c:forEach var="instructor" items="${instructors}">
				<!-- Create url for showing courses for that instructor -->
				<c:url var="courseLink" value="/instructor/courses?target=${instructor.encId}" />
				<tr>
					<td>${instructor.first_name}</td>
					<td>${instructor.last_name}</td>
					<td>${instructor.email}</td>
					<td>${instructor.hobby}</td>
					<td>${instructor.youtube_channel}</td>
					<td>
						<a href="${courseLink}">See courses</a>
						|
						<a href="${updateLink}?target=${instructor.encId}">Update</a>
						|
						<a onclick="deleteSubmit(${instructor.encId})"
								href="javascript:void(0)">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<form:form action="delete" method="post" id="instructorDeleteForm">
		<input type="hidden" name="theId" value="" />
	</form:form>

	<p>
		<a href="${pageContext.request.contextPath}/">Back to main menu</a>
	</p>
</body>
<script>
	function deleteSubmit(theID) {
		let confirmation = confirm('Are you sure you want to delete this value?');
		if(confirmation) {
			let form = document.getElementById("instructorDeleteForm");
			form.elements["theId"].value = theID;
			form.submit();
		}
	}
</script>
</html>