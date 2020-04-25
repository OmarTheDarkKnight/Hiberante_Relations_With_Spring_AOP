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
	<h2>Instructors</h2>
	<!-- Create url for showing instructor form -->
	<c:url var="updateLink" value="/instructor/instructor-form?target=" />

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
				<!-- Create url for delete -->
				<c:url var="deleteLink" value="/instructor/delete">
					<c:param name="target" value="${instructor.id}" />
				</c:url>

				<tr>
					<td>${instructor.firstName}</td>
					<td>${instructor.lastName}</td>
					<td>${instructor.email}</td>
					<td>${instructor.instructorDetails.hobby}</td>
					<td>${instructor.instructorDetails.youTubeChannel}</td>
					<td>
						<a href="${updateLink}${instructor.id}">Update</a>
						|
						<a onclick="if(!confirm('Are you sure you want to delete this value?')) return false"
								href="${deleteLink}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>