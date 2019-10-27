package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.RequiredFactoryFailedException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;
import oo.BuchungsPosition;
import oo.ReservierungsPosition;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservierungspositionFactory {
    public static ReservierungsPosition[] getReservierungsPositionenByRNR(int RNR) throws ResultSetIsNullException, EmptyResultSetException, RequiredFactoryFailedException {

        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getReservierungsPositionenByRNR(RNR);
        ResultSet rs = Connector.getQueryResult(c, sql);
        ReservierungsPosition[] reservierungspositionen = null;

        if (rs == null) {
            SupportMethods.close(c, rs);
            throw new ResultSetIsNullException();
        }

        int rsSize = SupportMethods.getResultSetSize(rs);

        if (rsSize < 1) {
            SupportMethods.close(c, rs);
            throw new EmptyResultSetException();
        }

        reservierungspositionen = new ReservierungsPosition[rsSize];
        try {
            int counter = 0;
            while (rs.next()) {
                reservierungspositionen[counter] = new ReservierungsPosition(
                        rs.getInt("PositionsID"),
                        rs.getInt("RNR"),
                        rs.getInt("SitzID")
                );
                counter++;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            SupportMethods.close(c, rs);
            throw new RequiredFactoryFailedException();
        }

        SupportMethods.close(c, rs);
        return reservierungspositionen;
    }
}
