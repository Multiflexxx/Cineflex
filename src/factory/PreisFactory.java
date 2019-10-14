package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CineflexV1;
 * <p>
 * Copyright by @author Marcel Mertens
 * Website: https://mertens-web.ddns.net
 * <p>
 * Date: 14.10.2019
 */
public class PreisFactory
{
    int resultLength = 0;
    Connection connection;
    ResultSet resultSet;
    String sql;

    float grundPreis = 0;

    public PreisFactory()
    {
        connection = Connector.getConnection();
        sql = QueryBuilder.getPreiseLaenge();
        resultSet = Connector.getQueryResult(connection, sql);

        SupportMethods supportMethods = new SupportMethods();

        resultLength = supportMethods.getResultSetSize(resultSet);

        Connector.closeConnection(connection);
        resultSet = null;
    }

    public String getPreisJSON()
    {
        connection = Connector.getConnection();
        sql = QueryBuilder.getPreiseLaenge();
        resultSet = Connector.getQueryResult(connection, sql);

        SupportMethods supportMethods = new SupportMethods();

        int lResultLength = supportMethods.getResultSetSize(resultSet);

        //var preistypNor = {
        //                                'beschreibung': "Normalpreis",
        //                                'preis': 10,
        //                            };
        //
        //                            var preistypJun = {
        //                                'beschreibung': "Studenten / Schülerpreis",
        //                                'preis': 7,
        //                            };
        //
        //                            var preistypSen = {
        //                                'beschreibung': "Seniorenpreis",
        //                                'preis': 8,
        //                            };
        //
        //                            var preistyp = [preistypNor, preistypJun, preistypSen];


        String dataJSONString = "[";
        dataJSONString += "{'beschreibung': 'Normalpreis', 'preis': " + returnGrundpreis() + "}";

        if(lResultLength > 0) {

            try
            {
                while (resultSet.next())
                {
                    dataJSONString += "{'beschreibung': '";
                    dataJSONString += resultSet.getString("Änderungsbeschreibung");
                    dataJSONString += "', 'preis': ";
                    dataJSONString += grundPreis + Float.parseFloat(resultSet.getString("Änderungswert"));
                    dataJSONString += "}";
                }

            }

            catch (Exception e)
            {

            }

        }

        dataJSONString += "]";

        return dataJSONString;
    }

    private float returnGrundpreis()
    {
        int lHelp = 0;

        connection = Connector.getConnection();
        sql = QueryBuilder.getGrundPreis();
        resultSet = Connector.getQueryResult(connection, sql);

        SupportMethods supportMethods = new SupportMethods();

        lHelp = supportMethods.getResultSetSize(resultSet);

        if(lHelp > 0)
        {
            try
            {
                while (resultSet.next())
                {
                    return Float.parseFloat(resultSet.getString("Änderungswert"));
                }
            }

            catch (Exception e)
            {
                Connector.closeConnection(connection);
                resultSet = null;
                return -1;
            }

            Connector.closeConnection(connection);
            resultSet = null;
            return grundPreis;
        }

        else
        {
            Connector.closeConnection(connection);
            resultSet = null;
            return  -1;
        }
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
