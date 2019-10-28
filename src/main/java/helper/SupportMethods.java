package helper;

import db_connector.Connector;
import java.sql.Connection;
import java.sql.ResultSet;

public class SupportMethods {
    /**
     *
     * @param rs
     * @return rowCount
     */
    public static int getResultSetSize(ResultSet rs) {

        int rowCount = 0;

        if(rs == null) {
            return -1;
        }
        else {
            try {
//                rs.beforeFirst();
                while (rs.next()) {
                    rowCount++;
                }
                rs.beforeFirst();
                return rowCount;
            } catch(Exception e) {
                e.printStackTrace();
            }
            return -2;
        }
    }

    /**
     *
     * @param input
     * @return
     */
    public static String removeHTMLCode(String input)
    {
        return input.replaceAll("[<(|\\n)+?>%\\\\#]", " ");
    }

    /**
     *
     * @param c
     * @param rs
     */
    public static void close(Connection c, ResultSet rs) {
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
    }

    /**
     *
     * @param c
     */
    public static void close(Connection c) {
        Connector.closeConnection(c);
    }

    /**
     *
     * @param rs
     */
    public static void close(ResultSet rs) {
        Connector.closeResultSet(rs);
    }

    /**
     *
     * @param input
     * @return string
     */
    public static String removeSQLInjections(String input) {
        return input.replaceAll("[*#'\\-\\/(),\\[\\]\\^(--)]", " ");
    }
}