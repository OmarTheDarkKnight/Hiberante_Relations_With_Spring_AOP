<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <title>Student's courses</title>
</head>
<body>
<style>
    .error {
        color: red;
    }
</style>
<h3>Courses Taken By ${student.first_name}</h3>
<div>
    <div class="table">
        <table border="1">
            <tr>
                <th>Title</th>
                <th>Rating</th>
                <th>Action</th>
            </tr>
            <c:forEach var="course" items="${student.courses}">
                <tr>
                    <!--  -->
                    <c:url var="detailsLink" value="/reviews/${course.encId}"/>
                    <td>${course.title}</td>
                    <td>${course.rating}</td>
                    <td>
                        <a href="${detailsLink}" >See detailed review</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<p>
    <a href="${pageContext.request.contextPath}/student/all">Back to List</a>
</p>
</body>
</html>