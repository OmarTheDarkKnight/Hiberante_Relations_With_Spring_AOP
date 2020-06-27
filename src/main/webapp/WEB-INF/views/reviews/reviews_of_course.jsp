<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <title>${course.title}'s reviews</title>
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

<h2>Reviews of ${course.title}</h2>

<p>
    <c:forEach var="message" items="${messages}">
        <span class="${message.key}">${message.value}</span><br/>
    </c:forEach>
</p>

<!-- Create course update link -->
<c:url var="updateLink" value="/reviews/review-form" />

<div>
    <p>Course Taken by : ${course.name} </p>
    <p>email : ${course.email} </p>
    <p>Course Rating : ${course.rating}</p>
</div>

<div class="button">
    <a href="${updateLink}">
        <button>Give Review</button>
    </a>
</div>

<div class="table">
    <table border="1">
        <tr>
            <th>Rating</th>
            <th>Comment</th>
            <th>Action</th>
        </tr>
        <c:forEach var="review" items="${course.reviewDtoList}">
            <tr>
                <td>${review.rating}</td>
                <td>${review.comment}</td>
                <td>
                    <a href="${updateLink}?parent=${course.endId}&target=${review.encId}">Update</a>
                    |
                    <a onclick="deleteSubmit('${review.encId}', '${course.endId}')"
                       href="javascript:void(0)">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<form:form action="delete" method="post" id="reviewDeleteForm">
    <input type="hidden" name="theId" value="" />
    <input type="hidden" name="theParentId" value="" />
</form:form>

<p>
    <a href="${pageContext.request.contextPath}/course/all">Back to course list</a>
</p>
</body>
<script>
    function deleteSubmit(theID, PID) {
        let confirmation = confirm('Are you sure you want to delete this review?');
        if(confirmation) {
            let form = document.getElementById("reviewDeleteForm");
            form.elements["theId"].value = theID;
            form.elements["thePId"].value = PID;
            form.submit();
        }
    }
</script>
</html>