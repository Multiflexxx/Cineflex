package helper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupportMethods {

    public static int getResultSetSize(ResultSet rs) {

        int rowCount = 0;
        boolean check = false;

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
                check = true;
            }
            try {
                rs.beforeFirst();
            } catch (SQLException e) {
                e.printStackTrace();
                if(!check) {
                    return -2;
                }
            }
            return -3;
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
