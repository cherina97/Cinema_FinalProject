<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>All sessions</title>

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
            <th>Film title</th>
            <th>Start At</th>
            <th>Week day</th>
            <th>Tickets</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>

        <c:forEach var="session" items="${requestScope.sessionList}">
            <tbody>
            <td>${session.id}</td>
            <td>${session.film.filmTitle}</td>
            <td>${session.startAt} </td>
            <td>${session.weekDay} </td>
            <td>
                <a href="${pageContext.request.contextPath}/allSession/tickets?id=${session.id}">
                    Tickets
                </a>
            </td>
            <td> <a href="${pageContext.request.contextPath}/allSession/update?id=${session.id}">Update</a> </td>
            <td> <a href="${pageContext.request.contextPath}/allSession/delete?id=${session.id}">Delete</a> </td>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>