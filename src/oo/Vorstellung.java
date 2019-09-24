package oo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Vorstellung {
    int vorstellungsID;
    private String sprache; //gewünschtes Datumsformat: Wochentag, DDMM -> aber für ticket mit jahr
    Film film;
    Kinosaal saal;
    Date datum;
    Date uhrzeit;

    public Vorstellung(int vorstellungsID, Date datum, Date uhrzeit, String sprache, Film film, Kinosaal saal) {
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(Date uhrzeit) {
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
