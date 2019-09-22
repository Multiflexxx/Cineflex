package handler;
import db_connector.Connector;
import db_connector.QueryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@WebServlet(name = "SearchHandler")
public class SearchHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("inputDate");
        String time = request.getParameter("inputTime");
        String fsk = request.getParameter("inputFSK");
        String searchText = request.getParameter("inputSearchText");
        time += ":00";
        PrintWriter out = response.getWriter();
        out.print(request.getRequestURL() + "\n");
        out.print(request.getRequestURI() + "\n");
        out.print("Date Input: " + date + "\n");
        out.print("Time Input: " + time + "\n");
        out.print("FSK Input: " + fsk + "\n");
        out.print("Search Text: " + searchText + "\n");
        out.print(QueryBuilder.defaultSearchQuery(searchText, date, time, Integer.parseInt(fsk)));


        //out.print(QueryBuilder.showSearchResults(searchText, date, time, Integer.parseInt(fsk)));
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.defaultSearchQuery(searchText, date, time, Integer.parseInt(fsk));
        ResultSet rs = Connector.getQueryResult(c, sql);

        try {
            if(rs != null) {
                while (rs.next()) {
                    out.print("Link: " + rs.getString("BildLink") + "\n");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        Connector.closeConnection(c);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}