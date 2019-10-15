package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreisFactory
{
    public PreisFactory()
    {

    }

    public String[] getPreisJSONArray()
    {
        Connection connection = Connector.getConnection();
        String sql = QueryBuilder.getPreiseInfos();
        ResultSet resultSet = Connector.getQueryResult(connection, sql);

        SupportMethods supportMethods = new SupportMethods();

        int lResultLength = supportMethods.getResultSetSize(resultSet);

        String[] lJSONDataArray = null;

        float grundPreis = returnGrundpreis();

        if(lResultLength > 0) {

            lJSONDataArray = new String[lResultLength];
            lJSONDataArray[0] = "{'tooltip': 'null', 'beschreibung': 'Normalpreis', 'preis': " + grundPreis + "}";

            int counter = 1;

            try
            {
                while (resultSet.next())
                {
                    if(!resultSet.getString("Änderungsbeschreibung").equals("Grundpreis"))
                    {
                        String lHelperString = "{'tooltip': '";
                        lHelperString += resultSet.getString("TooltipDeskriptor");
                        lHelperString += "', 'beschreibung': '";
                        lHelperString += resultSet.getString("Änderungsbeschreibung");
                        lHelperString += "', 'preis': ";
                        lHelperString += (grundPreis + resultSet.getFloat("Änderungswert"));
                        lHelperString += "}";

                        lJSONDataArray[counter] = lHelperString;

                        counter++;
                    }
                }
            }

            catch (Exception e)
            {
                return null;
            }
        }

        return lJSONDataArray;
    }

    public int getPreiskategorienLaenge()
    {
        int resultLength = -1;

        Connection connection = Connector.getConnection();
        String sql = QueryBuilder.getPreiseInfos();
        ResultSet resultSet = Connector.getQueryResult(connection, sql);

        SupportMethods supportMethods = new SupportMethods();

        resultLength = supportMethods.getResultSetSize(resultSet);

        if(resultLength > 0)
        {
            Connector.closeConnection(connection);
            return resultLength;
        }

        return -2;
    }

    private float returnGrundpreis()
    {
        int lHelp = 0;

        Connection connection = Connector.getConnection();
        String sql = QueryBuilder.getGrundPreis();
        ResultSet resultSet = Connector.getQueryResult(connection, sql);

        SupportMethods supportMethods = new SupportMethods();

        lHelp = supportMethods.getResultSetSize(resultSet);

        if(lHelp > 0)
        {
            try
            {
                while (resultSet.next())
                {
                    float gp = resultSet.getFloat("Änderungswert");
                    Connector.closeConnection(connection);
                    return gp;
                }
            }

            catch (Exception e)
            {
                Connector.closeConnection(connection);
                resultSet = null;
                return -1;
            }
        }

        else
        {
            Connector.closeConnection(connection);
            resultSet = null;
            return  -2;
        }

        return -3;
    }


    /*int resultLength = 0;
    Connection connection;
    ResultSet resultSet;
    String sql;

    public PreisFactory(int vorstellungsID)
    {
        connection = Connector.getConnection();
        sql = QueryBuilder.getSeatInfo(vorstellungsID);
        resultSet = Connector.getQueryResult(connection, sql);

        SupportMethods supportMethods = new SupportMethods();

        resultLength = supportMethods.getResultSetSize(resultSet);
    }

    public String getPreisJSON()
    {
        if(resultLength > 0)
        {


            return ";";
        }

        else
        {
            return "null";
        }

    }

    public int getPreiskategorienLaenge()
    {
        return resultLength;
    }*/
}
