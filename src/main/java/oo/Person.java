package oo;

public abstract class Person {
    String plz, email, vorname, nachname, passwort, datum;
    int personenID;
    //fremdschlüssel
    String strasse;
    int hausnummer;

    /**
     *
     * @param plz
     * @param email
     * @param vorname
     * @param nachname
     * @param passwort
     * @param datum
     * @param personenID
     */
    public Person(String plz, String email, String vorname, String nachname, String passwort, String datum, int personenID) {
        this.plz = plz;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
        this.passwort = passwort;
        this.datum = datum;
        this.personenID = personenID;
    }

    /**
     *
     * @param plz
     * @param email
     * @param vorname
     * @param nachname
     * @param passwort
     * @param datum
     * @param personenID
     * @param strasse
     * @param hausnummer
     */
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

    /**
     *  Empty Constructor
     */
    public Person() {
    }

    /**
     *
     * @return strasse
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     *
     * @param strasse
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
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
     * @return plz
     */
    public String getPlz() {
        return plz;
    }

    /**
     *
     * @param plz
     */
    public void setPlz(String plz) {
        this.plz = plz;
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
     * @return passwort
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     *
     * @param passwort
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     *
     * @return datum
     */
    public String getDatum() {
        return datum;
    }

    /**
     *
     * @param datum
     */
    public void setDatum(String datum) {
        this.datum = datum;
    }

    /**
     *
     * @return personenID
     */
    public int getPersonenID() {
        return personenID;
    }

    /**
     *
     * @param personenID
     */
    public void setPersonenID(int personenID) {
        this.personenID = personenID;
    }
}
