package helper;

public class PLZFormatter {
    public static String addLeadingZeros(int plz) {
        String output = "";
        if(plz <= 9) {
            output = "00000" + plz;
        } else if(plz <= 99) {
            output = "0000" + plz;
        } else if(plz <= 999) {
            output = "000" + plz;
        } else if(plz <= 9999) {
            output = "0" + plz;
        } else {
            output = "" + plz;
        }
        return output;
    }
}
