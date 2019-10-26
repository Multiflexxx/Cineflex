package helper;

public class PLZFormatter {
    /**
     *
     * @param plz
     * @return output
     */
    public static String addLeadingZeros(int plz) {
        String output = "";
        if(plz <= 9) {
            output = "0000" + plz;
        } else if(plz <= 99) {
            output = "000" + plz;
        } else if(plz <= 999) {
            output = "00" + plz;
        } else if(plz <= 9999) {
            output = "0" + plz;
        } else {
            output = "" + plz;
        }
        return output;
    }
}
