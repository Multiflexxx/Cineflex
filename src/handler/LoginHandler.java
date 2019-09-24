package handler;

import Password.PassMD5;
//import com.mysql.cj.Session;
import db_connector.Connector;
import db_connector.QueryBuilder;
import oo.Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

//@WebServlet(name = "LoginHandler")
public class LoginHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmailLog");
        String pw = null;

        try {
            pw = PassMD5.hash(request.getParameter("inputPassword"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Login login = new Login(email, pw);
        ResultSet rs = login.getLoginResult();

//        Connection c = null;
//
//        c = Connector.getConnection();
//        if (c == null)
//            response.getOutputStream().print("Geht nicht!");
//        String sql = QueryBuilder.createLoginQuery(email, pw);
//        ResultSet rs = Connector.getQueryResult(c, sql);



        try {
            if (rs.next() == false ) {
                response.sendRedirect("index.jsp");

            } else {
                do {
                    HttpSession session = request.getSession(true);

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
        //Connector.closeConnection(c);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
