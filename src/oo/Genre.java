package oo;

public class Genre {
  int genreID;
  String genrebezeichnung, deskriptor;

  /**
   *
   * @param genreID
   * @param genrebezeichnung
   * @param deskriptor
   */
  public Genre(int genreID, String genrebezeichnung, String deskriptor){
    this.genreID = genreID;
    this.genrebezeichnung = genrebezeichnung;
    this.deskriptor = deskriptor;
  }

  /**
   *
   * @return genreID
   */
  public int getGenreID() {
    return genreID;
  }

  /**
   *
   * @param genreID
   */
  public void setGenreID(int genreID) {
    this.genreID = genreID;
  }

  /**
   *
   * @return genrebezeichnung
   */
  public String getGenrebezeichnung() {
    return genrebezeichnung;
  }

  /**
   *
   * @param genrebezeichnung
   */
  public void setGenrebezeichnung(String genrebezeichnung) {
    this.genrebezeichnung = genrebezeichnung;
  }

  /**
   *
   * @return deskriptor
   */
  public String getDeskriptor() {
    return deskriptor;
  }

  /**
   *
   * @param deskriptor
   */
  public void setDeskriptor(String deskriptor) {
    this.deskriptor = deskriptor;
  }
}
