<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="db_connector.Connector" %>
<%@ page import="db_connector.QueryBuilder" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body>
<jsp:include page="elements/header.jsp"/>

<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>

<%
    HttpSession s = request.getSession();
    s.invalidate();
%>

<div class="jumbotron jumbotron-fluid footer">
    <div class="container">
        <h1 class="display-4">Sie wurden erfolgreich ausgeloggt</h1>
        <p class="lead">Sie können nun zurück zur Startseite</p>
        <a class="btn btn-primary btn-lg" href="index.jsp" role="button">Zurück zur Startseite</a>
    </div>
</div>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>
