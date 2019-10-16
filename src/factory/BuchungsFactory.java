package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import oo.Buchungsbeleg;
import oo.Sitz;
import oo.Vorstellung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuchungsFactory {
    public static void createBuchungBeleg(int[] sitzeIDs, int[] preiseVerIDs, int vorstellungsID, int KNR) {
        // TODO: sitzeIDs and preise must have the same length -> check
        Sitz[] sitze = new Sitz[sitzeIDs.length];
        for(int i = 0; i < sitze.length; i++) {
            sitze[i] = SitzFactory.getSitzById(sitzeIDs[i]);
        }

        Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(vorstellungsID);


        // Create Buchungsbeleg
        // KundenID (KID)
        // VorstellungsID vorstellung.getVorstellungsID()
        // Preis???
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.createBuchungsBeleg(KNR, vorstellung.getVorstellungsID(), 0);
        ResultSet rs = Connector.getQueryResult(c, sql);
        int lastBNR = -1;

        if(rs != null) {
            try {
                rs.next();
                lastBNR = rs.getInt("LAST_INSERT_ID()");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // In one loop
            // Create Buchungsposition(en)
            // PositionsID -> 1++
            // SitzID sitze[i]
        if(lastBNR > 0) {
            for (int i = 0; i < sitze.length; i++) {
                sql = QueryBuilder.createBuchungsposition(i + 1, lastBNR, sitze[i].getSitzID());
                Connector.executeQuery(c, sql);

                // Create PreisänderungBuchung
                // PositionsID
                // PreisänderungsID
                sql = QueryBuilder.createPreisänderungBuchung(i + 1, preiseVerIDs[i]);
                Connector.executeQuery(c, sql);
            }
        }

        Connector.closeResultSet(rs);
        Connector.closeConnection(c);

    }

}
