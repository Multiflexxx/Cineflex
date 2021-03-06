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
<jsp:include page="search.jsp"/>

<!-- Karussell-->
<!-- Bitte nur Bilder der größe 1656x630 einfügen-->
<div class="karussell mb-3">
    <div id="karussellangebote" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#karussellangebote" data-slide-to="0" class="active"></li>
            <li data-target="#karussellangebote" data-slide-to="1"></li>
            <li data-target="#karussellangebote" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="img/banner/ouatih.jpg" class="d-block w-100" alt="Once Upon A Time In Hollywood">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Once Upon a Time in Hollywood</h5>
                    <p>"Bester Film EU WEST BRUDA"- Alle</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="img/banner/popcorn.jpg" class="d-block w-100" alt="Popcorn">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Angebot</h5>
                    <p>1 Packung Popcorn zum Preis von 2</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="img/banner/filme.jpg" class="d-block w-100" alt="Filme">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Movie Night</h5>
                    <p>An diesem Abend zeigen wir Filme.</p>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#karussellangebote" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#karussellangebote" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>

<%
    //Connection c = Connector.getConnection();

    SimpleDateFormat st = new SimpleDateFormat("HH:mm");
    Date time = new Date();

    String plz = "00000";
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("plz")) {
            plz = cookie.getValue();
        }
    }

    if (plz == "00000") {

    }

    out.write("<div class=\"container\">");
    out.write("<div class=\"card-deck mb-3\" style=\"max-width: 1400px;\">");
    Film[] titelFilm = null;
    try {
        titelFilm = FilmFactory.getTitelPageFilme(plz);
        if (titelFilm[0] != null) {
        for (int i = 0; i < titelFilm.length; i++) {
            String hrefURL = "singleMovie.jsp?id=";
            LocalDate localDate = LocalDate.now();
            hrefURL += titelFilm[i].getFilmID();
            hrefURL += "&date=" + localDate.toString();
            hrefURL += "&time=" + st.format(time);
            out.write("<div class=\"card\">");
            out.write("<img src='" + titelFilm[i].getBildLink() + "' class=\"card-img-top\" alt='" + titelFilm[i].getTitel() + "'>");
            out.write("<div class=\"card-body text-center\">");
            out.write("<h5 class=\"card-title\">" + titelFilm[i].getTitel() + "</h5>");
            out.write("<p class=\"card-text\"><small class=\"text-muted\">Neu bei uns!</small></p>");
            //out.write("<div class=\"card-footer text-center\">");
            out.write("<a href=\"" + hrefURL + "\" class=\"btn btn-primary\">Zum Film</a>");
            //out.write("</div>");
            out.write("</div>");
            out.write("</div>");
        }
    } else {
            out.write("<div class=\"card\">");
            out.write("<div class=\"card-body text-center\">");
            out.write("<h5 class=\"card-title\">Für dein ausgewähltes Kino gibt es keine Vorstellungen ..</h5>");
            out.write("<p class=\"card-text\"><small class=\"text-muted\">Gerne kannst Du ein anderes Kino von uns besuchen.</small></p>");
            out.write("</div>");
            out.write("</div>");
        }
    } catch (Exception e) {
        response.sendRedirect("error/error500.jsp");
    }
    out.write("</div>");
    out.write("</div>");

%>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>