<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'uk_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="prop"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="ISO-8859-1">
    <title><fmt:message key="allSession.all"/></title>

    <link rel="stylesheet" href="css/allSessions.css">
    <link rel="stylesheet" href="css/allFilms.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createFilm.css"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


</head>
<body>

<div class="cont">

    <div class="header">
        <jsp:include page="navbar.jsp"></jsp:include>
    </div>

    <div class="mainBlock">
        <main role="main" class="container">
            <div class="sortingButtons">
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/allSession?sortBy=filmTitle"
                   role="button"><fmt:message key="allSession.sort.title"/></a>
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/allSession?sortBy=freePlace"
                   role="button"><fmt:message key="allSession.sort.seats"/></a>
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/allSession?sortBy=date" role="button">
                    <fmt:message key="allSession.sort.date"/></a>
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/allSession?sortBy=allSession"
                   role="button"><fmt:message key="allSession.all"/></a>
            </div>

            <table class="table">
                <caption><fmt:message key="allSession.all"/></caption>
                <thead>
                <tr>
                    <%--                    <th>Id</th>--%>
                    <th><fmt:message key="allFilms.poster"/></th>
                    <th><fmt:message key="allFilms.title"/></th>
                    <th><fmt:message key="allFilms.desc"/></th>
                    <th><fmt:message key="allSession.date"/></th>
                    <th><fmt:message key="allSession.start"/></th>
                    <th><fmt:message key="allFilms.duration"/></th>
                    <th><fmt:message key="allSession.seats"/></th>
                    <th><fmt:message key="allFilms.all"/></th>
                    <c:if test="${sessionScope.user.roleId == 2}">
                        <th>Update</th>
                        <th>Delete</th>
                    </c:if>

                </tr>
                </thead>
                <c:forEach var="session" items="${requestScope.allSession}">
                    <tbody>
                        <%--                    <td>${session.id}</td>--%>
                    <td>
                        <div class="image">
                            <img src="${pageContext.servletContext.contextPath}/posterServlet?id=${session.film.id}"/>
                        </div>
                    </td>
                    <td>${session.film.filmTitle}</td>
                    <td>${session.film.description}</td>
                    <td>${session.date} </td>
                    <td>${session.startAt} </td>
                    <td>${session.film.duration} </td>
                    <td>${session.freePlaces}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/allSession/tickets?id=${session.id}">
                            <fmt:message key="allSession.tickets"/>
                        </a>
                    </td>

                    <c:if test="${sessionScope.user.roleId == 2}">
                        <td>
                            <a href="${pageContext.request.contextPath}/allSession/admin/update?id=${session.id}">Update</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/allSession/admin/delete?id=${session.id}">Delete</a>
                        </td>
                    </c:if>
                    </tbody>
                </c:forEach>
            </table>
        </main>
    </div>
</div>

<script src="js/navbar.js"></script>

</body>
</html>