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
    <%--@elvariable id="course" type="java"--%>
    <form:form action="saveCourse" method="post" modelAttribute="course">
        <form:hidden path="encId" />
        <form:hidden path="encInstructor_id" />
        <div>
            <form:label path="title" cssErrorClass="error">Course title : </form:label>
            <form:input path="title" />
            <form:errors path="title" cssClass="error"/>
        </div>

        <div>
            <c:choose>
                <c:when test="${empty course.email}">
                    <form:label path="email" cssErrorClass="error">Instructor's Email : </form:label>
                    <form:input path="email" />
                    <form:errors path="email" cssClass="error"/>
                </c:when>
                <c:otherwise>
                    Instructor's Email : ${course.email}
                </c:otherwise>
            </c:choose>
        </div>

        <div>
            <input type="submit" value="Save" />
        </div>
    </form:form>
</div>

<p>
    <a href="${pageContext.request.contextPath}/course/all">Back to List</a>
</p>
</body>
</html>
