<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <title>Instructor form</title>
</head>
<body>
<h3>Instructor Form</h3>
<div>
    <%--@elvariable id="instructor" type="java"--%>
    <form:form action="saveInstructor" method="post" modelAttribute="instructor">
        <div>
            <form:label path="firstName">First Name : </form:label>
            <form:input path="firstName" />
        </div>

        <div>
            <form:label path="lastName">Last Name : </form:label>
            <form:input path="lastName" />
        </div>

        <div>
            <form:label path="email">Email : </form:label>
            <form:input path="email" />
        </div>

        <div>
            <form:label path="instructorDetails.hobby">Hobby : </form:label>
            <form:input path="instructorDetails.hobby" />
        </div>

        <div>
            <form:label path="instructorDetails.youTubeChannel">Channel : </form:label>
            <form:input path="instructorDetails.youTubeChannel" />
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
