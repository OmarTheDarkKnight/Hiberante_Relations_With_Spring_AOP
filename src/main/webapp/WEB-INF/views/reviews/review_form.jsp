<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <title>Course form</title>
</head>
<body>
<style>
    .error {
        color: red;
    }
</style>
<h3>Course Form</h3>
<div>
    <%--@elvariable id="review" type="java"--%>
    <form:form action="${pageContext.request.contextPath}/reviews/saveReview" method="post" modelAttribute="review">
        <form:hidden path="encId" />
        <form:hidden path="encCourse_id" />

        <div>
            <form:label path="rating" cssErrorClass="error">Rating : </form:label>
            <form:input path="rating" />
            <form:errors path="rating" cssClass="error"/>
        </div>

        <div>
            <form:label path="comment" cssErrorClass="error">Comment : </form:label>
            <form:textarea path="comment" />
            <form:errors path="comment" cssClass="error"/>
        </div>

        <div>
            <input type="submit" value="Save" />
        </div>
    </form:form>
</div>

<p>
    <a href="${pageContext.request.contextPath}/reviews/${pid}">Back to The Reviews</a>
</p>
</body>
</html>