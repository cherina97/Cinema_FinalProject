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
        <caption>All films</caption>

        <thead>
        <tr>
            <th>Id</th>
            <th>Film Title</th>
            <th>Description</th>
            <th>Duration</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>

        <c:forEach var="film" items="${requestScope.filmList}">
            <tbody>
            <td>${film.id}</td>
            <td>${film.filmTitle} </td>
            <td>${film.description} </td>
            <td>${film.duration} </td>
            <td> <a href="${pageContext.request.contextPath}/allFilms/update?id=${film.id}">Update</a> </td>
            <td> <a href="${pageContext.request.contextPath}/allFilms/delete?id=${film.id}">Delete</a> </td>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>