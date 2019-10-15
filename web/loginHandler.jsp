<%@ page import="Password.PassMD5" %>
<%@ page import="factory.LoginFactory" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="oo.UserLogin" %>

<%
    String email = request.getParameter("inputEmailLog");

    String pw = null;

    try {
        pw = PassMD5.hash(request.getParameter("inputPassword"));
    } catch (Exception e) {
        e.printStackTrace();
        // Exit !
        return;
    }

    UserLogin userLogin = LoginFactory.getUserLogin(email, pw);


        if (userLogin == null) {
            response.sendRedirect("index.jsp");

        } else {

                session.setAttribute("email", email);
                session.setAttribute("vorname", userLogin.getFirstname());
                session.setAttribute("nachname", userLogin.getLastname());
                session.setAttribute("PID", userLogin.getPID());
                session.setAttribute("KID", userLogin.getKID());

//                String lastaccessed = request.getParameter("lastaccessed");
//                String time = request.getParameter("time");
//                if (lastaccessed != null && time != null) {
//                    session.setAttribute(lastaccessed, time);
//                }
                session.setMaxInactiveInterval(600);
                response.sendRedirect("index.jsp");

        }

%>