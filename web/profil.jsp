<%@ page import="factory.KundenFactory" %>
<%@ page import="factory.BuchungsFactory" %>
<%@ page import="helper.DateFormatter" %>
<%@ page import="factory.TicketHistoryFactory" %>
<%@ page import="oo.*" %>
<%@ page import="exception.RequiredFactoryFailedException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="search.jsp"/>
<%
    int PID = Integer.parseInt(session.getAttribute("PID").toString());
    Kunde k = KundenFactory.getKunde(PID);
    TicketHistory [] buchungsbelege = null;
    try {
        buchungsbelege = TicketHistoryFactory.getTicketHistoryByKID(k.getKundenID());
    } catch (RequiredFactoryFailedException e) {
        e.printStackTrace();
    }
    //Buchungsbeleg buchungsbelege[] = BuchungsFactory.getBuchungsbelegeByKID(k.getKundenID());
%>

<script src="javascript/download.js"></script>

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
                            for (TicketHistory b : buchungsbelege) {
                    %>
                    <div class="card border-primary mt-3 mb-3">
                        <div class="card-header border-primary">Buchung <%=b.getBelegID()%>
                        </div>
                        <div class="card-body border-primary card-body-beleg">
                            <div class="row">
                                <div class="col-lg-3 text-center mb-2">
                                    <img src="<%=b.getBelegVorstellung().getFilm().getBildLink()%>"
                                         class="card-img beleg_img" alt="<%= b.getBelegVorstellung().getFilm().getTitel()%>">
                                </div>

                                <div class="col-lg-9 d-flex flex-column justify-content-center">
                                    <h3 class="card-title"><%=b.getBelegVorstellung().getFilm().getTitel()%>
                                        am <%=DateFormatter.getFrontendDate(b.getBelegVorstellung().getDatum())%>
                                        um <%=DateFormatter.getFrontendTime(b.getBelegVorstellung().getUhrzeit())%>
                                        Uhr</h3>
                                    <%
                                        Gebaeude g = b.getBelegVorstellung().getSaal().getGebaeude();
                                    %>
                                    <p class="card-text"><%=b.getBelegVorstellung().getSaal().getBezeichnung()%>
                                    </p>
                                    <p class="card-text">Adresse: <%=g.getStrasse()%> <%=g.getHausnummer()%>
                                        , <%=g.getPlz()%> <%=g.getOrtsname()%>
                                    </p>
                                    <p class="card-text">Tickets: ...</p>
                                    <p class="card-text">Preis: <%=b.getBelegPreis()%> €</p>
                                    <button class="btn btn-outline-primary" onclick="download_buchung(<%=b.getBelegID()%>)">Download</button>
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
                                <input type="text" class="form-control" id="formVorname" name="formVorname"
                                       value="<%=k.getVorname()%>"
                                       readonly>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="formNachname">Name</label>
                                <input type="text" class="form-control" id="formNachname" name="formNachname"
                                       value="<%=k.getNachname()%>"
                                       readonly>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputEmail">E-Mail</label>
                                <input type="text" class="form-control" id="inputEmail" name="inputEmail"
                                       value="<%=k.getEmail()%>"
                                       readonly>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputPasswort">Passwort</label>
                                <input type="text" class="form-control" id="inputPasswort" name="inputPasswort"
                                       value="********"
                                       readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputStrasse">Straße</label>
                            <input type="text" class="form-control" id="inputStrasse" name="inputStrasse"
                                   value="<%=k.getStrasse()%>" readonly>
                            <input type="text" class="form-control" id="inputHausnummer" name="inputHausnummer" value="<%=k.getHausnummer()%>" readonly>
                            <input id="PID" style="display: none" name="PID" value="<%=k.getPersonenID()%>">
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