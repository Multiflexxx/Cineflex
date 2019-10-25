package oo;

public class BuchungsStornierung {

		private int BNR;
		private int strnNr;

		public BuchungsStornierung(int BNR, int strnNr) {
				this.BNR = BNR;
				this.strnNr = strnNr;
		}

		public int getBNR() {
				return BNR;
		}

		public void setBNR(int BNR) {
				this.BNR = BNR;
		}

		public int getStrnNr() {
				return strnNr;
		}

		public void setStrnNr(int strnNr) {
				this.strnNr = strnNr;
		}
}
