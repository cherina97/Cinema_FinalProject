<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Create genre</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createFilm.css"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div class="cont">

    <div class="header">
        <jsp:include page="navbar.jsp"></jsp:include>
    </div>

    <div class="mainBlock">

        <main role="main" class="container">
            <form class="register-form" action="addGenre" method="post">

                <div class="form-group">
                    <label>Enter a genre name: </label>
                    <input name="genreName" type="text" class="form-control" placeholder="Genre name"
                           required>
                </div>

                <div class="form-group">
                    <label>Enter a genre name in Ukrainian: </label>
                    <input name="genreNameUK" type="text" class="form-control" placeholder="Genre name uk"
                           required>
                </div>

                <button type="submit" class="btn btn-primary">Add genre</button>
            </form>
        </main>
    </div>

</div>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

</body>
</html>