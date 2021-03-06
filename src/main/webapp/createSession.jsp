<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%--<jsp:useBean id="allFilms" scope="request" type="java.util.List"/>--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Create session</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createFilm.css"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"></link>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/vendors/formvalidation/dist/css/formValidation.min.css">

</head>
<body>

<div class="cont">

    <div class="header">
        <jsp:include page="navbar.jsp"></jsp:include>
    </div>

    <div class="mainBlock">

        <main role="main" class="container">

            <form class="register-form" action="createSession" method="post">

                <div class="form-group">
                    <label> Select film for session
                        <select name="filmId" class="custom-select" required>
                            <option value="" disabled selected hidden> Select film for session</option>
                            <c:forEach var="film" items="${requestScope.allFilms}">
                                <option value="${film.id}">${film.filmTitle}</option>
                            </c:forEach>
                        </select>
                    </label>
                </div>

                <div class="form-group">
                    <label>Enter a start time: </label>
                    <input name="startAt" type="time" class="form-control" placeholder="Start at"
                           required>
                    <div class="error">
                        <p> ${requestScope.error} </p>
                    </div>
                </div>

                <div class="form-group">
                    <label>Enter a date: </label>
                    <input name="date" type="date" class="form-control" placeholder="Date"
                           required>
                </div>

                <button type="submit" class="btn btn-primary">Create session</button>
            </form>
        </main>
    </div>

</div>

<script src="js/navbar.js"></script>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

</body>
</html>