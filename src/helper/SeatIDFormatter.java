package helper;

/**
 * CineflexV1;
 * <p>
 * Copyright by @author Marcel Mertens
 * Website: https://mertens-web.ddns.net
 * <p>
 * Date: 14.10.2019
 */
public class SeatIDFormatter
{
    public static int[] seatsStringToIntArray(String seatIDString)
    {
        String[] values = seatIDString.split("\\|");
        int arrayLength = 0;

        for (int i = 0; i < values.length; i++)
        {
            if(!values[i].equals("-1"))
            {
                arrayLength++;
            }
        }

        int[] seatIDs = new int[arrayLength];

        int seatsIDCounter = 0;

        for (int i = 0; i < values.length; i++)
        {
            if(!values[i].equals("-1"))
            {
                seatIDs[seatsIDCounter] = Integer.parseInt(values[i]);
                seatsIDCounter++;
            }
        }

        if (arrayLength > 0)
        {
            return seatIDs;
        }

        else
        {
            return null;
        }
    }
}
