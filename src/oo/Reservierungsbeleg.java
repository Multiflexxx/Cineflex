package oo;

public class Reservierungsbeleg extends Beleg {

  public Reservierungsbeleg(int belegID, float preis, Vorstellung vorstellung, Sitz[] sitzauswahl) {
    super(belegID, preis, vorstellung, sitzauswahl);
  }
}
