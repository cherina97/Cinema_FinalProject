<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Create film</title>
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div id="viewport">

    <form class="register-form" action="addFilm" method="post" enctype="multipart/form-data">

        <label>
            <input name="filmTitle" type="text" placeholder="Film title"/>
        </label>

        <label>
            <input name="filmTitleUK" type="text" placeholder="Film title uk"/>
        </label>

        <label>
            <input name="description" type="text" placeholder="Description"/>
        </label>

        <label>
            <input name="descriptionUK" type="text" placeholder="Description uk"/>
        </label>

        <label>
            <input name="duration" type="time" placeholder="Duration"/>
        </label>

        <br>
        <br>

        <label>
            <input name="poster" type="file"/>
        </label>

        <br>
        <br>

        <select name="genres" multiple>
            <option value="" disabled selected hidden> Select subject</option>
            <c:forEach var="genre" items="${requestScope.genres}">
                <option value="${genre.id}" >${genre.genreName}</option>
            </c:forEach>
        </select>



        <button type="submit">Add film</button>
    </form>

</div>

</body>
</html>