package factory;

import com.google.zxing.WriterException;
import db_connector.Connector;
import db_connector.QueryBuilder;
//import oo.Buchungsbeleg;
import exception.RequiredFactoryFailedException;
import helper.DateFormatter;
import helper.SupportMethods;
import oo.Buchungsbeleg;
import oo.Kunde;
import oo.Sitz;
import oo.Vorstellung;
import qr_code.QrCodeGenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BuchungsFactory {

    // TODO: Change Signature to Buchungsbeleg?
    public static int createBuchungBeleg(int[] sitzeIDs, int[] preiseVerIDs, String seats, String preisVer, int vorstellungsID, int KNR) {
        // TODO: sitzeIDs and preise must have the same length -> check
        if (sitzeIDs.length != preiseVerIDs.length) {
            // throw new UnequalParameterLength();
            return -1;
        }

        Sitz[] sitze = new Sitz[sitzeIDs.length];
        for (int i = 0; i < sitze.length; i++) {
            sitze[i] = SitzFactory.getSitzById(sitzeIDs[i]);
        }

        Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(vorstellungsID);


        float price = 0;
        try {
            price = PreisFactory.getBuchungsPreis(seats, preisVer, vorstellung.getFilm().getFilmID());
        } catch (RequiredFactoryFailedException e) {
            e.printStackTrace();
        }


        Connection c = Connector.getConnection();
        Date date = new Date();
        String timestamp = DateFormatter.getSQLDateAndTime(date);
        String sql = QueryBuilder.createBuchungsBeleg(KNR, vorstellung.getVorstellungsID(), price, timestamp);
        Connector.executeQuery(c, sql);
        sql = QueryBuilder.getBuchungsbelegByKIDandTimestamp(KNR, timestamp);
        ResultSet rs = Connector.getQueryResult(c, sql);

        int lastBNR = -1;

        if (rs != null) {
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
//        if(lastBNR > 0) {
//            for (int i = 0; i < sitze.length; i++) {
//                sql = QueryBuilder.createBuchungsposition(i + 1, lastBNR, sitze[i].getSitzID());
//                Connector.executeQuery(c, sql);
//
//                // Create PreisänderungBuchung
//                // PositionsID
//                // PreisänderungsID
//                sql = QueryBuilder.createPreisänderungBuchung(i + 1, lastBNR, preiseVerIDs[i]);
//                Connector.executeQuery(c, sql);
//            }
//        }
        createBuchungsPositionen(c, lastBNR, sitze, preiseVerIDs);

        createBuchungsbelegQrCode(KNR, vorstellung, sitze);

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

        if (rs != null) {
            try {
                if (rs.next()) {
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
                SupportMethods.close(c, rs);
                return null;
            }
        } else {
            SupportMethods.close(c, rs);
            return null;
        }
        SupportMethods.close(c, rs);
        return buchungsbeleg;
    }

    public static void createBuchungsbelegQrCode(int KID, Vorstellung vorstellung, Sitz [] sitze) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getJustCreatedBuchung(KID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Buchungsbeleg buchungsbeleg = null;

        if (rs != null) {
            try {
                if (rs.next()) {
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
                SupportMethods.close(c, rs);
            }
        } else {
            SupportMethods.close(c, rs);
        }
        SupportMethods.close(c, rs);

        // PATH: /usr/local/tomcat/qr_codes/qrcode+KID+buchungsbeleg.getBelegID().png
        String path = "../../../GitProjekte/CineflexV1/out/artifacts/CineflexV1_war_exploded/img/qrcode/qrcode" + KID + buchungsbeleg.getBelegID() + ".png";
        String qrcodeinfo = "{'Kundennr': " + KID;
        qrcodeinfo += ", 'VorstellungID': " + vorstellung.getVorstellungsID();
        qrcodeinfo += ", 'Film': " + vorstellung.getFilm().getTitel() + "'}";
        try {
            QrCodeGenerator.generateQRCodeImage(qrcodeinfo, path);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Buchungsbeleg[] getBuchungsbelegeByKID(int KID) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getBuchungsbelegeByKID(KID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Buchungsbeleg[] buchungsbelege = null;

        if (rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            buchungsbelege = new Buchungsbeleg[rsSize];

            try {
                int counter = 0;
                while (rs.next()) {
                    Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(rs.getInt("vorstellungsID"));
                    Kunde kunde = KundenFactory.getKundeByKID(rs.getInt("KID"));
                    buchungsbelege[counter] = new Buchungsbeleg(
                            rs.getInt("BNR"),
                            rs.getFloat("Preis"),
                            vorstellung,
                            kunde,
                            new Date(rs.getTimestamp("Zeitstempel").getTime())
                    );
                    counter++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                SupportMethods.close(c, rs);
                return null;
            }
        } else {
            SupportMethods.close(c, rs);
            return null;
        }
        SupportMethods.close(c, rs);
        return buchungsbelege;
    }

    public static void createBuchungsPositionen(Connection c, int BNR, Sitz[] sitze, int[] preiseVerIDs) {
        if (BNR > 0) {
            for (int i = 0; i < sitze.length; i++) {
                String sql = QueryBuilder.createBuchungsposition(i + 1, BNR, sitze[i].getSitzplatzID());
                Connector.executeQuery(c, sql);

                // Create PreisänderungBuchung
                // PositionsID
                // PreisänderungsID
                sql = QueryBuilder.createPreisänderungBuchung(i + 1, BNR, preiseVerIDs[i]);
                Connector.executeQuery(c, sql);
            }
        }
    }
}