package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.*;
import helper.SupportMethods;
import oo.ReservierungsStornierung;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oo.Reservierungsbeleg;

public class ReservierungsStornierungFactory {
    public static ReservierungsStornierung createStornierung(int RNR) throws RequiredFactoryFailedException, FailedDataInsertionException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.createReservierungsStornierung(RNR);
        Connector.executeQuery(c, sql);

        SupportMethods.close(c);

        ReservierungsStornierung reservierungsStornierung = null;

        try {
            reservierungsStornierung = getReservierungsStornierungByRNR(RNR);
        } catch (ResultSetIsNullException | FailedObjectCreationException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (EmptyResultSetException e) {
            e.printStackTrace();
            throw new FailedDataInsertionException();
        }

        Reservierungsbeleg reservierungsbeleg = ReservierungsFactory.getReservierungsbelegByRNR(RNR);
        if(reservierungsbeleg != null) {
            KundenFactory.subtractTreuepunkte(reservierungsbeleg.getKunde().getKundenID(), reservierungsbeleg.getPreis());
        }

        return reservierungsStornierung;
    }

    public static ReservierungsStornierung getReservierungsStornierungByStrnNR(int StrnNR) throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getReservierungsStornierungByStrnNR(StrnNR);
        ResultSet rs = Connector.getQueryResult(c, sql);

        ReservierungsStornierung reservierungsStornierung = null;
        if(rs == null) {
            throw new ResultSetIsNullException();
        }

        if(SupportMethods.getResultSetSize(rs) < 1) {
            throw new EmptyResultSetException();
        }

        try{
            rs.next();
            reservierungsStornierung = new ReservierungsStornierung(
                    rs.getInt("BNR"),
                    rs.getInt("StrnNR")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedObjectCreationException();
        }

        return reservierungsStornierung;
    }

    public static ReservierungsStornierung getReservierungsStornierungByRNR(int RNR) throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getReservierungsStornierungByRNR(RNR);
        ResultSet rs = Connector.getQueryResult(c, sql);
        ReservierungsStornierung reservierungsStornierung = null;

        if(rs == null) {
            throw new ResultSetIsNullException();
        }

        if(SupportMethods.getResultSetSize(rs) < 1) {
            throw new EmptyResultSetException();
        }

        try{
            rs.next();
            reservierungsStornierung = new ReservierungsStornierung(
                    rs.getInt("RNR"),
                    rs.getInt("StrnNR")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedObjectCreationException();
        }

        return reservierungsStornierung;
    }

    public static ReservierungsStornierung[] getReservierungsStornierungByKID(int KID) throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getReservierungsStornierungByKID(KID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        ReservierungsStornierung[] reservierungsStornierungs = null;

        if(rs == null) {
            throw new ResultSetIsNullException();
        }

        int rsSize = SupportMethods.getResultSetSize(rs);
        if(rsSize < 1) {
            throw new EmptyResultSetException();
        }

        try{
            int counter = 0;
            while(rs.next()) {
                reservierungsStornierungs[counter] = new ReservierungsStornierung(
                        rs.getInt("RNR"),
                        rs.getInt("StrnNR")
                );
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedObjectCreationException();
        }

        return reservierungsStornierungs;
    }

    // public static
}
