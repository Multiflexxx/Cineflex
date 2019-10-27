package oo;

public class ReservierungsPosition {
    private int positionsID;
    private int RNR;
    private int sitzID;

    public ReservierungsPosition(int positionsID, int RNR, int sitzID) {
        this.positionsID = positionsID;
        this.RNR = RNR;
        this.sitzID = sitzID;
    }

    public int getPositionsID() {
        return positionsID;
    }

    public void setPositionsID(int positionsID) {
        this.positionsID = positionsID;
    }

    public int getBNR() {
        return RNR;
    }

    public void setBNR(int BNR) {
        this.RNR = BNR;
    }

    public int getSitzID() {
        return sitzID;
    }

    public void setSitzID(int sitzID) {
        this.sitzID = sitzID;
    }
}
