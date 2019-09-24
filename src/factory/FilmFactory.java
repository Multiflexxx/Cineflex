package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import oo.Film;

import java.sql.Connection;
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
                }



                return filme;
            }
        }
        return null;
    }

//    public static Film[] getFilme() {
//        return null;
//    }
}
