<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <title>Instructor form</title>
</head>
<body>
<style>
    .error {
        color: red;
    }
</style>
<h3>Instructor Form</h3>
<div>
    <div class="table">
        <table border="1">
            Courses taken by ${instructor.first_name} ${instructor.last_name}
            <br>
            Email: ${instructor.email}
            <tr>
                <th>Title</th>
                <th>Rating</th>
            </tr>
            <c:forEach var="course" items="${instructor.courses}">
                <tr>
                    <td>${course.title}</td>
                    <td>${course.rating}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div>
        <a href="${pageContext.request.contextPath}/course/course-form?parent=${instructor.encId}">Add a new course</a>
    </div>
</div>

<p>
    <a href="${pageContext.request.contextPath}/instructor/all">Back to List</a>
</p>
</body>
</html>