package factory;

import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.RequiredFactoryFailedException;
import helper.DateFormatter;
import helper.SupportMethods;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import oo.Kunde;
import oo.Reservierungsbeleg;
import oo.Sitz;
import oo.Vorstellung;
import pdf_generator.PdfGenerator;
import qr_code.QrCodeGenerator;
import send_mail.Email_Sender;

public class ReservierungsFactory {

    /**
     * @param sitzeIDs
     * @param preiseVerIDs
     * @param vorstellungsID
     * @param KNR
     * @return
     */
    public static int createReservierungsBelege(int[] sitzeIDs, int[] preiseVerIDs, String seats,
                                                String preisVer, int vorstellungsID, int KNR) throws IOException, DocumentException {
        if (sitzeIDs.length != preiseVerIDs.length) {
//      throw new UnequalParameterLength();
            return -1;
        }

        Sitz[] sitze = new Sitz[sitzeIDs.length];
        for (int i = 0; i < sitze.length; i++) {
            sitze[i] = SitzFactory.getSitzById(sitzeIDs[i]);
        }



        Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(vorstellungsID);

        float preis = 0;

        try {
            preis = PreisFactory.getBuchungsPreis(seats,preisVer,vorstellung.getFilm().getFilmID());
        }
        catch (RequiredFactoryFailedException e)
        {
            e.printStackTrace();
        }


        // Create reservierungsbeleg with timestamp
        Connection c = Connector.getConnection();
        String timeStamp = DateFormatter.getSQLDateAndTime(new Date());
        String sql = QueryBuilder.createReservierungsbeleg(KNR, vorstellung.getVorstellungsID(), preis, timeStamp);
        Connector.executeQuery(c, sql);

        // Get RNR for the created Beleg
        sql = QueryBuilder.getReservierungsbelegByKIDandTimestamp(KNR, timeStamp);
        ResultSet rs = Connector.getQueryResult(c, sql);
        int lastRNR = -1;

        if (rs != null) {
            try {
                rs.next();
                lastRNR = rs.getInt("RNR");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Create Reservierungsbelege
        createReservierungsPositionen(c, lastRNR, sitze, preiseVerIDs);
        createReservierungsBelegPDF(KNR, vorstellung, sitze);
        SitzsperreFactory.deleteSitzsperrenByVorstellung(vorstellungsID);
        SupportMethods.close(c, rs);
        return 0;
    }

    /**
     * @param RNR
     * @return reservierungsbeleg
     */
    public static Reservierungsbeleg getReservierungsbelegByRNR(int RNR) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getReservierungsbelegByRNR(RNR);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Reservierungsbeleg reservierungsbeleg = null;

        if (rs != null) {
            try {
                rs.next();
                Kunde kunde = KundenFactory.getKundeByKID(rs.getInt("KID"));
                Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(rs.getInt("VorstellungsID"));
                reservierungsbeleg = new Reservierungsbeleg(
                        rs.getInt("RNR"),
                        rs.getFloat("Preis"),
                        vorstellung,
                        kunde,
                        new Date(rs.getTimestamp("Zeitstempel").getTime())
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reservierungsbeleg;
    }

    /**
     * @param KID
     * @return reservierungsbelege
     */
    public static Reservierungsbeleg[] getReservierungsbelegByKID(int KID) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getReservierungsbelegByKID(KID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Reservierungsbeleg[] reservierungsbelege = null;

        if (rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if (rsSize > 0) {
                reservierungsbelege = new Reservierungsbeleg[rsSize];
                try {
                    int counter = 0;
                    while (rs.next()) {
                        Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(rs.getInt("VorstellungsID"));
                        Kunde kunde = KundenFactory.getKundeByKID(rs.getInt("KID"));
                        reservierungsbelege[counter] = new Reservierungsbeleg(
                                rs.getInt("RNR"),
                                rs.getFloat("Preis"),
                                vorstellung,
                                kunde,
                                new Date(rs.getTimestamp("Zeitstempel").getTime())
                        );
                        counter++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return reservierungsbelege;

    }

    /**
     * @param c
     * @param RNR
     * @param sitze
     * @param preiseVerIDs
     */
    public static void createReservierungsPositionen(Connection c, int RNR, Sitz[] sitze,
                                                     int[] preiseVerIDs) {
        if (RNR > 0) {
            for (int i = 0; i < sitze.length; i++) {
                String sql = QueryBuilder.createReservierungsposition(i + 1, RNR, sitze[i].getSitzplatzID());
                Connector.executeQuery(c, sql);

                // Create PreisänderungBuchung
                // PositionsID
                // PreisänderungsID
                sql = QueryBuilder.createPreisänderungReservierung(i + 1, RNR, preiseVerIDs[i]);
                Connector.executeQuery(c, sql);
            }
        }
    }

    public static void createReservierungsBelegPDF(int KID, Vorstellung vorstellung, Sitz[] sitze) throws IOException, DocumentException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getJustCreatedReservierung(KID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Reservierungsbeleg reservierungsbeleg = null;
        Kunde kunde = null;

        if (rs != null) {
            try {
                if (rs.next()) {
                    kunde = KundenFactory.getKundeByKID(rs.getInt("KID"));
                    reservierungsbeleg = new Reservierungsbeleg(
                            rs.getInt("RNR"),
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

        String pathQR = "/usr/local/tomcat/qr_codes/qrcodeR" + KID + reservierungsbeleg.getBelegID() + ".png";
        //String pathQR = "../../../GitProjekte/CineflexV1/out/artifacts/CineflexV1_war_exploded/img/qrcode/qrcode" + KID + buchungsbeleg.getBelegID() + ".png";
        String qrcodeinfo = "{'Kundennr': " + KID;
        qrcodeinfo += ", 'VorstellungID': " + vorstellung.getVorstellungsID();
        qrcodeinfo += ", 'Film': " + vorstellung.getFilm().getTitel() + "'}";
        try {
            QrCodeGenerator.generateQRCodeImage(qrcodeinfo, pathQR);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        String pathPDF = "/usr/local/tomcat/belege_pdf/pdfR" + KID + reservierungsbeleg.getBelegID() + ".pdf";
        //String pathPDF = "../../../GitProjekte/CineflexV1/out/artifacts/CineflexV1_war_exploded/img/qrcode/pdf" + KID + buchungsbeleg.getBelegID() + ".pdf";
        PdfGenerator.createReservierungsPDF(pathPDF, pathQR, reservierungsbeleg, vorstellung, sitze, kunde);

        String m_body = "Vielen Dank " + kunde.getVorname() + " für deine Reservierung. \n\n Hole deine Tickets min. 30 Minuten vor Vorstellungsbeginn bei uns an der Kasse ab. \n\n Viel Spaß. Dein Multiflexxx Team";
        Email_Sender.sendMultipartMail(kunde.getEmail(), "Reservierung by Multiflexxx" + kunde.getKundenID() + reservierungsbeleg.getBelegID(), m_body, pathPDF);
    }

}
