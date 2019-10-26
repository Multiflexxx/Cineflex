package oo;

/**
 * Class Admin
 */
public class Admin extends Person {
    int mitarbeiterID;

    /**
     * Constructor
     * @param plz
     * @param email
     * @param vorname
     * @param nachname
     * @param passwort
     * @param datum
     * @param personenID
     * @param mitarbeiterID
     */
    public Admin(String plz, String email, String vorname, String nachname, String passwort, String datum, int personenID, int mitarbeiterID) {
        super(plz, email, vorname, nachname, passwort, datum, personenID);
        this.mitarbeiterID = mitarbeiterID;
    }

    /**
     * Constructor
     * @param mitarbeiterID
     */
    public Admin(int mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }

    /**
     * Empty Constructor
     */
    public Admin() {
    }

    /**
     *
     * @return mitarbeiterID
     */
    public int getMitarbeiterID() {
        return mitarbeiterID;
    }

    /**
     *
     * @param mitarbeiterID
     */
    public void setMitarbeiterID(int mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }
}
