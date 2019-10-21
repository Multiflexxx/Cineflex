package oo;

public class Preisänderung {
    private int preisänderungsID;
    private double änderungswert;
    private String änderungsBeschreibung;
    private String tooltipDeskriptor;
    private boolean grundpreis_relevant;

    public Preisänderung(int preisänderungsID, double änderungswert, String änderungsBeschreibung, String tooltipDeskriptor, boolean grundpreis_relevant) {
        this.preisänderungsID = preisänderungsID;
        this.änderungswert = änderungswert;
        this.änderungsBeschreibung = änderungsBeschreibung;
        this.tooltipDeskriptor = tooltipDeskriptor;
        this.grundpreis_relevant = grundpreis_relevant;
    }

    public int getPreisänderungsID() {
        return preisänderungsID;
    }

    public void setPreisänderungsID(int preisänderungsID) {
        this.preisänderungsID = preisänderungsID;
    }

    public double getÄnderungswert() {
        return änderungswert;
    }

    public void setÄnderungswert(double änderungswert) {
        this.änderungswert = änderungswert;
    }

    public String getÄnderungsBeschreibung() {
        return änderungsBeschreibung;
    }

    public void setÄnderungsBeschreibung(String änderungsBeschreibung) {
        this.änderungsBeschreibung = änderungsBeschreibung;
    }

    public String getTooltipDeskriptor() {
        return tooltipDeskriptor;
    }

    public void setTooltipDeskriptor(String tooltipDeskriptor) {
        this.tooltipDeskriptor = tooltipDeskriptor;
    }

    public boolean isGrundpreis_relevant() {
        return grundpreis_relevant;
    }

    public void setGrundpreis_relevant(boolean grundpreis_relevant) {
        this.grundpreis_relevant = grundpreis_relevant;
    }
}
