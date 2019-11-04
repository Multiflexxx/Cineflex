package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.*;
import helper.SupportMethods;
import oo.BuchungsStornierung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oo.Buchungsbeleg;

public class BuchungsStornierungFactory {

	/**
	 * Create a BuchungsStornierung for a single Buchung
	 * @param BNR ID of the Buchung
	 * @return Returns the created BuchungsStornierung
	 * @throws RequiredFactoryFailedException
	 * @throws FailedDataInsertionException
	 */
    public static BuchungsStornierung createStornierung(int BNR)
            throws RequiredFactoryFailedException, FailedDataInsertionException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.createBuchungsStornierung(BNR);
        Connector.executeQuery(c, sql);

        SupportMethods.close(c);

        BuchungsStornierung buchungsStornierung = null;

        try {
            buchungsStornierung = getBuchungsStornierungByBNR(BNR);
        } catch (ResultSetIsNullException | FailedObjectCreationException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (EmptyResultSetException e) {
            e.printStackTrace();
            throw new FailedDataInsertionException();
        }

        Buchungsbeleg buchungsbeleg = BuchungsFactory.getBuchungsbelegByBNR(BNR);
        if (buchungsbeleg != null) {
            KundenFactory.subtractTreuepunkte(buchungsbeleg.getKunde().getKundenID(), buchungsbeleg.getPreis());
        }

        return buchungsStornierung;
    }

	/**
	 * Returns a BuchungsStornierung for a single Buchung
	 * @param StrnNR ID of the BuchungsStornierung
	 * @return Returns BuchungsStornierung
	 * @throws ResultSetIsNullException
	 * @throws EmptyResultSetException
	 * @throws FailedObjectCreationException
	 */
    public static BuchungsStornierung getBuchungsStornierungByStrnNR(int StrnNR)
            throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getBuchungsStornierungByStrnNR(StrnNR);
        ResultSet rs = Connector.getQueryResult(c, sql);
        BuchungsStornierung buchungsStornierung = null;
        if (rs == null) {
            throw new ResultSetIsNullException();
        }

        if (SupportMethods.getResultSetSize(rs) < 1) {
            throw new EmptyResultSetException();
        }

        try {
            rs.next();
            buchungsStornierung = new BuchungsStornierung(
                    rs.getInt("BNR"),
                    rs.getInt("StrnNR")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedObjectCreationException();
        }

        return buchungsStornierung;
    }

	/**
	 * Returns the BuchungsStornierung of a single Buchung, if one exists
	 * @param BNR ID of the Buchungs
	 * @return Returns a BuchungsStornierung
	 * @throws ResultSetIsNullException
	 * @throws EmptyResultSetException
	 * @throws FailedObjectCreationException
	 */
    public static BuchungsStornierung getBuchungsStornierungByBNR(int BNR)
            throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getBuchungsStornierungByBNR(BNR);
        ResultSet rs = Connector.getQueryResult(c, sql);
        BuchungsStornierung buchungsStornierung = null;
        if (rs == null) {
            throw new ResultSetIsNullException();
        }

        if (SupportMethods.getResultSetSize(rs) < 1) {
            throw new EmptyResultSetException();
        }

        try {
            rs.next();
            buchungsStornierung = new BuchungsStornierung(
                    rs.getInt("BNR"),
                    rs.getInt("StrnNR")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedObjectCreationException();
        }

        return buchungsStornierung;
    }

	/**
	 * Returns all BuchungsStornierungen of a customer
	 * @param KID ID of the customer
	 * @return Returns BuchungsStornierung[]
	 * @throws ResultSetIsNullException
	 * @throws EmptyResultSetException
	 * @throws FailedObjectCreationException
	 */
    public static BuchungsStornierung[] getBuchungsStornierungByKID(int KID)
            throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getBuchungsStornierungByKID(KID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        BuchungsStornierung[] buchungsStornierung = null;

        if (rs == null) {
            throw new ResultSetIsNullException();
        }

        int rsSize = SupportMethods.getResultSetSize(rs);
        if (rsSize < 1) {
            throw new EmptyResultSetException();
        }

        try {
            int counter = 0;
            while (rs.next()) {
                buchungsStornierung[counter] = new BuchungsStornierung(
                        rs.getInt("BNR"),
                        rs.getInt("StrnNR")
                );
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedObjectCreationException();
        }

        return buchungsStornierung;
    }
}
