<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
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
    <title>Your tickets</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createFilm.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/boughtTickets.css"/>

</head>
<body>

<div class="cont">

    <div class="header">
        <jsp:include page="navbar.jsp"></jsp:include>
    </div>

    <div class="mainBlock">
        <main role="main" class="container">

            <div>
                <c:forEach var="ticket" items="${requestScope.ticketsByUser}">
                    <div class="cardWrap">
                        <div class="card cardLeft">
                            <h1><span><fmt:message key="tickets.ticket"/> </span></h1>
                            <div class="title">
                                <h2>
                                    <c:choose>
                                        <c:if test="${language == 'en'}">
                                            ${ticket.session.film.filmTitle}
                                        </c:if>
                                        <c:otherwise>
                                            ${ticket.session.film.filmTitleUK}
                                        </c:otherwise>
                                    </c:choose>
                                </h2>
                                <span><fmt:message key="tickets.film"/> </span>
                            </div>
                            <div class="name">
                                <h2>${sessionScope.user.firstName} ${sessionScope.user.lastName}</h2>
                                <span><fmt:message key="tickets.name"/> </span>
                            </div>
                            <div class="seat">
                                <h2>${ticket.seatNumber}</h2>
                                <span><fmt:message key="tickets.seat"/> </span>
                            </div>
                            <div class="time">
                                <h2>${ticket.session.startAt}</h2>
                                <span><fmt:message key="tickets.time"/> </span>
                            </div>

                        </div>
                        <div class="card cardRight">
                            <div class="eye"></div>
                            <div class="number">
                                <h3>${ticket.seatNumber}</h3>
                                <span><fmt:message key="tickets.seat"/> </span>
                            </div>
                            <div class="barcode"></div>
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