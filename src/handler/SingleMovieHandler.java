package handler;

import db_connector.Connector;
import db_connector.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        PrintWriter out = response.getWriter();

        out.print("ID: " + id + "\n");
        out.print("Date: " + date + "\n");
        out.print("Time: " + time + "\n");


        String query = QueryBuilder.showMovieById(id, date, time, "86153");
        out.print(query + "\n");
        Connection c = Connector.getConnection();
        ResultSet rs = Connector.getQueryResult(c, query);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
