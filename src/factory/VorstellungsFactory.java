package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import oo.Film;
import oo.Kinosaal;
import oo.Vorstellung;

import javax.management.Query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class VorstellungsFactory {
    private static Film sfilm = null;
    private static String sdate = "";
    private static String stime = "";
    private static String splz = "";

    public static Vorstellung[] getVorstellungen(Film film, String date, String time, String plz) {

        sfilm = film;
        sdate = date;
        stime = time;
        splz = plz;

        Vorstellung[] vorstellungen = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.showMovieById("" + film.getFilmID(), date, time, plz);
        ResultSet rs = Connector.getQueryResult(c, sql);


        if(rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            vorstellungen = new Vorstellung[rsSize];

            if(rsSize > 0) {
                try {
                    int counter = 0;
                    while (rs.next()) {
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
                            return null;
                        }

                        vorstellungen[counter] = new Vorstellung(rs.getInt("VorstellungsID"),
                                                                    date_Datum,
                                                                    time_Uhrzeit,
                                                                    rs.getString("Sprache.Sprachenname"),
                                                                    film,
                                                                    KinosaalFactory.getKinosaal(rs.getInt("SaalID")));
                        counter++;
                    }
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            } else {
                vorstellungen = new Vorstellung[1];
                vorstellungen[0] = null;
                return vorstellungen;
            }

            return vorstellungen;
        }


        return null;
    }

    public static String getLastSQLQuery() {
        return QueryBuilder.showMovieById("" + sfilm.getFilmID(), sdate, stime, splz);
    }
}
