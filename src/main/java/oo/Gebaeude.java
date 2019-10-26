package oo;

public class Gebaeude {
    int gebäudeId;
    String strasse;
    int hausnummer;
    int plz;
    String ortsname;

    /**
     *
     * @param gebäudeId
     * @param strasse
     * @param hausnummer
     * @param plz
     * @param ortsname
     */
    public Gebaeude(int gebäudeId, String strasse, int hausnummer, int plz, String ortsname) {
        this.gebäudeId = gebäudeId;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ortsname = ortsname;
    }

    /**
     *
     * @return ortsname
     */
    public String getOrtsname() {
        return ortsname;
    }

    /**
     *
     * @return gebäudeId
     */
    public int getGebäudeId() {
        return gebäudeId;
    }

    /**
     *
     * @return strasse
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     *
     * @return hausnummer
     */
    public int getHausnummer() {
        return hausnummer;
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
     * @param gebäudeId
     */
    public void setGebäudeId(int gebäudeId) {
        this.gebäudeId = gebäudeId;
    }

    /**
     *
     * @param strasse
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     *
     * @param hausnummer
     */
    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    /**
     *
     * @param plz
     */
    public void setPlz(int plz) {
        this.plz = plz;
    }

    /**
     *
     * @param ortsname
     */
    public void setOrtsname(String ortsname) {
        this.ortsname = ortsname;
    }
}
