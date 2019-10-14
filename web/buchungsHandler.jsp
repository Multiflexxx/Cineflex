<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="helper.SeatIDFormatter" %>
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
    String seatsData = request.getParameter("seats_data");

    int[] seatIDs = SeatIDFormatter.seatsStringToIntArray(seatsData);

    // If no seat is selected -> redirect to errorPage
    if(seatIDs == null)
    {
        response.sendRedirect("error500.jsp");
    }

    // TEST
    for(int i = 0; i < seatIDs.length; i++)
    {
        out.println(seatIDs[i]);
    }

%>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>
