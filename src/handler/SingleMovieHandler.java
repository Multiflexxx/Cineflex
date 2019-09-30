package handler;

import db_connector.Connector;
import db_connector.QueryBuilder;
import factory.FilmFactory;
import factory.VorstellungsFactory;
import helper.DateFormatter;
import oo.DateFormatter;
import oo.Film;
import oo.Vorstellung;
//import org.hamcrest.Factory;

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
        Film film = null;
        Vorstellung vorstellung [] = null;
        film = FilmFactory.getFilm(Integer.parseInt(id));
        try {
            vorstellung = VorstellungsFactory.getVorstellungen(film, date, time, plz);

        } catch (Exception e) {
            out.write(plz);
            out.write("Geht nicht!");
        }
        if (vorstellung[0] == null) {
            out.write(date + time + plz + " " + Integer.parseInt(id));
            return;
        }
        film = vorstellung[0].getFilm();

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
            if(!vorstellung[0].equals(null)) {
                out.write("<div class=\"row no-gutters\">");
                out.write("<div class=\"col-lg-5\">");
                out.write("<a href=\"#\"><img src='" + film.getBildLink() +  "' class=\"card-img\" alt='" + film.getTitel() + "'></a>");
                out.write("<div class=\"table-responsive mt-2\">");
                out.write("<table class=\"table text-center\">");
                out.write("<tbody>");
                int counter = 1;
                for (int i = 0; i < vorstellung.length; i++) {
                    switch (counter) {
                        case 1:
                            counter++;
                            out.write("<tr>");
                            out.write("<td><button class=\"film-btn\" onclick=\"\">" + DateFormatter.getFrontendDate(vorstellung[i].getDatum()) + "<br>" + DateFormatter.getFrontendTime(vorstellung[i].getUhrzeit()) + " Uhr</button></td>");
                            break;
                        case 2:
                            counter++;
                            out.write("<td><button class=\"film-btn\" onclick=\"\">" + DateFormatter.getFrontendDate(vorstellung[i].getDatum()) + "<br>" + DateFormatter.getFrontendTime(vorstellung[i].getUhrzeit()) + " Uhr</button></td>");
                            break;
                        case 3:
                            counter = 1;
                            out.write("<td><button class=\"film-btn\" onclick=\"\">" + DateFormatter.getFrontendDate(vorstellung[i].getDatum()) + "<br>" + DateFormatter.getFrontendTime(vorstellung[i].getUhrzeit())  + " Uhr</button></td>");
                            out.write("</tr>");
                            break;
                    }
                }

                if (counter == 1) {
                    out.write("<tr>");
                    out.write("<td></td>");
                    out.write("<td></td>");
                    out.write("<td><button class=\"film-btn\" onclick=\"\">mehr</button></td>");
                    out.write("</tr>");
                } else if (counter == 2) {
                    out.write("<td></td>");
                    out.write("<td><button class=\"film-btn\" onclick=\"\">mehr</button></td>");
                    out.write("</tr>");
                } else if ( counter == 3) {
                    out.write("<td><button class=\"film-btn\" onclick=\"\">mehr</button></td>");
                    out.write("</tr>");
                }
                out.write("</tbody>");
                out.write("</table>");
                out.write("</div>");
                out.write("</div>");
                out.write("<div class=\"col-lg-7\">");
                out.write("<div class=\"card-body\">");
                out.write("<h5 class=\"card-title\">" + film.getTitel() + "</h5>");
                out.write("<p class=\"card-text\"><small class=\"text-muted\">" + film.getDauer() + " Minuten | FSK " + film.getFsk() + " | Sprache " + vorstellung[0].getSprache() + "</small></p>");
                out.write("<p class=\"card-text mrb-justify\">" + film.getBeschreibung() + "</p>");
                out.write("<iframe width=\"560\" height=\"315\" src=\"" + film.getTrailerLink() + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
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