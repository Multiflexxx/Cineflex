<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="factory.VorstellungsFactory" %>
<%@ page import="oo.Vorstellung" %>
<%@ page import="helper.DateFormatter" %>
<%@ page import="java.util.Date" %>
<%@ page import="factory.PreisFactory" %>
<%@ page import="oo.Sitzsperre" %>
<%@ page import="factory.SitzsperreFactory" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>

<%
    String id = request.getParameter("id");
    Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(Integer.parseInt(id));

    Sitzsperre[] sitzsperre = SitzsperreFactory.getLockedSeats(vorstellung.getVorstellungsID());

    String formatDatum = DateFormatter.getFrontendDate(vorstellung.getDatum());
    String formatUhrzeit = DateFormatter.getFrontendTime(vorstellung.getUhrzeit());
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
    <div class="card mt-3 mb-3">
        <div class="row align-items-md-center">
            <div class="col mb-3"></div>
            <div class="col-lg-2 mb-3">
                <img src="<%=vorstellung.getFilm().getBildLink()%>" class="img-thumbnail" alt="Bild">
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
            <div class="col-lg-12">
                <%
                    if (vorstellung.getSaal().getSitzplan() == null) {
                        out.write("<div class=\"jumbotron jumbotron-fluid footer\">\n" +
                                "    <div class=\"container\">\n" +
                                "        <h1 class=\"display-4\">Hier ist etwas schiefgelaufen (Sitzplan nicht gefunden)</h1>\n" +
                                "        <p class=\"lead\">Das tut uns leid</p>\n" +
                                "        <a class=\"btn btn-primary btn-lg\" href=\"index.jsp\" role=\"button\">Zurück zur Startseite</a>\n" +
                                "    </div>\n" +
                                "</div>");
                    } else {
                        out.write("<p align=\"center\">Leinwand</p>");
                        int arrayLength = vorstellung.getSaal().getSitzplan().length;
                        int counter = 0;
                        out.write("<hr width=\"75%\" height=\"3em\" color=\"grey\">");


                        out.write("<div class=\"table-responsive\">");
                        out.write("<table class=\"sitzplan\">");
                        out.write("<tbody>");
                        counter = 0;
                        int ctrRowlength = 0;
                        out.write("<tr>");
                        while (counter < arrayLength) {
                            out.write("<td>");
                            char row = vorstellung.getSaal().getSitzplan()[counter].getReihe();
                            int uniqueID = vorstellung.getSaal().getSitzplan()[counter].getSitzID();
                            int seatNr= vorstellung.getSaal().getSitzplan()[counter].getNummer();
                            char category = vorstellung.getSaal().getSitzplan()[counter].getSitzklasse();
                            if (sitzsperre[0] != null ) {
                                boolean ssgesetzt = false;
                                for (int i=0; i<sitzsperre.length; i++) {
                                    if (sitzsperre[i].getSitzplatzID() == uniqueID) {
                                        out.write("<button id=\"" + row + seatNr + "\" class=\"seat seat_occupied\" onclick=\"chooseSeat('" + row + seatNr + "'," + vorstellung.getSaal().getRowLength(row) + ")\" uniqueID='" + uniqueID + "' seat_cat='" + category + "' disabled>" + category + "</button>");
                                        ssgesetzt = true;
                                        break;
                                    }
                                }
                                if (!ssgesetzt) {
                                    out.write("<button id=\"" + row + seatNr + "\" class=\"seat\" onclick=\"chooseSeat('" + row + seatNr + "'," + vorstellung.getSaal().getRowLength(row) + ")\" uniqueID='" + uniqueID + "' seat_cat='" + category + "'>" + category + "</button>");
                                }
                            } else {
                                out.write("<button id=\"" + row + seatNr + "\" class=\"seat\" onclick=\"chooseSeat('" + row + seatNr + "'," + vorstellung.getSaal().getRowLength(row) + ")\" uniqueID='" + uniqueID + "' seat_cat='" + category + "'>" + category + "</button>");
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
                        }
                    </script>
                </div>
            </div>
            <div class="col-lg-2 pay-to-win">
                <button id="btn_res" onclick="onClickReservieren()" class="btn btn-outline-secondary mb-2" disabled>Reservieren</button>
                <button id="btn_buc" onclick="onClickBuchen(<%=vorstellung.getVorstellungsID()%>)" class="btn btn-outline-secondary mb-2 " disabled>Buchen</button>
            </div>
            <div class="col"></div>
        </div>
    </div>
</div>
</div>
</div>
<jsp:include page="elements/footer.jsp"/>
</body>
</html>
