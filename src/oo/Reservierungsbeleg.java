package oo;

public class Reservierungsbeleg extends Beleg {

  public Reservierungsbeleg(int belegID, float preis, Vorstellung vorstellung, Kunde kunde,
      Sitz[] sitzauswahl, String uhrzeit) {
    super(belegID, preis, vorstellung, kunde, sitzauswahl, uhrzeit);
  }

}
