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

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }


    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getStraße() {
        return straße;
    }

    public void setStraße(String straße) {
        this.straße = straße;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getAdresszusatz() {
        return adresszusatz;
    }

    public void setAdresszusatz(String adresszusatz) {
        this.adresszusatz = adresszusatz;
    }
}
