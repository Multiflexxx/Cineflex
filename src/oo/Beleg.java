package oo;

public abstract class Beleg {
  int belegID;
  float preis;
  Vorstellung vorstellung;
  Kunde kunde;
  Sitz[] sitzauswahl;

  public Beleg() {
  }

  public Beleg(int belegID, float preis, Vorstellung vorstellung, Kunde kunde, Sitz[] sitzauswahl) {
    belegID = belegID;
    this.preis = preis;
    this.vorstellung = vorstellung;
    this.kunde = kunde;
    this.sitzauswahl = sitzauswahl;
  }

  public int getBelegID() {
    return belegID;
  }

  public float getPreis() {
    return preis;
  }

  public Vorstellung getVorstellung() {
    return vorstellung;
  }

  public Kunde getKunde() {
    return kunde;
  }

  public Sitz[] getSitzauswahl() {
    return sitzauswahl;
  }

  public void setBelegID(int belegID) {
    belegID = belegID;
  }

  public void setPreis(float preis) {
    this.preis = preis;
  }

  public void setVorstellung(Vorstellung vorstellung) {
    this.vorstellung = vorstellung;
  }

  public void setKunde(Kunde kunde) {
    this.kunde = kunde;
  }

  public void setSitzauswahl(Sitz[] sitzauswahl) {
    this.sitzauswahl = sitzauswahl;
  }
}
