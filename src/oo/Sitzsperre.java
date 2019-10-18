package oo;

import java.util.Date;

public class Sitzsperre {
  private int sitzplatzID;
  private int BNR;
  private int KNR;
  private Date timestamp;

  public Sitzsperre(int sitzplatzID, int BNR, int KNR, Date timestamp) {
    this.sitzplatzID = sitzplatzID;
    this.BNR = BNR;
    this.KNR = KNR;
    this.timestamp = timestamp;
  }

  public int getSitzplatzID() {
    return sitzplatzID;
  }

  public void setSitzplatzID(int sitzplatzID) {
    this.sitzplatzID = sitzplatzID;
  }

  public int getBNR() {
    return BNR;
  }

  public void setBNR(int BNR) {
    this.BNR = BNR;
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
