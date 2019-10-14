<%@ page import="helper.SeatIDFormatter" %><%--
  Created by IntelliJ IDEA.
  User: marcel
  Date: 13.10.19
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String seatsData = request.getParameter("seats_data");

    int[] seatIDs = SeatIDFormatter.seatsStringToIntArray(seatsData);

    // If no seat is selected -> redirect to errorPage
    if(seatIDs == null)
    {
        response.sendRedirect("error/error500.jsp");
    }

    // TEST
    for(int i = 0; i < seatIDs.length; i++)
    {
        out.println(seatIDs[i]);
    }

%>>

</body>
</html>
