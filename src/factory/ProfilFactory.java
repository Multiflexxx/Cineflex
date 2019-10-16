package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;

import java.sql.Connection;

public class ProfilFactory {
    public static void aendereProfil(String vorname, String nachname, String email, String passwort, String strasse, String hausnummer, int PID) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.aendereProfil(vorname, nachname, email, passwort, strasse, hausnummer, PID);
        Connector.executeQuery(c, sql);
        Connector.closeConnection(c);
    }
}
