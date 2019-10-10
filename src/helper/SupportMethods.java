package helper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupportMethods {

    public static int getResultSetSize(ResultSet rs) {

        int rowCount = 0;

        if(rs == null) {
            return -1;
        }
        else {
            try {
                while (rs.next()) {
                    rowCount++;
                }
                rs.beforeFirst();
                return rowCount;
            } catch(Exception e) {
                e.printStackTrace();
            }
            try {
                rs.beforeFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return -2;
        }

            /*if (rs != null) {
                if (rs.last()) {
                    int size = rs.getRow();
                    rs.beforeFirst();
                    return size;
                }
            }*/

    }
}
