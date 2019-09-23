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

<h1>Hier finden Sie uns:</h1>
<div class="container" style="margin-top:15px; margin-bottom:15px;">
    <iframe src="https://www.google.com/maps/d/embed?mid=1iFLu1_eFR5Kvh6D5A1hARlFCm13TXPUb&hl=de" width="1000" height="1000"></iframe>
</div>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>
