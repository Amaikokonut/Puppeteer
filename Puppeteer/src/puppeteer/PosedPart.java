package puppeteer;

import java.io.IOException;

public class PosedPart {
	int part;
	int spcs;
	int mf;
	int gspcs;
	char slot;
	int pose;
	int dirn;
	int x;
	int y;
	PosedCreature origin;
	FileInfo fileInfo;
	
	
	public PosedPart(PosedCreature origin, int part, int spcs, int mf, char slot, int pose, int dirn, int x, int y) {
		this.origin = origin;
		this.part = part;
		this.spcs = spcs;
		this.mf = mf;
//important to know that gspcs is the combo of mf and spcs, a 0-7 index traced to CreatureInfo.speciesList 
		this.gspcs = CreatureInfo.GetSpcs (mf, spcs);
		this.slot = slot;
		this.pose = pose;
		this.dirn = dirn;
		this.x = x;
		this.y = y;
//get the info for your file
		try
		{
			this.fileInfo = new FileInfo(this.part, this.pose, this.dirn, this.origin.expression, this.gspcs, this.origin.age, this.slot, this.origin.eyes);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//GenerateCreature.DrawCreatureFromScratch(this.origin);
		
	}
	
	public void UpdateFileInfo() {
		try
		{
			this.fileInfo = new FileInfo(this.part, this.pose, this.dirn, this.origin.expression, this.gspcs, this.origin.age, this.slot, this.origin.eyes);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UpdateSpcs(int spcs) {
		this.spcs = spcs;
		//important to know that gspcs is the combo of mf and spcs, a 0-7 index traced to CreatureInfo.speciesList 
		this.gspcs = CreatureInfo.GetSpcs (mf, spcs);
		UpdateFileInfo();
	}
	
	public void UpdateMF(int mf) {
		this.mf = mf;
		this.gspcs = CreatureInfo.GetSpcs (mf, spcs);
		UpdateFileInfo();
	}
	
	public void UpdateSlot(char slot) {
		this.slot = slot;
		UpdateFileInfo();
	}
	
	public void UpdatePose(int pose) {
		this.pose = pose;
		UpdateFileInfo();
	}
	
	public void UpdateDirn(int dirn) {
		this.dirn = dirn;
		UpdateFileInfo();
	}
	
	public void UpdateX(int x) {
		this.x = x;
		UpdateFileInfo();
	}
	
	public void UpdateY(int y) {
		this.y = y;
		UpdateFileInfo();
	}
	
}
