package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import oo.Film;
import oo.Kinosaal;
import oo.Vorstellung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VorstellungsFactory {
    public static Vorstellung[] getVorstellungen(Film film, String date, String time, String plz) {
        Vorstellung[] vorstellungen = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.showMovieById("" + film.getFilmID(), date, time, plz);
        ResultSet rs = Connector.getQueryResult(c, sql);

        if(rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if(rsSize > 0) {
                try {
                    int counter = 0;
                    while (rs.next()) {
                        // `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, Sprache.Sprachenname
                        vorstellungen[counter] = new Vorstellung(rs.getInt("VorstellungsID"),
                                                                    rs.getString("Datum"),
                                                                    rs.getString("Uhrzeit"),
                                                                    rs.getString("Sprache.Sprachenname"),
                                                                    film,
                                                                    KinosaalFactory.getKinosaal(rs.getInt("SaalID")));
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
}
