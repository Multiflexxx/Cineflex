package helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    private static SimpleDateFormat frontendDate = new SimpleDateFormat("EEE, dd. MMM", new Locale("de", "DE"));
    private static SimpleDateFormat frontendTime = new SimpleDateFormat("HH:mm", new Locale("de", "DE"));
    private static SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sqlTime = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat sqlDateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // private static SimpleDateFormat timestamp = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss", new Locale("de", "DE"));

    /**
     *
     * @param date
     * @return
     */
    public static String getFrontendDate(Date date) {
        return frontendDate.format(date);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getFrontendTime(Date date) {
        return frontendTime.format(date);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getSQLDate(Date date) {
        return sqlDate.format(date);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getSQLTime(Date date) {
        return sqlTime.format(date);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getSQLDateAndTime(Date date) {
        return sqlDateAndTime.format(date);
    }
}
