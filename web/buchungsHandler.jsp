<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="helper.SeatIDFormatter" %>
<%@ page import="helper.ArrayBuilder" %>
<%@ page import="factory.BuchungsFactory" %>
<%@ page import="java.lang.reflect.Array" %>
<!DOCTYPE html>
<html>
<jsp:include page="elements/head.jsp"/>

<body class="d-flex flex-column h-100">

<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>

<%
//    String seatsData = request.getParameter("seats_data");
//
//    int[] seatIDs = SeatIDFormatter.seatsStringToIntArray(seatsData);
//
//    // If no seat is selected -> redirect to errorPage
//    if(seatIDs == null)
//    {
//        response.sendRedirect("error500.jsp");
//    }
//
//    // TEST
//    for(int i = 0; i < seatIDs.length; i++)
//    {
//        out.println(seatIDs[i]);
//    }

    if(session.getAttribute("email") == null) {
        out.write("Bitte melden Sie sich an <br />");
    } else {
        int vorstellungsID = Integer.parseInt(request.getParameter("vorstellungsid"));
        String seats = request.getParameter("seats_data");
        String preisVer = request.getParameter("tickets_data");

        int[] seatsInt = ArrayBuilder.stringToIntArray(seats, ",");
        int[] presVerInt = ArrayBuilder.stringToIntArray(preisVer, ",");

        out.write(ArrayBuilder.intArrayToString(seatsInt) + "<br />");
        out.write(ArrayBuilder.intArrayToString(presVerInt));

        BuchungsFactory.createBuchungBeleg(seatsInt, presVerInt, vorstellungsID, Integer.parseInt(session.getAttribute("KID").toString()));
    }
%>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>
