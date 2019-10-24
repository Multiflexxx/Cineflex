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

    private int sitzplatzID, nummer;
    private char reihe, sitzklasse;


    public Sitz(int sitzplatzID, int nummer, char reihe, char sitzklasse)
    {
        this.sitzplatzID = sitzplatzID;
        this.nummer = nummer;
        this.reihe = reihe;
        this.sitzklasse = sitzklasse;

    }

    public int getSitzplatzID() {
        return sitzplatzID;
    }

    public void setSitzplatzID(int sitzplatzID) {
        this.sitzplatzID = sitzplatzID;
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


}
