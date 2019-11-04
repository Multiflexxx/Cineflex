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
		 * Returns a Kunde Object from the Database
		 *
		 * @param PID PID of the person linked to the customer account
		 * @param mockRs For tests
		 * @return Returns a Kunde Object
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
		 * Returns a Kunde Object from the Database
		 *
		 * @param PID PID of the person linked to the customer account
		 * @return Returns a Kunde Object
		 */
		public static Kunde getKunde(int PID) {
				return getKunde(PID, null);
		}

		/**
		 * Returns a Kunde Object from the Database
		 *
		 * @param KID KID of the customer
		 * @param mockRs For tests
		 * @return Returns a Kunde Object
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
		 * Returns a Kunde Object from the Database
		 * @param KID KID of the customer
		 * @return Returns a Kunde Object
		 */
		public static Kunde getKundeByKID(int KID) {
				return getKundeByKID(KID, null);
		}

    /**
     * Adds TP to the account of a customer, in accordance with the entered price
     * @param KID ID of the customer
     * @param preis Price of transaction
     * @return Returns the updated Kunden object
     */
		public static Kunde addTreuepunkte(int KID, float preis) {
				Connection c = Connector.getConnection();
				String sql = QueryBuilder.addTreuepunkte(KID, SupportMethods.preisToTreuepunkte(preis));
				Connector.executeQuery(c, sql);

				return getKundeByKID(KID);
		}

    /**
     * Subtracts TP from a customer, in accordance with the entered price
     * @param KID ID of the customer
     * @param preis Price of the cancelled transaction
     * @return Returns the updated Kunden object
     */
		public static Kunde subtractTreuepunkte(int KID, float preis) {
				Connection c = Connector.getConnection();
				int treuepunkte = SupportMethods.preisToTreuepunkte(preis);

				// Get current amount of Treuepunkte
				Kunde kunde = getKundeByKID(KID);

				// If the amount is lower than the one to subtract, set it to 0
				if (kunde.getTreuepunkte() - treuepunkte < 0) {
						treuepunkte = kunde.getTreuepunkte();
				}
				String sql = QueryBuilder.subtractTreuepunkte(KID, treuepunkte);
				Connector.executeQuery(c, sql);

				return getKundeByKID(KID);
		}

    /**
     * Not implemented yet!
     * @param PID
     */
		public static void deleteKundenkonto(int PID) {
				Connection c = Connector.getConnection();
				String sql = QueryBuilder.deleteAccount(PID);
				Connector.executeQuery(c, sql);
				SupportMethods.close(c);
		}
}
