<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit film</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div id="viewport">

    <form class="register-form" action="${pageContext.request.contextPath}/allFilms/update" method="post">

        <input name="id" type="hidden" value="${requestScope.filmById.id}"/>


        <label>
            <input name="filmTitle" type="text" placeholder="Film title" value="${requestScope.filmById.filmTitle}"/>
        </label>

        <label>
            <input name="description" type="text" placeholder="Description" value="${requestScope.filmById.description}"/>
        </label>

        <label>
            <input name="duration" type="time" placeholder="Duration" value="${requestScope.filmById.duration}"/>
        </label>

        <button type="submit">Save changes</button>
    </form>

</div>

</body>
</html>