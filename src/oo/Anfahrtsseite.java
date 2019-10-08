package oo;

import db_connector.Connector;
import db_connector.QueryBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Anfahrtsseite {

    public static String getAnfahrtsseite(String ort) throws UnsupportedEncodingException, SQLException {
        String baseStringGoogle = "https://www.google.com/maps/search/?api=1&query=";
        Connection c = Connector.getConnection();
        ResultSet rs = Connector.getQueryResult(c, QueryBuilder.getKinosByName(ort));
        String variableString = "" + rs.getString("Straße") + rs.getString("Hausnummer") + rs.getString("PLZ");
        variableString = URLEncoder.encode(variableString, "UTF-8");
        Connector.closeConnection(c);
        return("<iframe src=\"" + baseStringGoogle + variableString + "\" width=\"1000\" height=\"1000\"></iframe>");
    }

}
