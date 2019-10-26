package oo;

public class Preisänderung {
    private int preisänderungsID;
    private double änderungswert;
    private String änderungsBeschreibung;
    private String tooltipDeskriptor;
    private boolean grundpreis_relevant;

    /**
     *
     * @param preisänderungsID
     * @param änderungswert
     * @param änderungsBeschreibung
     * @param tooltipDeskriptor
     * @param grundpreis_relevant
     */
    public Preisänderung(int preisänderungsID, double änderungswert, String änderungsBeschreibung, String tooltipDeskriptor, boolean grundpreis_relevant) {
        this.preisänderungsID = preisänderungsID;
        this.änderungswert = änderungswert;
        this.änderungsBeschreibung = änderungsBeschreibung;
        this.tooltipDeskriptor = tooltipDeskriptor;
        this.grundpreis_relevant = grundpreis_relevant;
    }

    /**
     *
     * @return preisänderungsID
     */
    public int getPreisänderungsID() {
        return preisänderungsID;
    }

    /**
     *
     * @param preisänderungsID
     */
    public void setPreisänderungsID(int preisänderungsID) {
        this.preisänderungsID = preisänderungsID;
    }

    /**
     *
     * @return änderungswert
     */
    public double getÄnderungswert() {
        return änderungswert;
    }

    /**
     *
     * @param änderungswert
     */
    public void setÄnderungswert(double änderungswert) {
        this.änderungswert = änderungswert;
    }

    /**
     *
     * @return änderungsBeschreibung
     */
    public String getÄnderungsBeschreibung() {
        return änderungsBeschreibung;
    }

    /**
     *
     * @param änderungsBeschreibung
     */
    public void setÄnderungsBeschreibung(String änderungsBeschreibung) {
        this.änderungsBeschreibung = änderungsBeschreibung;
    }

    /**
     *
     * @return tooltipDeskriptor
     */
    public String getTooltipDeskriptor() {
        return tooltipDeskriptor;
    }

    /**
     *
     * @param tooltipDeskriptor
     */
    public void setTooltipDeskriptor(String tooltipDeskriptor) {
        this.tooltipDeskriptor = tooltipDeskriptor;
    }

    /**
     *
     * @return grundpreis_relevant
     */
    public boolean isGrundpreis_relevant() {
        return grundpreis_relevant;
    }

    /**
     *
     * @param grundpreis_relevant
     */
    public void setGrundpreis_relevant(boolean grundpreis_relevant) {
        this.grundpreis_relevant = grundpreis_relevant;
    }
}
