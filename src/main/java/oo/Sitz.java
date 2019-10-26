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

    /**
     *
     * @param sitzplatzID
     * @param nummer
     * @param reihe
     * @param sitzklasse
     */
    public Sitz(int sitzplatzID, int nummer, char reihe, char sitzklasse)
    {
        this.sitzplatzID = sitzplatzID;
        this.nummer = nummer;
        this.reihe = reihe;
        this.sitzklasse = sitzklasse;

    }

    /**
     *
     * @return sitzplatzID
     */
    public int getSitzplatzID() {
        return sitzplatzID;
    }

    /**
     *
     * @param sitzplatzID
     */
    public void setSitzplatzID(int sitzplatzID) {
        this.sitzplatzID = sitzplatzID;
    }

    /**
     *
     * @return nummer
     */
    public int getNummer() {
        return nummer;
    }

    /**
     *
     * @param nummer
     */
    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    /**
     *
     * @return reihe
     */
    public char getReihe() {
        return reihe;
    }

    /**
     *
     * @param reihe
     */
    public void setReihe(char reihe) {
        this.reihe = reihe;
    }

    /**
     *
     * @return sitzklasse
     */
    public char getSitzklasse() {
        return sitzklasse;
    }

    /**
     *
     * @param sitzklasse
     */
    public void setSitzklasse(char sitzklasse) {
        this.sitzklasse = sitzklasse;
    }
}
