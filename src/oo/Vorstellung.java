package oo;

public class Vorstellung {
    int vorstellungsID;
    private String datum, uhrzeit, sprache; //gewünschtes Datumsformat: Wochentag, DDMM -> aber für ticket mit jahr
    Film film;
    Kinosaal saal;

    public Vorstellung(int vorstellungsID, String datum, String uhrzeit, String sprache, Film film, Kinosaal saal) {
        this.vorstellungsID = vorstellungsID;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.sprache = sprache;
        this.film = film;
        this.saal = saal;
    }

    public int getVorstellungsID() {
        return vorstellungsID;
    }

    public void setVorstellungsID(int vorstellungsID) {
        this.vorstellungsID = vorstellungsID;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(String uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public String getSprache() {
        return sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Kinosaal getSaal() {
        return saal;
    }

    public void setSaal(Kinosaal saal) {
        this.saal = saal;
    }

}
