<%@ page import="oo.Film" %>
<%@ page import="factory.FilmFactory" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">

<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>

<%
    String date = request.getParameter("inputDate");
    String time = request.getParameter("inputTime");
    String fsk = request.getParameter("inputFSK");
    String searchText = request.getParameter("inputSearchText");
    int genreID = Integer.parseInt(request.getParameter("inputGenre"));
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

    try {
        if(filme != null) {

            out.write("<div class=\"container\">");
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
            out.write("</div>");
        } else {
            out.println("sql");
        }
    } catch(Exception e) {
        e.printStackTrace();
    }
%>

<jsp:include page="elements/footer.jsp"/>
</body>
