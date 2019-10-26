package helper;

public class ExceptionHandler {
    /**
     *
     * @param e
     * @return output
     */
    public static String exceptionStackTraceToString(Exception e) {
        String output = "Exception has occured: ";
        output += e.toString() + "<br />";
        for(int i = 0; i < e.getStackTrace().length; i ++) {
            output += e.getStackTrace()[i].toString() + "<br />";
        }
        return output;
    }
}
