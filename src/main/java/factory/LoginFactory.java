package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import oo.UserLogin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFactory {

    /**
     *
     * @param email
     * @param passwordHash
     * @param mockRs
     * @return userLogin
     */
    public static UserLogin getUserLogin(String email, String passwordHash, ResultSet mockRs) {
        UserLogin userLogin = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.createLoginQuery(email, passwordHash);

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

    /**
     *
     * @param email
     * @param passwordHash
     * @return UserLogin
     */
    public static UserLogin getUserLogin(String email, String passwordHash)
    {
        return getUserLogin(email, passwordHash, null);
    }
}
