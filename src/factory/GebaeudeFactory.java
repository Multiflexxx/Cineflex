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
            try {
                rs.beforeFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (rsSize > 0) {
                int counter = 0;
                gebäude = new Gebaeude[rsSize];
                try {
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
            Connector.closeResultSet(rs);
            Connector.closeConnection(c);
            return gebäude;
        } else {
            Connector.closeResultSet(rs);
            Connector.closeConnection(c);
            return gebäude;
        }
    }
}
