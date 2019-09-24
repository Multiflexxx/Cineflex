package oo;

public class Kinosaal {
  int saalID;
  String bezeichnung;
  //Sitzplan sitzplan;

  public Kinosaal(int saalID, String bezeichnung /*, Sitzplan sitzplan*/) {
    this.saalID = saalID;
    this.bezeichnung = bezeichnung;
    //this.sitzplan = sitzplan;
  }

  public int getSaalID() {
    return saalID;
  }

  public String getBezeichnung() {
    return bezeichnung;
  }
/*
  public Sitzplan getSitzplan() {
    return sitzplan;
  }
*/
  public void setSaalID(int saalID) {
    this.saalID = saalID;
  }

  public void setBezeichnung(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }
/*
  public void setSitzplan(Sitzplan sitzplan) {
    this.sitzplan = sitzplan;
  }
  */
}
