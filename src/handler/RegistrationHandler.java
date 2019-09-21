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
import java.util.Date;

@WebServlet(name = "RegistrationForm")
public class RegistrationHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("inputVorname");
        String lastname = request.getParameter("inputNachname");
        String date = request.getParameter("inputGeb");
        String email = request.getParameter("inputEmail");
        String pass = request.getParameter("inputPasswordReg");


        Connection c = null;

        c = Connector.getConnection();
        if (c == null)
            response.getOutputStream().print("Geht nicht!");
        String sql = QueryBuilder.createUser(firstname, lastname, date, email, pass);
        //Connector.executeQuery(c, sql);

        response.getOutputStream().print(sql);

        /*try {
            response.sendRedirect("success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        response.getOutputStream().print("flop1");
        Connector.closeConnection(c);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
