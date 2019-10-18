package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnfahrtsseiteFactory {

    public static String getAnfahrtsseite(String ort, ResultSet mockRs) throws UnsupportedEncodingException, SQLException {

        String baseStringGoogle = "https://www.google.com/maps/embed/v1/place?key=AIzaSyCM1EeAy6KlTOa-jHIsL_rCEhDghnqZ5Y8&q=";
        Connection c = Connector.getConnection();
        ResultSet rs = null;

        // Set Mock Resultset, if available
        if(mockRs == null)
        {
            rs = Connector.getQueryResult(c, QueryBuilder.getKinosByName(ort));
        }

        else
        {
            rs = mockRs;
        }

        rs.next();
        String variableString = rs.getString("Straße") + " " + rs.getString("Hausnummer") + " " + rs.getString("PLZ");
        variableString = URLEncoder.encode(variableString, "UTF-8");
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
        return ("<iframe src=\"" + baseStringGoogle + variableString + "\" width=\"1000\" height=\"1000\"></iframe>");
    }

    public static String getAnfahrtseite(String ort) throws UnsupportedEncodingException, SQLException
    {
        return getAnfahrtsseite(ort, null);
    }

}
