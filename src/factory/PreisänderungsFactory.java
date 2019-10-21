package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;
import oo.Preisänderung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreisänderungsFactory {
    public static Preisänderung getPreisänderungById(int preisänderungsID) throws ResultSetIsNullException, EmptyResultSetException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getPreisänderungByID(preisänderungsID);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Preisänderung preisänderung = null;

        if(rs == null) {
            throw new ResultSetIsNullException();
        }

        int rsSize = SupportMethods.getResultSetSize(rs);

        if(rsSize == 0) {
            throw new EmptyResultSetException();
        }

        try {
            rs.next();
            preisänderung = new Preisänderung(
              rs.getInt("PreisänderungsID"),
              rs.getDouble("Änderungswert"),
              rs.getString("Änderungsbeschreibung"),
              rs.getString("TooltipDeskriptor"),
              rs.getBoolean("grundpreis_relevant")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            SupportMethods.close(c, rs);
            return null;
        }

        SupportMethods.close(c, rs);
        return preisänderung;
    }
}
