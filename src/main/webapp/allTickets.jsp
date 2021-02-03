<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Tickets</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/allSessions.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>

</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div id="viewport">
    <table>
        <caption>All tickets by selected session</caption>

        <thead>
        <tr>
            <th>Session id</th>
            <th>Ticket id</th>
            <th>Seat Number</th>
            <th>Price</th>
            <th>User id</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>

        <c:forEach var="ticket" items="${requestScope.ticketList}">
            <tbody>
            <td><c:out value="${requestScope.sessionId}"/></td>
            <td>${ticket.id} </td>
            <td>${ticket.seatNumber} </td>
            <td>${ticket.price} </td>
            <td>${ticket.userId} </td>
            <td>
                <c:choose>
                    <c:when test="${ticket.userId ne null}">
                        Available to buy
                    </c:when>
                    <c:otherwise>
                        Seat is taken
                    </c:otherwise>
                </c:choose>
            </td>

            <td>
                <c:if test="${sessionScope.user.get() ne null}">
                    <a href="${pageContext.request.contextPath}/allSession/tickets/buy">
                        Buy a ticket
                    </a>
                </c:if>
            </td>
            </tbody>
        </c:forEach>
    </table>

</div>

<script src="${pageContext.request.contextPath}/js/navbar.js"></script>
</body>
</html>