<%@ page import="java.sql.Connection" %>
<%@ page import="db_connector.Connector" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="factory.FilmFactory" %>
<%@ page import="oo.Film" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">

<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="filter.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>


<jsp:include page="elements/footer.jsp"/>
</body>
</html>
