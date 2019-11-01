package oo;

public class ReservierungsPosition {
    private int positionsID;
    private int RNR;
    private int sitzID;

    /**
     *
     * @param positionsID
     * @param RNR
     * @param sitzID
     */
    public ReservierungsPosition(int positionsID, int RNR, int sitzID) {
        this.positionsID = positionsID;
        this.RNR = RNR;
        this.sitzID = sitzID;
    }

    /**
     *
     * @return positionsID
     */
    public int getPositionsID() {
        return positionsID;
    }

    /**
     *
     * @param positionsID
     */
    public void setPositionsID(int positionsID) {
        this.positionsID = positionsID;
    }

    /**
     *
     * @return RNR
     */
    public int getBNR() {
        return RNR;
    }

    /**
     *
     * @param BNR
     */
    public void setBNR(int BNR) {
        this.RNR = BNR;
    }

    /**
     *
     * @return sitzID
     */
    public int getSitzID() {
        return sitzID;
    }

    /**
     *
     * @param sitzID
     */
    public void setSitzID(int sitzID) {
        this.sitzID = sitzID;
    }
}
