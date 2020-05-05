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
            Courses taken by ${instructor.firstName} ${instructor.lastName}
            <tr>
                <th>Title</th>
                <th>Review</th>
            </tr>
            <c:forEach var="course" items="${instructor.courses}">
                <tr>
                    <td>${course.title}</td>
                    <c:set var="sum" value="${0}"/>
                    <c:forEach var="review" items="${course.reviews}">
                        <c:set var="sum" value="${sum + review.rating}" />
                    </c:forEach>
                    <td>${sum / fn:length(course.reviews)}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<p>
    <a href="${pageContext.request.contextPath}/instructor/all">Back to List</a>
</p>
</body>
</html>