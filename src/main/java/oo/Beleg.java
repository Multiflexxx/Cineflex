package oo;

import java.util.Date;

public abstract class Beleg {
  int belegID;
  float preis;
  Vorstellung vorstellung;
  Kunde kunde;
  Date uhrzeit;

  /**
   *
   * @param belegID
   * @param preis
   * @param vorstellung
   * @param kunde
   * @param uhrzeit
   */
  public Beleg(int belegID, float preis, Vorstellung vorstellung, Kunde kunde, Date uhrzeit) {
    this.belegID = belegID;
    this.preis = preis;
    this.vorstellung = vorstellung;
    this.kunde = kunde;
    this.uhrzeit = uhrzeit;
  }

  /**
   *
   * @return belegID
   */
  public int getBelegID() {
    return belegID;
  }

  /**
   *
   * @returnpreis
   */
  public float getPreis() {
    return preis;
  }

  /**
   *
   * @return vorstellung
   */
  public Vorstellung getVorstellung() {
    return vorstellung;
  }

  /**
   *
   * @return kunde
   */
  public Kunde getKunde() {
    return kunde;
  }

  /**
   *
   * @return uhrzeit
   */
  public Date getUhrzeit() {
    return uhrzeit;
  }

  /**
   *
   * @param belegID
   */
  public void setBelegID(int belegID) {
    this.belegID = belegID;
  }

  /**
   *
   * @param preis
   */
  public void setPreis(float preis) {
    this.preis = preis;
  }

  /**
   *
   * @param vorstellung
   */
  public void setVorstellung(Vorstellung vorstellung) {
    this.vorstellung = vorstellung;
  }

  /**
   *
   * @param kunde
   */
  public void setKunde(Kunde kunde) {
    this.kunde = kunde;
  }

  /**
   *
   * @param uhrzeit
   */
  public void setUhrzeit(Date uhrzeit) {
    this.uhrzeit = uhrzeit;
  }
}
