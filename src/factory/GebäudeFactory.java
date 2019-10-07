package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import oo.Gebäude;

import java.sql.Connection;
import java.sql.ResultSet;

public class GebäudeFactory {
    public static Gebäude[] getGebäude() {
        Gebäude[] gebäude = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.showAllCinemas();
        ResultSet rs = Connector.getQueryResult(c, sql);

        if (rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if (rsSize > 0) {
                try {
                    int counter = 0;
                    while (rs.next()) {
                        gebäude[counter] = new Gebäude(
                                rs.getInt("GebäudeId"),
                                rs.getString("Straße"),
                                rs.getInt("Hausnummer"),
                                rs.getInt("PLZ"),
                                rs.getString("Ort.Ortsname")
                        );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return gebäude;
        } else {
            gebäude = new Gebäude[1];
            gebäude[0] = null;
            return gebäude;
        }
    }
}
