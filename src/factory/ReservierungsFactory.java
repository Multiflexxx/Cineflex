package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.DateFormatter;
import helper.SupportMethods;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import oo.Kunde;
import oo.Reservierungsbeleg;
import oo.Sitz;
import oo.Vorstellung;

public class ReservierungsFactory {

  public static int createReservierungsBelege(int[] sitzeIDs, int[] preiseVerIDs,
      int vorstellungsID, int KNR) {
    if (sitzeIDs.length != preiseVerIDs.length) {
//      throw new UnequalParameterLength();
      return -1;
    }

    Sitz[] sitze = new Sitz[sitzeIDs.length];
    for (int i = 0; i < sitze.length; i++) {
      sitze[i] = SitzFactory.getSitzById(sitzeIDs[i]);
    }

    Vorstellung vorstellung = VorstellungsFactory.getVorstellungById(vorstellungsID);

    // Create reservierungsbeleg with timestamp
    Connection c = Connector.getConnection();
    String timeStamp = DateFormatter.getSQLDateAndTime(new Date());
    String sql = QueryBuilder.createReservierungsbeleg(KNR, vorstellung.getVorstellungsID(), 0, timeStamp);
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
    SupportMethods.close(c, rs);
    return 0;
  }

  public static Reservierungsbeleg getReservierungsbelegByRNR(int RNR) {
    Connection c = Connector.getConnection();
    String sql = QueryBuilder.getReservierungsbelegByRNR(RNR);
    ResultSet rs = Connector.getQueryResult(c, sql);
    Reservierungsbeleg reservierungsbeleg = null;

    if(rs != null) {
      try{
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
      }catch(SQLException e) {
        e.printStackTrace();
      }
    }
    return reservierungsbeleg;
  }

  public static Reservierungsbeleg[] getReservierungsbelegByKID(int KID) {
    Connection c = Connector.getConnection();
    String sql = QueryBuilder.getReservierungsbelegByKID(KID);
    ResultSet rs = Connector.getQueryResult(c, sql);
    Reservierungsbeleg[] reservierungsbelege = null;

    if(rs != null) {
      int rsSize = SupportMethods.getResultSetSize(rs);
      if(rsSize > 0) {
        reservierungsbelege = new Reservierungsbeleg[rsSize];
        try{
          int counter = 0;
          while(rs.next()) {
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
        } catch(SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return reservierungsbelege;

  }

  public static void createReservierungsPositionen(Connection c, int RNR, Sitz[] sitze,
      int[] preiseVerIDs) {
    if (RNR > 0) {
      for (int i = 0; i < sitze.length; i++) {
        String sql = QueryBuilder.createReservierungsposition(i + 1, RNR, sitze[i].getSitzID());
        Connector.executeQuery(c, sql);

        // Create PreisänderungBuchung
        // PositionsID
        // PreisänderungsID
        sql = QueryBuilder.createPreisänderungReservierung(i + 1, RNR, preiseVerIDs[i]);
        Connector.executeQuery(c, sql);
      }
    }
  }
}
