package oo;

public class Kunde extends Person {
    int kundenID, treuepunkte;

    public Kunde() {
    }

    public Kunde(String plz, String email, String vorname, String nachname, String passwort, String datum, int personenID, String strasse, int hausnummer, int kundenID, int treuepunkte) {
        super(plz, email, vorname, nachname, passwort, datum, personenID, strasse, hausnummer);
        this.kundenID = kundenID;
        this.treuepunkte = treuepunkte;
    }

    public int getKundenID() {
        return kundenID;
    }

    public void setKundenID(int kundenID) {
        this.kundenID = kundenID;
    }

    public int getTreuepunkte() {
        return treuepunkte;
    }

    public void setTreuepunkte(int treuepunkte) {
        this.treuepunkte = treuepunkte;
    }
}


