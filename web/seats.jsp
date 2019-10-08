<%--
  Created by IntelliJ IDEA.
  User: D073701
  Date: 01.10.2019
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="factory.VorstellungsFactory"%>
<%@ page import="oo.Vorstellung" %>
<%@ page import="helper.DateFormatter" %>
<%@ page import="java.util.Date" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>
<html>
<head>
    <title>TEST</title>
</head>
<body>
    <%
        String id = request.getParameter("id");
        Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(Integer.parseInt(id));
        String formatDatum = DateFormatter.getFrontendDate(vorstellung.getDatum());
        String formatUhrzeit = DateFormatter.getFrontendTime(vorstellung.getUhrzeit());
        if(vorstellung ==null) {
            out.write("<div class=\"jumbotron jumbotron-fluid footer\">\n" +
                    "    <div class=\"container\">\n" +
                    "        <h1 class=\"display-4\">Hier ist etwas schiefgelaufen</h1>\n" +
                    "        <p class=\"lead\">Das tut uns leid</p>\n" +
                    "        <a class=\"btn btn-primary btn-lg\" href=\"index.jsp\" role=\"button\">Zurück zur Startseite</a>\n" +
                    "    </div>\n" +
                    "</div>");
        }
        ;%>
    <div class="container-fluid">
        <div class="media">
            <img src="<%=vorstellung.getFilm().getBildLink()%>" class="align-self-start mr-3 img-thumbnail" alt="Bild" width="15%">
            <div class="media-body">
                <h1><%=vorstellung.getFilm().getTitel()%></h1>
                <h2><%=formatDatum%> um <%=formatUhrzeit%></h2>
                <span class="badge badge-pill badge-info"><%=vorstellung.getSprache()%></span>
                <span class="badge badge-pill badge-secondary"><%=vorstellung.getSaal().getBezeichnung()%></span>
            </div>
        </div>

    </div>

</body>
</html>
