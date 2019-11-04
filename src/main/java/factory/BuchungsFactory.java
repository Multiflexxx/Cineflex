package factory;

import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.RequiredFactoryFailedException;
import helper.DateFormatter;
import helper.SupportMethods;
import oo.Buchungsbeleg;
import oo.Kunde;
import oo.Sitz;
import oo.Vorstellung;
import pdf_generator.PdfGenerator;
import qr_code.QrCodeGenerator;
import send_mail.Email_Sender;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BuchungsFactory {

    /**
     * Creates Database entries for a Buchung
     *
     * @param sitzeIDs       IDs of the seats to be booked (integer array)
     * @param preiseVerIDs   IDs of the pricing changes for the booked seats (integer array)
     * @param seats          IDs of the seats to be booked (comma separated numbers as a String)
     * @param preisVer       IDs of the pricing changes for the booked seats (comma separated numbers as a String)
     * @param vorstellungsID ID of the Vorstellung for the Buchung
     * @param KNR            ID of the customer
     * @return Exit code
     * @throws IOException
     * @throws DocumentException
     */
    public static int createBuchungBeleg(int[] sitzeIDs, int[] preiseVerIDs, String seats, String preisVer, int vorstellungsID, int KNR) throws IOException, DocumentException {
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

        createBuchungsPositionen(c, lastBNR, sitze, preiseVerIDs);
        createBuchungsbelegPDF(KNR, vorstellung, sitze);
        SitzsperreFactory.deleteSitzsperrenByVorstellung(vorstellungsID);

        // Give Kunde Treuepunkte
        KundenFactory.addTreuepunkte(KNR, price);

        SupportMethods.close(c, rs);
        return lastBNR;
    }

    /**
     * Return a Buchungsbeleg given a valid BNR
     *
     * @param BNR A valid BNR
     * @return Returns a Buchungsbeleg
     */
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

    /**
     * Creates a PDF and send it via Email to the specified customer
     *
     * @param KID         ID of the customer
     * @param vorstellung ID of the vorstellung
     * @param sitze       Booked seats
     * @throws IOException
     * @throws DocumentException
     */
    public static void createBuchungsbelegPDF(int KID, Vorstellung vorstellung, Sitz[] sitze) throws IOException, DocumentException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getJustCreatedBuchung(KID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Buchungsbeleg buchungsbeleg = null;
        Kunde kunde = null;

        if (rs != null) {
            try {
                if (rs.next()) {
                    kunde = KundenFactory.getKundeByKID(rs.getInt("KID"));
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

        String pathQR = "/usr/local/tomcat/qr_codes/qrcodeB" + KID + buchungsbeleg.getBelegID() + ".png";
        //String pathQR = "../../../GitProjekte/CineflexV1/out/artifacts/CineflexV1_war_exploded/img/qrcode/qrcode" + KID + buchungsbeleg.getBelegID() + ".png";
        String qrcodeinfo = "{'Kundennr': " + KID;
        qrcodeinfo += ", 'VorstellungID': " + vorstellung.getVorstellungsID();
        qrcodeinfo += ", 'Film': " + vorstellung.getFilm().getTitel() + "'}";
        try {
            QrCodeGenerator.generateQRCodeImage(qrcodeinfo, pathQR);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        String pathPDF = "/usr/local/tomcat/belege_pdf/pdfB" + KID + buchungsbeleg.getBelegID() + ".pdf";
        //String pathPDF = "../../../GitProjekte/CineflexV1/out/artifacts/CineflexV1_war_exploded/img/qrcode/pdf" + KID + buchungsbeleg.getBelegID() + ".pdf";
        PdfGenerator.createBuchungsPDF(pathPDF, pathQR, buchungsbeleg, vorstellung, sitze, kunde);

        String m_body = "Vielen Dank " + kunde.getVorname() + " für deine Buchung.\n\n Anbei erhältst du deine Tickets. \n\n Viel Spaß. Dein Multiflexxx Team";
        Email_Sender.sendMultipartMail(kunde.getEmail(), "Buchung by Multiflexxx" + kunde.getKundenID() + buchungsbeleg.getBelegID(), m_body, pathPDF);
    }

    /**
     * Returns all Buchungsbelege in a customer's history
     *
     * @param KID ID of the customer
     * @return Returns the customer's history as a Buchungsbeleg array
     */
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

    /**
     * Creates databse entries for the Buchungspositionen for a Buchung
     *
     * @param c            Connection to the Database
     * @param BNR          ID of the Buchung
     * @param sitze        Booked Seats
     * @param preiseVerIDs IDs of the pricing changes
     */
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