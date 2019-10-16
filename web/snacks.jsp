<%@ page import="java.sql.Connection" %>
<%@ page import="db_connector.Connector" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="factory.FilmFactory" %>
<%@ page import="oo.Film" %>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <h1>Unsere Snacks und Getränke</h1>

    <table class="tableSnacks">
        <td>
    <div class="snacks">
    <h5>Popcorn</h5>
    <h7>wahlweise süß oder salzig</h7>
<table class="tableSnacks">
    <tr>
        <td>Popcorn 1,6 l</td>
        <td>3,50€</td>
    </tr>
    <tr>
        <td>Popcorn 3,0 l</td>
        <td>6,00€</td>
    </tr>
    <tr>
        <td>Popcorn 5,0 l</td>
        <td>8,00€</td>
    </tr>
</table>

    <h5>Nachos</h5>
    <h7>wahlweise mit Käse- oder Salsasauce</h7>
    <table class="tableSnacks">
        <tr>
            <td>Nachos klein</td>
            <td>3,50€</td>
        </tr>
        <tr>
            <td>Nachos groß</td>
            <td>5,50€</td>
        </tr>
    </table>

    <h5>Sonstige Snacks</h5>
    <table class="tableSnacks">
        <tr>
            <td>M&M's</td>
            <td>3,50€</td>
        </tr>
        <tr>
            <td>Chips</td>
            <td>1,80€</td>
        </tr>
        <tr>
            <td>Ben&Jerry Eis (Cookie, Vanille, Schoko)</td>
            <td>2,00€</td>
        </tr>
    </table>
    </div>
        </td>
        <td valign="top">
    <div class="drinks">
    <h5>Alkoholfreie Getränke</h5>
    <table class="tableSnacks">
        <tr>
            <td>Stilles Wasser</td>
            <td>0,5 l</td>
            <td>2,00€</td>
        </tr>
        <tr>
            <td>Mineralwasser</td>
            <td>0,5 l</td>
            <td>2,00€</td>
        </tr>
        <tr>
            <td>Cola</td>
            <td>0,5 l</td>
            <td>3.50€</td>
        </tr>
        <tr>
            <td>Fanta</td>
            <td>0,5 l</td>
            <td>3.50€</td>
        </tr>
        <tr>
            <td>Spezi</td>
            <td>0,5 l</td>
            <td>3.50€</td>
        </tr>
        <tr>
            <td>Sprite</td>
            <td>0,5 l</td>
            <td>3.50€</td>
        </tr>
        <tr>
            <td>Eistee Lemon/Pfirsich</td>
            <td>0,5 l</td>
            <td>3.50€</td>
        </tr>
    </table>

    <h5>Alkoholische Getränke</h5>
    <table class="tableSnacks">
        <tr>
            <td>Bier</td>
            <td>0,5 l</td>
            <td>3,00€</td>
        </tr>
        <tr>
            <td>Radler</td>
            <td>0,5 l</td>
            <td>3.50€</td>
        </tr>
        <tr>
            <td>Wein</td>
            <td>0,5 l</td>
            <td>3.50€</td>
        </tr>
        <tr>
            <td>Sekt</td>
            <td>0,5 l</td>
            <td>11€</td>
        </tr>
    </table>
</div>
    </table>

    <h1>Unsere Sparmenüs</h1>
    <div class="card-deck">
        <div class="card">
            <img class="card-img-top" src="..." alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Popcorn Sparmenü</h5>
                <p class="card-text">Eine große Portion Popocorn und ein Getränk*</p>
                <p class="card-text"><small class="text-muted">*Softdrink 1,0 l <i>oder</i> alkoholisches Getränk 0,75 l</small></p>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="..." alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Nachos Sparmenü</h5>
                <p class="card-text">Eine große Portion Nachos + ein Getränk*</p>
                <p class="card-text"><small class="text-muted">*Softdrink 1,0 l <i>oder</i> alkoholisches Getränk 0,75 l</small></p>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="..." alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Pärchenmenü</h5>
                <p class="card-text">Eine große Portion Nachos (2 Saucen) <i>oder</i> Popcorn + zwei Getränke*  </p>
                <p class="card-text"><small class="text-muted">*Softdrink 0,75 l <i>oder</i> alkoholisches Getränk 0,5 l </small></p>
            </div>
        </div>
    </div>
</div>
<jsp:include page="elements/footer.jsp"/>
</body>
</html>
