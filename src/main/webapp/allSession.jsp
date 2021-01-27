<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Create session</title>

    <link rel="stylesheet" href="css/allSessions.css">
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div id="viewport">
    <table>
        <caption>All sessions</caption>

        <thead>
        <tr>
            <th>Id</th>
            <th>Film Title</th>
            <th>Description</th>
            <th>Start At</th>
            <th>Duration</th>
            <th>Tickets</th>
            <th>Action</th>
        </tr>
        </thead>

        <c:forEach var="session" items="${requestScope.sessionList}">
            <tbody>
            <td><c:out value="${session.id}"/></td>
            <td>${session.filmTitle} </td>
            <td>${session.description} </td>
            <td>${session.startAt} </td>
            <td>${session.duration} </td>
            <td>${session.tickets} </td>
            <td><a href="${pageContext.request.contextPath}/tickets">
                Tickets
            </a></td>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>