<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="db_connector.Connector" %>
<%@ page import="db_connector.QueryBuilder" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body>
<jsp:include page="elements/header.jsp"/>

<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>

<div class="jumbotron jumbotron-fluid footer">
    <div class="container">
        <h1 class="display-4">Hier finden Sie uns:</h1>
        <%
            try{
                String baseStringGoogle = "https://www.google.com/maps/search/?api=1&query=";
                Connection c = Connector.getConnection();
                ResultSet rs = Connector.getQueryResult(c, QueryBuilder.showAllCinemas());
                String ort = rs.getString("Ortsname");
                rs = Connector.getQueryResult(c, QueryBuilder.getKinosByName(ort));
                String variableString = "" + rs.getString("Straße") + rs.getString("Hausnummer") + rs.getString("PLZ");
                variableString = URLEncoder.encode(variableString, "UTF-8");
                out.println("<iframe src=\"" + baseStringGoogle + variableString + "\" width=\"1000\" height=\"1000\"></iframe>");
            }catch(Exception e){
                out.println("<iframe src=\"https://www.google.com/maps/d/embed?mid=1iFLu1_eFR5Kvh6D5A1hARlFCm13TXPUb&hl=de\" width=\"1000\" height=\"1000\"></iframe>");
            }
        %>

    </div>
</div>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>
