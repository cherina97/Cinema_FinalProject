<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
    <meta>
    <title>All genres</title>

    <link rel="stylesheet" href="css/allSessions.css">
    <link rel="stylesheet" href="css/allFilms.css">
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div id="viewport">
    <table>
        <caption>All genres</caption>

        <thead>
        <tr>
            <th>Id</th>
            <th>Genre name</th>
            <th>Genre name uk</th>
        </tr>
        </thead>

        <c:forEach var="genre" items="${requestScope.genres}">
            <tbody>
            <td>${genre.id}</td>
            <td>${genre.genreName}</td>
            <td>${genre.genreNameUK}</td>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>