package oo;

import java.util.Date;

public class Reservierungsbeleg extends Beleg {

  /**
   *
   * @param belegID
   * @param preis
   * @param vorstellung
   * @param kunde
   * @param uhrzeit
   */
  public Reservierungsbeleg(int belegID, float preis, Vorstellung vorstellung, Kunde kunde, Date uhrzeit) {
    super(belegID, preis, vorstellung, kunde, uhrzeit);
  }

}
