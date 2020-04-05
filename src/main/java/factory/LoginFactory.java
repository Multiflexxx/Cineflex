package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import oo.UserLogin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFactory {

    /**
     * Used for logging in a user, returns a UserLogin object that can be used to log in a user
     * @param email Email address of the customer
     * @param passwordHash Password Hash
     * @param mockRs For tests
     * @return userLogin Returns a UserLogin object
     */
    public static UserLogin getUserLogin(String email, String passwordHash, ResultSet mockRs) {
        email = SupportMethods.removeHTMLCode(email);
        email = SupportMethods.removeSQLInjections(email);
        passwordHash = SupportMethods.removeHTMLCode(passwordHash);
        passwordHash = SupportMethods.removeSQLInjections(passwordHash);

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
     * Used for logging in a user, returns a UserLogin object that can be used to log in a user
     * @param email Email address of the customer
     * @param passwordHash Password Hash
     * @return userLogin Returns a UserLogin object
     */
    public static UserLogin getUserLogin(String email, String passwordHash)
    {
        return getUserLogin(email, passwordHash, null);
    }
}
