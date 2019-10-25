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
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="search.jsp"/>


<div class="container">
    <%
        if (session.getAttribute("email") == null) {
            out.write("<div class=\"jumbotron jumbotron-fluid footer\">\n" +
                    "    <div class=\"container\">\n" +
                    "        <h1 class=\"display-4\">Bitte melden sie isch zuerst an</h1>\n" +
                    "        <p class=\"lead\">Erst dann können sie Buchungen abschließen</p>\n" +
                    "        <a class=\"btn btn-primary btn-lg\" href=\"index.jsp\" role=\"button\">Zurück zur Startseite</a>\n" +
                    "    </div>\n" +
                    "</div>");
        } else {
            int vorstellungsID = Integer.parseInt(request.getParameter("vorstellungs_id"));
            int filmID = Integer.parseInt(request.getParameter("film_id"));
            String seats = request.getParameter("seats_data");
            String preisVer = request.getParameter("tickets_data");
            int KID = Integer.parseInt(session.getAttribute("KID").toString());
            int[] seatsInt = ArrayBuilder.stringToIntArray(seats, ",");
            SitzsperreFactory.lockSeats(seatsInt, vorstellungsID, KID);

            try {
                TempBuchungHandler.addTempBuchungToSession(session, seats, preisVer);
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
    <div class="card mt-3 mb-3">
        <div class="row">
            <div class="col-lg-5 d-flex flex-column justify-content-center">
                <h5>Dein(e) ausgewählte(s/n) Ticket(s)</h5>
                <ul class="li st-group">
                    <li class="list-group-item list-group-item-light">Reihe M Platz 1</li>
                    <li class="list-group-item list-group-item-light">Reihe M Platz 2</li>
                    <li class="list-group-item list-group-item-light">Reihe M Platz 3</li>
                </ul>
            </div>
            <div class="col-lg-7">
                <div class="card-body">
                    <h5 class="card-title">Ticketpreis:</h5>
                    <%=stringPrice%>
                    <p class="card-text"><small class="text-muted">Davon enthaltene MwSt: <%=stringMwSt%>
                    </small></p>
                    <div id="paypal-button-container"></div>
                    <script src="https://www.paypal.com/sdk/js?client-id=AdK-QibhcEoeaSTF4BytwJavRDQ7fnd2Q1H338CFUyRZE2BLPEpbVByY6vpLX1K1u-y2lZKM05NU0yb4&currency=EUR"></script>
                    <script>
                        // Render the PayPal button into #paypal-button-container
                        var price =<%=preis%>;
                        var tax = <%=mwst%>;

                        var vID = <%=vorstellungsID%>;
                        var seats = "<%=seats%>";
                        var preisVer = "<%=preisVer%>";

                        paypal.Buttons({

                            style: {
                                color: 'blue',
                                shape: 'pill',
                                label: 'pay',
                                height: 40
                            },

                            // Set up the transaction
                            createOrder: function (data, actions) {
                                return actions.order.create({
                                    purchase_units: [{
                                        description: 'Cineflex Order 69',
                                        amount: {
                                            currency_code: 'EUR',
                                            value: price,
                                        },
                                    }]
                                });
                            },

                            // Finalize the transaction
                            onApprove: function (data, actions) {
                                return actions.order.capture().then(function (details) {
                                    var form = document.createElement("form");
                                    form.setAttribute("method", "post");
                                    form.setAttribute("action", "payment.jsp");
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

                                    //window.location.replace("payment.jsp");
                                });
                            }


                        }).render('#paypal-button-container');
                    </script>

                </div>
            </div>
        </div>
    </div>
    <%
        }
    %>
</div>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>