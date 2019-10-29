<%@ page import="factory.AnfahrtsseiteFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="search.jsp"/>

<div class="container">
    <div class="card mt-3 mb-3">
        <div class="card-header"><h3 class="display-4">Hier finden Sie uns:</h3></div>
        <div class="trailer-con">
            <%
                try {

                    Cookie cookie = null;
                    Cookie[] cookies = null;
                    String outputValue = "Standort";

                    cookies = request.getCookies();

                    if (cookies != null) {
                        for (int i = 0; i < cookies.length; i++) {
                            if (cookies[i].getName().equals("city")) {
                                cookie = cookies[i];
                                outputValue = cookie.getValue();
                                break;
                            }
                        }
                    }
                    out.write("" + AnfahrtsseiteFactory.getAnfahrtseite(outputValue));
                } catch (Exception e) {
                    out.write("<iframe src=\"https://www.google.com/maps/d/embed?mid=1iFLu1_eFR5Kvh6D5A1hARlFCm13TXPUb&hl=de\" width=\"1000em\" height=\"800em\"></iframe>");
                } finally {

                }
            %>
        </div>
    </div>
</div>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>
