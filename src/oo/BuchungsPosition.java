package oo;

public class BuchungsPosition {
		private int positionsID;
		private int BNR;
		private int sitzID;

		public BuchungsPosition(int positionsID, int BNR, int sitzID) {
				this.positionsID = positionsID;
				this.BNR = BNR;
				this.sitzID = sitzID;
		}

		public int getPositionsID() {
				return positionsID;
		}

		public void setPositionsID(int positionsID) {
				this.positionsID = positionsID;
		}

		public int getBNR() {
				return BNR;
		}

		public void setBNR(int BNR) {
				this.BNR = BNR;
		}

		public int getSitzID() {
				return sitzID;
		}

		public void setSitzID(int sitzID) {
				this.sitzID = sitzID;
		}
}
