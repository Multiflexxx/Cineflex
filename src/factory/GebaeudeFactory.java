package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import oo.Gebaeude;

import java.sql.Connection;
import java.sql.ResultSet;

public class GebaeudeFactory {
    public static Gebaeude[] getGebaeude(ResultSet mockRs) {
        Gebaeude[] gebäude = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.showAllCinemas();
        ResultSet rs = null;

        if(mockRs == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs;
        }

        if (rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            /*try {
                rs.beforeFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            if (rsSize > 0) {
                //int counter = 0;
                gebäude = new Gebaeude[rsSize];
                try {
                    //while (rs.next()) {
                    for(int i = 0; i < rsSize; i++){
                        rs.next();
                        int gebID = rs.getInt("GebäudeId");
                        String strasse = rs.getString("Straße");
                        int hausnummer = rs.getInt("Hausnummer");
                        int plz = rs.getInt("PLZ");
                        String ort = rs.getString("Ort.Ortsname");

                        gebäude[i] = new Gebaeude(gebID, strasse, hausnummer, plz, ort);

                        //counter++;
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

    public static Gebaeude[] getGebaeude()
    {
        return getGebaeude(null);
    }
}
