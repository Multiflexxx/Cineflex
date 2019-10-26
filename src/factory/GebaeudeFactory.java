package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.FailedObjectCreationException;
import exception.ResultSetIsNullException;
import helper.SupportMethods;
import java.sql.SQLException;
import oo.Gebaeude;

import java.sql.Connection;
import java.sql.ResultSet;

public class GebaeudeFactory {
    /**
     *
     * @param mockRs
     * @return Gebaeude[]
     */
    public static Gebaeude[] getGebaeude(ResultSet mockRs) {
        Gebaeude[] gebäude = null;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.showAllCinemas();
        ResultSet rs = null;

        if(mockRs == null)
        {
            rs = Connector.getQueryResult(c, sql);
        }

        else
        {
            rs = mockRs;
        }

        if (rs != null) {
            int rsSize = SupportMethods.getResultSetSize(rs);
            /*try {
                rs.beforeFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            if (rsSize > 0) {
                //int counter = 0;
                gebäude = new Gebaeude[rsSize];
                try {
                    //while (rs.next()) {
                    for(int i = 0; i < rsSize; i++){
                        rs.next();
                        int gebID = rs.getInt("GebäudeId");
                        String strasse = rs.getString("Straße");
                        int hausnummer = rs.getInt("Hausnummer");
                        int plz = rs.getInt("PLZ");
                        String ort = rs.getString("Ort.Ortsname");

                        gebäude[i] = new Gebaeude(gebID, strasse, hausnummer, plz, ort);

                        //counter++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SupportMethods.close(c, rs);
            return gebäude;
        } else {
            SupportMethods.close(c, rs);
            return gebäude;
        }
    }

    /**
     *
     * @return Gebaeude[]
     */
    public static Gebaeude[] getGebaeude()
    {
        return getGebaeude(null);
    }

    /**
     *
     * @param id
     * @return
     * @throws ResultSetIsNullException
     * @throws EmptyResultSetException
     * @throws FailedObjectCreationException
     */
    public static Gebaeude getGebaeudeById(int id)
        throws ResultSetIsNullException, EmptyResultSetException, FailedObjectCreationException {
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getGebaeudeById(id);
        ResultSet rs = Connector.getQueryResult(c, sql);
        Gebaeude gebaeude = null;

        if(rs == null) {
            throw new ResultSetIsNullException();
        }

        if(SupportMethods.getResultSetSize(rs) < 1) {
            throw new EmptyResultSetException();
        }

        try {
            rs.next();
            gebaeude = new Gebaeude(
                rs.getInt("GebäudeID"),
                rs.getString("Straße"),
                rs.getInt("Hausnummer"),
                rs.getInt("PLZ"),
                OrtsFactory.getOrtByPLZ(rs.getInt("PLZ")).getOrtsName()
            );
        }catch(SQLException e) {
            e.printStackTrace();
            throw new FailedObjectCreationException();
        }

        SupportMethods.close(c, rs);
        return gebaeude;
    }
}
