<%@ page buffer="none" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="helper.ArrayBuilder" %>
<%@ page import="factory.SitzsperreFactory" %>
<%@ page import="factory.PreisFactory" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Currency" %>
<%@ page import="java.util.Locale" %>
<%@ page import="helper.TempBuchungHandler" %>
<%@ page import="oo.Sitz" %>
<%@ page import="exception.InvalidInputValueException" %>
<%@ page import="exception.RequiredFactoryFailedException" %>
<%@ page import="oo.Vorstellung" %>
<%@ page import="factory.VorstellungsFactory" %>
<%@ page import="helper.DateFormatter" %>
<%
    if (session.getAttribute("email") != null) {
        int vorstellungsID = Integer.parseInt(request.getParameter("vorstellungs_id"));
        int filmID = Integer.parseInt(request.getParameter("film_id"));
        String seats = request.getParameter("seats_data");
        String preisVer = request.getParameter("tickets_data");
        int KID = Integer.parseInt(session.getAttribute("KID").toString());
        int[] seatsInt = ArrayBuilder.stringToIntArray(seats, ",");
        SitzsperreFactory.lockSeats(seatsInt, vorstellungsID, KID);
        Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(vorstellungsID);

        try {
            TempBuchungHandler.addTempBuchungToSession(session, seats, preisVer, vorstellungsID);
        } catch (InvalidInputValueException e) {
            e.printStackTrace();
        } catch (RequiredFactoryFailedException e) {
            e.printStackTrace();
        }
        Sitz[] sitz = (Sitz[]) session.getAttribute("temp_sitze");
        sitz[0].getReihe();

        float preis = PreisFactory.getBuchungsPreis(seats, preisVer, filmID);
        float mwst = (float) (preis * 0.07);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("de", "DE"));
        numberFormat.setCurrency(Currency.getInstance("EUR"));

        String stringPrice = numberFormat.format(preis);
        String stringMwSt = numberFormat.format(mwst);
%>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="search.jsp"/>


<div class="container">
    <div class="card mt-3 mb-3">
        <div class="row">
            <div class="col-lg-5">
                <div class="card-body">
                    <%
                        String titel =  vorstellung.getFilm().getTitel();
                        String datum = DateFormatter.getFrontendDate(vorstellung.getDatum());
                        String time = DateFormatter.getFrontendTime(vorstellung.getUhrzeit());

                        if (sitz.length > 1) {
                            out.write("<h5 class=\"card-title\">Deine ausgewählten Tickets für:</h5>");
                        } else {
                            out.write("<h5 class=\"card-title\">Dein ausgewähltes Ticket für:</h5>");
                        }
                        out.write("<h6 class=\"card-title\">" + titel + "</h6>");
                        out.write("<h6 class=\"card-title\">" + datum + " um " + time +"</h6>");
                    %>
                    <ul class="list-group list-group-flush list-ticket">
                        <%
                            for (int i = 0; i < sitz.length; i++) {
                                out.write("<li class=\"list-group-item list-group-item-light list-group-item-action rounded text-center mb-2\">" + vorstellung.getSaal().getBezeichnung()  + " Reihe " + sitz[i].getReihe() + " Platz " + sitz[i].getNummer() + "</li>");
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div class="col-lg-7">
                <div class="card-body">
                    <h5 class="card-title">Ticketpreis:</h5>
                    <%=stringPrice%>
                    <p class="card-text"><small class="text-muted">Davon enthaltene MwSt: <%=stringMwSt%>
                    </small></p>

                    <h5 class="card-title">Bist du sicher, dass du diese Tickets reservieren möchtest?</h5>

                    <br><br>
                    <div class="text-center">
                    <button id="btn_res" onclick="submitReservierung()" class="btn btn-outline-primary btn-lg mb-2">
                        Jetzt reservieren
                    </button>
                    </div>

                    <script>
                        // Render the PayPal button into #paypal-button-container
                        var price =<%=preis%>;
                        var tax = <%=mwst%>;

                        var vID = <%=vorstellungsID%>;
                        var seats = "<%=seats%>";
                        var preisVer = "<%=preisVer%>";

                        function submitReservierung() {
                            var form = document.createElement("form");
                            form.setAttribute("method", "post");
                            form.setAttribute("action", "reservierung.jsp");
                            var hiddenField0 = document.createElement("input");
                            hiddenField0.setAttribute("type", "hidden");
                            hiddenField0.setAttribute("name", "vorstellungs_id");
                            hiddenField0.setAttribute("value", vID);
                            var hiddenField1 = document.createElement("input");
                            hiddenField1.setAttribute("type", "hidden");
                            hiddenField1.setAttribute("name", "presVerInput");
                            hiddenField1.setAttribute("value", preisVer);
                            var hiddenField2 = document.createElement("input");
                            hiddenField2.setAttribute("type", "hidden");
                            hiddenField2.setAttribute("name", "seats_input");
                            hiddenField2.setAttribute("value", seats);
                            form.appendChild(hiddenField0);
                            form.appendChild(hiddenField1);
                            form.appendChild(hiddenField2);

                            document.body.appendChild(form);
                            form.submit();
                        }
                    </script>

                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="elements/footer.jsp"/>
</body>
</html>

<%
    } else {
        String url = request.getParameter("url");
        response.sendRedirect(url);
    }
%>