package oo;

public class Kunde extends Person {
    int kundenID, treuepunkte;

    /**
     * Empty Constructor
     */
    public Kunde() {
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
     * @param kundenID
     * @param treuepunkte
     */
    public Kunde(String plz, String email, String vorname, String nachname, String passwort, String datum, int personenID, String strasse, int hausnummer, int kundenID, int treuepunkte) {
        super(plz, email, vorname, nachname, passwort, datum, personenID, strasse, hausnummer);
        this.kundenID = kundenID;
        this.treuepunkte = treuepunkte;
    }

    /**
     *
     * @return kundenID
     */
    public int getKundenID() {
        return kundenID;
    }

    /**
     *
     * @param kundenID
     */
    public void setKundenID(int kundenID) {
        this.kundenID = kundenID;
    }

    /**
     *
     * @return treuepunkte
     */
    public int getTreuepunkte() {
        return treuepunkte;
    }

    /**
     *
     * @param treuepunkte
     */
    public void setTreuepunkte(int treuepunkte) {
        this.treuepunkte = treuepunkte;
    }
}


