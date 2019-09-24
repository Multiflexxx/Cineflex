package oo;

public class Vorstellung {
    int vorstellungsID;
    String Datum, Uhrzeit, Sprache; //gewünschtes Datumsformat: Wochentag, DDMM -> aber für ticket mit jahr
    Film film;
    Kinosaal saal;

    public Vorstellung(int vorstellungsID, String datum, String uhrzeit, String sprache, Film film, Kinosaal saal) {
        this.vorstellungsID = vorstellungsID;
        Datum = datum;
        Uhrzeit = uhrzeit;
        Sprache = sprache;
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
        return Datum;
    }

    public void setDatum(String datum) {
        Datum = datum;
    }

    public String getUhrzeit() {
        return Uhrzeit;
    }

    public void setUhrzeit(String uhrzeit) {
        Uhrzeit = uhrzeit;
    }

    public String getSprache() {
        return Sprache;
    }

    public void setSprache(String sprache) {
        Sprache = sprache;
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
