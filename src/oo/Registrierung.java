package oo;

import java.util.Date;

public class Registrierung {
    int PID;
    String vorname;
    String nachname;
    Date geburtsdatum;
    String email;
    String passwordHash;
    int plz;
    String straße;
    int hausnummer;
    String adresszusatz;

    /**
     *
     * @param vorname
     * @param nachname
     * @param geburtsdatum
     * @param email
     * @param passwordHash
     * @param plz
     * @param straße
     * @param hausnummer
     * @param adresszusatz
     */
    public Registrierung(String vorname, String nachname, Date geburtsdatum, String email, String passwordHash, int plz, String straße, int hausnummer, String adresszusatz) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.email = email;
        this.passwordHash = passwordHash;
        this.plz = plz;
        this.straße = straße;
        this.hausnummer = hausnummer;
        this.adresszusatz = adresszusatz;
    }

    /**
     *
     * @param PID
     * @param vorname
     * @param nachname
     * @param geburtsdatum
     * @param email
     * @param passwordHash
     * @param plz
     * @param straße
     * @param hausnummer
     * @param adresszusatz
     */
    public Registrierung(int PID, String vorname, String nachname, Date geburtsdatum, String email, String passwordHash, int plz, String straße, int hausnummer, String adresszusatz) {
        this.PID = PID;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.email = email;
        this.passwordHash = passwordHash;
        this.plz = plz;
        this.straße = straße;
        this.hausnummer = hausnummer;
        this.adresszusatz = adresszusatz;
    }

    /**
     *
     * @return vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     *
     * @param vorname
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     *
     * @return nachname
     */
    public String getNachname() {
        return nachname;
    }

    /**
     *
     * @param nachname
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     *
     * @return geburtsdatum
     */
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     *
     * @param geburtsdatum
     */
    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     *
     * @param passwordHash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
     * @param plz
     */
    public void setPlz(int plz) {
        this.plz = plz;
    }

    /**
     *
     * @return straße
     */
    public String getStraße() {
        return straße;
    }

    /**
     *
     * @param straße
     */
    public void setStraße(String straße) {
        this.straße = straße;
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
     * @param hausnummer
     */
    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    /**
     *
     * @return adresszusatz
     */
    public String getAdresszusatz() {
        return adresszusatz;
    }

    /**
     *
     * @param adresszusatz
     */
    public void setAdresszusatz(String adresszusatz) {
        this.adresszusatz = adresszusatz;
    }
}
