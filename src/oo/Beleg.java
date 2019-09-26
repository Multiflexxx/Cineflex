package oo;

public abstract class Beleg {
  int belegID;
  float preis;
  Vorstellung vorstellung;
  Kunde kunde;
  Sitz[] sitzauswahl;
  String uhrzeit;

  public Beleg() {
  }

  public Beleg(int belegID, float preis, Vorstellung vorstellung, Kunde kunde, Sitz[] sitzauswahl, String uhrzeit) {
    this.belegID = belegID;
    this.preis = preis;
    this.vorstellung = vorstellung;
    this.kunde = kunde;
    this.sitzauswahl = sitzauswahl;
    this.uhrzeit = uhrzeit;
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

  public String getUhrzeit() {
    return uhrzeit;
  }

  public void setBelegID(int belegID) {
    this.belegID = belegID;
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

  public void setUhrzeit(String uhrzeit) {
    this.uhrzeit = uhrzeit;
  }
}
