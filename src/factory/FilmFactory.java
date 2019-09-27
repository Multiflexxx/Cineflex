package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import oo.DateFormatter;
import oo.Film;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmFactory {
    public static Film[] getFilme(String search, String date, String time, int fsk) {
        Film[] filme;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.defaultSearchQuery(search, date, time, fsk);
        ResultSet rs = Connector.getQueryResult(c, sql);

        if(rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if(rsSize > 0) {
                filme = new Film[rsSize];
                try {
                    int counter = 0;
                    while (rs.next()) {
                        // Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung
                        //(String titel, String beschreibung, String bildLink, String trailerLink, int dauer, int fsk, int filmID, boolean dreiD)
                        filme[counter] = new Film(rs.getString("Titel"),
                                            rs.getString("Beschreibung"),
                                            rs.getString("BildLink"),
                                            rs.getString("TrailerLink"),
                                            rs.getInt("Dauer"),
                                            rs.getInt("FSK"),
                                            rs.getInt("Vorstellung.FilmID"),
                                            rs.getBoolean("3D"));

                        counter++;
                    }
                }catch(SQLException e) {
                    e.printStackTrace();
                }
                for(Film f : filme) {
                    sql = QueryBuilder.getGenreNamesById(f.getFilmID());
                    rs = Connector.getQueryResult(c, sql);
                    try {
                        rsSize = SupportMethods.getResultSetSize(rs);
                        if(rsSize > 0) {
                            int counter = 0;
                            String[] genres = null;
                            while(rs.next()) {
                                genres = new String[rsSize];
                                genres[counter] = rs.getString("Genrebezeichnung");
                                counter++;
                            }
                            f.setGenre(genres);
                        }
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }

                    sql = QueryBuilder.getSpracheById(f.getFilmID());
                    rs = Connector.getQueryResult(c, sql);

                    try {
                        rsSize = SupportMethods.getResultSetSize(rs);
                        if(rsSize > 0) {
                            int counter = 0;
                            String[] sprachen = null;
                            while(rs.next()) {
                                sprachen = new String[rsSize];
                                sprachen[counter] = rs.getString("Sprachenname");
                                counter++;
                            }
                            f.setSprache(sprachen);
                        }
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return filme;
            } else {
                filme = new Film[1];
                filme[0] = null;
                return filme;
            }
        }
        return null;
    }

    public static Film[] getFilme() {
        return getFilme("", DateFormatter.getSQLDate(new Date()), "08:00:00", 18);
    }

    public static Film getFilm(int id) {
        Film film = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getMovieById(id);<
        ResultSet rs = Connector.getQueryResult(c, sql);

        if(rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if(rsSize > 0 && rsSize == 1) {
                try {
                    while (rs.next()) {
                        film = new Film(rs.getString("Titel"),
                                rs.getString("Beschreibung"),
                                rs.getString("BildLink"),
                                rs.getString("TrailerLink"),
                                rs.getInt("Dauer"),
                                rs.getInt("FSK"),
                                rs.getInt("FilmID"),
                                rs.getBoolean("3D"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            sql = QueryBuilder.getGenreNamesById(film.getFilmID());
            rs = Connector.getQueryResult(c, sql);
            try {
                rsSize = SupportMethods.getResultSetSize(rs);
                if(rsSize > 0) {
                    int counter = 0;
                    String[] genres = null;
                    while(rs.next()) {
                        genres = new String[rsSize];
                        genres[counter] = rs.getString("Genrebezeichnung");
                        counter++;
                    }
                    film.setGenre(genres);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

            sql = QueryBuilder.getSpracheById(film.getFilmID());
            rs = Connector.getQueryResult(c, sql);

            try {
                rsSize = SupportMethods.getResultSetSize(rs);
                if(rsSize > 0) {
                    int counter = 0;
                    String[] sprachen = null;
                    while(rs.next()) {
                        sprachen = new String[rsSize];
                        sprachen[counter] = rs.getString("Sprachenname");
                        counter++;
                    }
                    film.setSprache(sprachen);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return film;
    }

//    public static Film[] getFilme() {
//        return null;
//    }
}
