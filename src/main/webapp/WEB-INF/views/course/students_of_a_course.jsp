<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>${course.title}'s students</title>
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

<h2>Students who took ${course.title}</h2>

<p>
    <c:forEach var="message" items="${messages}">
        <span class="${message.key}">${message.value}</span><br/>
    </c:forEach>
</p>

<div class="table">
    <table border="1">
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
        </tr>
        <c:forEach var="student" items="${course.students}">
            <tr>
                <td>${student.first_name}</td>
                <td>${student.last_name}</td>
                <td>${student.email}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<p>
    <a href="${pageContext.request.contextPath}/course/all">Back to course list</a>
</p>
</body>
</html>