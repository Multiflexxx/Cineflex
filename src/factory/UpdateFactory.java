package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import oo.Vorstellung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateFactory {
    public static boolean checkVorstellungPLZ(Vorstellung v, int plz) {
        String sql = QueryBuilder.getVorstellungByIdPLZ(v.getVorstellungsID());
        Connection c = null;
        c = Connector.getConnection();
        ResultSet rs = Connector.getQueryResult(c, sql);

        if(rs != null) {
            try {
                if(rs.next()) {
                    if(rs.getInt("PLZ") == plz) {
                        Connector.closeResultSet(rs);
                        Connector.closeConnection(c);
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
        return false;
    }
}
