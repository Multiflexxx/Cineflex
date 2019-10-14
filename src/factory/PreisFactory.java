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
        if(resultLength > 0) {

            try
            {
                while (resultSet.next())
                {

                    resultSet.getString("PreisänderunggsID");
                    resultSet.getString("Änderungswert");
                    resultSet.getString("Änderungsbeschreibung");
                }

            }

            catch (Exception e)
            {

            }

        }

        return "";
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
