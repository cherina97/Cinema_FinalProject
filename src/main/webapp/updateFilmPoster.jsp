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

    <form class="register-form" action="${pageContext.request.contextPath}/allFilms/updatePoster" method="post" enctype="multipart/form-data">

        <input name="id" type="hidden" value="${requestScope.filmById.id}"/>

        <label>
            <img src="${pageContext.servletContext.contextPath}/posterServlet?id=${requestScope.filmById.id}" width="250" height="400" alt="..."/>
            <input name="poster" type="file" value="${requestScope.filmById.poster}"/>
        </label>

        <button type="submit">Save changes</button>
    </form>

</div>

</body>
</html>