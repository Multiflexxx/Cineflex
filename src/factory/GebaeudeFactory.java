package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import oo.Gebaeude;

import java.sql.Connection;
import java.sql.ResultSet;

public class GebaeudeFactory {
    public static Gebaeude[] getGebaeude() {
        Gebaeude[] gebäude = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.showAllCinemas();
        ResultSet rs = Connector.getQueryResult(c, sql);

        if (rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            gebäude = new Gebaeude[rsSize];
            if (rsSize > 0) {
                try {
                    int counter = 0;
                    gebäude = new Gebaeude[rsSize];
                    while (rs.next()) {
                        gebäude[counter] = new Gebaeude(
                                rs.getInt("GebäudeId"),
                                rs.getString("Straße"),
                                rs.getInt("Hausnummer"),
                                rs.getInt("PLZ"),
                                rs.getString("Ort.Ortsname")
                        );
                        counter++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return gebäude;
        } else {
            gebäude = new Gebaeude[1];
            gebäude[0] = new Gebaeude(-1, "Egal", 0, 0, "RS is Null");
            return gebäude;
        }
    }
}
