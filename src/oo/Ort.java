package oo;

public class Ort {
    String ortsName;
    int plz;

  public Ort(String ortsName, int plz) {
    this.ortsName = ortsName;
    this.plz = plz;
  }

  public String getOrtsName() {
    return ortsName;
  }

  public void setOrtsName(String ortsName) {
    this.ortsName = ortsName;
  }

  public int getPlz() {
    return plz;
  }

  public void setPlz(int plz) {
    this.plz = plz;
  }
}
