package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;

import java.sql.Connection;

public class ProfilFactory {
    /**
     *
     * @param vorname
     * @param nachname
     * @param email
     * @param passwort
     * @param strasse
     * @param hausnummer
     * @param PID
     */
    public static void aendereProfil(String vorname, String nachname, String email, String passwort, String strasse, int hausnummer, int PID) {
        vorname = SupportMethods.removeHTMLCode(vorname);
        vorname = SupportMethods.removeSQLInjections(vorname);
        nachname = SupportMethods.removeHTMLCode(nachname);
        nachname = SupportMethods.removeSQLInjections(nachname);
        email = SupportMethods.removeSQLInjections(email);
        email = SupportMethods.removeHTMLCode(email);
        vorname = SupportMethods.removeHTMLCode(vorname);
        vorname = SupportMethods.removeSQLInjections(vorname);

        Connection c = Connector.getConnection();
        String sql = QueryBuilder.aendereProfil(vorname, nachname, email, passwort, strasse, hausnummer, PID);
        Connector.executeQuery(c, sql);
        Connector.closeConnection(c);
    }
}
