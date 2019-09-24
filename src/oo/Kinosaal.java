package oo;

public class Kinosaal {
  int saalID;
  String bezeichnung;
  Sitz[] sitzplan;

  public Kinosaal(int saalID, String bezeichnung, Sitz[] sitzplan) {
    this.saalID = saalID;
    this.bezeichnung = bezeichnung;
    this.sitzplan = sitzplan;
  }

  public int getSaalID() {
    return saalID;
  }

  public String getBezeichnung() {
    return bezeichnung;
  }

  public Sitz[] getSitzplan() {
    return sitzplan;
  }

  public void setSaalID(int saalID) {
    this.saalID = saalID;
  }

  public void setBezeichnung(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }

  public void setSitzplan(Sitz[] sitzplan) {
    this.sitzplan = sitzplan;
  }
}
