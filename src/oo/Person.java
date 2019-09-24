package oo;

public abstract class Person {
    String plz, email, vorname, nachname, passwort, datum;
    int personenID;
    //fremdschlüssel
    String strasse;
    int hausnummer;

    public Person(String plz, String email, String vorname, String nachname, String passwort, String datum, int personenID) {
        this.plz = plz;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
        this.passwort = passwort;
        this.datum = datum;
        this.personenID = personenID;
    }

    public Person(String plz, String email, String vorname, String nachname, String passwort, String datum, int personenID, String strasse, int hausnummer) {
        this.plz = plz;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
        this.passwort = passwort;
        this.datum = datum;
        this.personenID = personenID;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }

    public Person() {
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getPersonenID() {
        return personenID;
    }

    public void setPersonenID(int personenID) {
        this.personenID = personenID;
    }
}
