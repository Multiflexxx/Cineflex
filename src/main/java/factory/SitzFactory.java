package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.FailedObjectCreationException;
import exception.RequiredFactoryFailedException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;
import oo.BuchungsPosition;
import oo.Buchungsbeleg;
import oo.Sitz;
import oo.Sitzsperre;

import javax.management.Query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SitzFactory {
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
        Sitz[] bookedSeats = null;
        try {
            bookedSeats = getBookedSeats(vorstellungsID);
        } catch (FailedObjectCreationException | ResultSetIsNullException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (EmptyResultSetException e) {
           // Empty resultSet -> no booked Seats
        }

        Sitz[] reservedSeats = null;
        try{
            reservedSeats = getReservedSeats(vorstellungsID);
        } catch (ResultSetIsNullException | FailedObjectCreationException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (EmptyResultSetException e) {
            // Empty ResultSet -> No reserved Seats
        }

        Sitzsperre[] lockedSeats = null;
        try {
            lockedSeats = SitzsperreFactory.getLockedSeats(vorstellungsID);
        } catch (FailedObjectCreationException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (ResultSetIsNullException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (EmptyResultSetException e) {
            // Empty ResultSet -> no locked Seats
        }

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

        Sitz[] allLockedSeats = null;
        if(length1 + length2 + length3 > 0) {
            allLockedSeats = new Sitz[length1 + length2 + length3];
        }

        int i = 0;
        if(bookedSeats != null) {
            for(i = 0; i < length1; i++) {
                allLockedSeats[i] = bookedSeats[i];
            }
        }
        int j = 0;
        if(lockedSeats != null) {
            for(j = 0; j < length2; j++) {
                allLockedSeats[length1 + j] = getSitzById(lockedSeats[j].getSitzplatzID());
            }
        }
        int k = 0;
        if(reservedSeats != null) {
            for(k = 0; k < length3; k++) {
                allLockedSeats[length1 + length2 + k] = reservedSeats[k];
            }
        }

        return allLockedSeats;
    }

    public static Sitz[] getReservedSeats(int vorstellungsID) throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getReservedSeats(vorstellungsID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Sitz[] reservedSeats = null;

        if(rs == null) {
            throw new ResultSetIsNullException();
        }

        int rsSize = SupportMethods.getResultSetSize(rs);
        if(rsSize < 1) {
            throw new EmptyResultSetException();
        }

        reservedSeats = new Sitz[rsSize];
        try {
            int counter = 0;
            while(rs.next()) {
                reservedSeats[counter] = new Sitz(
                        rs.getInt("SitzplatzID"),
                        rs.getInt("Nummer"),
                        rs.getString("Reihe").charAt(0),
                        rs.getString("Sitzklasse").charAt(0)
                );
                counter++;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new FailedObjectCreationException();
        }

        return reservedSeats;
    }


    public static Sitz[] getSitzeByBNR(int BNR) {
        try {
            BuchungsPosition[] posi = BuchungspositionFactory.getBuchungspositionenByBNR(BNR);
        } catch (EmptyResultSetException e) {
            e.printStackTrace();
        } catch (ResultSetIsNullException e) {
            e.printStackTrace();
        } catch (RequiredFactoryFailedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Sitz getSitzById(int id)
    {
        return getSitzById(id, null);
    }
}
