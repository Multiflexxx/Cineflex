package db_connector;

import java.sql.*;

public class Connector {
    /**
     *
     * @return c
     */
    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
