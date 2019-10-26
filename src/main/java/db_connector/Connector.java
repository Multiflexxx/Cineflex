package db_connector;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Connector {
    /**
     *
     * @return c
     */
    public static Connection getConnection() {
        // TODO: DO NOT REMOVE
        // Create new properties
        /*Properties prop = new Properties();

        try
        {
            // read login data from file
            prop.load(new FileInputStream("/usr/local/tomcat/conf/databaseLoginData.properties"));
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        // set login data
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String port = prop.getProperty("port");
        String database = prop.getProperty("database");
        String host = prop.getProperty("host");
        */

        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //c = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+"?allowMultiQueries=true", username, password);
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cineflex?allowMultiQueries=true", "multiflex", "multiflexxx123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     *
     * @param c
     * @param sql
     */
    public static void executeQuery(Connection c, String sql) {
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param c
     * @param sql
     * @return rs
     */
    public static ResultSet getQueryResult(Connection c, String sql) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  rs;
    }

    /**
     *
     * @param c
     */
    public static void closeConnection(Connection c) {
        if(c != null) {
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param rs
     */
    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
