<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<jsp:useBean id="allFilms" scope="request" type="java.util.List"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update session</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>
</head>
<body>

<div class="cont">

    <div class="header">
        <jsp:include page="navbar.jsp"></jsp:include>
    </div>

    <div class="mainBlock">

        <main role="main" class="container">

            <form class="register-form" action="${pageContext.request.contextPath}/allSession/update" method="post">

                <input name="id" type="hidden" value="${requestScope.sessionById.id}"/>

                <label>
                    <select name="filmId">
                        <%--                <option value="${requestScope.sessionById.film.filmTitle}"> </option>--%>
                        <c:forEach var="film" items="${allFilms}">
                            <%--                    <option value="${film.id}">${film.filmTitle}</option>--%>
                            <option value="${film.id}"
                                ${requestScope.sessionById.film.filmTitle == film.filmTitle ? 'selected="selected"' : ''}>
                                    ${film.filmTitle}
                            </option>
                        </c:forEach>
                    </select>
                </label>


                <label>
                    <input name="startAt" type="time" value="${requestScope.sessionById.startAt}"/>
                </label>

                <label>
                    <select name="weekDay">
                        <%--                <option value="${requestScope.sessionById.weekDay}"></option>--%>
                        <c:forEach items="${applicationScope.weekDays}" var="dayOfWeek">
                            <%--                    <option value="${dayOfWeek.name()}">${dayOfWeek.name()}</option>--%>
                            <option value="${dayOfWeek.name()}"
                                ${requestScope.sessionById.weekDay == dayOfWeek.name() ? 'selected="selected"' : ''}>
                                    ${dayOfWeek.name()}
                            </option>
                        </c:forEach>
                    </select>
                </label>

                <button>Edit session</button>
            </form>
        </main>
    </div>

</div>

</body>
</html>