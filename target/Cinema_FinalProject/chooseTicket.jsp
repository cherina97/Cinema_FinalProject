<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Tickets</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/allSessions.css"/>
    <%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/choose.css"/>
    <%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createFilm.css"/>--%>

</head>
<body>

<div class="cont">

    <div class="header">
        <jsp:include page="navbar.jsp"></jsp:include>
    </div>

    <div class="mainBlock">

        <main role="main" class="container">
            <div class="seatStructure">
                <div style="text-align: center;">

                    <form action="${pageContext.request.contextPath}/allSession/tickets" method="post">
                        <table id="seatsBlock">

                            <input name="id" type="hidden" value="${sessionScope.sessionId}"/>

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

                            <tr>
                                <th>Row 3</th>
                                <c:forEach var="ticket" items="${requestScope.range3}">
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
                                <th>Row 4</th>
                                <c:forEach var="ticket" items="${requestScope.range4}">
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
                                <th>Row 5</th>
                                <c:forEach var="ticket" items="${requestScope.range5}">
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
                                <th>Row 6</th>
                                <c:forEach var="ticket" items="${requestScope.range6}">
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

                        <c:if test="${sessionScope.user ne null}">
                            <button class="btn btn-dark"> Buy</button>
                        </c:if>
                    </form>
                </div>
            </div>
        </main>
    </div>
</div>


</div>
<script src="js/navbar.js"></script>


<script src="${pageContext.request.contextPath}/js/choose.js"></script>
<script src="${pageContext.request.contextPath}/js/navbar.js"></script>
</body>
</html>