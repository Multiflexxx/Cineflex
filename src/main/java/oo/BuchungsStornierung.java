package oo;

public class BuchungsStornierung {

	private int BNR;
	private int strnNr;

	/**
	 *
	 * @param BNR
	 * @param strnNr
	 */
	 public BuchungsStornierung(int BNR, int strnNr) {
	 	this.BNR = BNR;
		this.strnNr = strnNr;
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
	 * @return strnNr
	 */
	public int getStrnNr() {
		return strnNr;
	}

	/**
	 *
	 * @param strnNr
	 */
	public void setStrnNr(int strnNr) {
		this.strnNr = strnNr;
	}
}
