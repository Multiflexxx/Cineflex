package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import oo.Kunde;
import oo.UserLogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KundenFactory {

    public static Kunde getKunde(int PID) {
        Kunde kunde = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getKundeByPID(PID);
        ResultSet rs = Connector.getQueryResult(c, sql);

        if (rs != null) {
            try {
                rs.next();
                kunde = new Kunde(null, rs.getString("E-Mail"), rs.getString("Vorname"), rs.getString("Nachname"), rs.getString("Passwort"), rs.getString("GebDatum"), rs.getInt("PID"), rs.getString("Straße"), rs.getInt("Hausnummer"), rs.getInt("KID"), rs.getInt("Treuepunkte"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Connector.closeConnection(c);
        Connector.closeResultSet(rs);

        return kunde;
    }

    public static Kunde getKundeByKID(int KID) {
        Kunde kunde = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getKundeByKID(KID);
        ResultSet rs = Connector.getQueryResult(c, sql);

        if (rs != null) {
            try {
                rs.next();
                kunde = new Kunde(null, rs.getString("E-Mail"), rs.getString("Vorname"), rs.getString("Nachname"), rs.getString("Passwort"), rs.getString("GebDatum"), rs.getInt("PID"), rs.getString("Straße"), rs.getInt("Hausnummer"), rs.getInt("KID"), rs.getInt("Treuepunkte"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Connector.closeConnection(c);
        Connector.closeResultSet(rs);

        return kunde;
    }
}
