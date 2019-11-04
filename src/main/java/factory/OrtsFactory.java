package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oo.Ort;

public class OrtsFactory {

    /**
     * Returns a Ort object given an existing PLZ
     * @param plz PLZ of the ORT
     * @return Returns a Ort object
     * @throws ResultSetIsNullException
     * @throws EmptyResultSetException
     */
    public static Ort getOrtByPLZ(int plz) throws ResultSetIsNullException, EmptyResultSetException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getOrtByPLZ(plz);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Ort ort = null;

        if (rs == null) {
            throw new ResultSetIsNullException();
        }

        if (SupportMethods.getResultSetSize(rs) < 1) {
            throw new EmptyResultSetException();
        }

        try {
            rs.next();
            ort = new Ort(
                    rs.getString("Ortsname"),
                    rs.getInt("PLZ")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SupportMethods.close(c, rs);

        return ort;
    }
}
