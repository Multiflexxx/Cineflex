package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.EmptyResultSetException;
import exception.RequiredFactoryFailedException;
import exception.ResultSetIsNullException;
import helper.ArrayBuilder;
import helper.SupportMethods;
import oo.Preisänderung;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;

public class PreisFactory {
    /**
     * Empty Constructor
     */
    public PreisFactory() {

    }

    /**
     *
     * @param id
     * @return lJSONDataArray
     */
    public static String[] getPreisJSONArray(int id) {
        Connection connection = Connector.getConnection();
        String sql = QueryBuilder.getPreiseInfos();
        ResultSet resultSet = Connector.getQueryResult(connection, sql);

        int lResultLength = SupportMethods.getResultSetSize(resultSet);

        String[] lJSONDataArray = null;

        float[] grundPreis = returnGrundpreis(id);

        if (lResultLength > 0) {

            lJSONDataArray = new String[lResultLength];
            //lJSONDataArray[0] = "{'tooltip': 'null', 'id': 0, 'beschreibung': 'Normalpreis', 'preis': " + grundPreis[0] + ", 'preisL':  " + grundPreis[1] + "}";

            int counter = 0;

            try {
                while (resultSet.next()) {
                    String lHelperString = "{'tooltip': '";
                    lHelperString += resultSet.getString("TooltipDeskriptor");
                    lHelperString += "', 'id': '";
                    lHelperString += resultSet.getString("PreisänderungsID");
                    lHelperString += "', 'beschreibung': '";
                    lHelperString += resultSet.getString("Änderungsbeschreibung");
                    lHelperString += "', 'preis': ";
                    lHelperString += (grundPreis[0] + resultSet.getFloat("Änderungswert"));
                    lHelperString += ", 'preisL': ";
                    lHelperString += (grundPreis[1] + resultSet.getFloat("Änderungswert"));
                    lHelperString += "}";

                    lJSONDataArray[counter] = lHelperString;

                    counter++;
                }
            } catch (Exception e) {
                return null;
            }
        }

        return lJSONDataArray;
    }

    /**
     *
     * @return resultLength
     */
    public static int getPreiskategorienLaenge() {
        int resultLength = -1;

        Connection connection = Connector.getConnection();
        String sql = QueryBuilder.getPreiseInfos();
        ResultSet resultSet = Connector.getQueryResult(connection, sql);

        resultLength = SupportMethods.getResultSetSize(resultSet);

        if (resultLength > 0) {
            Connector.closeConnection(connection);
            return resultLength;
        }

        return -2;
    }

    /**
     * 0 = Parket
     * 1 = Loge
     * @param id
     * @return grundpreis
     */
    private static float[] returnGrundpreis(int id) {
        int lHelp0 = 0;
        int lHelp1 = 0;
        float [] grundpreis = new float[2];
        float gp = 0;
        int dauer = 0;
        int dreid = -1;


        Connection connection = Connector.getConnection();
        String sql0 = QueryBuilder.getGrundPreis(id);
        ResultSet resultSet0 = Connector.getQueryResult(connection, sql0);
        String sql1 = QueryBuilder.getPreisveränderungen();
        ResultSet resultSet1 = Connector.getQueryResult(connection, sql1);

        lHelp0 = SupportMethods.getResultSetSize(resultSet0);
        lHelp1 = SupportMethods.getResultSetSize(resultSet1);

        if (lHelp0 > 0) {
            try {
                while (resultSet0.next()) {
                    gp = resultSet0.getFloat("Grundpreis");
                    dauer = resultSet0.getInt("Dauer");
                    dreid = resultSet0.getInt("3D");
                }
            } catch (Exception e) {
                Connector.closeConnection(connection);
                resultSet0 = null;
                grundpreis[0]= -1;
                return grundpreis;
            }
        } else {
            Connector.closeConnection(connection);
            resultSet0 = null;
            grundpreis[0]= -2;
            return grundpreis;
        }

        float[] ap = new float[lHelp1];

        if (lHelp1 > 0) {
            try {
                int counter1 = 0;
                while (resultSet1.next()) {
                    ap[counter1] = resultSet1.getFloat("Änderungswert");
                    counter1++;
                }
                if (dreid == 1) {
                    gp += ap[1];
                }
                if (dauer - 120 > 0) {
                    gp += ap[2];
                }

                grundpreis[0] = gp;
                gp += ap[0];
                grundpreis[1] = gp;
                return grundpreis;
            } catch (Exception e) {
                Connector.closeConnection(connection);
                resultSet1 = null;
                grundpreis[0]= -1;
                return grundpreis;
            }
        } else {
            Connector.closeConnection(connection);
            resultSet1 = null;
            grundpreis[0]= -2;
            return grundpreis;
        }
    }

    /**
     *
     * @param seats
     * @param preisänderungen
     * @param filmID
     * @return returnValue
     * @throws RequiredFactoryFailedException
     */
    public static float getBuchungsPreis(String seats, String preisänderungen, int filmID) throws RequiredFactoryFailedException{
        float returnValue = 0;
        int[] seatIDs = ArrayBuilder.stringToIntArray(seats, ",");
        int[] preisVerIDs = ArrayBuilder.stringToIntArray(preisänderungen, ",");
        // 0: Parket
        // 1: Loge
        float[] filmGrundpreise = returnGrundpreis(filmID);
        Connection c = Connector.getConnection();

        for(int i = 0; i < seatIDs.length; i++)  {
            try {
                returnValue += (SitzFactory.getSitzById(seatIDs[i]).getSitzklasse() == "L".charAt(0) ? filmGrundpreise[1] : filmGrundpreise[0]);
                if(preisVerIDs[i] != 0) {
                    Preisänderung p = PreisänderungsFactory.getPreisänderungById(preisVerIDs[i]);
                    returnValue += p.getÄnderungswert();
                }
            } catch (ResultSetIsNullException e) {
                e.printStackTrace();
                throw new RequiredFactoryFailedException();
            } catch (EmptyResultSetException e) {
                e.printStackTrace();
                throw new RequiredFactoryFailedException();
            }
        }

        return returnValue;
    }
}
