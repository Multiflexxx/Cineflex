<%@ page import="oo.Film" %>
<%@ page import="factory.FilmFactory" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>

<%
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    SimpleDateFormat st = new SimpleDateFormat("HH:mm");
    Date time = new Date();

    Film filme[] = FilmFactory.getFilme();
    String plz = "00000";
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("plz")) {
            plz = cookie.getValue();
        }
    }

    if (filme != null) {
%>
<div class="container">
    <%
        if (filme != null) {
            for (Film f : filme) {
                String hrefURL = "SingleMovieHandler?";
                hrefURL += "id=" + f.getFilmID();  //getString("FilmID");
                hrefURL += "&date=" + sd.format(date);
                ;
                hrefURL += "&time=" + st.format(time);
                ;
    %>

    <div class="card mt-3 mb-3">
        <div class="row">
            <div class="col-lg-5">
                <img src="<%= f.getBildLink()%>" class="card-img" alt="<%= f.getTitel()%>">
            </div>
            <div class="col-lg-7">
                <div class="card-body">
                    <h5 class="card-title"><%=f.getTitel()%>
                    </h5>
                    <p class="card-text"><small class="text-muted"><%=f.getDauer()%> min | FSK <%=f.getFsk()%>
                    </small></p>
                    <p class="card-text mrb-justify"><%=f.getBeschreibung()%>
                    </p>
                    </p>
                    <a href="<%=hrefURL%>" class="btn btn-primary">Zum Film</a>
                </div>
            </div>
        </div>

    </div>
    <%
                }
            }
        }
    %>
</div>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>