package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import oo.Kunde;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KundenFactory {

		/**
		 * @return kunde
		 */
		public static Kunde getKunde(int PID, ResultSet mockRs) {
				Kunde kunde = null;
				Connection c = Connector.getConnection();
				String sql = QueryBuilder.getKundeByPID(PID);

				ResultSet rs = null;

				if (mockRs == null) {
						rs = Connector.getQueryResult(c, sql);
				} else {
						rs = mockRs;
				}

				if (rs != null) {
						try {
								rs.next();
								kunde = new Kunde(
								    null,
                    rs.getString("E-Mail"),
                    rs.getString("Vorname"),
										rs.getString("Nachname"),
                    rs.getString("Passwort"),
                    rs.getString("GebDatum"),
										rs.getInt("PID"),
                    rs.getString("Straße"),
                    rs.getInt("Hausnummer"),
										rs.getInt("KID"),
                    rs.getInt("Treuepunkte"));
						} catch (SQLException e) {
								e.printStackTrace();
						}
				}

				Connector.closeConnection(c);
				Connector.closeResultSet(rs);

				return kunde;
		}

		/**
		 * @return Kunde
		 */
		public static Kunde getKunde(int PID) {
				return getKunde(PID, null);
		}

		/**
		 * @return kunde
		 */
		public static Kunde getKundeByKID(int KID, ResultSet mockRs) {
				Kunde kunde = null;
				Connection c = Connector.getConnection();
				String sql = QueryBuilder.getKundeByKID(KID);

				ResultSet rs = null;

				if (mockRs == null) {
						rs = Connector.getQueryResult(c, sql);
				} else {
						rs = mockRs;
				}

				if (rs != null) {
						try {
								rs.next();
								kunde = new Kunde(null, rs.getString("E-Mail"), rs.getString("Vorname"),
										rs.getString("Nachname"), rs.getString("Passwort"), rs.getString("GebDatum"),
										rs.getInt("PID"), rs.getString("Straße"), rs.getInt("Hausnummer"),
										rs.getInt("KID"), rs.getInt("Treuepunkte"));
						} catch (SQLException e) {
								e.printStackTrace();
						}
				}

				Connector.closeConnection(c);
				Connector.closeResultSet(rs);

				return kunde;
		}

		/**
		 * @return Kunde
		 */
		public static Kunde getKundeByKID(int KID) {
				return getKundeByKID(KID, null);
		}

		public static Kunde addTreuepunkte(int KID, float preis) {
		    Connection c = Connector.getConnection();
		    String sql = QueryBuilder.addTreuepunkte(KID, SupportMethods.preisToTreuepunkte(preis));
		    Connector.executeQuery(c, sql);

		    return getKundeByKID(KID);
    }

    public static Kunde subtractTreuepunkte(int KID, float preis) {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.subtractTreuepunkte(KID, SupportMethods.preisToTreuepunkte(preis));
        Connector.executeQuery(c, sql);

        return getKundeByKID(KID);
    }
}
