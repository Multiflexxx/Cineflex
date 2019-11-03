<%@ page import="factory.BuchungsStornierungFactory" %>
<%@ page import="exception.RequiredFactoryFailedException" %>
<%@ page import="exception.FailedDataInsertionException" %>
<%@ page import="oo.ReservierungsStornierung" %>
<%@ page import="factory.ReservierungsStornierungFactory" %>
<%@ page import="send_mail.Email_Sender" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="search.jsp"/>

<%
    int RID = Integer.parseInt(request.getParameter("RID"));
    try {
        ReservierungsStornierungFactory.createStornierung(RID);
        Email_Sender.sendMail(session.getAttribute("email").toString(), "Stornierung", "Hallo " + session.getAttribute("vorname").toString()+" ,\ndeine Reservierung wurde storniert.");

    } catch (RequiredFactoryFailedException | FailedDataInsertionException e) {
        e.printStackTrace();
    }
%>

<div class="container">
    <div class="card mt-3 mb-3">
        <div class="card-header">
            <h1 class="card-title">Stornierung: Schade ... Edelmann-Move! 31er</h1>
        </div>
        <div class="card-body">
            <p class="card-text">Du erhältst per E-Mail eine Bestätigung zu deiner stornierten Reservierung.</p>
            <br>
            <p class="card-text">Du kannst nun zurück zur Startseite</p>
            <a class="btn btn-primary btn-lg" href="index.jsp" role="button">Zurück zur Startseite</a>
        </div>
    </div>
</div>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>