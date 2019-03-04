package puppeteer;

public class PosedPart {
	int spcs;
	char slot;
	int pose;
	int dirn;
	int x;
	int y;
	
	
	public PosedPart(int spcs, char slot, int pose, int dirn, int x, int y) {
		this.spcs = spcs;
		this.slot = slot;
		this.pose = pose;
		this.dirn = dirn;
		this.x = x;
		this.y = y;
	}
	
	public void UpdateSpcs(int spcs) {
		this.spcs = spcs;
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
