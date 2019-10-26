<%@ page import="helper.ArrayBuilder" %>
<%@ page import="factory.ReservierungsFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="search.jsp"/>


<%
    int vorstellungsID = Integer.parseInt(request.getParameter("vorstellungs_id"));
    String seats = request.getParameter("seats_input");
    String preisVer = request.getParameter("presVerInput");
    int[] seatsInt = ArrayBuilder.stringToIntArray(seats, ",");
    int[] preisVerInt = ArrayBuilder.stringToIntArray(preisVer, ",");
    int KID = Integer.parseInt(session.getAttribute("KID").toString());
    ReservierungsFactory.createReservierungsBelege(seatsInt, preisVerInt, seats, preisVer, vorstellungsID, KID);
%>

<div class="container">
    <div class="card mt-3 mb-3">
        <div class="card-header">
            <h1 class="card-title">Vielen Dank für Ihren Reservierung und viel Spaß beim Film!</h1>
        </div>
        <div class="card-body">
            <p class="card-text">Ihr Reservierung bleibt bis 30 Minuten vor Filmbegin bestehen.</p>
            <p class="card-text">Sie können nun zurück zur Startseite</p>
            <a class="btn btn-primary btn-lg" href="index.jsp" role="button">Zurück zur Startseite</a>
        </div>
    </div>
</div>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>