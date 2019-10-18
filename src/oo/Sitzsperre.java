package oo;

import java.util.Date;

public class Sitzsperre {
  private int sitzplatzID;
  private int KNR;
  private int vorstellungsID;
  private Date timestamp;

  public Sitzsperre(int sitzplatzID, int vorstellungsID, int KNR, Date timestamp) {
    this.sitzplatzID = sitzplatzID;
    this.KNR = KNR;
    this.timestamp = timestamp;
    this.vorstellungsID = vorstellungsID;
  }

  public int getSitzplatzID() {
    return sitzplatzID;
  }

  public void setSitzplatzID(int sitzplatzID) {
    this.sitzplatzID = sitzplatzID;
  }

  public int getKNR() {
    return KNR;
  }

  public void setKNR(int KNR) {
    this.KNR = KNR;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }
}
