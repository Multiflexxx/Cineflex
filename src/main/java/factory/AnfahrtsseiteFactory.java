package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnfahrtsseiteFactory {

    /**
     * Returns an iframe containing the Google Maps Data for the currently selected location
     *
     * @param ort    current location
     * @param mockRs For mocking and testing
     * @return Returns the iframe as a String
     * @throws UnsupportedEncodingException
     * @throws SQLException
     */
    public static String getAnfahrtsseite(String ort, ResultSet mockRs) throws UnsupportedEncodingException, SQLException {

        String baseStringGoogle = "https://www.google.com/maps/embed/v1/place?key=AIzaSyCM1EeAy6KlTOa-jHIsL_rCEhDghnqZ5Y8&q=";
        Connection c = Connector.getConnection();
        ResultSet rs = null;

        // Set Mock Resultset, if available
        if (mockRs == null) {
            rs = Connector.getQueryResult(c, QueryBuilder.getKinosByName(ort));
        } else {
            rs = mockRs;
        }

        rs.next();
        String variableString = rs.getString("Straße") + " " + rs.getString("Hausnummer") + " " + rs.getString("PLZ");
        variableString = URLEncoder.encode(variableString, "UTF-8");
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
        return ("<iframe src=\"" + baseStringGoogle + variableString + "\" width=\"1000\" height=\"1000\"></iframe>");
    }

    /**
     * Overloaded Method head for normal operation, return an iframe for currently selected location
     *
     * @param ort current location
     * @return Return and iframe as a String
     * @throws UnsupportedEncodingException
     * @throws SQLException
     */
    public static String getAnfahrtseite(String ort) throws UnsupportedEncodingException, SQLException {
        return getAnfahrtsseite(ort, null);
    }

}
