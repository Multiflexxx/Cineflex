package handler;

import Password.PassMD5;
//import com.mysql.cj.Session;
import db_connector.Connector;
import db_connector.QueryBuilder;

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
    /*
        if(email.equals("") || pw.equals(""))
        {
            response.getOutputStream().println("Bitte alle Pflichtfelder ausfüllen!");
            return;
        }
*/
        Connection c = null;

        c = Connector.getConnection();
        if (c == null)
            response.getOutputStream().print("Geht nicht!");
        String sql = QueryBuilder.createLoginQuery(email, pw);
        ResultSet rs = Connector.getQueryResult(c, sql);



        try {
            if (rs.next() == false ) {
                //response.getOutputStream().print("Leer");
                response.sendRedirect("index.jsp");

            } else {
                do {
//                    PrintWriter out = response.getWriter();

                    HttpSession session = request.getSession(true);
 /*                   out.println(session.getId());
                    out.println("<br>");
                    out.println("Session created: ");
                    out.println(new Date(session.getCreationTime()) + "<br>");
                    out.println("Session last accessed: ");
                    out.println(new Date(session.getLastAccessedTime()));*/

                    session.setAttribute("email", email);
                    session.setAttribute("name", rs.getString("Vorname"));
/*                    out.println("User: ");
                    out.println(session.getAttribute("email"));*/

                    String lastaccessed = request.getParameter("lastaccessed");
                    String time = request.getParameter("time");
                    if (lastaccessed != null && time != null) {
                        session.setAttribute(lastaccessed, time);
                    }
                    session.setMaxInactiveInterval(600);
                    response.sendRedirect("index.jsp");
                    //response.getOutputStream().print("top");
                    //response.sendRedirect("success.jsp");
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //response.getOutputStream().print("flop1");
        Connector.closeConnection(c);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
