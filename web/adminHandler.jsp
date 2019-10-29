<%@ page import="oo.Kunde" %>
<%@ page import="factory.KundenFactory" %>
<%@ page import="oo.Buchungsbeleg" %>
<%@ page import="factory.BuchungsFactory" %>
<%@ page import="helper.DateFormatter" %>
<%@ page import="factory.FilmFactory" %><%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 16.10.2019
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<%
    String titel = request.getParameter("filmTitel");
    String beschreibung = request.getParameter("filmBeschreibung");
    int dauer = Integer.parseInt(request.getParameter("filmDauer"));
    int FSK = Integer.parseInt(request.getParameter("FSK"));
    int DDD = Integer.parseInt(request.getParameter("DDD"));
    String BildLink = request.getParameter("bildLink");
    String TrailerLink = request.getParameter("trailerLink");
    float Grundpreis = Float.parseFloat(request.getParameter("filmGrundpreis"));
    FilmFactory.filmHinzufuegen(titel,beschreibung,dauer,FSK,DDD,BildLink,TrailerLink,Grundpreis);
%>
<jsp:include page="elements/footer.jsp"/>
<script src="" crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>