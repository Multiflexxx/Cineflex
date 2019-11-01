<%@ page import="factory.StayLoggedInFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<%
    Cookie cookie = null;
    Cookie[] cookies = null;

    cookies = request.getCookies();

    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("stayLoggedIn")) {
                Cookie stay = cookies[i];
                StayLoggedInFactory.deleteStayLoggedIn(stay.getValue());
                stay.setMaxAge(0);
                response.addCookie(stay);
            }
        }
    }

    HttpSession s = request.getSession();
    s.invalidate();
%>

<jsp:include page="elements/header.jsp"/>

<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="search.jsp"/>

<div class="container">
    <div class="card mt-3 mb-3">
        <div class="card-header">
            <h1 class="card-title">Sie wurden erfolgreich ausgeloggt</h1>
        </div>
        <div class="card-body">
            <p class="card-text">Sie können nun zurück zur Startseite</p>
            <a class="btn btn-primary btn-lg" href="index.jsp" role="button">Zurück zur Startseite</a>
        </div>
    </div>
</div>

<script>
    window.setTimeout(function () {
        // Move to a new location or you can do something else
        window.location.href = "index.jsp";
    }, 3000);
</script>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>
