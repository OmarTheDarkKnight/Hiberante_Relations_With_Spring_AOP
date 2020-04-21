<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>First name</th>
			<th>Last name</th>
			<th>Email</th>
		</tr>
		<c:forEach var="instructor" items="${instructors}">
			<tr>
				<td>${instrunctor.firstName}</td>
				<td>${instrunctor.lastName}</td>
				<td>${instrunctor.email}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>