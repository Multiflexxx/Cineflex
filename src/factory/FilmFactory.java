package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import helper.DateFormatter;
import oo.Film;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmFactory {
    public static Film[] getFilme(String search, String date, String time, int fsk, String plz) {
        Film[] filme;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.defaultSearchQuery(search, date, time, fsk, plz);
        ResultSet rs = Connector.getQueryResult(c, sql);
        SupportMethods sup = new SupportMethods();

        if(rs != null) {
            int rsSize = sup.getResultSetSize(rs);
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
                    setGenres(f);
                    setSprachen(f);
                }
                Connector.closeConnection(c);
                return filme;
            } else {
                filme = new Film[1];
                filme[0] = null;
                Connector.closeConnection(c);
                return filme;
            }
        }
        Connector.closeConnection(c);
        return null;
    }

  public static Film[] getFilme(String search, String date, String time, int fsk, String plz, int genreID) {
    Film[] filme;
    Connection c = Connector.getConnection();
    String sql = QueryBuilder.genreSearchQuery(search, date, time, fsk, plz, genreID);
    ResultSet rs = Connector.getQueryResult(c, sql);
    SupportMethods sup = new SupportMethods();

    if(rs != null) {
      int rsSize = sup.getResultSetSize(rs);
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
          setGenres(f);
          setSprachen(f);
        }
        Connector.closeConnection(c);
        return filme;
      } else {
        filme = new Film[1];
        filme[0] = null;
        Connector.closeConnection(c);
        return filme;
      }
    }
    Connector.closeConnection(c);
    return null;
  }

  public static Film[] getFilme(String plz) {
        return getFilme("", DateFormatter.getSQLDate(new Date()), "08:00:00", 18, plz);
    }

    public static Film[] getTitelPageFilme(String plz) {
        Film[] filme;
        int rsSize;

        Connection c = Connector.getConnection();
        String sql = QueryBuilder.showTitelPageFilmsbyPLZ(plz);
        ResultSet rs = Connector.getQueryResult(c, sql);
        SupportMethods sup = new SupportMethods();

        if(rs != null) {
            rsSize = sup.getResultSetSize(rs);
            if(rsSize > 0) {
                filme = new Film[rsSize];
                try {
                    int counter = 0;
                    while (rs.next()) {
                        filme[counter] = new Film(
                                rs.getString("Titel"),
                                rs.getString("Beschreibung"),
                                rs.getString("BildLink"),
                                rs.getString("TrailerLink"),
                                rs.getInt("Dauer"),
                                rs.getInt("FSK"),
                                rs.getInt("Film.FilmID"),
                                rs.getBoolean("3D"));

                        counter++;
                    }
                }catch(SQLException e) {
                    e.printStackTrace();
                }
                for(Film f : filme) {
                    setGenres(f);
                    setSprachen(f);
                }
            } else {
                filme = new Film[1];
                filme[0] = null;
            }
            Connector.closeConnection(c);
            return filme;
        }
        Connector.closeConnection(c);
        return null;
    }



    public static Film getFilm(int id) {
        Film film = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getMovieById(id);
        ResultSet rs = Connector.getQueryResult(c, sql);
        // Connector.closeConnection(c);
        SupportMethods sup = new SupportMethods();

        if(rs != null) {
            int rsSize = sup.getResultSetSize(rs);
            if(rsSize > 0 && rsSize == 1) {
                try {
                    while (rs.next()) {
                        film = new Film(rs.getString("Titel"),
                                rs.getString("Beschreibung"),
                                rs.getString("BildLink"),
                                rs.getString("TrailerLink"),
                                rs.getInt("Dauer"),
                                rs.getInt("FSK"),
                                rs.getInt("Film.FilmID"),
                                rs.getBoolean("3D"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            setSprachen(film);
            setGenres(film);
        }
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
        return film;
    }

    private static void setSprachen(Film film) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getGenreNamesById(film.getFilmID());
        ResultSet rs = Connector.getQueryResult(c, sql);
        // Connector.closeConnection(c);
        SupportMethods sup = new SupportMethods();
        try {
            int rsSize = sup.getResultSetSize(rs);
            if(rsSize > 0) {
                int counter = 0;
                String[] genres = null;
                genres = new String[rsSize];
                while(rs.next()) {
                    genres[counter] = rs.getString("Genrebezeichnung");
                    counter++;
                }
                film.setGenre(genres);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
    }

    private static void setGenres(Film film) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getSpracheById(film.getFilmID());
        ResultSet rs = Connector.getQueryResult(c, sql);
        SupportMethods sup = new SupportMethods();

        try {
            int rsSize = sup.getResultSetSize(rs);
            if(rsSize > 0) {
                int counter = 0;
                String[] sprachen = null;
                sprachen = new String[rsSize];
                while(rs.next()) {
                    sprachen[counter] = rs.getString("Sprachenname");
                    counter++;
                }
                film.setSprache(sprachen);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
    }
}
