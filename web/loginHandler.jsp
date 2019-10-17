<%@ page import="Password.PassMD5" %>
<%@ page import="factory.LoginFactory" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="oo.UserLogin" %>

<%
    String email = request.getParameter("inputEmailLog");

    String pw = null;

    try {
        pw = PassMD5.hash(request.getParameter("inputPassword"));
    } catch (Exception e) {
        e.printStackTrace();
        // Exit !
        return;
    }

    UserLogin userLogin = LoginFactory.getUserLogin(email, pw);


        if (userLogin == null) {
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body>
<jsp:include page="elements/header.jsp"/>

<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>

<div class="jumbotron jumbotron-fluid footer">
    <div class="container">
        <h1 class="display-4">E-Mail oder Passwort stimmen nicht überein</h1>
        <p class="lead">Falls sie ihr Passwort vergessen haben, schreiben sie eine E-Mail an
            support@cineflex.multiflexxx.de</p>
        <a class="btn btn-primary btn-lg" href="index.jsp" role="button">Zurück zur Startseite</a>
    </div>
</div>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>


<%
    } else {

        session.setAttribute("email", email);
        session.setAttribute("vorname", userLogin.getFirstname());
        session.setAttribute("nachname", userLogin.getLastname());
        session.setAttribute("PID", userLogin.getPID());
        session.setAttribute("KID", userLogin.getKID());

//                String lastaccessed = request.getParameter("lastaccessed");
//                String time = request.getParameter("time");
//                if (lastaccessed != null && time != null) {
//                    session.setAttribute(lastaccessed, time);
//                }
        session.setMaxInactiveInterval(600);
        response.sendRedirect("index.jsp");

    }

%>