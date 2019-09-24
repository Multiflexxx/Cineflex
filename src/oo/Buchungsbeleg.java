package oo;

public class Buchungsbeleg extends Beleg {

  public Buchungsbeleg(int belegID, float preis, Vorstellung vorstellung, Kunde kunde,
      Sitz[] sitzauswahl) {
    super(belegID, preis, vorstellung, kunde, sitzauswahl);
  }

}
