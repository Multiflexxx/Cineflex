package factory;

import db_connector.Connector;
import helper.SupportMethods;
import java.sql.Connection;
import java.sql.ResultSet;
import oo.Sitz;
import oo.Sitzsperre;
import oo.Vorstellung;

public class SitzsperreFactory {
  public static Sitzsperre[] lockSeats(int[] seatIDs, int vorstellungsID, int KID) {

    return null;
  }

  public static Sitzsperre[] getLockedSeats(int vorstellungsID) {
    return null;
  }

  public static Sitzsperre[] updateLockedSeats() {
    return null;
  }

  public static Sitzsperre[] getTimedOutSeats() {
    Connection c = null;
    c = Connector.getConnection();
    String sql = "";
    ResultSet rs = Connector.getQueryResult(c, sql);

    if(rs != null) {

    } else {
      return null;
    }
    return null;
  }

  public static Sitzsperre[] deleteTimedOutSeats(Sitzsperre[] s) {
    return null;
  }


  // Overload lockSeats(int[] seatIDs)
  public static Sitzsperre[] locSeats(Sitz[]  seats, Vorstellung v, int KID) {
    int[] seatIDs = new int[seats.length];
    for(int i = 0; i < seats.length; i++) {
      seatIDs[i] = seats[i].getSitzID();
    }
    return lockSeats(seatIDs, v.getVorstellungsID(), KID);
  }

  public static Sitzsperre[] getLockedSeats(Vorstellung v) {
    return getLockedSeats(v.getVorstellungsID());
  }
}
