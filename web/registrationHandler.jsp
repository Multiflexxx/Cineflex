<%@ page import="helper.SupportMethods" %>
<%@ page import="Password.PassMD5" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="db_connector.Connector" %>
<%@ page import="db_connector.QueryBuilder" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="factory.LoginFactory" %>
<%@ page import="oo.UserLogin" %>
<%@ page import="factory.RegistrierungFactory" %>
<%@ page import="exception.registrierung.UnmatchingPasswordException" %>
<%@ page import="exception.registrierung.EmptyInputValueException" %>
<%@ page import="exception.registrierung.UserAlreadyExistsException" %>
<%@ page import="exception.RequiredFactoryFailedException" %>
<%@ page import="exception.EmptyResultSetException" %>
<%@ page import="factory.OrtsFactory" %>
<%@ page import="exception.ResultSetIsNullException" %>
<%
    String firstname = SupportMethods.removeHTMLCode(request.getParameter("inputVorname"));
    String lastname = SupportMethods.removeHTMLCode(request.getParameter("inputNachname"));
    String date = SupportMethods.removeHTMLCode(request.getParameter("inputGeb"));
    String email = SupportMethods.removeHTMLCode(request.getParameter("inputEmailReg"));
    String pass = request.getParameter("inputPasswordReg");
    String passWdh = request.getParameter("inputPasswordRegWdh");
    String wohnort = SupportMethods.removeHTMLCode(request.getParameter("inputOrt"));
    String postleitzahl = SupportMethods.removeHTMLCode(request.getParameter("inputPLZ"));
    postleitzahl = SupportMethods.removeNoneNumericals(postleitzahl);
    String straße = SupportMethods.removeHTMLCode(request.getParameter("inputStr"));
    String hausnummer = SupportMethods.removeHTMLCode(request.getParameter("inputHNr"));
    hausnummer = SupportMethods.removeNoneNumericals(hausnummer);
    String adresszusatz = SupportMethods.removeHTMLCode(request.getParameter("inputAdz"));

    if (!pass.equals(passWdh)) {
        response.getOutputStream().println("Die Passwörter stimmen nicht überein!");
        return;
    }

    if (pass.length() < 6) {
        response.getOutputStream().println("Das Passwort muss mindestens 6 Zeichen lang sein!");
        return;
    }

    if (firstname.equals("") || lastname.equals("") || date.equals("") || email.equals("") || pass.equals("") || passWdh.equals("") || wohnort.equals("") || postleitzahl.equals("") || straße.equals("") || hausnummer.equals("")) {
        response.getOutputStream().println("Bitte alle Pflichtfelder ausfüllen!");
        return;
    }

    try {
        OrtsFactory.getOrtByPLZ(Integer.parseInt(postleitzahl));
    } catch (ResultSetIsNullException e) {
        e.printStackTrace();
        return;
    } catch (EmptyResultSetException e) {
        e.printStackTrace();
        response.sendRedirect("error500.jsp");
    }

    try {
        pass = PassMD5.hash(pass);
        passWdh = PassMD5.hash(passWdh);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }

//    Connection c = null;
//
//    c = Connector.getConnection();
//    if (c == null)
//        response.getOutputStream().print("Geht nicht!");
//
//    String checkUserSQL = QueryBuilder.getUserForRegistration(firstname, lastname, date, email);
//
//    ResultSet resultSet = Connector.getQueryResult(c, checkUserSQL);
//
//    if (SupportMethods.getResultSetSize(resultSet) > 0) {
//        // User Already Exists in DB!
//        response.sendRedirect("/error500.jsp");
//    }
//
//    // E-mail to lower case
//    email = email.toLowerCase();

    Date gebDateSQL;

    try {
        gebDateSQL = new SimpleDateFormat("yyyy-MM-dd").parse(date);
    } catch (Exception e) {
        return;
    }

//    String sql = QueryBuilder.createUser(firstname, lastname, gebDateSQL, email, pass, Integer.parseInt(hausnummer), straße, adresszusatz, Integer.parseInt(postleitzahl));
//    Connector.executeQuery(c, sql);
    try {
        RegistrierungFactory.createRegistrierung(firstname, lastname, gebDateSQL, email, pass, passWdh, wohnort, Integer.parseInt(postleitzahl), straße, Integer.parseInt(hausnummer), adresszusatz);
    } catch (UnmatchingPasswordException | EmptyInputValueException | UserAlreadyExistsException | RequiredFactoryFailedException | EmptyResultSetException e) {
        e.printStackTrace();
    }
    UserLogin userLogin = LoginFactory.getUserLogin(email, pass);

    try {
        session = request.getSession(true);

        session.setAttribute("email", email);
        session.setAttribute("vorname", userLogin.getFirstname());
        session.setAttribute("nachname", userLogin.getLastname());
        session.setAttribute("PID", userLogin.getPID());
        session.setAttribute("KID", userLogin.getKID());
        session.setMaxInactiveInterval(600);
        response.sendRedirect("index.jsp");
    } catch (Exception e) {
        e.printStackTrace();
    }

   // Connector.closeConnection(c);
%>