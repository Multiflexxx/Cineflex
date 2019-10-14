package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;

import java.sql.Connection;
import java.sql.ResultSet;

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
    public PreisFactory()
    {

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
