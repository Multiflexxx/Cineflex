<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="factory.VorstellungsFactory" %>
<%@ page import="oo.Vorstellung" %>
<%@ page import="helper.DateFormatter" %>
<%@ page import="java.util.Date" %>
<%@ page import="factory.PreisFactory" %>
<%@ page import="oo.Sitzsperre" %>
<%@ page import="factory.SitzsperreFactory" %>
<%@ page import="oo.Sitz" %>
<%@ page import="factory.SitzFactory" %>
<%@ page import="exception.RequiredFactoryFailedException" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="search.jsp"/>

<%
    //Vorstellung von request parametern holen und über factory bauen
    String id = request.getParameter("id");
    Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(Integer.parseInt(id));

    //sitzsperre für bereits ausgewählte sitze
    //Sitzsperre[] sitzsperre = SitzsperreFactory.getLockedSeats(vorstellung.getVorstellungsID());
    Sitz[] sitzsperre = null;
    try {
        sitzsperre = SitzFactory.getAllLockedSeats(vorstellung.getVorstellungsID());
    } catch (RequiredFactoryFailedException e) {
        e.printStackTrace();
        response.sendRedirect("error500.jsp");
    }

    //Uhrzeit formatieren
    String formatDatum = DateFormatter.getFrontendDate(vorstellung.getDatum());
    String formatUhrzeit = DateFormatter.getFrontendTime(vorstellung.getUhrzeit());

    //falls vorstellung nicht gebildet werden kann, eine fehlermeldung
    if (vorstellung == null) {
        out.write("<div class=\"jumbotron jumbotron-fluid footer\">\n" +
                "    <div class=\"container\">\n" +
                "        <h1 class=\"display-4\">Hier ist etwas schiefgelaufen(Vorstellung nicht gefunden)</h1>\n" +
                "        <p class=\"lead\">Das tut uns leid</p>\n" +
                "        <a class=\"btn btn-primary btn-lg\" href=\"index.jsp\" role=\"button\">Zurück zur Startseite</a>\n" +
                "    </div>\n" +
                "</div>");
    }
%>

<div class="container">
    <div class="alert alert-danger alert-dismissible fade show mt-3" id="booking_denied" role="alert" style="display: none">
        Melde dich für das Buchen oder Reservieren zuerst an!
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="card mt-3 mb-3">
        <div class="row align-items-md-center">
            <div class="col mb-3"></div>
            <div class="col-lg-2 mb-3 ">
                <img src="<%=vorstellung.getFilm().getBildLink()%>" class="card-img img-thumbnail" alt="Bild">
            </div>
            <div class="col-lg-7 mb-3">
                <h1><%=vorstellung.getFilm().getTitel()%>
                </h1>
                <h2><%=formatDatum%> um <%=formatUhrzeit%>
                </h2>
                <span class="badge badge-pill badge-info"><%=vorstellung.getSprache()%></span>
                <span class="badge badge-pill badge-secondary"><%=vorstellung.getSaal().getBezeichnung()%></span>
            </div>
            <div class="col mb-3"></div>
        </div>
        <div class="row mb-5">
            <div class="col"></div>
            <div class="col-lg">
                <%
                    //falls die vorstellung keinen sitzplan hat, fehlermeldung
                    if (vorstellung.getSaal().getSitzplan() == null) {
                        out.write("<div class=\"jumbotron jumbotron-fluid footer\">\n" +
                                "    <div class=\"container\">\n" +
                                "        <h1 class=\"display-4\">Hier ist etwas schiefgelaufen (Sitzplan nicht gefunden)</h1>\n" +
                                "        <p class=\"lead\">Das tut uns leid</p>\n" +
                                "        <a class=\"btn btn-primary btn-lg\" href=\"index.jsp\" role=\"button\">Zurück zur Startseite</a>\n" +
                                "    </div>\n" +
                                "</div>");
                    } else {
                        //überschrift und drumherum
                        out.write("<p align=\"center\">Leinwand</p>");
                        int arrayLength = vorstellung.getSaal().getSitzplan().length;
                        int counter = 0;
                        out.write("<hr width=\"75%\" height=\"3em\" color=\"grey\">");

                        //tabelle mit buttons als sitzplan
                        out.write("<div class=\"table-responsive\">");
                        out.write("<table class=\"sitzplan\">");
                        out.write("<tbody>");
                        counter = 0;
                        int ctrRowlength = 0;
                        out.write("<tr>");
                        //geht den ganzen sitzplan der als array gespeichert ist durch
                        while (counter < arrayLength) {
                            out.write("<td>");
                            //daten des sitzen zur übersichtlichkeit bereits vorher aus dem objekt gezogen
                            char row = vorstellung.getSaal().getSitzplan()[counter].getReihe();
                            int uniqueID = vorstellung.getSaal().getSitzplan()[counter].getSitzplatzID();
                            int seatNr = vorstellung.getSaal().getSitzplan()[counter].getNummer();
                            char category = vorstellung.getSaal().getSitzplan()[counter].getSitzklasse();
                            //erstellung der tabelle mit den sitzen als buttons
                            if (sitzsperre != null) {
                                boolean ssgesetzt = false;
                                for (int i = 0; i < sitzsperre.length; i++) {
                                    if (sitzsperre[i].getSitzplatzID() == uniqueID) {
                                        out.write("<button id=\"" + row + seatNr + "\" class=\"seat seat_occupied\" onclick=\"chooseSeat('" + row + seatNr + "'," + vorstellung.getSaal().getRowLength(row) + ")\" uniqueID='" + uniqueID + "' seat_cat='" + category + "' disabled>" + category + "</button>");
                                        ssgesetzt = true;
                                        break;
                                    }
                                }
                                if (!ssgesetzt) {
                                    out.write("<button id=\"" + row + seatNr + "\" class=\"seat seat_" + category + "\" onclick=\"chooseSeat('" + row + seatNr + "'," + vorstellung.getSaal().getRowLength(row) + ")\" uniqueID='" + uniqueID + "' seat_cat='" + category + "'>" + category + "</button>");
                                }
                            } else {
                                out.write("<button id=\"" + row + seatNr + "\" class=\"seat seat_" + category + "\" onclick=\"chooseSeat('" + row + seatNr + "'," + vorstellung.getSaal().getRowLength(row) + ")\" uniqueID='" + uniqueID + "' seat_cat='" + category + "'>" + category + "</button>");
                            }

                            out.write("</td>");
                            if (counter >= arrayLength - 1) {
                                out.write("<td>");
                                out.write(" &nbsp;" + row);
                                out.write("</td>");
                                out.write("</tr>");
                            } else if (row != vorstellung.getSaal().getSitzplan()[counter + 1].getReihe()) {
                                out.write("<td>");
                                out.write("&nbsp;" + row);
                                out.write("</td>");
                                out.write("</tr>");
                                out.write("<tr>");
                            }

                            counter++;
                        }
                        out.write("</tbody>");
                        out.write("</table>");
                        out.write("</div>");
                    }
                %>
            </div>
            <div class="col-lg d-flex flex-column justify-content-center text-center">
                <h6>P: Parkett</h6>
                <h6>L: Louge</h6>
                <h6>B: Behindert</h6>
            </div>
            <div class="col"></div>
        </div>

        <div class="row text-center" id="ticket_checkout">
            <div class="col"></div>
            <div class="col-lg-7">
                <div class="table-responsive" id="tickets">
                    <script>
                        window.onload = function () {
                            <%

                                // PreisFactory preisFactory = new PreisFactory();

                                String[] preistypArray = PreisFactory.getPreisJSONArray(vorstellung.getFilm().getFilmID());

                                for(int i = 0; i < preistypArray.length; i++)
                                {
                                    out.write("preistyp[" + i +"] = " + preistypArray[i] + ";\n");
                                }
                            %>

                            createTable(preistyp, <%=PreisFactory.getPreiskategorienLaenge()%>);

                            function setInputUrl() {
                                url = window.location.href;
                            }
                            setInputUrl();
                        }
                    </script>
                </div>
            </div>
            <div class="col-lg-2 pay-to-win">
                <button id="btn_res" onclick="onClickReservieren(<%=vorstellung.getVorstellungsID() + ", " + vorstellung.getFilm().getFilmID()%>)" class="btn btn-outline-secondary mb-2" disabled>
                    Reservieren
                </button>
                <button id="btn_buc" onclick="onClickBuchen(<%=vorstellung.getVorstellungsID() + ", " + vorstellung.getFilm().getFilmID()%>)" class="btn btn-outline-secondary mb-2 " disabled>
                    Buchen
                </button>
            </div>
            <div class="col"></div>
        </div>
    </div>
</div>
<jsp:include page="elements/footer.jsp"/>
</body>
</html>


<%
    if (session.getAttribute("email") == null) {
%>
<script>
    function buchenDenied() {
        document.getElementById("booking_denied").style.display = "inherit";
    }
    buchenDenied();
</script>
<%
    }
%>