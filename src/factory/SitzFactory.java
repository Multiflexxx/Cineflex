package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.FailedObjectCreationException;
import exception.RequiredFactoryFailedException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;
import oo.Sitz;
import oo.Sitzsperre;
import org.junit.internal.runners.statements.Fail;

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

        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
        return sitz;
    }

    public static Sitz[] getBookedSeats(int vorstellungsID) throws FailedObjectCreationException, EmptyResultSetException, ResultSetIsNullException {
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
        } catch (FailedObjectCreationException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (ResultSetIsNullException e) {
            throw new RequiredFactoryFailedException();
        } catch (EmptyResultSetException e) {
           // Empty resultSet -> no booked Seats
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

        Sitz[] allLockedSeats = null;
        if(length1 + length2 > 0) {
            allLockedSeats = new Sitz[length1 + length2];
        }

        int i = 0;
        if(bookedSeats != null) {
            for(i = 0; i < length1; i++) {
                allLockedSeats[i] = bookedSeats[i];
            }
        }
        if(lockedSeats != null) {
            for(int j = 0; j < length2; j++) {
                allLockedSeats[length1 + j] = getSitzById(lockedSeats[j].getSitzplatzID());
            }
        }

        return allLockedSeats;
    }

    public static Sitz getSitzById(int id)
    {
        return getSitzById(id, null);
    }
}
