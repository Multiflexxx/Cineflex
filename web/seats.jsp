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
        if(vorstellung!=null){
            out.write("<p> Film" + vorstellung.getFilm().getTitel() + "am" + vorstellung.getDatum() + "um" + vorstellung.getUhrzeit() + "in der Sprache" + vorstellung.getSprache() + "im Saal" + vorstellung.getSaal().getBezeichnung()+ "</p>");
        }else{
            out.write("<p> Hier ist etwas schiefgelaufen </p>");
        };%>

</body>
</html>
