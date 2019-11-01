package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import helper.DateFormatter;
import oo.Film;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmFactory {
    /**
     * Used also for Mocks
     * @param search
     * @param date
     * @param time
     * @param fsk
     * @param plz
     * @param mockRs1
     * @param mockRs2
     * @param mockRs3
     * @return
     */
    public static Film[] getFilme(String search, String date, String time, int fsk, String plz, ResultSet mockRs1, ResultSet mockRs2, ResultSet mockRs3) {
        search = SupportMethods.removeHTMLCode(search);
        search = SupportMethods.removeSQLInjections(search);
        date = SupportMethods.removeHTMLCode(date);
        date = SupportMethods.removeSQLInjections(date);
        time = SupportMethods.removeHTMLCode(time);
        time = SupportMethods.removeSQLInjections(time);
        plz = SupportMethods.removeHTMLCode(plz);
        plz = SupportMethods.removeSQLInjections(plz);

        Film[] filme;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.defaultSearchQuery(search, date, time, fsk, plz);

        ResultSet rs = null;

        if(mockRs1 == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs1;
        }

        if(rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if(rsSize > 0) {
                filme = new Film[rsSize];
                try {
                    for (int i = 0; i < rsSize; i++){
                        rs.next();
                        // Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung
                        //(String titel, String beschreibung, String bildLink, String trailerLink, int dauer, int fsk, int filmID, boolean dreiD)
                        filme[i] = new Film(rs.getString("Titel"),
                                            rs.getString("Beschreibung"),
                                            rs.getString("BildLink"),
                                            rs.getString("TrailerLink"),
                                            rs.getInt("Dauer"),
                                            rs.getInt("FSK"),
                                            rs.getInt("Vorstellung.FilmID"),
                                            rs.getBoolean("3D"));
                    }
                }catch(SQLException e) {
                    e.printStackTrace();
                }
                for(Film f : filme) {
                    setGenres(f, mockRs2);
                    setSprachen(f, mockRs3);
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

    /**
     *
     * @param search
     * @param date
     * @param time
     * @param fsk
     * @param plz
     * @return Film[]
     */
    public static Film[] getFilme(String search, String date, String time, int fsk, String plz) {
        return getFilme(search, date, time, fsk, plz, null, null, null);
    }

    /**
     *
     * @param search
     * @param date
     * @param time
     * @param fsk
     * @param plz
     * @param genreID
     * @param mockRs1
     * @param mockRs2
     * @param mockRs3
     * @return Film[]
     */
    public static Film[] getFilme(String search, String date, String time, int fsk, String plz, int genreID, ResultSet mockRs1, ResultSet mockRs2, ResultSet mockRs3) {
        Film[] filme;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.genreSearchQuery(search, date, time, fsk, plz, genreID);

        ResultSet rs = null;

        if(mockRs1 == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs1;
        }

        if(rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if(rsSize > 0) {
                filme = new Film[rsSize];
                try {
                    for (int i = 0; i < rsSize; i++){
                        rs.next();
                        // Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung
                        //(String titel, String beschreibung, String bildLink, String trailerLink, int dauer, int fsk, int filmID, boolean dreiD)
                        filme[i] = new Film(rs.getString("Titel"),
                            rs.getString("Beschreibung"),
                            rs.getString("BildLink"),
                            rs.getString("TrailerLink"),
                            rs.getInt("Dauer"),
                            rs.getInt("FSK"),
                            rs.getInt("Vorstellung.FilmID"),
                            rs.getBoolean("3D"));
                    }
                }catch(SQLException e) {
                    e.printStackTrace();
                }
                for(Film f : filme) {
                    setGenres(f, mockRs2);
                    setSprachen(f, mockRs3);
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

    /**
     *
     * @param plz
     * @return Film[]
     */
  public static Film[] getFilme(String plz) {
      return getFilme("", DateFormatter.getSQLDate(new Date()), "08:00:00", 18, plz);
  }

    /**
     *
     * @param search
     * @param date
     * @param time
     * @param fsk
     * @param plz
     * @param genreID
     * @return Film[]
     */
  public static Film[] getFilme(String search, String date, String time, int fsk, String plz, int genreID) {
      return getFilme(search, date, time, fsk, plz, genreID, null, null, null);
  }

    /**
     *
     * @param plz
     * @param mockRs1
     * @param mockRs2
     * @param mockRs3
     * @return Film[]
     */
  public static Film[] getTitelPageFilme(String plz, ResultSet mockRs1, ResultSet mockRs2, ResultSet mockRs3) {
        Film[] filme;
        int rsSize;

        Connection c = Connector.getConnection();
        String sql = QueryBuilder.showTitelPageFilmsbyPLZ(plz);

        ResultSet rs = null;

        if(mockRs1 == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs1;
        }

        if(rs != null) {
            rsSize = SupportMethods.getResultSetSize(rs);
            if(rsSize > 0) {
                filme = new Film[rsSize];
                try {
                    for (int i = 0; i < rsSize; i++){
                        rs.next();
                        filme[i] = new Film(
                                rs.getString("Titel"),
                                rs.getString("Beschreibung"),
                                rs.getString("BildLink"),
                                rs.getString("TrailerLink"),
                                rs.getInt("Dauer"),
                                rs.getInt("FSK"),
                                rs.getInt("Film.FilmID"),
                                rs.getBoolean("3D"));
                    }
                }catch(SQLException e) {
                    e.printStackTrace();
                }
                for(Film f : filme) {
                    setGenres(f, mockRs2);
                    setSprachen(f, mockRs3);
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

    /**
     *
     * @param plz
     * @return Film[]
     */
    public static Film[] getTitelPageFilme(String plz)
    {
        return getTitelPageFilme(plz, null, null, null);
    }

    /**
     *
     * @param id
     * @param mockRs1
     * @param mockRs2
     * @param mockRs3
     * @return Film
     */
    public static Film getFilm(int id, ResultSet mockRs1, ResultSet mockRs2, ResultSet mockRs3) {
        Film film = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getMovieById(id);

        ResultSet rs = null;

        if(mockRs1 == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs1;
        }

        if(rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if(rsSize > 0 && rsSize == 1) {
                try {
                    rs.next();
                        film = new Film(rs.getString("Titel"),
                                rs.getString("Beschreibung"),
                                rs.getString("BildLink"),
                                rs.getString("TrailerLink"),
                                rs.getInt("Dauer"),
                                rs.getInt("FSK"),
                                rs.getInt("Film.FilmID"),
                                rs.getBoolean("3D"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            setSprachen(film, mockRs2);
            setGenres(film, mockRs3);
        }
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
        return film;
    }

    /**
     *
     * @param id
     * @return Film
     */
    public static Film getFilm(int id) {
        return getFilm(id, null, null, null);
    }

    /**
     *
     * @param film
     * @param mockRs
     */
    private static void setSprachen(Film film, ResultSet mockRs) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getGenreNamesById(film.getFilmID());

        ResultSet rs = null;

        if(mockRs == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs;
        }

        try {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if(rsSize > 0) {
                String[] genres = null;
                genres = new String[rsSize];
                for (int i = 0; i < rsSize; i++){
                    rs.next();
                    genres[i] = rs.getString("Genrebezeichnung");
                }
                film.setGenre(genres);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
    }

    /**
     *
     * @param film
     * @param mockRs
     */
    private static void setGenres(Film film, ResultSet mockRs) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getSpracheById(film.getFilmID());

        ResultSet rs = null;

        if(mockRs == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs;
        }

        try {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if(rsSize > 0) {
                String[] sprachen = null;
                sprachen = new String[rsSize];
                for (int i = 0; i < rsSize; i++){
                    rs.next();
                    sprachen[i] = rs.getString("Sprachenname");
                }
                film.setSprache(sprachen);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
    }
    public static void filmHinzufuegen(String titel, String beschreibung, int dauer, int FSK, int DDD, String bildLink, String trailerLink, float Grundpreis) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.createFilm(titel,beschreibung,dauer,FSK,DDD,bildLink,trailerLink,Grundpreis);
        Connector.executeQuery(c, sql);
        Connector.closeConnection(c);
    }
}
