package oo;

public class ReservierungsStornierung {
		private int RNR;
		private int StrnNr;

		public ReservierungsStornierung(int RNR, int strnNr) {
				this.RNR = RNR;
				StrnNr = strnNr;
		}

		public int getRNR() {
				return RNR;
		}

		public void setRNR(int RNR) {
				this.RNR = RNR;
		}

		public int getStrnNr() {
				return StrnNr;
		}

		public void setStrnNr(int strnNr) {
				StrnNr = strnNr;
		}
}
