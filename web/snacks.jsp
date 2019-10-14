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

<% //BildFormat: 1920x560> %>

<div class="container">
<div class="card bg-dark text-white">
    <img src="img/snacks/popcorn.jpg"  class="card-img" alt="sparmenue">
    <div class="content">
        <h5>Unser Snack-Angebot</h5>
        <p>Hier findest du alles was dein Herz begehrt.</p>
        <a href="" class="btn btn-primary">Alle Snacks anzeigen</a>
    </div>
</div>

    <div class="card bg-dark text-white">
        <img src="img/snacks/sparmenue.jpg"  class="card-img" alt="sparmenue">
        <div class="content2">
            <h5>Sparmenüs</h5>
            <p>Genieße jetzt deinen Kinobesuch noch mehr und hol dir eins unserer Sparmenüs!</p>
            <a href="" class="btn btn-primary">Sparmenüs anzeigen</a>
        </div>
    </div>

    <div class="card bg-dark text-white">
        <img src="img/snacks/ingredients.jpg"  class="card-img" alt="ingredients">
        <div class="content3">
            <h5>Inhaltsstoffe</h5>
            <p>Hier finden Sie alle Inhaltsstoffe und Nährwerte, die in unseren Snacks enthalten sind.</p>
            <a href="" class="btn btn-primary">Erfahre mehr</a>
        </div>
    </div>
</div>



<jsp:include page="elements/footer.jsp"/>
</body>
</html>

