<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <title>Student form</title>
</head>
<body>
<style>
    .error {
        color: red;
    }
</style>
<h3>Student Form</h3>
<div>
    <%--@elvariable id="student" type="java"--%>
    <form:form action="save-student" method="post" modelAttribute="student">
        <form:hidden path="encId" />
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
            <input type="submit" value="Save" />
        </div>
    </form:form>
</div>

<p>
    <a href="${pageContext.request.contextPath}/student/all">Back to Students</a>
</p>
</body>
</html>
