package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.*;
import helper.SupportMethods;
import oo.BuchungsStornierung;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oo.Buchungsbeleg;

public class BuchungsStornierungFactory {

		public static BuchungsStornierung createStornierung(int BNR)
				throws RequiredFactoryFailedException, FailedDataInsertionException {
				Connection c = Connector.getConnection();
				String sql = QueryBuilder.createBuchungsStornierung(BNR);
				Connector.executeQuery(c, sql);

				SupportMethods.close(c);

				BuchungsStornierung buchungsStornierung = null;

				try {
						buchungsStornierung = getBuchungsStornierungByBNR(BNR);
				} catch (ResultSetIsNullException | FailedObjectCreationException e) {
						e.printStackTrace();
						throw new RequiredFactoryFailedException();
				} catch (EmptyResultSetException e) {
						e.printStackTrace();
						throw new FailedDataInsertionException();
				}

				Buchungsbeleg buchungsbeleg = BuchungsFactory.getBuchungsbelegByBNR(BNR);
				if(buchungsbeleg != null) {
						KundenFactory.subtractTreuepunkte(buchungsbeleg.getKunde().getKundenID(), buchungsbeleg.getPreis());
				}

				return buchungsStornierung;
		}

		public static BuchungsStornierung getBuchungsStornierungByStrnNR(int StrnNR)
				throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
				Connection c = Connector.getConnection();
				String sql = QueryBuilder.getBuchungsStornierungByStrnNR(StrnNR);
				ResultSet rs = Connector.getQueryResult(c, sql);
				BuchungsStornierung buchungsStornierung = null;
				if (rs == null) {
						throw new ResultSetIsNullException();
				}

				if (SupportMethods.getResultSetSize(rs) < 1) {
						throw new EmptyResultSetException();
				}

				try {
						rs.next();
						buchungsStornierung = new BuchungsStornierung(
								rs.getInt("BNR"),
								rs.getInt("StrnNR")
						);
				} catch (SQLException e) {
						e.printStackTrace();
						throw new FailedObjectCreationException();
				}

				return buchungsStornierung;
		}

		public static BuchungsStornierung getBuchungsStornierungByBNR(int BNR)
				throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
				Connection c = Connector.getConnection();
				String sql = QueryBuilder.getBuchungsStornierungByBNR(BNR);
				ResultSet rs = Connector.getQueryResult(c, sql);
				BuchungsStornierung buchungsStornierung = null;
				if (rs == null) {
						throw new ResultSetIsNullException();
				}

				if (SupportMethods.getResultSetSize(rs) < 1) {
						throw new EmptyResultSetException();
				}

				try {
						rs.next();
						buchungsStornierung = new BuchungsStornierung(
								rs.getInt("BNR"),
								rs.getInt("StrnNR")
						);
				} catch (SQLException e) {
						e.printStackTrace();
						throw new FailedObjectCreationException();
				}

				return buchungsStornierung;
		}

		public static BuchungsStornierung[] getBuchungsStornierungByKID(int KID)
				throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
				Connection c = Connector.getConnection();
				String sql = QueryBuilder.getBuchungsStornierungByKID(KID);
				ResultSet rs = Connector.getQueryResult(c, sql);
				BuchungsStornierung[] buchungsStornierung = null;

				if (rs == null) {
						throw new ResultSetIsNullException();
				}

				int rsSize = SupportMethods.getResultSetSize(rs);
				if (rsSize < 1) {
						throw new EmptyResultSetException();
				}

				try {
						int counter = 0;
						while (rs.next()) {
								buchungsStornierung[counter] = new BuchungsStornierung(
										rs.getInt("BNR"),
										rs.getInt("StrnNR")
								);
								counter++;
						}
				} catch (SQLException e) {
						e.printStackTrace();
						throw new FailedObjectCreationException();
				}

				return buchungsStornierung;
		}
}
