<%@ page import="Password.PassMD5" %>
<%@ page import="factory.LoginFactory" %>
<%@ page import="oo.UserLogin" %>

<%
    String email = request.getParameter("inputEmailLog");
    String url = request.getParameter("inputURL");
    String pw = null;

    try {
        pw = PassMD5.hash(request.getParameter("inputPassword"));
    } catch (Exception e) {
        e.printStackTrace();
        // Exit !
        return;
    }

    // E-mail to lower case
    email = email.toLowerCase();

    UserLogin userLogin = LoginFactory.getUserLogin(email, pw);


    if (userLogin == null) {
        session.setAttribute("loginfailed", "1");
    } else {

        session.setAttribute("email", email);
        session.setAttribute("vorname", userLogin.getFirstname());
        session.setAttribute("nachname", userLogin.getLastname());
        session.setAttribute("PID", userLogin.getPID());
        session.setAttribute("KID", userLogin.getKID());
        session.removeAttribute("login");
        session.setMaxInactiveInterval(600);
    }
    response.sendRedirect(url);
%>