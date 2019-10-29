<%@ page import="oo.Kunde" %>
<%@ page import="factory.KundenFactory" %>
<%@ page import="oo.Buchungsbeleg" %>
<%@ page import="factory.BuchungsFactory" %>
<%@ page import="helper.DateFormatter" %><%--
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

%>
<div class="container">
    <div class="card mt-3 mb-3">
        <div class="card-header">
            <h1>Administration</h1>
        </div>
        <div class="card-body">
            <nav>
                <div class="nav nav-pills nav-justified mb-2" id="nav-pills" role="tablist">
                    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-filme" role="tab"
                       aria-controls="nav-filme" aria-selected="true">Filme hinzufügen</a>
                    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-vorstellungen" role="tab"
                       aria-controls="nav-vorstellungen" aria-selected="false">Vorstellungen hinzufügen</a>
                </div>
            </nav>
            <div class="tab-content" id="nav-tabContent">

                <div class="tab-pane fade show active" id="nav-filme" role="tabpanel" aria-labelledby="nav-home-tab">
                    <h3>Filme hinzufügen</h3>
                    <form name="filmeForm" action="adminHandler.jsp" method="post">
                        <label for="filmeTitel">Titel</label>
                        <input type="text" class="form-control" id="filmeTitel" placeholder="Titel des Films" name="filmTitel">
                        <label for="filmeBeschreibung">Beschreibung</label>
                        <textarea class="form-control" id="filmeBeschreibung" placeholder="Beschreibung des Films" name="filmBeschreibung"></textarea>
                        <label for="filmeDauer">Dauer</label>
                        <input type="number" class="form-control" id="filmeDauer" placeholder="Dauer in Minuten" name="filmDauer">
                        <label for="FSKnull">FSK</label><br>
                        <label class="radio-inline"><input type="radio" id="FSKnull" name="FSK" value="0">0</label>
                        <label class="radio-inline"><input type="radio" id="FSKsechs" name="FSK" value="6">6</label>
                        <label class="radio-inline"><input type="radio" id="FSKzwoelf" name="FSK" value="12">12</label>
                        <label class="radio-inline"><input type="radio" id="FSKsechszehn" name="FSK" value="16">16</label>
                        <label class="radio-inline"><input type="radio" id="FSKachtzehn" name="FSK" value="18">18</label>
                        <br>
                        <label for="DDD">3D</label><br>
                        <label><input type="checkbox" id="DDD" name="DDD" value="1">3D</label><br>
                        <label>Bild Link</label>
                        <input type="text" class="form-control" id="bildLink" placeholder="Link zum Bild auf dem Server" name="bildLink">
                        <label>Trailer Link</label>
                        <input type="text" class="form-control" id="trailerLink" placeholder="YouTube Embedded Link" name="trailerLink">
                        <label>Grundpreis</label>
                        <input type="number" class="form-control" id="grundpreis" placeholder="Grundpreis" name="filmGrundpreis">
                        <button type="submit" class="btn btn-primary" id="filmHinzufuegen" onclick="filmHinzufuegen()">
                            Film hinzufügen
                        </button>
                    </form>

                </div>

                <div class="tab-pane fade" id="nav-vorstellungen" role="tabpanel" aria-labelledby="nav-profile-tab">
                    <h3>Accountinformationen</h3>
                    <form name="ProfilForm" action="profilHandler.jsp" method="post">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="formVorname">Vorname</label>
                                <input type="text" class="form-control" id="formVorname" placeholder=""
                                       value=""
                                       readonly>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="formNachname">Name</label>
                                <input type="text" class="form-control" id="formNachname" placeholder=""
                                       value=""
                                       readonly>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputEmail">E-Mail</label>
                                <input type="text" class="form-control" id="inputEmail" placeholder=""
                                       value=""
                                       readonly>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputPasswort">Passwort</label>
                                <input type="text" class="form-control" id="inputPasswort" placeholder=""
                                       value=""
                                       readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputStrasse">Straße</label>
                            <input type="text" class="form-control" id="inputStrasse" placeholder=""
                                   value="" readonly>
                            <input id="PID" style="display: none" value="">
                        </div>
                        <button type="button" class="btn btn-primary" id="bearbeitenButton"
                                onclick="">
                            Bearbeiten
                        </button>
                        <nav>
                            <button type="submit" class="btn btn-primary" id="submitButton" onclick=""
                                    style="display: none">
                                Speichern
                            </button>
                            <button type="button" class="btn btn-primary" id="abbrechenButton"
                                    onclick=""
                                    style="display: none">Abbrechen
                            </button>
                        </nav>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="elements/footer.jsp"/>
<script src="javascript/admin.js" crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>