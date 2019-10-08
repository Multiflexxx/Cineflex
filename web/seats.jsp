<%--
  Created by IntelliJ IDEA.
  User: D073701
  Date: 01.10.2019
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="factory.VorstellungsFactory"%>
<%@ page import="oo.Vorstellung" %>
<%@ page import="helper.DateFormatter" %>
<%@ page import="java.util.Date" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>
<html>
<head>
    <title></title>
</head>
<body>
    <%
        String id = request.getParameter("id");
        Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(Integer.parseInt(id));
        String formatDatum = DateFormatter.getFrontendDate(vorstellung.getDatum());
        String formatUhrzeit = DateFormatter.getFrontendTime(vorstellung.getUhrzeit());
        if(vorstellung ==null) {
            out.write("<div class=\"jumbotron jumbotron-fluid footer\">\n" +
                    "    <div class=\"container\">\n" +
                    "        <h1 class=\"display-4\">Hier ist etwas schiefgelaufen</h1>\n" +
                    "        <p class=\"lead\">Das tut uns leid</p>\n" +
                    "        <a class=\"btn btn-primary btn-lg\" href=\"index.jsp\" role=\"button\">Zurück zur Startseite</a>\n" +
                    "    </div>\n" +
                    "</div>");
        }
        ;%>
    <div class="container-fluid">
        <div class="media">
            <img src="<%=vorstellung.getFilm().getBildLink()%>" class="align-self-start mr-3 img-thumbnail" alt="Bild" width="15%">
            <div class="media-body">
                <h1><%=vorstellung.getFilm().getTitel()%></h1>
                <h2><%=formatDatum%> um <%=formatUhrzeit%></h2>
                <span class="badge badge-pill badge-info"><%=vorstellung.getSprache()%></span>
                <span class="badge badge-pill badge-secondary"><%=vorstellung.getSaal().getBezeichnung()%></span>
            </div>
        </div>
        <%
            out.write("<div class=\"container\">");
            int arrayLength = vorstellung.getSaal().getSitzplan().length;
            int counter = 0;
            out.write("<div class=\"row\">");
            while(counter < arrayLength) {
                out.write("<div class=\"col-sm align-items-center\">");
                out.write("<button type=\"button\" class=\"btn btn-primary margin: 5 2px\"\">" + vorstellung.getSaal().getSitzplan()[counter].getNummer() + vorstellung.getSaal().getSitzplan()[counter].getReihe() +"</button>");
                out.write("</div>");
                if(counter >= arrayLength-1) {
                out.write("</div>");
                }
                else if(vorstellung.getSaal().getSitzplan()[counter].getReihe() != vorstellung.getSaal().getSitzplan()[counter+1].getReihe()) {
                out.write("</div>");
                out.write("<div class=\"row\">");
                }

                counter++;
            }
            out.write("</div>");
        %>
        <div class="container">
            <div class="row text-center">
                <div class="col">
                    <div class="col" id="sitzplan" style="margin: 0 auto;"></div>
                    <div id="endgame">
                        <br>
                        <button onclick="" style="margin:0 5px">Reservieren</button>
                        <button onclick="" style="margin: 0 5px">Buchen</button>
                        <button onclick="" style="margin: 0 5px">Zurück</button>
                        <br>
                        <br>
                    </div>
                </div>

                <div class="col align-self-center">
                    <div class="cardtext-white bg-dark" style="max-width: 21rem; min-width: 15rem; margin: 0 auto;">
                        <div class="card-header">
                            <h5>Ticket Preise</h5>
                        </div>
                        <div class="card-body">
                            <table class="table table-dark text-center">
                                <tbody>
                                <tr>
                                    <td>Schüler / Student</td>
                                    <td>10 €</td>
                                </tr>

                                <tr>
                                    <td>Normal</td>
                                    <td>12 €</td>
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

    </div>
    <jsp:include page="elements/footer.jsp"/>
</body>
</html>
