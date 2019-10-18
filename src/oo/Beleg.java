package oo;

import java.util.Date;

public abstract class Beleg {
  int belegID;
  float preis;
  Vorstellung vorstellung;
  Kunde kunde;
  Date uhrzeit;

  public Beleg(int belegID, float preis, Vorstellung vorstellung, Kunde kunde, Date uhrzeit) {
    this.belegID = belegID;
    this.preis = preis;
    this.vorstellung = vorstellung;
    this.kunde = kunde;
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



  public Date getUhrzeit() {
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


  public void setUhrzeit(Date uhrzeit) {
    this.uhrzeit = uhrzeit;
  }
}
