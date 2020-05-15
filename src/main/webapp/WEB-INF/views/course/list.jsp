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

    <!-- Create course update link -->
    <c:url var="updateLink" value="/course/course-form" />

    <div class="button">
        <a href="${updateLink}">
            <button>Add Course</button>
        </a>
    </div>

    <div class="table">
        <table border="1">
            <tr>
                <th>Title</th>
                <th>Instructor's name</th>
                <th>Instructor's email</th>
                <th>Rating</th>
                <th>Action</th>
            </tr>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td>${course.title}</td>
                    <td>${course.instructor.firstName} ${course.instructor.lastName}</td>
                    <td>${course.instructor.email}</td>
                    <td>${course.rating}</td>
                    <td>
                        <a href="${updateLink}?target=${course.id}&parent=${course.instructor.id}">Update</a>
                        |
                        <a onclick="deleteSubmit(${course.id})"
                           href="javascript:void(0)">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <form:form action="delete" method="post" id="courseDeleteForm">
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
            let form = document.getElementById("courseDeleteForm");
            form.elements["theId"].value = theID;
            form.submit();
        }
    }
</script>
</html>