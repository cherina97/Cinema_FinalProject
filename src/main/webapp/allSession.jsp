<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
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

            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="All" role="tabpanel"
                     aria-labelledby="home-tab">
                    <c:if test="${requestScope.allSession.size() == 0}" var="event" scope="session">
                    <h4 class="text-center py-3 empty-events">
                        <i class="far fa-folder-open"></i> No event
                    </h4>
                    </c:if>
                    <div class="row">
                        <c:forEach var="session" items="${requestScope.allSession}">
                            <div class="col-md-4">
                                <br>
                                <div class="card">
                                    <img class="card-img-top"
                                         src="${pageContext.servletContext.contextPath}/posterServlet?id=${session.film.id}"
                                         alt="Card image cap">
                                    <div class="card-body">
                                        <h5 class="card-title">
                                            <c:choose>
                                                <c:when test="${language == 'en'}">
                                                    ${session.film.filmTitle}
                                                </c:when>
                                                <c:otherwise>
                                                    ${session.film.filmTitleUK}
                                                </c:otherwise>
                                            </c:choose>

                                        </h5>
                                        <p class="card-text">
                                            <c:choose>
                                                <c:when test="${language == 'en'}">
                                                    ${session.film.description}
                                                </c:when>
                                                <c:otherwise>
                                                    ${session.film.descriptionUK}
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item"><fmt:message
                                                key="allSession.date"/>: ${session.date}</li>
                                        <li class="list-group-item"><fmt:message
                                                key="allSession.start"/>: ${session.startAt};
                                            <fmt:message key="allFilms.duration"/>: ${session.film.duration} </li>
                                        <li class="list-group-item"><fmt:message
                                                key="allSession.seats"/>: ${session.freePlaces}</li>
                                        <c:if test="${sessionScope.user.roleId == 2}">
                                            <li class="list-group-item">
                                                <a href="${pageContext.request.contextPath}/allSession/admin/update?id=${session.id}">Update</a>
                                                <a href="${pageContext.request.contextPath}/allSession/admin/delete?id=${session.id}">Delete</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                    <div class="card-body">
                                        <a href="${pageContext.request.contextPath}/allSession/tickets?id=${session.id}"
                                           class="card-link"><fmt:message key="allSession.tickets"/></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>

        </main>
    </div>
</div>

<script src="js/navbar.js"></script>

</body>
</html>