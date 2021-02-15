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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/choose.css"/>

</head>
<body onload="onLoaderFunc()">

<div id="viewport">

    <!-- Sidebar -->
    <jsp:include page="navbar.jsp"></jsp:include>

    <!-- Content -->
    <div id="content">
        <div class="inputForm">
            <div style="text-align: center;">
                Name *: <input type="text" id="Username" required>
                Number of Seats *: <input type="number" id="Numseats" required>
                <br/><br/>
                <button onclick="takeData()">Start Selecting</button>
            </div>
        </div>

        <div class="seatStructure">
            <div style="text-align: center;">


                <%-- table--%>
                <form action="${pageContext.request.contextPath}/allSession/tickets" method="post">
                    <table id="seatsBlock">

                        <p id="notification"></p>

                        <%--HEAD--%>
                        <thead>
                        <tr>
                            <th colspan="14">
                                <div class="screen">SCREEN</div>
                            </th>
                        </tr>

                        </thead>


                        <%--BODY--%>
                        <tbody>

                        <tr>
                            <th>Row 1</th>

                            <c:forEach var="ticket" items="${requestScope.range1}">
                                <td>
                                    <c:choose>
                                        <c:when test="${ticket.userId != 0}">
                                            xxx
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="seats" value="${ticket.seatNumber}"
                                                   name="checkedSeats">
                                            <div class="circle_container">
                                                <div class="circle_main">
                                                    <div class="circle_text_container">
                                                        <div class="circle_text">
                                                                ${ticket.seatNumber}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:forEach>

                            <th></th>
                        </tr>

                        <tr>
                            <th>Row 2</th>
                            <c:forEach var="ticket" items="${requestScope.range2}">
                                <td>
                                    <c:choose>
                                        <c:when test="${ticket.userId != 0}">
                                            xxx
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="seats" value="${ticket.seatNumber}"
                                                   name="checkedSeats">
                                            <div class="circle_container">
                                                <div class="circle_main">
                                                    <div class="circle_text_container">
                                                        <div class="circle_text">
                                                                ${ticket.seatNumber}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:forEach>
                            <th></th>
                        </tr>


                        </tbody>


                    </table>
                    <button> Buy</button>

                </form>

                <%--                    <br/>--%>
                <%--                    <button onclick="updateTextArea()">Confirm Selection</button>--%>
            </div>
        </div>

        <%--            <br/><br/>--%>

        <%--            <div class="displayerBoxes">--%>
        <%--                <div style="text-align: center;">--%>
        <%--                    <table class="Displaytable">--%>
        <%--                        <tr>--%>
        <%--                            <th>Name</th>--%>
        <%--                            <th>Number of Seats</th>--%>
        <%--                            <th>Seats</th>--%>
        <%--                        </tr>--%>
        <%--                        <tr>--%>
        <%--                            <td><textarea id="nameDisplay"></textarea></td>--%>
        <%--                            <td><textarea id="NumberDisplay"></textarea></td>--%>
        <%--                            <td><textarea id="seatsDisplay"></textarea></td>--%>
        <%--                        </tr>--%>
        <%--                    </table>--%>
        <%--                </div>--%>
        <%--            </div>--%>

    </div>

</div>


<script src="${pageContext.request.contextPath}/js/choose.js"></script>
<script src="${pageContext.request.contextPath}/js/navbar.js"></script>
</body>
</html>