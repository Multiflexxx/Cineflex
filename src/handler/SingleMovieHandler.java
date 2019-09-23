package handler;

import db_connector.Connector;
import db_connector.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SingleMovieHandler")
public class SingleMovieHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        String time =  request.getParameter("time");
        String plz = "00000";
        PrintWriter out = response.getWriter();

        // Get PLZ
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies) {
            if(c.getName() == "PLZ") {
                plz = c.getValue();
            }
        }
        if(plz == "00000") {
            out.print("Keine PLZ + \n");
        }


        out.print("ID: " + id + "\n");
        out.print("Date: " + date + "\n");
        out.print("Time: " + time + "\n");
        out.print("PLZ: " + plz + "\n");


        String query = QueryBuilder.showMovieById(id, date, time, plz);
        out.print(query + "\n");
        Connection c = Connector.getConnection();
        ResultSet rs = Connector.getQueryResult(c, query);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
