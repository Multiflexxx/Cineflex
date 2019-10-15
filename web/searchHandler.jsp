<%@ page import="oo.Film" %>
<%@ page import="factory.FilmFactory" %>
<%@ page import="helper.SupportMethods" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">

<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>

<%
    String date = SupportMethods.removeHTMLCode(request.getParameter("inputDate"));
    String time = SupportMethods.removeHTMLCode(request.getParameter("inputTime"));
    String fsk = SupportMethods.removeHTMLCode(request.getParameter("inputFSK"));
    String searchText = SupportMethods.removeHTMLCode(request.getParameter("inputSearchText"));
    int genreID = Integer.parseInt(SupportMethods.removeHTMLCode(request.getParameter("inputGenre")));
    time += ":00";

    String plz = "00000";
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("plz")) {
            plz = cookie.getValue();
        }
    }

    Film filme[] = null;

    if (genreID == -1) {
        filme = FilmFactory.getFilme(searchText, date, time, Integer.parseInt(fsk), plz);
    }else{
        filme = FilmFactory.getFilme(searchText, date, time, Integer.parseInt(fsk), plz, genreID);
    }
    out.write("<div class=\"container\">");
    try {
        if(filme[0] != null) {
            for (Film f : filme) {
                String hrefURL = "singleMovie.jsp?";
                hrefURL += "id=" + f.getFilmID();  //getString("FilmID");
                hrefURL += "&date=" + date;
                hrefURL += "&time=" + time;
                out.write("<div class=\"card mt-3 mb-3\">");
                out.write("<div class=\"row no-gutters\">");
                out.write("<div class=\"col-lg-5\">");
                out.write("<img src='" + f.getBildLink() +  "' class=\"card-img\" alt='" + f.getTitel() + "'>");
                out.write("</div>");
                out.write("<div class=\"col-lg-7\">");
                out.write("<div class=\"card-body\">");
                out.write("<h5 class=\"card-title\">" + f.getTitel() + "</h5>");
                out.write("<p class=\"card-text\"><small class=\"text-muted\">" + f.getDauer() + " min | FSK " + f.getFsk() + "</small></p>");
                out.write("<p class=\"card-text mrb-justify\">" + f.getBeschreibung() + "</p>");
                out.write("<a href=\"" + hrefURL + "\" class=\"btn btn-primary\">Zum Film</a>");
                out.write("</div>");
                out.write("</div>");
                out.write("</div>");
                out.write("</div>");
            }
        } else {
            out.write("<br><br>");
            out.write("<div class=\"card\">");
            out.write("<div class=\"card-body text-center\">");
            out.write("<h5 class=\"card-title\">Deine Suche ergab keine Ergebnisse. Das tut uns sehr Leid.</h5>");
            out.write("<p class=\"card-text\"><small class=\"text-muted\">Bitte Versuch es nocheinmal.</small></p>");
            out.write("</div>");
            out.write("</div>");
        }

    } catch(Exception e) {
        e.printStackTrace();
    }
    out.write("</div>");
%>

<jsp:include page="elements/footer.jsp"/>
</body>
