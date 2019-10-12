package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;

import java.sql.Connection;
import java.sql.ResultSet;

public class LoginFactory {

    private String email, passwordHash;
    Connection connection = null;

    public LoginFactory(){

    }

    public LoginFactory(String email, String passwordHash)
    {
        this.email = email;
        this.passwordHash = passwordHash;
        connection = Connector.getConnection();
    }

    public ResultSet getLoginResult()
    {
        if (connection == null)
        {
            return null;
        }

        String sql = QueryBuilder.createLoginQuery(email, passwordHash);

        return Connector.getQueryResult(connection, sql);
    }
}
