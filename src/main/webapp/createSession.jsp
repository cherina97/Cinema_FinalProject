<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
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
            <input name="filmTitle" type="text" placeholder="Film title"/>
        </label>

        <label>
            <input name="description" type="text" placeholder="Description"/>
        </label>

        <label>
            <input name="startAt" type="time" placeholder="Start at"/>
        </label>

        <label>
            <input name="duration" type="time" placeholder="Duration"/>
        </label>

<%--        <label>--%>
<%--            <input name="tickets" type="text" placeholder="Count of tickets"/>--%>
<%--        </label>--%>

        <button>Create session</button>
    </form>

</div>

</body>
</html>