package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import oo.Sitz;

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

    public static Sitz getSitzById(int id)
    {
        return getSitzById(id, null);
    }
}
