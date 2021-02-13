<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Create genre</title>
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div id="viewport">

    <form class="register-form" action="addGenre" method="post">

        <label>
            <input name="genreName" type="text" placeholder="Genre name"/>
        </label>

        <label>
            <input name="genreNameUK" type="text" placeholder="Genre name uk"/>
        </label>
        <button>Add genre</button>
    </form>

</div>

</body>
</html>