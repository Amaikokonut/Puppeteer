package puppeteer;

public class PosedCreature {
	int spcs;
	int mf;
	int gspcs;
	char slot;
	int age;
	int pose;
	int dirn;
	int expression;
	int eyes;
	PosedPart[] part = new PosedPart[14];
	
//creating a new PosedCreature	
	public PosedCreature(int spcs, int mf, char slot, int age, int pose, int dirn, int expression, int eyes) {
		this.spcs = spcs;
		this.mf = mf;
//important to know that gspcs is the combo of mf and spcs, a 0-7 index traced to CreatureInfo.speciesList 
		this.gspcs = CreatureInfo.GetSpcs (mf, spcs);
		this.slot = slot;
		this.age = age;
		this.pose = pose;
		this.dirn = dirn;
		this.expression = expression;
		this.eyes = eyes;
		
//now you gotta create all the body parts in their default states 
		for(int i = 0; i < 14; i++) {
			part[i] = new PosedPart(gspcs, slot, pose, dirn, 0, 0);
		}
	}
	
	public void DbgOuts() {
		System.out.println("Debug info for this creature:");
		System.out.println("");
		System.out.println("Species: " + CreatureInfo.speciesList[this.gspcs]);
		System.out.println("Slot: " + this.slot);
		System.out.println("Age: " + CreatureInfo.lifeStages[this.age]);
		System.out.println("Pose: " + this.pose);
		System.out.println("Direction: " + CreatureInfo.dirn[this.dirn]);
		System.out.println("Expression: " + CreatureInfo.expressions[this.expression]);
		System.out.println("Eyes: " + ((this.eyes == 1) ? "Closed" : "Open"));
		System.out.println("");
		System.out.println("Debug info for this creature's parts:");
		System.out.println("");
		for(int i = 0; i < 14; i++) {
			System.out.println("Part " + CreatureInfo.bodyParts[i]);
			System.out.println("Species: " + CreatureInfo.speciesList[this.part[i].spcs]);
			System.out.println("Slot: " + this.part[i].slot);
			System.out.println("Pose: " + this.part[i].pose);
			System.out.println("Direction: " + CreatureInfo.dirn[this.part[i].dirn]);
			System.out.println("X-offset: " + this.part[i].x);
			System.out.println("Y-offset: " + this.part[i].y);
			System.out.println("");
			
		}
	}
	
	public void UpdateSpcs(int spcs) {
		this.spcs = spcs;
		this.gspcs = CreatureInfo.GetSpcs (mf, spcs);
		
		//update all the part spcs too:
		for(int i = 0; i < 14; i++) {
			part[i].UpdateSpcs(gspcs);
		}
	}
	
	public void UpdateMF(int mf) {
		this.mf = mf;
		this.gspcs = CreatureInfo.GetSpcs (mf, spcs);
		
		//update all the part spcs too:
		for(int i = 0; i < 14; i++) {
			part[i].UpdateSpcs(gspcs);
		}
	}
	
	public void UpdateSlot(char slot) {
		this.slot = slot;
		
		//update all the part slots too:
		for(int i = 0; i < 14; i++) {
			part[i].UpdateSlot(slot);
		}
	}
	
	public void UpdateAge(int age) {
		this.age = age;
		//parts don't have individual ages so you don't have to worry about that 
	}
	
	public void UpdatePose(int pose) {
		this.pose = pose;
		
		//update all the part poses too:
		for(int i = 0; i < 14; i++) {
			part[i].UpdatePose(pose);
		}
	}
	
	public void UpdateDirn(int dirn) {
		this.dirn = dirn;
		
		//update all the part poses too:
		for(int i = 0; i < 14; i++) {
			part[i].UpdateDirn(dirn);
		}
	}
	
	public void UpdateEyes(int eyes) {
		this.eyes = eyes;
		//parts don't have individual.... eyes.. what, of course not
		//why would you think that
	}
	
	public void UpdateExpression(int expression) {
		this.expression = expression;
		//parts don't have individual.... expressions.. what, of course not
		//why would you think that
	}
}
