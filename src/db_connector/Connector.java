package db_connector;

import java.sql.*;

public class Connector {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection c = null;

        Class.forName("com.mysql.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cineflex?allowMultiQueries=true", "multiflex", "multiflexxx123");

        return c;
    }

    public static void executeQuery(Connection c, String sql) {
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static void closeConnection(Connection c) {
        if(c != null) {
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
