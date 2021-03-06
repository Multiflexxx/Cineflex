package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import oo.Film;
import oo.Vorstellung;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VorstellungsFactory {
    private static Film sfilm = null;
    private static String sdate = "";
    private static String stime = "";
    private static String splz = "";

    /**
     *
     * @param film
     * @param date
     * @param time
     * @param plz
     * @return vorstellungen
     */
    public static Vorstellung[] getVorstellungen(Film film, String date, String time, String plz, ResultSet mockRs) {

        sfilm = film;
        sdate = date;
        stime = time;
        splz = plz;

        Vorstellung[] vorstellungen = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.showMovieById("" + film.getFilmID(), date, time, plz);

        ResultSet rs = null;

        if(mockRs == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs;
        }

        if(rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            vorstellungen = new Vorstellung[rsSize];

            if(rsSize > 0) {
                try {
                    for (int i = 0; i < rsSize; i++){
                        rs.next();
                        // `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, Sprache.Sprachenname
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date_Datum = null;
                        try {
                            date_Datum = dateFormatter.parse(rs.getString("Datum"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
                        Date time_Uhrzeit = null;
                        try {
                            time_Uhrzeit = timeFormatter.parse(rs.getString("Uhrzeit"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if(date_Datum == null || time_Uhrzeit == null) {
                            Connector.closeResultSet(rs);
                            Connector.closeConnection(c);
                            return null;
                        }

                        vorstellungen[i] = new Vorstellung(rs.getInt("VorstellungsID"),
                                                                    date_Datum,
                                                                    time_Uhrzeit,
                                                                    rs.getString("Sprache.Sprachenname"),
                                                                    film,
                                                                    KinosaalFactory.getKinosaal(rs.getInt("Kinosaal.SaalID")));
                    }
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            } else {
                vorstellungen = new Vorstellung[1];
                vorstellungen[0] = null;
                Connector.closeResultSet(rs);
                Connector.closeConnection(c);
                return vorstellungen;
            }
            Connector.closeResultSet(rs);
            Connector.closeConnection(c);
            return vorstellungen;
        }
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
        return null;
    }

    public static Vorstellung[] getVorstellungen(Film film, String date, String time, String plz)
    {
        return getVorstellungen(film, date, time, plz, null);
    }

    /**
     *
     * @param id
     * @return vorstellung
     */
    public static Vorstellung getVorstellungById(int id,ResultSet mockRs) {
        Vorstellung vorstellung = null;

        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getVorstellungByID(id);

        ResultSet rs = null;

        if(mockRs == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs;
        }

        if(rs!=null) {

                try {
                        rs.next();
                        //VorstellungsID, Datum, Uhrzeit, FilmID, SaalID, SprachID, Titel, Beschreibung, Dauer, FSK, 3D, BildLink, TrailerLink, Grundpreis, Sprachenname, GebäudeID,  Saalbezeichnung
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date_Datum = null;
                        try {
                            date_Datum = dateFormatter.parse(rs.getString("Datum"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
                        Date time_Uhrzeit = null;
                        try {
                            time_Uhrzeit = timeFormatter.parse(rs.getString("Uhrzeit"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        vorstellung = new Vorstellung(rs.getInt("VorstellungsID"),
                                date_Datum,
                                time_Uhrzeit,
                                rs.getString("Sprachenname"),
                                FilmFactory.getFilm(rs.getInt("FilmID")),
                                KinosaalFactory.getKinosaal(rs.getInt("SaalID")));
                }catch(SQLException e) {
                    e.printStackTrace();
                }
                Connector.closeResultSet(rs);
                Connector.closeConnection(c);
            return vorstellung;
        }
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);

      return vorstellung;
    }

    public static Vorstellung getVorstellungById(int id)
    {
        return getVorstellungById(id, null);
    }

    /**
     *
     * @return String
     */
    public static String getLastSQLQuery() {
        return QueryBuilder.showMovieById("" + sfilm.getFilmID(), sdate, stime, splz);
    }
}
