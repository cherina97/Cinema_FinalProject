<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'uk_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="prop"/>

<!DOCTYPE html>

<html lang="${language}">
<head>
    <meta>
    <title>All sessions</title>

    <link rel="stylesheet" href="css/allSessions.css">
    <link rel="stylesheet" href="css/allFilms.css">
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div id="viewport">
    <caption>All films</caption>
    <table>

        <thead>
        <tr>
            <th>Id</th>
            <th>Poster</th>
            <th>Film Title</th>
            <th>Description</th>
            <th>Duration</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>

        <c:forEach var="film" items="${requestScope.filmListPagination}">
            <tbody>
            <td>${film.id}</td>
            <td>
                <div class="image">
                    <img src="${pageContext.servletContext.contextPath}/posterServlet?id=${film.id}"/>

                </div>
            </td>
            <td>${film.filmTitle} ${film.filmTitleUK}</td>
            <td>${film.description} ${film.descriptionUK}</td>
            <td>${film.duration} </td>
            <td><a href="${pageContext.request.contextPath}/allFilms/update?id=${film.id}">Update</a>
                <a href="${pageContext.request.contextPath}/allFilms/updatePoster?id=${film.id}">Update poster</a>
            </td>
            <td><a href="${pageContext.request.contextPath}/allFilms/delete?id=${film.id}">Delete</a></td>
            </tbody>
        </c:forEach>
    </table>

    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <c:if test="${requestScope.currentPage != 1}">
                <li class="page-item">
                    <a class="page-link" href="allFilms?page=${requestScope.currentPage - 1}"> &laquo; </a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                <c:choose>
                    <c:when test="${requestScope.currentPage eq i}">
                        <li class="page-item active">
                            <a class="page-link"> ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="allFilms?page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                <li class="page-item">
                    <a class="page-link" href="allFilms?page=${requestScope.currentPage + 1}"> &raquo; </a>
                </li>
            </c:if>
        </ul>
    </nav>

</div>

</body>
</html>