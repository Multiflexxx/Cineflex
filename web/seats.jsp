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
        if(vorstellung!=null){
            out.write("<p> Film " + vorstellung.getFilm().getTitel() + " am " + formatDatum + " um " + formatUhrzeit + " in der Sprache " + vorstellung.getSprache() + " im Saal " + vorstellung.getSaal().getBezeichnung()+ "</p>");
        }else{
            out.write("<p> Hier ist etwas schiefgelaufen </p>");
        };%>
    <div class="container-fluid">
        <h1><%=vorstellung.getFilm().getTitel()%>></h1>
        <h2><%=formatDatum%> , <%=formatUhrzeit%></h2>
        <span class="badge badge-pill badge-info"><%=vorstellung.getSprache()%></span>
        <span class="badge badge-pill badge-secondary"><%=vorstellung.getSaal().getBezeichnung()%>></span>
    </div>

</body>
</html>
