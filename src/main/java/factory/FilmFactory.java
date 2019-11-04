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
    /** Returns all Filme that are result of a query composed of the input parameters
     * Used also for Mocks
     * @param search Search phrase, leave empty to match any movie title/description
     * @param date Date (greater or equal match)
     * @param time Time (greater or equal match)
     * @param fsk FKS age (lesser or equal)
     * @param plz PLZ of the cinema
     * @param mockRs1
     * @param mockRs2
     * @param mockRs3
     * @return Returns a Film Array containing all movies matching the input parameters
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

    /** Returns all Filme that are result of a query composed of the input parameters
     * Used also for Mocks
     * @param search Search phrase, leave empty to match any movie title/description
     * @param date Date (greater or equal match)
     * @param time Time (greater or equal match)
     * @param fsk FKS age (lesser or equal)
     * @param plz PLZ of the cinema
     * @return Returns a Film Array containing all movies matching the input parameters
     */
    public static Film[] getFilme(String search, String date, String time, int fsk, String plz) {
        return getFilme(search, date, time, fsk, plz, null, null, null);
    }

    /**
     * Returns all Filme that are result of a query composed of the input parameters
     * @param search Search phrase, leave empty to match any movie title/description
     * @param date Date (greater or equal match)
     * @param time Time (greater or equal match)
     * @param fsk FKS age (lesser or equal)
     * @param plz PLZ of the cinema
     * @param genreID ID of the Genre the movie should have
     * @param mockRs1 For tests
     * @param mockRs2 For tests
     * @param mockRs3 For tests
     * @return Returns a Film Array containing all movies matching the input parameters
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
     * Returns all Filme for a specified cinema (by PLZ)
     * @param plz Specified PLZ
     * @return Returns a Film Array containing all movies matching the input parameters
     */
  public static Film[] getFilme(String plz) {
      return getFilme("", DateFormatter.getSQLDate(new Date()), "08:00:00", 18, plz);
  }

    /**
     * Returns all Filme that are result of a query composed of the input parameters
     * @param search Search phrase, leave empty to match any movie title/description
     * @param date Date (greater or equal match)
     * @param time Time (greater or equal match)
     * @param fsk FKS age (lesser or equal)
     * @param plz PLZ of the cinema
     * @param genreID ID of the Genre the movie should have
     * @return Returns a Film Array containing all movies matching the input parameters
     */
  public static Film[] getFilme(String search, String date, String time, int fsk, String plz, int genreID) {
      return getFilme(search, date, time, fsk, plz, genreID, null, null, null);
  }

    /**
     * Returns Filme for the website's homepage. Returns (6) Filme running in the specified PLZ
     * @param plz PLZ of the specified cinema
     * @param mockRs1 For tests
     * @param mockRs2 For test
     * @param mockRs3 For test
     * @return Returns a Film Array containing all movies matching the input parameters
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
     * Returns Filme for the website's homepage. Returns (6) Filme running in the specified PLZ
     * @param plz PLZ of the specified cinema
     * @return Returns a Film Array containing all movies matching the input parameters
     */
    public static Film[] getTitelPageFilme(String plz)
    {
        return getTitelPageFilme(plz, null, null, null);
    }

    /**
     * Returns a single Film matching the specified MovieID
     * @param id ID of the specified movie
     * @param mockRs1 For tests
     * @param mockRs2 For tests
     * @param mockRs3 For tests
     * @return Returns a Film with the given ID
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
     * Returns a single Film matching the specified MovieID
     * @param id ID of the specified movie
     * @return Returns a Film with the given ID
     */
    public static Film getFilm(int id) {
        return getFilm(id, null, null, null);
    }

    /**
     * Sets the sprachen Attribute of a Film Object
     * @param film Film to have its languages set
     * @param mockRs For tests
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
     * Sets the genres Attribute of a Film Object
     * @param film Film to have its genres set
     * @param mockRs For tests
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

    /**
     *  Adds a Movie to the database
     * @param titel Titel of the movie
     * @param beschreibung Movie description
     * @param dauer length
     * @param FSK FSK
     * @param DDD ???
     * @param bildLink Path to the Cover image
     * @param trailerLink Embeded YouTube Trailer Link
     * @param Grundpreis Price
     */
    public static void filmHinzufuegen(String titel, String beschreibung, int dauer, int FSK, int DDD, String bildLink, String trailerLink, float Grundpreis) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.createFilm(titel,beschreibung,dauer,FSK,DDD,bildLink,trailerLink,Grundpreis);
        Connector.executeQuery(c, sql);
        Connector.closeConnection(c);
    }
}
