<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="helper.ArrayBuilder" %>
<%@ page import="factory.SitzsperreFactory" %>
<%@ page import="factory.PreisFactory" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Currency" %>
<%@ page import="java.util.Locale" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>


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
            int[] presVerInt = ArrayBuilder.stringToIntArray(preisVer, ",");
            SitzsperreFactory.lockSeats(seatsInt, vorstellungsID, KID);
            float preis = PreisFactory.getBuchungsPreis(seats, preisVer, filmID);
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("de", "DE"));
            numberFormat.setCurrency(Currency.getInstance("EUR"));

            String stringPrice = numberFormat.format(preis);
            //BuchungsFactory.createBuchungBeleg(seatsInt, presVerInt, vorstellungsID, KID);
    %>
    <div class="card mt-3 mb-3">
        <div class="row">
            <div class="col-lg-5">
                <h5>Deine ausgewählten Tickets</h5>
                <ul class="list-group">
                    <li class="list-group-item list-group-item-light">T6</li>
                    <li class="list-group-item list-group-item-secondary">T7</li>
                    <li class="list-group-item list-group-item-dark">T8</li>
                </ul>
            </div>
            <div class="col-lg-7">
                <div class="card-body">
                    <h5 class="card-title">Ticketpreis:</h5>
                    <%=stringPrice%>
                    <p class="card-text"><small class="text-muted">Hier kann Text stehen</small></p>
                    <div id="paypal-button-container"></div>
                    <script
                            src="https://www.paypal.com/sdk/js?client-id=AdK-QibhcEoeaSTF4BytwJavRDQ7fnd2Q1H338CFUyRZE2BLPEpbVByY6vpLX1K1u-y2lZKM05NU0yb4">
                    </script>
                    <script>
                        paypal.Buttons({
                            createOrder: function(data, actions) {
                                return actions.order.create({
                                    purchase_units: [{
                                        amount: {
                                            value: '0.01'
                                        }
                                    }]
                                });
                            },
                            onApprove: function(data, actions) {
                                return actions.order.capture().then(function(details) {
                                    alert('Transaction completed by ' + details.payer.name.given_name);
                                    // Call your server to save the transaction
                                    return fetch('/paypal-transaction-complete', {
                                        method: 'post',
                                        headers: {
                                            'content-type': 'application/json'
                                        },
                                        body: JSON.stringify({
                                            orderID: data.orderID
                                        })
                                    });
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