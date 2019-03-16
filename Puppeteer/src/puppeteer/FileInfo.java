package puppeteer;

import java.io.File;

public class FileInfo
{
	//Passed a creature object and a part-- creates an object that contains:
	
	File sprite; //case-corrected true sprite file
	boolean spriteIsAdapted; //sprite filename differs from the one requested 
	boolean spriteIsSlotAdapted; //sprite filename differs so much it had to pick a new slot
	File att; //case-corrected true att file
	boolean attIsAdapted;
	boolean attIsSlotAdapted;
	int frame;
	int attX;
	int attY;
	int attAnchorX;
	int attAnchorY;
	
	public FileInfo (PosedCreature creature, int part) {
		//super-first, because this is pretty easy, you can construct the sprite pose number.
		this.frame = getFrame (part, creature.part[part].pose, creature.part[part].dirn, creature.expression);
		//now you have to construct the filename you are looking for:
		String pendingSpriteName = buildFilename(part, creature.part[part].gspcs, creature.age,  creature.part[part].slot);
		pendingSpriteName += ".c16";
		//then look for it in all the directories
		
		//then if you can't find it, you have to look for the next closest life stage
		
		//failing that, the next closest slot
		
		//I guess failing that the next closest species but geez
		
		//then do the same for atts
		
		//then actually parse the atts for the details of that part.
	}
	

//this gets the sprite file frame for the part/pose/direction/expression in question.
//expression only really matters if the part in question is the head (part 0)
	public int getFrame (int part, int pose, int dirn, int expression) {
		int frame = pose * 4 + (3 - dirn);
		//if we're dealing with the head part, we need to account for facial expression
		if (part == 0) {frame *=expression;}
		return frame;
	}
	
	public String buildFilename (int part, int gspcs, int stage, char slot) {
		char x = 'a';
		x += part;
		return x + Integer.toString(gspcs) + Integer.toString(stage) + slot;
	}
	
}