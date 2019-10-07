package oo;

public class Gebäude {
    int gebäudeId;
    String strasße;
    int hausnummer;
    int plz;
    String ortsname;

    public Gebäude(int gebäudeId, String strasße, int hausnummer, int plz, String ortsname) {
        this.gebäudeId = gebäudeId;
        this.strasße = strasße;
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

    public String getStrasße() {
        return strasße;
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

    public void setStrasße(String strasße) {
        this.strasße = strasße;
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
