package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.RequiredFactoryFailedException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oo.BuchungsPosition;

public class BuchungspositionFactory {
    /**
     * Returns Buchungspositionen for a single Buchung
     *
     * @param BNR    ID of the Buchung
     * @param mockRs For testing
     * @return Returns Buchungspoistionen for a single Buchung
     * @throws EmptyResultSetException
     * @throws ResultSetIsNullException
     * @throws RequiredFactoryFailedException
     */
    public static BuchungsPosition[] getBuchungspositionenByBNR(int BNR, ResultSet mockRs)
            throws EmptyResultSetException, ResultSetIsNullException, RequiredFactoryFailedException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getBuchungsPositionenByBNR(BNR);
        BuchungsPosition[] buchungspositionen = null;

        ResultSet rs = null;

        // Set Mock Resultset, if available
        if (mockRs == null) {
            rs = Connector.getQueryResult(c, sql);
        } else {
            rs = mockRs;
        }

        if (rs == null) {
            SupportMethods.close(c, rs);
            throw new ResultSetIsNullException();
        }

        int rsSize = SupportMethods.getResultSetSize(rs);

        if (rsSize < 1) {
            SupportMethods.close(c, rs);
            throw new EmptyResultSetException();
        }

        buchungspositionen = new BuchungsPosition[rsSize];
        try {
            for (int i = 0; i < rsSize; i++) {
                rs.next();
                buchungspositionen[i] = new BuchungsPosition(
                        rs.getInt("PositionsID"),
                        rs.getInt("BNR"),
                        rs.getInt("SitzID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            SupportMethods.close(c, rs);
            throw new RequiredFactoryFailedException();
        }

        SupportMethods.close(c, rs);
        return buchungspositionen;
    }

    public static BuchungsPosition[] getBuchungspositionenByBNR(int BNR) throws EmptyResultSetException, ResultSetIsNullException, RequiredFactoryFailedException {
        return getBuchungspositionenByBNR(BNR, null);
    }
}
