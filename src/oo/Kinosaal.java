package oo;

public class Kinosaal {
  int saalID;
  String bezeichnung;
  Sitz[] sitzplan;
  Gebaeude gebaeude;

  public Kinosaal(int saalID, String bezeichnung, Sitz[] sitzplan, Gebaeude gebaeude) {
    this.saalID = saalID;
    this.bezeichnung = bezeichnung;
    this.sitzplan = sitzplan;
    this.gebaeude = gebaeude;
  }

  public Gebaeude getGebaeude() {
    return gebaeude;
  }

  public void setGebaeude(Gebaeude gebaeude) {
    this.gebaeude = gebaeude;
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

  public int getRowLength(char row)
  {
    int rowCounter = 0;

    for (int i = 0; i < sitzplan.length; i++)
    {
      if(sitzplan[i].getReihe() == row)
      {
        rowCounter++;
      }
    }

    return rowCounter;
  }
}
