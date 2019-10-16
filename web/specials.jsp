<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 15.10.2019
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="oo.Film" %>
<%@ page import="factory.FilmFactory" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>


<div class="container">
    <div class="row">
        <div class="col-sm">
            <div class="card lootbox" style="width: 15rem; margin-top:2rem; margin-bottom: 1rem; align-self: center;">
                <img src="img/specials/g_g.png" class="card-img-top" alt="Gewöhnliche Lootbox">
                <div class="card-body">
                    <h5 class="card-title">Gewöhnliche Lootbox</h5>
                    <p class="card-text">5 Gewinne, mit einer geringen Chance auf seltene Gewinne.</p>
                    <a class="btn btn-primary" data-toggle="modal" data-target="#gModalCenter">1,99€</a>
                </div>
            </div>
        </div>
        <div class="col-sm">
            <div class="card lootbox" style="width: 15rem;  margin-top:2rem; margin-bottom: 1rem;">
                <img src="img/specials/s_g.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Seltene Lootbox</h5>
                    <p class="card-text">Mindestens 3 seltene Gewinne, mit geringer Chance auf epische Gewinne.*</p>
                    <a class="btn btn-primary" data-toggle="modal" data-target="#sModalCenter">4,99€</a>
                </div>
            </div>
        </div>
        <div class="col-sm">
            <div class="card lootbox" style="width: 15rem;  margin-top:2rem; margin-bottom: 1rem;">
                <img src="img/specials/e_g.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Epische Lootbox</h5>
                    <p class="card-text">Mindestens 3 epische Gewinne, mit Chance auf einen Supergewinn.*</p>
                    <a class="btn btn-primary" data-toggle="modal" data-target="#eModalCenter">49,99€</a>
                </div>
            </div>
        </div>
    </div>
    <div class="card text-center">
        <div class="card-header">
            Nur für kurze Zeit!
        </div>
        <div class="card-body">
            <h5 class="card-title">Spezialangebot</h5>
            <p class="card-text">5 x Epische Lootbox und zusätzlich 300 Diamanten + 5€ Gutschein für einen beliebigen
                Film**</p>
            <a href="#" class="btn btn-primary">229,99€</a>
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
                <img src="img/specials/g_g.png" class="card-img-top" id="gLoot" alt="Gewöhnliche Lootbox">
                <p id="gewinneg"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Wegwerfen</button>
                <button type="button" class="btn btn-primary" onclick="openBoxG()">Öffnen</button>
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
                <img src="img/specials/s_g.png" class="card-img-top" id="sLoot" alt="...">
                <p id="gewinnes"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Wegwerfen</button>
                <button type="button" class="btn btn-primary" onclick="openBoxS()">Öffnen</button>
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
                <img src="img/specials/e_g.png" class="card-img-top" id="eLoot" alt="...">
                <p id="gewinnee"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Wegwerfen</button>
                <button type="button" class="btn btn-primary" onclick="openBoxE()">Öffnen</button>
            </div>
        </div>
    </div>
</div>
<script src="javascript/lootbox.js" crossorigin="anonymous" type="text/javascript"></script>
<jsp:include page="elements/footer.jsp"/>
</body>
</html>