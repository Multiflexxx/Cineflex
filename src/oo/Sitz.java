package oo;

/**
 * CineflexV1;
 * <p>
 * Copyright by @author Marcel Mertens
 * Website: https://mertens-web.ddns.net
 * <p>
 * Date: 24.09.2019
 */
public class Sitz {

    private int sitzID, nummer;
    private char reihe, sitzklasse;
    private float grundpreis;
    private String beschreibung;

    public Sitz(int sitzID, int nummer, char reihe, char sitzklasse, String beschreibung, float grundpreis)
    {
        this.sitzID = sitzID;
        this.nummer = nummer;
        this.reihe = reihe;
        this.sitzklasse = sitzklasse;
        this.beschreibung = beschreibung;
        this.grundpreis = grundpreis;
    }

    public int getSitzID() {
        return sitzID;
    }

    public void setSitzID(int sitzID) {
        this.sitzID = sitzID;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public char getReihe() {
        return reihe;
    }

    public void setReihe(char reihe) {
        this.reihe = reihe;
    }

    public char getSitzklasse() {
        return sitzklasse;
    }

    public void setSitzklasse(char sitzklasse) {
        this.sitzklasse = sitzklasse;
    }

    public float getGrundpreis() {
        return grundpreis;
    }

    public void setGrundpreis(float grundpreis) {
        this.grundpreis = grundpreis;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
