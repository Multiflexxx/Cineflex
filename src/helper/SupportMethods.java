package helper;

import java.sql.ResultSet;

public class SupportMethods {
    public int getResultSetSize(ResultSet rs) {

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
        return input.replaceAll("[<(.|\\n)+?>%\\\\#]", "");
    }
}
