<%--
  Created by IntelliJ IDEA.
  User: D073701
  Date: 01.10.2019
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="factory.VorstellungsFactory"%>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>
<html>
<head>
    <title></title>
</head>
<body>
    <%
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        //VorstellungsFactory.getVorstellungen()
    %>
    <p>VorstellungsID: <%=id%> am <%=date%> um <%=time%></p>
</body>
</html>
