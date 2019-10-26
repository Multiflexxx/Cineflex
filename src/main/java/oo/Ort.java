package oo;

public class Ort {
    String ortsName;
    int plz;

    /**
     *
     * @param ortsName
     * @param plz
     */
    public Ort(String ortsName, int plz) {
        this.ortsName = ortsName;
        this.plz = plz;
    }

    /**
     *
     * @return ortsName
     */
    public String getOrtsName() {
        return ortsName;
    }

    /**
     *
     * @param ortsName
     */
    public void setOrtsName(String ortsName) {
        this.ortsName = ortsName;
    }

    /**
     *
     * @return plz
     */
    public int getPlz() {
        return plz;
    }

    /**
     *
     * @param plz
     */
    public void setPlz(int plz) {
        this.plz = plz;
    }
}
