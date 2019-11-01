package factory;

import Password.PassMD5;
import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.*;
import helper.SupportMethods;
import oo.StayLoggedIn;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StayLoggedInFactory {
    public static StayLoggedIn stayLoggedIn(String email, String passwordHash) throws FailedDataInsertionException, RequiredFactoryFailedException, FailedObjectCreationException {
        String id = "";
        try {
            id = PassMD5.hash(email + passwordHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new FailedDataInsertionException();
        }

        try {
            getStayLoggedInById(id);
        } catch (ResultSetIsNullException | FailedObjectCreationException e) {
            e.printStackTrace();
            throw new RequiredFactoryFailedException();
        } catch (EmptyResultSetException e) {
            // Continue as ususal
        }

        Connection c = Connector.getConnection();
        String sql = QueryBuilder.createStayLoggedIn(id, email, passwordHash);
        Connector.executeQuery(c, sql);

        StayLoggedIn stayLoggedIn = null;
        try {
            stayLoggedIn = getStayLoggedInById(id);
        } catch (ResultSetIsNullException | FailedObjectCreationException | EmptyResultSetException e) {
            e.printStackTrace();
            SupportMethods.close(c);
            throw new FailedObjectCreationException();
        }

        SupportMethods.close(c);
        return stayLoggedIn;
    }

    public static StayLoggedIn getStayLoggedInById(String id) throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getStayLoggedIn(id);
        ResultSet rs = Connector.getQueryResult(c, sql);

        if(rs == null) {
            SupportMethods.close(c, rs);
            throw new ResultSetIsNullException();
        }

        if(SupportMethods.getResultSetSize(rs) < 1) {
            SupportMethods.close(c, rs);
            throw new EmptyResultSetException();
        }

        StayLoggedIn stayLoggedIn = null;
        try{
            rs.next();
            stayLoggedIn = new StayLoggedIn(
                    rs.getString("id"),
                    rs.getString("E-Mail"),
                    rs.getString("passwordHash")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            SupportMethods.close(c, rs);
            throw new FailedObjectCreationException();
        }
        SupportMethods.close(c, rs);
        return stayLoggedIn;
    }

    public static void deleteStayLoggedIn(String id) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.deleteStayLoggedIn(id);
        Connector.executeQuery(c, sql);
        SupportMethods.close(c);
    }

}
