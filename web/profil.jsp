<%@ page import="oo.Kunde" %>
<%@ page import="factory.KundenFactory" %>
<%@ page import="oo.Buchungsbeleg" %>
<%@ page import="factory.BuchungsFactory" %>
<%@ page import="helper.DateFormatter" %>
<%@ page import="oo.Gebaeude" %><%--
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
    Buchungsbeleg buchungsbelege[] = BuchungsFactory.getBuchungsbelegeByKID(k.getKundenID());
%>
<div class="container">
    <div class="card mt-3 mb-3">
        <div class="card-header">
            <h1>Willkommen, <%=k.getVorname()%>
            </h1>
            <h2>Deine Treuepunkte: <%=k.getTreuepunkte()%>
            </h2>
        </div>
        <div class="card-body card-body-beleg">
            <nav>
                <div class="nav nav-pills nav-justified mb-2" id="nav-pills" role="tablist">
                    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
                       aria-controls="nav-home" aria-selected="true">Buchung</a>
                    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab"
                       aria-controls="nav-profile" aria-selected="false">Profil</a>
                </div>
            </nav>
            <div class="tab-content" id="nav-tabContent">

                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                    <%
                        if (buchungsbelege != null) {
                            for (Buchungsbeleg b : buchungsbelege) {
                    %>
                    <div class="card border-primary mt-3 mb-3">
                        <div class="card-header border-primary">Buchung <%=b.getBelegID()%>
                        </div>
                        <div class="card-body border-primary card-body-beleg">
                            <div class="row">
                                <div class="col-lg-3 text-center mb-2">
                                    <img src="<%=b.getVorstellung().getFilm().getBildLink()%>"
                                         class="card-img beleg_img" alt="<%= b.getVorstellung().getFilm().getTitel()%>">
                                </div>

                                <div class="col-lg-9 d-flex flex-column justify-content-center">
                                    <h3 class="card-title"><%=b.getVorstellung().getFilm().getTitel()%>
                                        am <%=DateFormatter.getFrontendDate(b.getVorstellung().getDatum())%>
                                        um <%=DateFormatter.getFrontendTime(b.getVorstellung().getUhrzeit())%>
                                        Uhr</h3>
                                    <%
                                        Gebaeude g = b.getVorstellung().getSaal().getGebaeude();
                                    %>
                                    <p class="card-text"><%=b.getVorstellung().getSaal().getBezeichnung()%>
                                    </p>
                                    <p class="card-text">Adresse: <%=g.getStrasse()%> <%=g.getHausnummer()%>
                                        , <%=g.getPlz()%> <%=g.getOrtsname()%>
                                    </p>
                                    <p class="card-text">Tickets: ...</p>
                                    <p class="card-text">Preis: <%=b.getPreis()%> €</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                    <h3>Accountinformationen</h3>
                    <form name="ProfilForm" action="profilHandler.jsp" method="post">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="formVorname">Vorname</label>
                                <input type="text" class="form-control" id="formVorname" placeholder=""
                                       value="<%=k.getVorname()%>"
                                       readonly>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="formNachname">Name</label>
                                <input type="text" class="form-control" id="formNachname" placeholder=""
                                       value="<%=k.getNachname()%>"
                                       readonly>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputEmail">E-Mail</label>
                                <input type="text" class="form-control" id="inputEmail" placeholder=""
                                       value="<%=k.getEmail()%>"
                                       readonly>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputPasswort">Passwort</label>
                                <input type="text" class="form-control" id="inputPasswort" placeholder=""
                                       value="<%=k.getPasswort()%>"
                                       readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputStrasse">Straße</label>
                            <input type="text" class="form-control" id="inputStrasse" placeholder=""
                                   value="<%=k.getStrasse()%> <%=k.getHausnummer()%>" readonly>
                            <input id="PID" style="display: none" value="<%=k.getPersonenID()%>">
                        </div>
                        <button type="button" class="btn btn-primary" id="bearbeitenButton"
                                onclick="onClickProfilBearbeiten()">
                            Bearbeiten
                        </button>
                        <nav>
                            <button type="submit" class="btn btn-primary" id="submitButton" onclick="onSubmit()"
                                    style="display: none">
                                Speichern
                            </button>
                            <button type="button" class="btn btn-primary" id="abbrechenButton"
                                    onclick="onClickProfilAbbrechen()"
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
<script src="javascript/profil.js" crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>
