package oo;

public class Gebaeude {
    int gebäudeId;
    String strasse;
    int hausnummer;
    int plz;
    String ortsname;

    public Gebaeude(int gebäudeId, String strasse, int hausnummer, int plz, String ortsname) {
        this.gebäudeId = gebäudeId;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ortsname = ortsname;
    }

    public String getOrtsname() {
        return ortsname;
    }

    public int getGebäudeId() {
        return gebäudeId;
    }

    public String getStrasse() {
        return strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public int getPlz() {
        return plz;
    }

    public void setGebäudeId(int gebäudeId) {
        this.gebäudeId = gebäudeId;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public void setOrtsname(String ortsname) {
        this.ortsname = ortsname;
    }
}
