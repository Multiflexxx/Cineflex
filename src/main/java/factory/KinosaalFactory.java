package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.FailedObjectCreationException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;
import oo.Kinosaal;
import oo.Sitz;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KinosaalFactory {
    /**
     * Returns a Kinosaal Object from the database
     * @param id ID of the Kinosaal
     * @param mockRs1 For tests
     * @param mockRs2 For tests
     * @return Sitzplan Returns a Kinsosaal Object
     */
    public static Kinosaal getKinosaal(int id, ResultSet mockRs1, ResultSet mockRs2) {

        Sitz[] Sitzplan;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getSitzplanBySaalID(id);

        ResultSet rs = null;

        if (mockRs1 == null) {
            rs = Connector.getQueryResult(c, sql);
        } else {
            rs = mockRs1;
        }

        if (rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            Sitzplan = new Sitz[rsSize];
            for (int i = 0; i < rsSize; i++) {
                try {
                    rs.next();
                    Sitzplan[i] = new Sitz(rs.getInt("SitzplatzID"), rs.getInt("Nummer"), rs.getString("Reihe").charAt(0), rs.getString("Sitzklasse").charAt(0)); //TODO beschreibung und grundpreis verbinden.
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Sitzplan = new Sitz[1];
            Sitzplan[0] = new Sitz(1, 2, 'A', 'B');
        }

        Kinosaal kinosaal = null;
        sql = QueryBuilder.getSaalById(id);

        if (mockRs2 == null) {
            rs = Connector.getQueryResult(c, sql);
        } else {
            rs = mockRs2;
        }

        if (rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if (rsSize > 0) {
                try {
                    rs.next();
                    kinosaal = new Kinosaal(
                            id,
                            rs.getString("Saalbezeichnung"),
                            Sitzplan,
                            GebaeudeFactory.getGebaeudeById(rs.getInt("GebäudeID"))
                    );
                } catch (SQLException | ResultSetIsNullException | EmptyResultSetException | FailedObjectCreationException e) {
                    e.printStackTrace();
                }
            }
        }

        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
        return kinosaal;
    }

    /**
     * Returns a Kinosaal Object from the database
     * @param id ID of the Kinosaal
     * @return Sitzplan Returns a Kinsosaal Object
     */
    public static Kinosaal getKinosaal(int id) {
        return getKinosaal(id, null, null);
    }
}
