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

        try {
            response.setContentType("text/html;charset=UTF-8");
            out.write("<html>");
            request.getRequestDispatcher("elements/head.jsp").include(request, response);
            out.write("<body class=\"d-flex flex-column h-100\">");
            request.getRequestDispatcher("elements/header.jsp").include(request, response);
            request.getRequestDispatcher("login.jsp").include(request, response);
            request.getRequestDispatcher("registration.jsp").include(request, response);
            request.getRequestDispatcher("filter.jsp").include(request, response);
            out.write("<div class=\"container\">");
            out.write("<div class=\"col-xl-12\">");
            out.write("<div class=\"card mt-3 mb-3 text-white w-100\">");
            if(rs.next() != false) {
                rs.beforeFirst();
                ArrayList titel = new ArrayList();
                ArrayList beschreibung = new ArrayList();
                ArrayList bild = new ArrayList();
                ArrayList trailer = new ArrayList();
                ArrayList dauer = new ArrayList();
                ArrayList fsk = new ArrayList();
                ArrayList sprache = new ArrayList();
                ArrayList datum = new ArrayList();
                ArrayList uhrzeit = new ArrayList();
                while (rs.next()) {
                    titel.add(rs.getString("Titel"));
                    beschreibung.add(rs.getString("Film.Beschreibung"));
                    bild.add(rs.getString("BildLink"));
                    trailer.add(rs.getString("TrailerLink"));
                    dauer.add(rs.getString("Dauer"));
                    fsk.add(rs.getString("FSK"));
                    sprache.add(rs.getString("Sprache.Sprachenname"));
                    datum.add(rs.getString("Datum"));
                    uhrzeit.add(rs.getString("Uhrzeit"));

                }
                out.write("<div class=\"row no-gutters\">");
                out.write("<div class=\"col-lg-5\">");
                out.write("<a href=\"#\"><img src='" + bild.get(0).toString() +  "' class=\"card-img\" alt='" + titel.get(0).toString() + "'></a>");
                //out.write("<h6>" + datum.get(0).toString() + "</h6>");
                //out.write(uhrzeit.get(0).toString());
                out.write("<div class=\"table-responsive mt-2\">");
                out.write("<table class=\"table text-center\">");
                out.write("<tbody>");
                int counter = 1;
                for (int i = 0; i < datum.size(); i++) {
                    switch (counter) {
                        case 1:
                            counter++;
                            out.write("<tr>");
                            out.write("<td><button class=\"film-btn\" onclick=\"\" style=\"vertical-align:middle\">" + datum.get(i).toString() + " " + uhrzeit.get(i).toString() + counter + " Uhr</button></td>");
                            break;
                        case 2:
                            counter++;
                            out.write("<td><button class=\"film-btn\" onclick=\"\">" + datum.get(i).toString() + " " + uhrzeit.get(i).toString() + counter + " Uhr</button></td>");
                            break;
                        case 3:
                            counter = 1;
                            out.write("<td><button class=\"film-btn\" onclick=\"\">" + datum.get(i).toString() + " " + uhrzeit.get(i).toString()  + counter+ " Uhr</button></td>");
                            out.write("</tr>");
                            break;
                    }
                }
                if (counter == 2) {
                    out.write("<td></td>");
                    out.write("<td></td>");
                    out.write("</tr>");
                } else if ( counter == 3) {
                    out.write("<td></td>");
                    out.write("</tr>");
                }
                out.write("</tbody>");
                out.write("</table>");
                out.write("</div>");
                out.write("</div>");
                out.write("<div class=\"col-lg-7\">");
                out.write("<div class=\"card-body\">");
                out.write("<h5 class=\"card-title\">" + titel.get(0).toString() + "</h5>");
                out.write("<p class=\"card-text\"><small class=\"text-muted\">" + dauer.get(0).toString() + " Minuten | FSK " + fsk.get(0).toString() + " | Sprache " + sprache.get(0).toString() + "</small></p>");
                out.write("<p class=\"card-text mrb-justify\">" + beschreibung.get(0).toString() + "</p>");
                out.write("<iframe width=\"560\" height=\"315\" src=\"" + trailer.get(0).toString() + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
                out.write("</div>");
                out.write("</div>");
                out.write("</div>");
            } else {
                out.write("<p class=\"card-text\">Vergewissere dich, dass du ein Kino selektiert hast! ANdernfalls ändere bitte deine Werte bei deiner  Suche!</p>");
            }
            out.write("</div>");
            out.write("</div>");
            out.write("</div>");
            request.getRequestDispatcher("elements/footer.jsp").include(request, response);
            out.write("</body>");
            out.write("</html>");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}