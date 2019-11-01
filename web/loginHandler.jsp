<%@ page import="Password.PassMD5" %>
<%@ page import="factory.LoginFactory" %>
<%@ page import="oo.UserLogin" %>
<%@ page import="factory.StayLoggedInFactory" %>
<%@ page import="exception.FailedDataInsertionException" %>
<%@ page import="exception.RequiredFactoryFailedException" %>
<%@ page import="exception.FailedObjectCreationException" %>
<%@ page import="oo.StayLoggedIn" %>

<%
    String email = request.getParameter("inputEmailLog");
    String url = request.getParameter("inputURL");
    String pw = null;
    String check = "";
    check = request.getParameter("inputCheckLogin");

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
        if (check != null && check.equals("true")) {
//            Cookie stay = new Cookie("stay", "yes");
//            Cookie stay_email = new Cookie("email", email);
//            Cookie stay_pwhash = new Cookie("pw", pw);
//            stay.setMaxAge(60*60*24*365);
//            stay_email.setMaxAge(60*60*24*365);
//            stay_pwhash.setMaxAge(60*60*24*365);
//            response.addCookie(stay);
//            response.addCookie(stay_email);
//            response.addCookie(stay_pwhash);
            try {
                StayLoggedIn stayLogged = StayLoggedInFactory.stayLoggedIn(email, pw);
                Cookie stay = new Cookie("stayLoggedIn", stayLogged.getId());
                response.addCookie(stay);
            } catch (FailedDataInsertionException | RequiredFactoryFailedException | FailedObjectCreationException e) {
                e.printStackTrace();
            }
        }
    }
    response.sendRedirect(url);
%>