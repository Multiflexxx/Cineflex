<%@ page import="Password.PassMD5" %>
<%@ page import="factory.LoginFactory" %>
<%@ page import="java.sql.ResultSet" %>

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

    LoginFactory login = new LoginFactory(email, pw);
    ResultSet rs = login.getLoginResult();

    try {
        if (rs.next() == false ) {
            response.sendRedirect("index.jsp");

        } else {
            do {
                //HttpSession session = request.getSession(true);

                session.setAttribute("email", email);
                session.setAttribute("name", rs.getString("Vorname"));

                String lastaccessed = request.getParameter("lastaccessed");
                String time = request.getParameter("time");
                if (lastaccessed != null && time != null) {
                    session.setAttribute(lastaccessed, time);
                }
                session.setMaxInactiveInterval(600);
                response.sendRedirect("index.jsp");
            } while (rs.next());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>