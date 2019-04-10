package puppeteer;

public class PosedPart
{
	int part;
	int spcs;
	int mf;
	int gspcs;
	char slot;
	int pose;
	int dirn;
	int x;
	int y;
	int age;
	int expression;
	int eyes;
	PosedCreature origin;
	FileInfo fileInfo;
	CreatureInfo creatureInfo = new CreatureInfo();
	Configgles gamePaths;
	
	public PosedPart(PosedCreature origin, int part, int spcs, int mf, char slot, int pose, int dirn, int x, int y, Configgles gamePaths)
	{
		this.gamePaths = gamePaths;
		this.origin = origin;
		this.age = this.origin.age;
		this.part = part;
		this.spcs = spcs;
		this.mf = mf;
		// important to know that gspcs is the combo of mf and spcs, a 0-7 index traced to CreatureInfo.speciesList
		this.gspcs = creatureInfo.GetSpcs(mf, spcs);
		this.slot = slot;
		this.pose = pose;
		this.dirn = dirn;
		this.x = x;
		this.y = y;
		//if you're the head you get expressions and eyes too
		if (part == 0) {
		this.expression = this.origin.expression;
		this.eyes = this.origin.eyes;
		}
		// get the info for your file
			this.fileInfo = new FileInfo(this.part, this.pose, this.dirn, this.origin.expression, this.gspcs, this.origin.age, this.slot, this.origin.eyes, gamePaths);

		//System.out.println("Part " + part + " was created");
		
	}
	
	public PosedPart() {
		
	}
	
	public void updateFile()
	{
		//obviously don't update fileInfo if there's no files
		if (gamePaths.gamePaths.size() > 0)
		{
				this.fileInfo.updateFile(this.part, this.pose, this.dirn, this.origin.expression, this.gspcs, this.origin.age, this.slot, this.origin.eyes);
		}		
	}
	
	public void updateFrame()
	{
		//obviously don't update fileInfo if there's no files
		if (gamePaths.gamePaths.size() > 0)
		{
				this.fileInfo.updateFrame(this.part, this.pose, this.dirn, this.origin.expression, this.origin.eyes);
		}		
	}
	
	public void updateFace()
	{
		//obviously don't update fileInfo if there's no files
		if (gamePaths.gamePaths.size() > 0)
		{
				this.fileInfo.updateFrame(this.part, this.pose, this.dirn, this.origin.expression, this.origin.eyes);
		}		
	}
	
	public void UpdateSpcs(int spcs)
	{
		this.spcs = spcs;
		// important to know that gspcs is the combo of mf and spcs, a 0-7 index traced to CreatureInfo.speciesList
		this.gspcs = creatureInfo.GetSpcs(mf, spcs);
		updateFile();
	}
	
	public void UpdateMF(int mf)
	{
		this.mf = mf;
		this.gspcs = creatureInfo.GetSpcs(mf, spcs);
		updateFile();
	}
	
	public void UpdateSlot(char slot)
	{
		this.slot = slot;
		updateFile();
	}
	
	public void UpdatePose(int pose)
	{
		this.pose = pose;
		updateFrame();
	}
	
	public void UpdateDirn(int dirn)
	{
		this.dirn = dirn;
		updateFrame();
	}
	
	public void UpdateX(int x)
	{
		this.x = x;
		updateFrame();
	}
	
	public void UpdateY(int y)
	{
		this.y = y;
		updateFrame();
	}
	
}
