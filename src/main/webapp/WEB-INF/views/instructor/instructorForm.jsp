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
    <%--@elvariable id="instructor" type="java"--%>
    <form:form action="saveInstructor" method="post" modelAttribute="instructor">
        <form:hidden path="id" />
        <form:hidden path="instructor_detail_id" />
        <div>
            <form:label path="first_name" cssErrorClass="error">First Name : </form:label>
            <form:input path="first_name" />
            <form:errors path="first_name" cssClass="error"/>
        </div>

        <div>
            <form:label path="last_name" cssErrorClass="error">Last Name : </form:label>
            <form:input path="last_name" />
            <form:errors path="last_name" cssClass="error"/>
        </div>

        <div>
            <form:label path="email" cssErrorClass="error">Email : </form:label>
            <form:input path="email" />
            <form:errors path="email" cssClass="error"/>
        </div>

        <div>
            <form:label path="hobby" cssErrorClass="error">Hobby : </form:label>
            <form:input path="hobby" />
            <form:errors path="hobby" cssClass="error"/>
        </div>

        <div>
            <form:label path="youtube_channel" cssErrorClass="error">Channel : </form:label>
            <form:input path="youtube_channel" />
            <form:errors path="youtube_channel" cssClass="error"/>
        </div>

        <div>
            <input type="submit" value="Save" />
        </div>
    </form:form>
</div>

<p>
    <a href="${pageContext.request.contextPath}/instructor/all">Back to List</a>
</p>
</body>
</html>
