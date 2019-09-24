package oo;

public class Admin extends Person {
    int mitarbeiterID;

    public Admin(String plz, String email, String vorname, String nachname, String passwort, String datum, int personenID, int mitarbeiterID) {
        super(plz, email, vorname, nachname, passwort, datum, personenID);
        this.mitarbeiterID = mitarbeiterID;
    }

    public Admin(int mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }

    public Admin() {
    }

    public int getMitarbeiterID() {
        return mitarbeiterID;
    }

    public void setMitarbeiterID(int mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }
}
