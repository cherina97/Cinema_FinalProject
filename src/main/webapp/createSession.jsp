<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<jsp:useBean id="allFilms" scope="request" type="java.util.List"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Create session</title>
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div id="viewport">

    <form class="register-form" action="createSession" method="post">

        <label>
            <select name="filmId">
                <option value="" disabled selected hidden> Select film for session</option>
                <c:forEach var="film" items="${allFilms}">
                    <option value="${film.id}">${film.filmTitle}</option>
                </c:forEach>
            </select>
        </label>


        <label>
            <input name="startAt" type="time" placeholder="Start at"/>
        </label>

        <label>
            <select name="weekDay">
                <c:forEach items="${applicationScope.weekDays}" var="dayOfWeek">
                    <option value="${dayOfWeek.name()}">${dayOfWeek.name()}</option>
                </c:forEach>
            </select>
        </label>

        <button>Create session</button>
    </form>

</div>

</body>
</html>