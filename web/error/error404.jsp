<%@ page import="java.sql.Connection" %>
<%@ page import="db_connector.Connector" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="factory.FilmFactory" %>
<%@ page import="oo.Film" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../elements/head.jsp"/>
<body class="d-flex flex-column h-100">

<jsp:include page="../elements/header.jsp"/>
<jsp:include page="../locationPicker.jsp"/>
<jsp:include page="../login.jsp"/>
<jsp:include page="../registration.jsp"/>
<jsp:include page="../filter.jsp"/>

<div class="container">
    <div class="card mt-3 mb-3">
        <div class="row">
            <div class="col-lg-5">
                <img src="/img/error/multiflexTeam.jpg" class="card-img" alt="It´s a triple">
            </div>
            <div class="col-lg-7">
                <div class="card-body">
                    <h5 class="card-title">Error 404</h5>
                    <p class="card-text"><small class="text-muted">Oh Baby! It´s a triple</small></p>
                    <p class="card-text mrb-justify">Hier ist etwas schief gelaufen! Du kannst gerne unseren Shop besuchen!</p>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../elements/footer.jsp"/>
</body>
</html>