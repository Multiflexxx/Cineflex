package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.FailedObjectCreationException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import oo.Sitz;
import oo.Sitzsperre;
import oo.Vorstellung;

public class SitzsperreFactory {
    private static int minutesUntilTimeOut = 10;

    public static Sitzsperre[] lockSeats(int[] seatIDs, int vorstellungsID, int KID) {
        Connection c = null;
        c = Connector.getConnection();
        Sitzsperre[] sitzSperre = new Sitzsperre[seatIDs.length];

        for (int i = 0; i < seatIDs.length; i++) {
            int sitzplatzID = seatIDs[i];
            sitzSperre[i] = new Sitzsperre(sitzplatzID, vorstellungsID, KID, new Date());
            String sql = QueryBuilder.createSitzsperre(sitzSperre[i].getSitzplatzID(), vorstellungsID, sitzSperre[i].getKNR(), sitzSperre[i].getTimestamp());
            Connector.executeQuery(c, sql);
        }
        Connector.closeConnection(c);
        return sitzSperre;
    }

    public static Sitzsperre[] getLockedSeats(int vorstellungsID) throws EmptyResultSetException, ResultSetIsNullException, FailedObjectCreationException {
        // Update Sitzsperren, remove Timed out Seats
        updateLockedSeats();

        Connection c = null;
        c = Connector.getConnection();

        Sitzsperre[] sitzSperre = null;

        String sql = QueryBuilder.getTimedOutSitzsperre(minutesUntilTimeOut, vorstellungsID);
        ResultSet rs = Connector.getQueryResult(c, sql);

        if (rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);

            if (rsSize > 0) {
                sitzSperre = new Sitzsperre[rsSize];
                int counter = 0;
                try {
                    while (rs.next()) {
                        sitzSperre[counter] = new Sitzsperre(
                                rs.getInt("SitzplatzID"),
                                rs.getInt("VorstellungsID"),
                                rs.getInt("KID"),
                                rs.getTimestamp("Zeitstempel")
                        );
                        counter++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new FailedObjectCreationException();
                }
            } else {

                throw new EmptyResultSetException();
            }
        } else {
            throw new ResultSetIsNullException();
        }
        return sitzSperre;
    }

    public static Sitzsperre[] updateLockedSeats() {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.deleteTimedOutSitzSperre(minutesUntilTimeOut);
        Connector.executeQuery(c, sql);

        return null;
    }

    // unnecessary?
    public static Sitzsperre[] getTimedOutSeats() {
        Connection c = null;
        c = Connector.getConnection();
        String sql = "";
        ResultSet rs = Connector.getQueryResult(c, sql);

        if (rs != null) {

        } else {
            return null;
        }
        return null;
    }

    public static Sitzsperre[] deleteTimedOutSeats(Sitzsperre[] s) {
        return null;
    }


    // Overload lockSeats(int[] seatIDs)
    public static Sitzsperre[] locSeats(Sitz[] seats, Vorstellung v, int KID) {
        int[] seatIDs = new int[seats.length];
        for (int i = 0; i < seats.length; i++) {
            seatIDs[i] = seats[i].getSitzplatzID();
        }
        return lockSeats(seatIDs, v.getVorstellungsID(), KID);
    }

    public static Sitzsperre[] getLockedSeats(Vorstellung v) {
        try {
            return getLockedSeats(v.getVorstellungsID());
        } catch (EmptyResultSetException e) {
            e.printStackTrace();
        } catch (ResultSetIsNullException e) {
            e.printStackTrace();
        } catch (FailedObjectCreationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
