package factory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupportMethods {
    static int getResultSetSize(ResultSet rs) {
        try {
            if (rs != null) {
                if (rs.last()) {
                    int size = rs.getRow();
                    rs.beforeFirst();
                    return size;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
