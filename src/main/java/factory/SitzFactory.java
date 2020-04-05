package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.FailedObjectCreationException;
import exception.RequiredFactoryFailedException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;
import oo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SitzFactory {

    /**
     * Returns a Seat object from the database given specific Seat ID
     * @param id ID of the seat
     * @param mockRs For tests
     * @return Returns Seat object
     */
    public static Sitz getSitzById(int id, ResultSet mockRs) {
        Sitz sitz = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getSitzById(id);
        ResultSet rs = null;

        if(mockRs == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs;
        }

        if(rs != null) {
            try {
                rs.next();
                // ID, Nummer, Reihe, sitzklasse
                sitz = new Sitz(
                        rs.getInt("SitzplatzID"),
                        rs.getInt("Nummer"),
                        rs.getString("Reihe").charAt(0),
                        rs.getString("Sitzklasse").charAt(0)
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        SupportMethods.close(c, rs);
        return sitz;
    }

    /**
     * Returns a Seat Array containing all booked seats for a single Vorstellung
     * @param vorstellungsID ID of the Vorstellung
     * @return Returns Seat Array
     * @throws FailedObjectCreationException
     * @throws EmptyResultSetException
     * @throws ResultSetIsNullException
     */
    private static Sitz[] getBookedSeats(int vorstellungsID) throws FailedObjectCreationException, EmptyResultSetException, ResultSetIsNullException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getBookedSeats(vorstellungsID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Sitz[] bookedSeats = null;

        if(rs == null) {
            throw new ResultSetIsNullException();
        }

        int rsSize = SupportMethods.getResultSetSize(rs);
        if(rsSize < 1) {
            throw new EmptyResultSetException();
        }

        try {
            bookedSeats = new Sitz[rsSize];
            int counter = 0;
            while(rs.next()) {
                bookedSeats[counter] = getSitzById(rs.getInt("SitzID"));
                counter++;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new FailedObjectCreationException();
        }
        return bookedSeats;
    }

    /**
     * Returns a Sitz[] Array of all locked for a given Vorstellung
     * @param vorstellungsID ID of Vortsellung
     * @return Return @code{null} when there is no seat locked Seat in any way, otherwise Sitz[]
     * @throws RequiredFactoryFailedException
     */
    public static Sitz[] getAllLockedSeats(int vorstellungsID) throws RequiredFactoryFailedException {
        // Get all booked seats for Vorstellung with vorstellungsID
        Sitz[] bookedSeats = null;
        try {
            bookedSeats = getBookedSeats(vorstellungsID);
        } catch (FailedObjectCreationException | ResultSetIsNullException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (EmptyResultSetException e) {
           // Empty resultSet -> no booked Seats
        }

        // Get all reserved seats for Vorstellung with vorstellungsID
        Sitz[] reservedSeats = null;
        try{
            reservedSeats = getReservedSeats(vorstellungsID);
        } catch (ResultSetIsNullException | FailedObjectCreationException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (EmptyResultSetException e) {
            // Empty ResultSet -> No reserved Seats
        }

        // Get all temporarily locked seats for Vorstellung with vorstellungsID
        Sitzsperre[] lockedSeats = null;
        try {
            lockedSeats = SitzsperreFactory.getLockedSeats(vorstellungsID);
        } catch (FailedObjectCreationException | ResultSetIsNullException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (EmptyResultSetException e) {
            // Empty ResultSet -> no locked Seats
        }

        // Get Total length of all locked seats
        int length1 = 0;
        if(bookedSeats != null) {
            length1 = bookedSeats.length;
        }

        int length2 = 0;
        if(lockedSeats != null) {
            length2 = lockedSeats.length;
        }

        int length3 = 0;
        if(reservedSeats != null) {
            length3 = reservedSeats.length;
        }

        // Create array to return all locked seats
        Sitz[] allLockedSeats = null;
        if(length1 + length2 + length3 > 0) {
            allLockedSeats = new Sitz[length1 + length2 + length3];
        }

        // Add booked seats to return array (if there ary any)
        int i = 0;
        if(bookedSeats != null) {
            for(i = 0; i < length1; i++) {
                allLockedSeats[i] = bookedSeats[i];
            }
        }

        // Add temporarily locked seats to return array (if there ary any)
        int j = 0;
        if(lockedSeats != null) {
            for(j = 0; j < length2; j++) {
                allLockedSeats[length1 + j] = getSitzById(lockedSeats[j].getSitzplatzID());
            }
        }

        // Add reserved seats to return array (if there ary any)
        int k = 0;
        if(reservedSeats != null) {
            for(k = 0; k < length3; k++) {
                allLockedSeats[length1 + length2 + k] = reservedSeats[k];
            }
        }

        return allLockedSeats;
    }


    /**
     *  Returns a Seat Array containing all reserved seats for a single Vorstellung
     * @param vorstellungsID ID of the Vorstellung
     * @return Seat Array
     * @throws ResultSetIsNullException
     * @throws EmptyResultSetException
     * @throws FailedObjectCreationException
     */
    private static Sitz[] getReservedSeats(int vorstellungsID) throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getReservedSeats(vorstellungsID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Sitz[] reservedSeats = null;

        if(rs == null) {
            SupportMethods.close(c, rs);
            throw new ResultSetIsNullException();
        }

        int rsSize = SupportMethods.getResultSetSize(rs);
        if(rsSize < 1) {
            SupportMethods.close(c, rs);
            throw new EmptyResultSetException();
        }

        reservedSeats = new Sitz[rsSize];
        try {
            reservedSeats = new Sitz[rsSize];
            int counter = 0;
            while(rs.next()) {
                reservedSeats[counter] = getSitzById(rs.getInt("SitzID"));
                counter++;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new FailedObjectCreationException();
        }

        SupportMethods.close(c, rs);
        return reservedSeats;
    }

    /**
     * Returns a Seats Array for all seats in a Buchungsbeleg
     * @param BNR ID of the Buchungsbeleg
     * @return returns Sitz Array
     * @throws RequiredFactoryFailedException
     */
    public static Sitz[] getSitzeByBNR(int BNR) throws RequiredFactoryFailedException {
        BuchungsPosition[] buchungspositionen = null;
        Sitz[] sitze = null;
        try {
            buchungspositionen = BuchungspositionFactory.getBuchungspositionenByBNR(BNR);
        } catch (EmptyResultSetException | ResultSetIsNullException | RequiredFactoryFailedException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        }

        if(buchungspositionen == null) {
            throw new RequiredFactoryFailedException();
        }

        if(buchungspositionen.length < 1) {
            throw new RequiredFactoryFailedException();
        }

        try{
            sitze = new Sitz[buchungspositionen.length];
            for(int i = 0; i < buchungspositionen.length; i++) {
                sitze[i] = getSitzById(buchungspositionen[i].getSitzID());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        }

        return sitze;
    }

    public static Sitz[] getSitzeByRNR(int RNR) throws RequiredFactoryFailedException {
        ReservierungsPosition[] reservierungspositionen = null;
        Sitz[] sitze = null;
        try {
            reservierungspositionen = ReservierungspositionFactory.getReservierungsPositionenByRNR(RNR);
        } catch (ResultSetIsNullException | EmptyResultSetException | RequiredFactoryFailedException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        }

        if(reservierungspositionen == null) {
            throw new RequiredFactoryFailedException();
        }

        if(reservierungspositionen.length < 1) {
            throw new RequiredFactoryFailedException();
        }

        try {
            sitze = new Sitz[reservierungspositionen.length];
            for(int i = 0; i < reservierungspositionen.length; i++) {
                sitze[i] = getSitzById(reservierungspositionen[i].getSitzID());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        }

        return sitze;
    }

    public static Sitz getSitzById(int id)
    {
        return getSitzById(id, null);
    }
}
