package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.RequiredFactoryFailedException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oo.BuchungsPosition;
import oo.Buchungsbeleg;

public class BuchungspositionFactory {
		public static BuchungsPosition[] getBuchungspositionenByBNR(int BNR)
				throws EmptyResultSetException, ResultSetIsNullException, RequiredFactoryFailedException {
				Connection c = Connector.getConnection();
				String sql = QueryBuilder.getBuchungsPositionenByBNR(BNR);
				ResultSet rs = Connector.getQueryResult(c, sql);
				BuchungsPosition[] buchungspositionen = null;

				if(rs == null) {
						throw new ResultSetIsNullException();
				}

				int rsSize = SupportMethods.getResultSetSize(rs);

				if(rsSize < 1) {
						throw new EmptyResultSetException();
				}

				buchungspositionen = new BuchungsPosition[rsSize];
				try{
						int counter = 0;
						while(rs.next()) {
								buchungspositionen[counter] = new BuchungsPosition(
										rs.getInt("PositionsID"),
										rs.getInt("BNR"),
										rs.getInt("SitzID")
								);
								counter++;
						}
				} catch (SQLException e) {
						e.printStackTrace();
						throw new RequiredFactoryFailedException();
				}

				SupportMethods.close(c, rs);
				return buchungspositionen;
		}
}
