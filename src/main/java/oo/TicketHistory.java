package oo;

import java.util.Date;

public class TicketHistory implements Comparable<TicketHistory> {
    String belegBezeichnung;
    Buchungsbeleg buchungsbeleg;
    Reservierungsbeleg reservierungsbeleg;
    BuchungsStornierung buchungsStornierung;
    ReservierungsStornierung reservierungsStornierung;
    Sitz[] sitze;

    /**
     *
     * @param buchungsbeleg
     * @param sitze
     * @param buchungsStornierung
     */
    public TicketHistory(Buchungsbeleg buchungsbeleg, Sitz[] sitze, BuchungsStornierung buchungsStornierung) {
        this.buchungsbeleg =buchungsbeleg;
        this.sitze = sitze;
        this.buchungsStornierung = buchungsStornierung;
        this.belegBezeichnung = "Buchung";
    }

    /**
     *
     * @param reservierungsbeleg
     * @param sitze
     * @param reservierungsStornierung
     */
    public TicketHistory(Reservierungsbeleg reservierungsbeleg, Sitz[] sitze, ReservierungsStornierung reservierungsStornierung) {
        this.reservierungsbeleg = reservierungsbeleg;
        this.sitze = sitze;
        this.reservierungsStornierung = reservierungsStornierung;
        this.belegBezeichnung = "Reservierung";
    }

    /**
     *
     * @return sitze
     */
    public Sitz[] getSitze() {
        return sitze;
    }

    /**
     *
     * @return belegID
     */
    public int getBelegID() {
        if(buchungsbeleg != null) {
            return buchungsbeleg.belegID;
        } else {
            return reservierungsbeleg.belegID;
        }
    }

    /**
     *
     * @return kunde
     */
    public Kunde getBelegKunde() {
        if(buchungsbeleg != null) {
            return buchungsbeleg.kunde;
        } else {
            return reservierungsbeleg.kunde;
        }
    }

    /**
     *
     * @return vorstellung
     */
    public Vorstellung getBelegVorstellung() {
        if(buchungsbeleg != null) {
            return buchungsbeleg.vorstellung;
        } else {
            return reservierungsbeleg.vorstellung;
        }
    }

    /**
     *
     * @return preis
     */
    public float getBelegPreis() {
        if(buchungsbeleg != null) {
            return buchungsbeleg.preis;
        } else {
            return reservierungsbeleg.preis;
        }
    }

    /**
     *
     * @return belegBezeichnung
     */
    public String getBelegBezeichnung() {
        return this.belegBezeichnung;
    }

    /**
     *
     * @return true|false
     */
    public boolean isStorniert() {
        return (buchungsStornierung != null || reservierungsStornierung != null);
    }

    /**
     *
     * @return Uhrzeit
     */
    public Date getBelegZeitstempel() {
        if(buchungsbeleg != null) {
            return buchungsbeleg.getUhrzeit();
        } else {
            return reservierungsbeleg.getUhrzeit();
        }
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(TicketHistory o) {
        return (getBelegZeitstempel().compareTo(o.getBelegZeitstempel()) * -1);
    }
}
