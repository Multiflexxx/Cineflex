package oo;

public class ReservierungsStornierung {
    private int RNR;
    private int StrnNr;

    /**
     *
     * @param RNR
     * @param strnNr
     */
    public ReservierungsStornierung(int RNR, int strnNr) {
        this.RNR = RNR;
        StrnNr = strnNr;
    }

    /**
     *
     * @return RNR
     */
    public int getRNR() {
        return RNR;
    }

    /**
     *
     * @param RNR
     */
    public void setRNR(int RNR) {
        this.RNR = RNR;
    }

    /**
     *
     * @return
     */
    public int getStrnNr() {
        return StrnNr;
    }

    /**
     *
     * @param strnNr
     */
    public void setStrnNr(int strnNr) {
        StrnNr = strnNr;
    }
}
