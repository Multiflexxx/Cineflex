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
import java.util.ArrayList;

@WebServlet(name = "SingleMovieHandler")
public class SingleMovieHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        String time =  request.getParameter("time");
        // Get PLZ
        String plz = "00000";
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies) {
            if(c.getName().equals("plz")) {
                plz = c.getValue();
            }
        }

        PrintWriter out = response.getWriter();
        String sql = QueryBuilder.showMovieById(id, date, time, plz);
        Connection c = Connector.getConnection();
        ResultSet rs = Connector.getQueryResult(c, sql);

        ArrayList <String> titel= new ArrayList<>();


        try {
            if(rs != null) {

                while (rs.next()) {
                    titel.add(rs.getString("Titel");
                }

                response.setContentType("text/html;charset=UTF-8");
                out.write("<html>");
                request.getRequestDispatcher("elements/head.jsp").include(request, response);
                out.write("<body>");
                request.getRequestDispatcher("elements/header.jsp").include(request, response);
                request.getRequestDispatcher("login.jsp").include(request, response);
                request.getRequestDispatcher("registration.jsp").include(request, response);
                request.getRequestDispatcher("filter.jsp").include(request, response);
                out.write("<div class=\"container\">");
                out.write("<div class=\"card mbg-dark text-white\" style=\"max-width: 1400px;\">");
                out.write("<div class=\"row no-gutters\">");
                out.write("<div class=\"col-lg-5\">");
             //   out.write("<a href=\"#\"><img src='" + rs.getString("BildLink") +  "' class=\"card-img\" alt='" + rs.getString("Titel") + "'></a>");
                //out.write("<h6>" + rs.getString("Datum") + "</h6>");
                out.write("<table class=\"table text-center\" id=\"endgame\">");
                out.write("<tbody>");

//                int counter = 1;
//
//                while (rs.next()) {
////                    out.write("<div class=\"card mbg-dark text-white\" style=\"max-width: 1400px;\">");
////                    out.write("<div class=\"row text-center\" style=\"max-width: 1400px;\">");
////                    out.write("<div class=\"col\">");
////                    out.write("<a href=\"#\"><img src='" + rs.getString("BildLink") +  "' class=\"card-img\" alt='" + rs.getString("Titel") + "'></a>");
////                    out.write("<br>");
////                    out.write("<br>");
////                    out.write("<div class=\"col text-left\">");
////                    out.write("<h6>" + rs.getString("Datum") + "</h6>");
////                    //out.write(""); //Table hier
////                    out.write("</div>");
////                    out.write("</div>");
////                    out.write("<div class=\"col text-left\">");
////                    //out.write("<div class=\"col text-left\">");
////                    out.write("<div class=\"card-body\">");
////                    out.write("<h5 class=\"card-title\">" + rs.getString("Titel") + "</h5>");
////                    out.write("<p class=\"card-text\"><small class=\"text-muted\">\"" + rs.getString("Dauer") + " Minuten | FSK " + rs.getString("FSK") + " | Sprache " + rs.getString("Sprache.Sprachenname") + "</small></p>");
////                    out.write("</div>");
////                    out.write("<p class=\"card-text\">" + rs.getString("Beschreibung") + "</p>");
////                    out.write("<br>");
////                    out.write("<iframe width=\"560\" height=\"315\" src=\"" + rs.getString("TrailerLink") + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
////                    out.write("</div>");
////                    out.write("</div>");
////                    out.write("</div>");
//                    if (counter == 1) {
//                        out.write("<tr>");
//                        out.write("<td><button></button></td>");
//                        counter++;
//
//                    } else if (counter == 2) {
//                        out.write("<td><button></button></td>");
//                        counter++;
//                    } else if (counter == 3) {
//                        out.write("<td><button>"+ rs.getString("Datum") + " " + rs.getString("Uhrzeit") + "</button></td>");
//                        out.write("</tr>");
//                        counter = 1;
//                    }
//                    //Table
//                }
                out.write("</tbody>");
                out.write("</table>");
                out.write("</div>");
                out.write("<div class=\"col-lg-7\">");
                out.write("<div class=\"card-body\">");
                out.write("<h5 class=\"card-title\">" + rs.getString("Titel") + "</h5>");
                out.write("<p class=\"card-text\"><small class=\"text-muted\">" + rs.getString("Dauer") + " Minuten | FSK " + rs.getString("FSK") + " | Sprache " + rs.getString("Sprache.Sprachenname") + "</small></p>");
                out.write("<p class=\"card-text mrb-justify\">" + rs.getString("Film.Beschreibung") + "</p>");
                out.write("<iframe width=\"560\" height=\"315\" src=\"" + rs.getString("TrailerLink") + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
                out.write("</div>");
                out.write("</div>");
                out.write("</div>");
                out.write("</div>");
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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
