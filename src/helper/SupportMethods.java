package helper;

import db_connector.Connector;
import java.sql.Connection;
import java.sql.ResultSet;

public class SupportMethods {
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

    public static String removeHTMLCode(String input)
    {
        return input.replaceAll("[<(|\\n)+?>%\\\\#]", " ");
    }

    public static void close(Connection c, ResultSet rs) {
        Connector.closeResultSet(rs);
        Connector.closeConnection(c);
    }

    public static void close(Connection c) {
        Connector.closeConnection(c);
    }

    public static void close(ResultSet rs) {
        Connector.closeResultSet(rs);
    }

    public static String removeSQLInjections(String input) {
        return input.replaceAll("[*#'\\-\\/(),\\[\\]\\^(--)]", " ");
    }
}
