package oo;

import java.util.Date;

public class Vorstellung {
    int vorstellungsID;
    private String sprache; //gewünschtes Datumsformat: Wochentag, DDMM -> aber für ticket mit jahr
    Film film;
    Kinosaal saal;
    Date datum;
    Date uhrzeit;

    /**
     *
     * @param vorstellungsID
     * @param datum
     * @param uhrzeit
     * @param sprache
     * @param film
     * @param saal
     */
    public Vorstellung(int vorstellungsID, Date datum, Date uhrzeit, String sprache, Film film, Kinosaal saal) {
        this.vorstellungsID = vorstellungsID;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.sprache = sprache;
        this.film = film;
        this.saal = saal;
    }

    /**
     *
     * @return vorstellungsID
     */
    public int getVorstellungsID() {
        return vorstellungsID;
    }

    /**
     *
     * @param vorstellungsID
     */
    public void setVorstellungsID(int vorstellungsID) {
        this.vorstellungsID = vorstellungsID;
    }

    /**
     *
     * @return datum
     */
    public Date getDatum() {
        return datum;
    }

    /**
     *
     * @param datum
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    /**
     *
     * @return uhrzeit
     */
    public Date getUhrzeit() {
        return uhrzeit;
    }

    /**
     *
     * @param uhrzeit
     */
    public void setUhrzeit(Date uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    /**
     *
     * @return sprache
     */
    public String getSprache() {
        return sprache;
    }

    /**
     *
     * @param sprache
     */
    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    /**
     *
     * @return film
     */
    public Film getFilm() {
        return film;
    }

    /**
     *
     * @param film
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     *
     * @return saal
     */
    public Kinosaal getSaal() {
        return saal;
    }

    /**
     *
     * @param saal
     */
    public void setSaal(Kinosaal saal) {
        this.saal = saal;
    }
}
