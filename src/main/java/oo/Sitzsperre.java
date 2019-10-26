package oo;

import java.util.Date;

public class Sitzsperre {
    private int sitzplatzID;
    private int KNR;
    private int vorstellungsID;
    private Date timestamp;

    /**
     *
     * @param sitzplatzID
     * @param vorstellungsID
     * @param KNR
     * @param timestamp
     */
    public Sitzsperre(int sitzplatzID, int vorstellungsID, int KNR, Date timestamp) {
        this.sitzplatzID = sitzplatzID;
        this.KNR = KNR;
        this.timestamp = timestamp;
        this.vorstellungsID = vorstellungsID;
    }

    /**
     *
     * @return sitzplatzID
     */
    public int getSitzplatzID() {
        return sitzplatzID;
    }

    /**
     *
     * @param sitzplatzID
     */
    public void setSitzplatzID(int sitzplatzID) {
        this.sitzplatzID = sitzplatzID;
    }

    /**
     *
     * @return KNR
     */
    public int getKNR() {
        return KNR;
    }

    /**
     *
     * @param KNR
     */
    public void setKNR(int KNR) {
        this.KNR = KNR;
    }

    /**
     *
     * @return timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
