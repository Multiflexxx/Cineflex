package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import oo.Kinosaal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KinosaalFactory {
    public static Kinosaal getKinosaal(int id) {
        Kinosaal kinosaal = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getSaalById(id);
        ResultSet rs = Connector.getQueryResult(c, sql);

        if(rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            if(rsSize > 0) {
                try {
                    kinosaal = new Kinosaal(id, rs.getString("Saalbezeichnung"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return kinosaal;
    }
}
