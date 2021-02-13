<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'uk_UA'}" scope="session" />
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
    <table>
        <caption>All films</caption>

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

        <c:forEach var="film" items="${requestScope.filmList}">
            <tbody>
            <td>${film.id}</td>
            <td>
                <div class="image">
                    <img src="${pageContext.servletContext.contextPath}/posterServlet?id=${film.id}" />

                </div>
            </td>
            <td>${film.filmTitle} </td>
            <td>${film.description} </td>
            <td>${film.duration} </td>
            <td><a href="${pageContext.request.contextPath}/allFilms/update?id=${film.id}">Update</a>
                <a href="${pageContext.request.contextPath}/allFilms/updatePoster?id=${film.id}">Update poster</a>
            </td>
            <td><a href="${pageContext.request.contextPath}/allFilms/delete?id=${film.id}">Delete</a></td>
            </tbody>
        </c:forEach>
    </table>

    <h3><fmt:message key="label.welcome"/></h3>

    <span class="lang">
        <form>
            <select name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
                <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>UK</option>
            </select>
        </form>
    </span>

</div>

</body>
</html>