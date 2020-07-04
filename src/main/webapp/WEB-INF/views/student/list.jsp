<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Students</title>
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

<h2>Students</h2>

<p>
    <c:forEach var="message" items="${messages}">
        <span class="${message.key}">${message.value}</span><br/>
    </c:forEach>
</p>

<!-- Create url for showing student form -->
<c:url var="updateLink" value="/student/student-form" />

<div class="button">
    <a href="${updateLink}">
        <button>Add Student</button>
    </a>
</div>

<div class="table">
    <table border="1">
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <!-- Create url for showing courses for that student -->
            <c:url var="courseLink" value="/student/courses?target=${student.encId}" />
            <tr>
                <td>${student.first_name}</td>
                <td>${student.last_name}</td>
                <td>${student.email}</td>
                <td>
                    <a href="${courseLink}">See ${student.first_name}'s courses</a>
                    |
                    <a href="${updateLink}?target=${student.encId}">Update Student Data</a>
                    |
                    <a onclick="deleteSubmit('${student.encId}')"
                       href="javascript:void(0)">Delete Student</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<form:form action="delete" method="post" id="studentDeleteForm">
    <input type="hidden" name="theId" value="" />
</form:form>

<p>
    <a href="${pageContext.request.contextPath}/">Back to main menu</a>
</p>
</body>
<script>
    function deleteSubmit(theID) {
        let confirmation = confirm('Are you sure you want to delete this student?');
        if(confirmation) {
            let form = document.getElementById("studentDeleteForm");
            form.elements["theId"].value = theID;
            form.submit();
        }
    }
</script>
</html>