package puppeteer;

public class PosedPart {
	int spcs;
	int mf;
	int gspcs;
	char slot;
	int pose;
	int dirn;
	int x;
	int y;
	
	
	public PosedPart(int spcs, int mf, char slot, int pose, int dirn, int x, int y) {
		this.spcs = spcs;
		this.mf = mf;
//important to know that gspcs is the combo of mf and spcs, a 0-7 index traced to CreatureInfo.speciesList 
		this.gspcs = CreatureInfo.GetSpcs (mf, spcs);
		this.slot = slot;
		this.pose = pose;
		this.dirn = dirn;
		this.x = x;
		this.y = y;
	}
	
	public void UpdateSpcs(int spcs) {
		this.spcs = spcs;
		//important to know that gspcs is the combo of mf and spcs, a 0-7 index traced to CreatureInfo.speciesList 
		this.gspcs = CreatureInfo.GetSpcs (mf, spcs);
	}
	
	public void UpdateMF(int mf) {
		this.mf = mf;
		this.gspcs = CreatureInfo.GetSpcs (mf, spcs);
	}
	
	public void UpdateSlot(char slot) {
		this.slot = slot;
	}
	
	public void UpdatePose(int pose) {
		this.pose = pose;
	}
	
	public void UpdateDirn(int dirn) {
		this.dirn = dirn;
	}
	
	public void UpdateX(int x) {
		this.x = x;
	}
	
	public void UpdateY(int y) {
		this.y = y;
	}
	
}
