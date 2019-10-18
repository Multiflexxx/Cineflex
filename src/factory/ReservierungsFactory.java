package factory;

import exception.UnequalParameterLength;
import oo.Reservierungsbeleg;

public class ReservierungsFactory {
  public static int createReservierungsBelege(int[] sitzeIDs, int[] preiseVerIDs, int vorstellungsID, int KNR) {
    if(sitzeIDs.length != preiseVerIDs.length) {
//      throw new UnequalParameterLength();
      return -1;
    }


    return 0;
  }
}
