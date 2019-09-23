package handler;
import db_connector.Connector;
import db_connector.QueryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;

@WebServlet(name = "SearchHandler")
public class SearchHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("inputDate");
        String time = request.getParameter("inputTime");
        String fsk = request.getParameter("inputFSK");
        String searchText = request.getParameter("inputSearchText");
        time += ":00";

        PrintWriter out = response.getWriter();
        //BufferedReader br = new BufferedReader(new FileReader("V1/elements/head.jsp"));


        Connection c = Connector.getConnection();
        String sql = QueryBuilder.defaultSearchQuery(searchText, date, time, Integer.parseInt(fsk));
        ResultSet rs = Connector.getQueryResult(c, sql);

        try {
            if(rs != null) {
                response.setContentType("text/html;charset=UTF-8");
                out.write("<html>");
                request.getRequestDispatcher("elements/head.jsp").include(request, response);
                out.write("<body>");
                request.getRequestDispatcher("elements/header.jsp").include(request, response);
                request.getRequestDispatcher("login.jsp").include(request, response);
                request.getRequestDispatcher("registration.jsp").include(request, response);
                request.getRequestDispatcher("filter.jsp").include(request, response);
                out.write("<div class=\"container\">");
                while (rs.next()) {
                    String hrefURL = "SingleMovieHandler?";
                    hrefURL += "id=" + rs.getString("FilmID");
                    hrefURL += "&date=" + date;
                    hrefURL += "&time=" + time;
                    out.write("<div class=\"card mbg-dark text-white\" style=\"max-width: 1400px;\">");
                    out.write("<div class=\"row no-gutters\">");
                    out.write("<div class=\"col-lg-5\">");
                    out.write("<img src='" + rs.getString("BildLink") +  "' class=\"card-img\" alt='" + rs.getString("Titel") + "'>");
                    out.write("</div>");
                    out.write("<div class=\"col-lg-7\">");
                    out.write("<div class=\"card-body\">");
                    out.write("<h5 class=\"card-title\">" + rs.getString("Titel") + "</h5>");
                    out.write("<p class=\"card-text\"><small class=\"text-muted\">" + rs.getString("Dauer") + " min | FSK " + rs.getString("FSK") + "</small></p>");
                    out.write("<p class=\"card-text mrb-justify\">" + rs.getString("Film.Beschreibung") + "</p>");
                    out.write("<a href=\"" + hrefURL + "\" class=\"btn btn-primary\">Zum Film</a>");
                    out.write("</div>");
                    out.write("</div>");
                    out.write("</div>");
                    out.write("</div>");
                }
                out.write("</div>");
                request.getRequestDispatcher("elements/footer.jsp").include(request, response);
                out.write("</body>");
                out.write("</html>");
            } else {
                out.println(sql);
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