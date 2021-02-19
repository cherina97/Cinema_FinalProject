<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Your tickets</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/allSessions.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createFilm.css"/>

</head>
<body>


<div id="viewport">
    <!-- Sidebar -->
    <jsp:include page="navbar.jsp"></jsp:include>

    <!-- Content -->
    <div id="content">
        <table>
            <caption>Your tickets</caption>

            <thead>
            <tr>
                <th>Id</th>
                <th>Seat number</th>
                <th>Price</th>
                <th>User id</th>
                <th>Session id</th>
            </tr>
            </thead>

            <c:forEach var="ticket" items="${requestScope.ticketsByUser}">
                <tbody>
                <td>${ticket.id}</td>
                <td>${ticket.seatNumber} </td>
                <td>${ticket.price} </td>
                <td>${ticket.userId} </td>
                <td>${ticket.sessionId} </td>
                </tbody>
            </c:forEach>
        </table>
    </div>
</div>

<script src="js/navbar.js"></script>


</body>
</html>