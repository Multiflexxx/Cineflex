<%@ page import="oo.Kunde" %>
<%@ page import="factory.KundenFactory" %><%--
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
<jsp:include page="filter.jsp"/>
<%
    int PID = Integer.parseInt(session.getAttribute("PID").toString());
    Kunde k = KundenFactory.getKunde(PID);
%>
<div class="container">
    <h1>Willkommen, <%=k.getVorname()%>
    </h1>
    <h2>Deine Treuepunkte: <%=k.getTreuepunkte()%>
    </h2>
    <br>
    <h3>Accountinformationen</h3>
    <form name="ProfilForm" action="profilHandler.jsp" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="formVorname">Vorname</label>
                <input type="text" class="form-control" id="formVorname" placeholder="" value="<%=k.getVorname()%>"
                       readonly>
            </div>
            <div class="form-group col-md-6">
                <label for="formNachname">Name</label>
                <input type="text" class="form-control" id="formNachname" placeholder="" value="<%=k.getNachname()%>"
                       readonly>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail">E-Mail</label>
                <input type="text" class="form-control" id="inputEmail" placeholder="" value="<%=k.getEmail()%>"
                       readonly>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPasswort">Passwort</label>
                <input type="text" class="form-control" id="inputPasswort" placeholder="" value="<%=k.getPasswort()%>"
                       readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="inputStrasse">Straße</label>
            <input type="text" class="form-control" id="inputStrasse" placeholder=""
                   value="<%=k.getStrasse()%> <%=k.getHausnummer()%>" readonly>
            <input id="PID" style="display: none" value="<%=k.getPersonenID()%>">
        </div>
        <button type="button" class="btn btn-primary" id="bearbeitenButton" onclick="onClickProfilBearbeiten()">
            Bearbeiten
        </button>
        <button type="submit" class="btn btn-primary" id="submitButton" onclick="onSubmit()" style="display: none">
            Speichern
        </button>
        <button type="button" class="btn btn-primary" id="abbrechenButton" onclick="onClickProfilAbbrechen()"
                style="display: none">Abbrechen
        </button>

    </form>
</div>
<jsp:include page="elements/footer.jsp"/>
<script src="javascript/profil.js" crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>
