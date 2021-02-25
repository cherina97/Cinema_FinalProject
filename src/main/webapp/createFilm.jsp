<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta>
    <title>Create film</title>

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

            <form action="addFilm" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label>Enter a film title: </label>
                    <input id="title" name="filmTitle" type="text" class="form-control" aria-describedby="emailHelp"
                           placeholder="Film title"
                           required
                           oninvalid="this.setCustomValidity('Cannot be empty')"
                           oninput="setCustomValidity('')">
                </div>
                <div class="error">
                    <p> ${requestScope.error} </p>
                </div>
                <div class="form-group">
                    <label>Enter a film title in Ukrainian: </label>
                    <input name="filmTitleUK" type="text" class="form-control" placeholder="Film title uk"
                           required
                           oninvalid="this.setCustomValidity('Cannot be empty')"
                           oninput="setCustomValidity('')">
                </div>
                <div class="form-group">
                    <label>Enter a description: </label>
                    <input name="description" type="text" class="form-control" placeholder="Description"
                           required
                           oninvalid="this.setCustomValidity('Cannot be empty')"
                           oninput="setCustomValidity('')">
                </div>
                <div class="form-group">
                    <label>Enter a description in Ukrainian: </label>
                    <input name="descriptionUK" type="text" class="form-control" placeholder="Description uk"
                           required
                           oninvalid="this.setCustomValidity('Cannot be empty')"
                           oninput="setCustomValidity('')">
                </div>
                <div class="form-group">
                    <label>Enter a duration of film: </label>
                    <input name="duration" type="time" class="form-control" required>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input name="poster" type="file" class="custom-file-input" id="customFile" required
                               oninvalid="this.setCustomValidity('Cannot be empty')"
                               oninput="setCustomValidity('')">
                        <label class="custom-file-label" for="customFile">Choose a poster for film</label>
                    </div>
                </div>
                <div class="form-group">
                    <select name="genres" class="custom-select" multiple required
                            oninvalid="this.setCustomValidity('Cannot be empty')"
                            oninput="setCustomValidity('')">
                        <option value="" disabled selected hidden> Select subject</option>
                        <c:forEach var="genre" items="${requestScope.genres}">
                            <option value="${genre.id}">${genre.genreName}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Add film</button>


            </form>
        </main>
    </div>


</div>

</div>

<script src="js/navbar.js"></script>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</body>
</html>