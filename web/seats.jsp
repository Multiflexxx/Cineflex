<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="factory.VorstellungsFactory" %>
<%@ page import="oo.Vorstellung" %>
<%@ page import="helper.DateFormatter" %>
<%@ page import="java.util.Date" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>
<html>
<body>
<%
    String id = request.getParameter("id");
    Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(Integer.parseInt(id));
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
//                        char c = vorstellung.getSaal().getSitzplan()[0].getReihe();
//                        out.write("<p align=\"center\">Leinwand</p>");
//                        for (int i=0; i<arrayLength; i++){
//                            if(c == vorstellung.getSaal().getSitzplan()[i].getReihe()) {
//                                counter++;
//                            }
//                        }
                        out.write("<hr width=\"75%\" height=\"3em\" color=\"grey\">");


                        out.write("<div class=\"table-responsive\">");
                        out.write("<table class=\"sitzplan\">");
                        out.write("<tbody>");
                        counter = 0;
                        out.write("<tr>");
                        while (counter < arrayLength) {
                            out.write("<td>");
                            out.write("<button id=\"" + vorstellung.getSaal().getSitzplan()[counter].getSitzID() + "\" class=\"seat\" onclick=\"chooseSeat(" + vorstellung.getSaal().getSitzplan()[counter].getSitzID() + ")\"></button>");
                            out.write("</td>");
                            if (counter >= arrayLength - 1) {
                                out.write("<td>");
                                out.write("<p> &nbsp;" + vorstellung.getSaal().getSitzplan()[counter].getReihe() + "</p>");
                                out.write("</td>");
                                out.write("</tr>");
                            } else if (vorstellung.getSaal().getSitzplan()[counter].getReihe() != vorstellung.getSaal().getSitzplan()[counter + 1].getReihe()) {
                                out.write("<td>");
                                out.write("<p> &nbsp;" + vorstellung.getSaal().getSitzplan()[counter].getReihe() + "</p>");
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
        <div class="row text-center">
            <div class="col">
                    <button onclick="" class="btn btn-secondary">Reservieren</button>
                    <button onclick="" class="btn btn-secondary">Buchen</button>
                    <button onclick="" class="btn btn-secondary">Zurück</button>
                </div>
        </div>
    </div>
</div>

<%--<div class="container-fluid">--%>
<%--    <div class="media">--%>
<%--        <img src="<%=vorstellung.getFilm().getBildLink()%>" class="align-self-start mr-3 img-thumbnail" alt="Bild"--%>
<%--             width="170em">--%>
<%--        <div class="media-body">--%>
<%--            <h1><%=vorstellung.getFilm().getTitel()%>--%>
<%--            </h1>--%>
<%--            <h2><%=formatDatum%> um <%=formatUhrzeit%>--%>
<%--            </h2>--%>
<%--            <span class="badge badge-pill badge-info"><%=vorstellung.getSprache()%></span>--%>
<%--            <span class="badge badge-pill badge-secondary"><%=vorstellung.getSaal().getBezeichnung()%></span>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <%--%>
<%--        if (vorstellung.getSaal().getSitzplan() == null) {--%>
<%--            out.write("<div class=\"jumbotron jumbotron-fluid footer\">\n" +--%>
<%--                    "    <div class=\"container\">\n" +--%>
<%--                    "        <h1 class=\"display-4\">Hier ist etwas schiefgelaufen (Sitzplan nicht gefunden)</h1>\n" +--%>
<%--                    "        <p class=\"lead\">Das tut uns leid</p>\n" +--%>
<%--                    "        <a class=\"btn btn-primary btn-lg\" href=\"index.jsp\" role=\"button\">Zurück zur Startseite</a>\n" +--%>
<%--                    "    </div>\n" +--%>
<%--                    "</div>");--%>
<%--        } else {--%>
<%--            int arrayLength = vorstellung.getSaal().getSitzplan().length;--%>
<%--            int counter = 0;--%>
<%--            char c = vorstellung.getSaal().getSitzplan()[0].getReihe();--%>
<%--            out.write("<p align=\"center\">Leinwand</p>");--%>
<%--            for (int i=0; i<arrayLength; i++){--%>
<%--                if(c == vorstellung.getSaal().getSitzplan()[i].getReihe()) {--%>
<%--                    counter++;--%>
<%--                }--%>
<%--            }--%>
<%--            out.write("<hr width=\"10%\" height=\"3em\" color=\"grey\">");--%>
<%--            out.write("<div class=\"d-flex p-2 justify-content-center\">");--%>
<%--            out.write("<table class=\"align-self-center\">");--%>
<%--            counter = 0;--%>
<%--            out.write("<tr>");--%>
<%--            while (counter < arrayLength) {--%>
<%--                out.write("<td>");--%>
<%--                out.write("<button id=\"" + vorstellung.getSaal().getSitzplan()[counter].getSitzID() + "\" class=\"seat\" onclick=\"chooseSeat(" + vorstellung.getSaal().getSitzplan()[counter].getSitzID() + ")\">" + vorstellung.getSaal().getSitzplan()[counter].getNummer() + vorstellung.getSaal().getSitzplan()[counter].getReihe() + "</button>");--%>
<%--                out.write("</td>");--%>
<%--                if (counter >= arrayLength - 1) {--%>
<%--                    out.write("<td>");--%>
<%--                    out.write("<p> &nbsp;" + vorstellung.getSaal().getSitzplan()[counter].getReihe() + "</p>");--%>
<%--                    out.write("</td>");--%>
<%--                    out.write("</tr>");--%>
<%--                } else if (vorstellung.getSaal().getSitzplan()[counter].getReihe() != vorstellung.getSaal().getSitzplan()[counter + 1].getReihe()) {--%>
<%--                    out.write("<td>");--%>
<%--                    out.write("<p> &nbsp;" + vorstellung.getSaal().getSitzplan()[counter].getReihe() + "</p>");--%>
<%--                    out.write("</td>");--%>
<%--                    out.write("</tr>");--%>
<%--                    out.write("<tr>");--%>
<%--                }--%>
<%--                counter++;--%>
<%--            }--%>
<%--            out.write("</table>");--%>
<%--            out.write("</div>");--%>
<%--        }--%>
<%--    %>--%>
<%--    <div class="container">--%>
<%--        <div class="row text-center">--%>
<%--            <div class="col">--%>
<%--                <div id="endgame">--%>
<%--                    <br>--%>
<%--                    <button onclick="" class="btn btn-secondary">Reservieren</button>--%>
<%--                    <button onclick="" class="btn btn-secondary">Buchen</button>--%>
<%--                    <button onclick="" class="btn btn-secondary">Zurück</button>--%>
<%--                    <br>--%>
<%--                    <br>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

        <div class="col align-self-center">
            <div class="cardtext-white bg-dark" style="max-width: 21rem; min-width: 15rem; margin: 0 auto;">
                <div class="card-header">
                    <h5>Ticket Preise</h5>
                </div>
                <div class="card-body">
                    <table class="table table-dark text-center">
                        <tbody>
                        <tr>
                            <td>Normal</td>
                            <td>12 €</td>
                        </tr>

                        <tr>
                            <td>Schüler / Student</td>
                            <td>10 €</td>
                        </tr>

                        <tr>
                            <td>Senior</td>
                            <td>10 €</td>
                        </tr>
                        </tbody>
                    </table>
                    <p style="text-align: right; font-size: 11px;">* Loge 2 € Aufpreis; Reihen G, H & I</p>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="elements/footer.jsp"/>
<script src="javascript/seats.js" crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>
