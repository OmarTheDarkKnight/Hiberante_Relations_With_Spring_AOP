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
        <form:hidden path="instructorDetails.id" />
        <div>
            <form:label path="firstName" cssErrorClass="error">First Name : </form:label>
            <form:input path="firstName" />
            <form:errors path="firstName" cssClass="error"/>
        </div>

        <div>
            <form:label path="lastName" cssErrorClass="error">Last Name : </form:label>
            <form:input path="lastName" />
            <form:errors path="lastName" cssClass="error"/>
        </div>

        <div>
            <form:label path="email" cssErrorClass="error">Email : </form:label>
            <form:input path="email" />
            <form:errors path="email" cssClass="error"/>
        </div>

        <div>
            <form:label path="instructorDetails.hobby" cssErrorClass="error">Hobby : </form:label>
            <form:input path="instructorDetails.hobby" />
            <form:errors path="instructorDetails.hobby" cssClass="error"/>
        </div>

        <div>
            <form:label path="instructorDetails.youTubeChannel" cssErrorClass="error">Channel : </form:label>
            <form:input path="instructorDetails.youTubeChannel" />
            <form:errors path="instructorDetails.youTubeChannel" cssClass="error"/>
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
