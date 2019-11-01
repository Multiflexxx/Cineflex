package oo;

public class BuchungsPosition {
    private int positionsID;
    private int BNR;
    private int sitzID;

    /**
     *
     * @param positionsID
     * @param BNR
     * @param sitzID
     */
    public BuchungsPosition(int positionsID, int BNR, int sitzID) {
        this.positionsID = positionsID;
        this.BNR = BNR;
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
     * @return BNR
     */
    public int getBNR() {
        return BNR;
    }

    /**
     *
     * @param BNR
     */
    public void setBNR(int BNR) {
        this.BNR = BNR;
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
