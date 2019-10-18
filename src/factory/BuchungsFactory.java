package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
//import oo.Buchungsbeleg;
import helper.DateFormatter;
import helper.SupportMethods;
import java.sql.Timestamp;
import javax.management.Query;
import oo.Buchungsbeleg;
import oo.Kunde;
import oo.Sitz;
import oo.Vorstellung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BuchungsFactory {

    public static int createBuchungBeleg(int[] sitzeIDs, int[] preiseVerIDs, int vorstellungsID, int KNR) {
        // TODO: sitzeIDs and preise must have the same length -> check
        if(sitzeIDs.length != preiseVerIDs.length) {
            return -1;
        }

        Sitz[] sitze = new Sitz[sitzeIDs.length];
        for(int i = 0; i < sitze.length; i++) {
            sitze[i] = SitzFactory.getSitzById(sitzeIDs[i]);
        }

        Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(vorstellungsID);


        // Create Buchungsbeleg
        // KundenID (KID)
        // VorstellungsID vorstellung.getVorstellungsID()
        // Preis???
        // TODO: RS ist null, keine aneinandergehängten SQL Statements?
        Connection c = Connector.getConnection();
        Date date = new Date();
        String timestamp = DateFormatter.getSQLDateAndTime(date);
        String sql = QueryBuilder.createBuchungsBeleg(KNR, vorstellung.getVorstellungsID(), 0, timestamp);
        Connector.executeQuery(c, sql);
        sql = QueryBuilder.getBuchungsbelegByKIDandTimestamp(KNR, timestamp);
        ResultSet rs = Connector.getQueryResult(c, sql);

        int lastBNR = -1;

        if(rs != null) {
            try {
                rs.next();
                lastBNR = rs.getInt("BNR");
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
                sql = QueryBuilder.createPreisänderungBuchung(i + 1, lastBNR, preiseVerIDs[i]);
                Connector.executeQuery(c, sql);
            }
        }

        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
        return 0;
    }

    public static Buchungsbeleg getBuchungsbelegByBNR(int BNR) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getBuchungsbelegByBNR(BNR);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Buchungsbeleg buchungsbeleg = null;
//        Timestamp t = rs.getTimestamp("");

        if(rs != null) {
            try {
                if(rs.next()) {
                    // Get Vorstellung for Buchungsbeleg
                    Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(rs.getInt("vorstellungsID"));
                    Kunde kunde = KundenFactory.getKundeByKID(rs.getInt("KID"));
                    buchungsbeleg = new Buchungsbeleg(
                        rs.getInt("BNR"),
                        rs.getFloat("Preis"),
                        vorstellung,
                        kunde,
                        new Date(rs.getTimestamp("Zeitstempel").getTime())
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
        return buchungsbeleg;
    }

    public static Buchungsbeleg[] getBuchungsbelegeByKNR(int KNR) {


        return null;
    }

}
