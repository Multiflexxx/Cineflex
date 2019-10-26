package oo;

public class Kinosaal {
  int saalID;
  String bezeichnung;
  Sitz[] sitzplan;
  Gebaeude gebaeude;

  /**
   *
   * @param saalID
   * @param bezeichnung
   * @param sitzplan
   * @param gebaeude
   */
  public Kinosaal(int saalID, String bezeichnung, Sitz[] sitzplan, Gebaeude gebaeude) {
    this.saalID = saalID;
    this.bezeichnung = bezeichnung;
    this.sitzplan = sitzplan;
    this.gebaeude = gebaeude;
  }

  /**
   *
   * @return gebaeude
   */
  public Gebaeude getGebaeude() {
    return gebaeude;
  }

  /**
   *
   * @param gebaeude
   */
  public void setGebaeude(Gebaeude gebaeude) {
    this.gebaeude = gebaeude;
  }

  /**
   *
   * @return saalID
   */
  public int getSaalID() {
    return saalID;
  }

  /**
   *
   * @return bezeichnung
   */
  public String getBezeichnung() {
    return bezeichnung;
  }

  /**
   *
   * @return sitzplan
   */
  public Sitz[] getSitzplan() {
    return sitzplan;
  }

  /**
   *
   * @param saalID
   */
  public void setSaalID(int saalID) {
    this.saalID = saalID;
  }

  /**
   *
   * @param bezeichnung
   */
  public void setBezeichnung(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }

  /**
   *
   * @param sitzplan
   */
  public void setSitzplan(Sitz[] sitzplan) {
    this.sitzplan = sitzplan;
  }

  /**
   *
   * @param row
   * @return rowCounter
   */
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
