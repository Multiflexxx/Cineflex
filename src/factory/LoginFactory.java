package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import oo.UserLogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFactory {

    public static UserLogin getUserLogin(String email, String passwordHash) {
        UserLogin userLogin = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.createLoginQuery(email, passwordHash);
        ResultSet rs = Connector.getQueryResult(c, sql);

        if(rs != null) {
            try {
                rs.next();
                userLogin = new UserLogin(
                        rs.getString("E-Mail"),
                        rs.getString("Vorname"),
                        rs.getString("Nachname"),
                        rs.getInt("PID"),
                        rs.getInt("KID")
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Connector.closeConnection(c);
        Connector.closeResultSet(rs);

        return userLogin;
    }
}
