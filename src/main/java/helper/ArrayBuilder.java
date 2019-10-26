package helper;

public class ArrayBuilder {
    /**
     *
     * @param string
     * @param del
     * @return arr
     */
    public static int[] stringToIntArray(String string, String del) {
        String[] splitString = string.split(del);
        int[] arr = new int[splitString.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(splitString[i]);
        }

        return arr;
    }

    /**
     *
     * @param string
     * @return stringToIntArray(string)
     */
    public static int[] stringToIntArray(String string) {
        return stringToIntArray(string, ",");
    }

    /**
     *
     * @param arr
     * @param del
     * @return output
     */
    public static String intArrayToString(int[] arr, String del) {
        String output = "";
        for(int i = 0; i < arr.length; i++) {
            if(i != arr.length - 1) {
                output += arr[i] + del;
            } else {
                output += arr[i];
            }
        }
        return output;
    }

    /**
     *
     * @param arr
     * @return intArrayToString
     */
    public static String intArrayToString(int[] arr) {
    return intArrayToString(arr, ", ");
}
}
