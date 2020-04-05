<%@ page import="factory.KundenFactory" %>
<%@ page import="oo.Kunde" %>
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
    int KID = Integer.parseInt(session.getAttribute("KID").toString());
    Kunde k = KundenFactory.getKundeByKID(KID);
%>

<div class="container">
    <div class="card mt-3">
        <div class="card-header">
            <h1>Willkommen, <%=k.getVorname()%>
            </h1>
            <h2>Deine Treuepunkte: <%=k.getTreuepunkte()%>

            </h2>
        </div>
    </div>

    <div class="alert alert-danger alert-dismissible fade show mt-3" id="chest_denied" role="alert"
         style="display: none">
        Du hast nicht genügend Treuepunkte
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>

    <div class="card-deck" style="max-width: 1400px;">
        <div class="card lootbox" style="width: 15rem;  margin-top:2rem; margin-bottom: 1rem;">
            <img src="img/specials/g_g.svg" class="card-img-top" alt="...">
            <div class="card-body text-center">
                <h5 class="card-title">Gewöhnliche Lootbox</h5>
                <p class="card-text">5 Gewinne, mit einer geringen Chance auf seltene Gewinne.</p>
                <a class="btn btn-primary" onclick="enoughTreueG(<%=k.getTreuepunkte()%>)">100
                    Treuepunkte</a>
            </div>
        </div>

        <div class="card lootbox" style="width: 15rem;  margin-top:2rem; margin-bottom: 1rem;">
            <img src="img/specials/s_g.svg" class="card-img-top" alt="...">
            <div class="card-body text-center">
                <h5 class="card-title">Seltene Lootbox</h5>
                <p class="card-text">Mindestens 3 seltene Gewinne, mit geringer Chance auf epische Gewinne.*</p>
                <a class="btn btn-primary" onclick="enoughTreueS(<%=k.getTreuepunkte()%>)">400
                    Treuepunkte</a>
            </div>
        </div>
        <div class="card lootbox" style="width: 15rem;  margin-top:2rem; margin-bottom: 1rem;">
            <img src="img/specials/e_g.svg" class="card-img-top" alt="...">
            <div class="card-body text-center">
                <h5 class="card-title">Epische Lootbox</h5>
                <p class="card-text">Mindestens 3 epische Gewinne, mit Chance auf einen Supergewinn.*</p>
                <a class="btn btn-primary" onclick="enoughTreueE(<%=k.getTreuepunkte()%>)">5000
                    Treuepunkte</a>
            </div>
        </div>
    </div>
    <div class="card text-center mb-3">
        <div class="card-header">
            Nur für kurze Zeit!
        </div>
        <div class="card-body">
            <h5 class="card-title">Spezialangebot</h5>
            <p class="card-text">5 x Epische Lootbox und zusätzlich 300 Diamanten + 5€ Gutschein für einen beliebigen
                Film**</p>
            <a href="#" class="btn btn-primary">229,99€ oder<br>20000 Treuepunkte</a>
        </div>
        <div class="card-footer text-muted">
            *Keine Garantie auf Gewinne. Glückspiel kann süchtig machen. <br>
            **Ausgeschlossen 3D oder Überlänge. Nicht kombinierbar mit anderen Rabatten. Rechtsweg ausgeschlossen.
        </div>
    </div>
</div>

<!-- Modal gewöhnlich-->
<div class="modal fade" id="gModalCenter" tabindex="-1" role="dialog" aria-labelledby="gModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="gModalCenterTitle">Gewöhnliche Lootbox</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <img src="img/specials/g_g.svg" class="card-img-top" id="gLoot" alt="Gewöhnliche Lootbox">
                <p id="gewinneg"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Wegwerfen</button>
                <button type="button" id="btnBoxG" class="btn btn-primary" onclick="openBoxG()">Öffnen</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal selten -->
<div class="modal fade" id="sModalCenter" tabindex="-1" role="dialog" aria-labelledby="sModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="sModalCenterTitle">Seltene Lootbox</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <img src="img/specials/s_g.svg" class="card-img-top" id="sLoot" alt="...">
                <p id="gewinnes"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Wegwerfen</button>
                <button type="button" id="btnBoxS" class="btn btn-primary" onclick="openBoxS()">Öffnen</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal episch-->
<div class="modal fade" id="eModalCenter" tabindex="-1" role="dialog" aria-labelledby="eModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="eModalCenterTitle">Epische Lootbox</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <img src="img/specials/e_g.svg" class="card-img-top" id="eLoot" alt="...">
                <p id="gewinnee"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Wegwerfen</button>
                <button type="button" id="btnBoxE" class="btn btn-primary" onclick="openBoxE()">Öffnen</button>
            </div>
        </div>
    </div>
</div>
<script src="javascript/lootbox.js" crossorigin="anonymous" type="text/javascript"></script>
<jsp:include page="elements/footer.jsp"/>

</body>
</html>