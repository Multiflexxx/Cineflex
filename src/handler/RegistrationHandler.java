package handler;

import db_connector.Connector;
import db_connector.QueryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

@WebServlet(name = "RegistrationForm")
public class RegistrationHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameter("inputVorname");
        request.getParameter("inputNachname");
        request.getParameter("inputGeb");
        request.getParameter("inputEmail");
        request.getParameter("inputPasswordReg");
        request.getParameter("inputPasswordRegWdh");


        Connection c = null;

        c = Connector.getConnection();
        if (c == null)
            response.getOutputStream().print("Geht nicht!");
        String sql = QueryBuilder.createLoginQuery();
        ResultSet rs = Connector.getQueryResult(c, sql);

        try {
            if (rs.next() == false ) {
                response.getOutputStream().print("Leer");
            } else {
                do {
                    response.getOutputStream().print("top");
                    response.sendRedirect("success.jsp");
                    String data = rs.getString("Vorname");
                    response.getOutputStream().print(data);
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getOutputStream().print("flop1");
        Connector.closeConnection(c);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
