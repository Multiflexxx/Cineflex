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
    TicketHistory[] beleg = null;
    try {
        beleg = TicketHistoryFactory.getTicketHistoryByKID(k.getKundenID());
    } catch (RequiredFactoryFailedException e) {
        e.printStackTrace();
    }
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
                       aria-controls="nav-home" aria-selected="true">Bestellungen</a>
                    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab"
                       aria-controls="nav-profile" aria-selected="false">Profil</a>
                </div>
            </nav>
            <div class="tab-content" id="nav-tabContent">

                <%--                <script>--%>
                <%--                    function showQR(titelImg, qrImg) {--%>
                <%--                        var img1 = titelImg,--%>
                <%--                            img2 = qrImg;--%>

                <%--                        var cardImg = document.getElementById('cardImg');--%>

                <%--                        console.log(img1, img2);--%>
                <%--                        console.log("Click");--%>

                <%--                        cardImg.src = img2;--%>
                <%--                        console.log(cardImg.src);--%>
                <%--                    }--%>
                <%--                </script>--%>

                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                    <%
                        if (beleg != null) {
                            for (TicketHistory b : beleg) {
                                String storno = "";
                                String card_color = "border-primary";
                                if (b.isStorniert()) {
                                    storno = "- Storniert";
                                    card_color = "border-danger";
                                }
                    %>
                    <div class="card <%=card_color%> mt-3 mb-3">
                        <div class="card-header <%=card_color%>"><h3 class="card-title"><%=b.getBelegBezeichnung()%>
                            vom <%=DateFormatter.getFrontendDate(b.getBelegZeitstempel())%> <%=storno%>
                        </h3></div>
                        <div class="card-body <%=card_color%> card-body-beleg">
                            <div class="row mb-2">
                                <div class="col-lg-3 text-center mb-2">
                                    <img src="<%=b.getBelegVorstellung().getFilm().getBildLink()%>"
                                         class="card-img beleg_img" id="cardImg"
                                         alt="<%= b.getBelegVorstellung().getFilm().getTitel()%>">
                                </div>
                                <%--                                onclick="showQR('<%=b.getBelegVorstellung().getFilm().getBildLink()%>', 'img/specials/g_o.png')--%>

                                <div class="col-lg-9 d-flex flex-column justify-content-center">
                                    <h4 class="card-title"><%=b.getBelegVorstellung().getFilm().getTitel()%>
                                        am <%=DateFormatter.getFrontendDate(b.getBelegVorstellung().getDatum())%>
                                        um <%=DateFormatter.getFrontendTime(b.getBelegVorstellung().getUhrzeit())%>
                                        Uhr</h4>
                                    <%
                                        Gebaeude g = b.getBelegVorstellung().getSaal().getGebaeude();
                                    %>
                                    <p class="card-text"><small
                                            class="text-muted">Adresse: <%=g.getStrasse()%> <%=g.getHausnummer()%>
                                        , <%=g.getPlz()%> <%=g.getOrtsname()%>
                                    </small></p>
                                    <h5 class="card-text">Preis: <%=b.getBelegPreis()%> €</h5>
                                    <ul class="list-group list-group-flush list-ticket">
                                        <%
                                            Sitz[] sitz = b.getSitze();
                                            for (int i = 0; i < sitz.length; i++) {
                                                out.write("<li class=\"list-group-item list-group-item-light rounded text-center mb-2\">" + b.getBelegVorstellung().getSaal().getBezeichnung() + " Reihe " + sitz[i].getReihe() + " Platz " + sitz[i].getNummer() + "</li>");
                                            }
                                        %>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <%
                            if (!b.isStorniert()) {
                        %>

                        <div class="card-footer <%=card_color%>">
                            <div class="row">
                                <div class="col-lg-6 mb-2">
                                    <button class="btn btn-primary btn-block"
                                            onclick="download_buchung(<%=b.getBelegID()%>)">Download
                                    </button>
                                </div>
                                <div class="col-lg-6 mb-2">
                                    <%
                                        String js_call = "";
                                        if (b.getBelegBezeichnung().equals("Buchung")) {
                                            js_call = "cancellationBuc(" + b.getBelegID() + ")";
                                        } else if (b.getBelegBezeichnung().equals("Reservierung")) {
                                            js_call = "cancellationRes(" + b.getBelegID() + ")";
                                        }
                                    %>
                                    <button class="btn btn-danger btn-block" onclick="<%=js_call%>">Stornieren</button>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
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
                            <input type="text" class="form-control" id="inputHausnummer" name="inputHausnummer"
                                   value="<%=k.getHausnummer()%>" readonly>
                            <input id="PID" style="display: none" name="PID" value="<%=k.getPersonenID()%>">
                        </div>
                        <button type="button" class="btn btn-primary" id="bearbeitenButton"
                                onclick="onClickProfilBearbeiten()">
                            Bearbeiten
                        </button>
                        <nav>
                            <div class="form-row">
                                <div class="col-md-6">
                                    <button type="submit" class="btn btn-primary" id="submitButton" onclick="onSubmit()"
                                            style="display: none">
                                        Speichern
                                    </button>
                                </div>
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-primary" id="abbrechenButton"
                                            onclick="onClickProfilAbbrechen()"
                                            style="display: none">Abbrechen
                                    </button>
                                </div>
                            </div>
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