package oo;

public class Genre {
  int genreID;
  String genrebezeichnung, deskriptor;

  public Genre(int genreID, String genrebezeichnung, String deskriptor){
    this.genreID = genreID;
    this.genrebezeichnung = genrebezeichnung;
    this.deskriptor = deskriptor;
  }

  public int getGenreID() {
    return genreID;
  }

  public void setGenreID(int genreID) {
    this.genreID = genreID;
  }

  public String getGenrebezeichnung() {
    return genrebezeichnung;
  }

  public void setGenrebezeichnung(String genrebezeichnung) {
    this.genrebezeichnung = genrebezeichnung;
  }

  public String getDeskriptor() {
    return deskriptor;
  }

  public void setDeskriptor(String deskriptor) {
    this.deskriptor = deskriptor;
  }
}
