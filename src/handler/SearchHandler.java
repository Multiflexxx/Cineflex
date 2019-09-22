package handler;
import db_connector.QueryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SearchHandler")
public class SearchHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String fsk = request.getParameter("fsk");
        String searchText = request.getParameter("searchText");
        time += ":00";
        PrintWriter out = response.getWriter();
        out.print(request.getRequestURL() + "\n");
        out.print(request.getRequestURI() + "\n");
        out.print("Date Input: " + date + "\n");
        out.print("Time Input: " + time + "\n");
        out.print("FSK Input: " + fsk + "\n");
        out.print("Search Text: " + searchText + "\n");


        out.print(QueryBuilder.showSearchResults(searchText, date, time, Integer.parseInt(fsk)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}