<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit film</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createFilm.css"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div class="cont">

    <div class="header">
        <jsp:include page="navbar.jsp"></jsp:include>
    </div>

    <div class="mainBlock">

        <main role="main" class="container">

            <form class="register-form" action="${pageContext.request.contextPath}/allFilms/admin/updatePoster" method="post"
                  enctype="multipart/form-data">

                <input name="id" type="hidden" value="${requestScope.filmById.id}"/>

                <img src="${pageContext.servletContext.contextPath}/posterServlet?id=${requestScope.filmById.id}"
                style="height: 250px; width: 200px">
                <div class="form-group">
                    <div class="custom-file">
                        <input name="poster" type="file" class="custom-file-input" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose a poster for film</label>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Save changes</button>
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