<%@ page import="Password.PassMD5" %>
<%@ page import="factory.LoginFactory" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="oo.UserLogin" %>
<%@ page import="factory.ProfilFactory" %>

<%
    String vorname = request.getParameter("formVorname");
    String nachname = request.getParameter("formNachname");
    String email = request.getParameter("inputEmail");
    String passwort = request.getParameter("inputPasswort");
    String strasse = request.getParameter("inputStrasse");
    int hausnummer = Integer.parseInt(request.getParameter("inputHausnummer"));
    int PID = Integer.parseInt(request.getParameter("PID"));

    passwort = PassMD5.hash(passwort);
    ProfilFactory.aendereProfil(vorname, nachname, email, passwort, strasse, hausnummer, PID);
    response.sendRedirect("profil.jsp");

%>