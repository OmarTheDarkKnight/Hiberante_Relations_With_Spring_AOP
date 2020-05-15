<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <title>Courses</title>
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

    <h2>Courses</h2>

    <p>
        <c:forEach var="message" items="${messages}">
            <span class="${message.key}">${message.value}</span><br/>
        </c:forEach>
    </p>

    <div class="table">
        <table border="1">
            <tr>
                <th>Title</th>
                <th>Instructor's name</th>
                <th>Instructor's email</th>
                <th>Rating</th>
            </tr>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td>${course.title}</td>
                    <td>${course.instructor.firstName} ${course.instructor.lastName}</td>
                    <td>${course.instructor.email}</td>
                    <td>${course.rating}</td>
                    <td>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <p>
        <a href="${pageContext.request.contextPath}/">Back to main menu</a>
    </p>
</body>
</html>
