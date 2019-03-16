package puppeteer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import puppeteer.pathcaseinsensitivity.CaseInsensitiveDirectory;
import puppeteer.pathcaseinsensitivity.MultipleFilesDifferingOnlyByCaseDetectedWhenTheyShouldntException;

public class FileInfo
{
	//Passed a creature object and a part-- creates an object that contains:
	
	File sprite; //case-corrected true sprite file
	boolean spriteIsAdapted; //sprite filename differs from the one requested 
	File att; //case-corrected true att file
	boolean attIsAdapted;
	boolean attIsSlotAdapted;
	int frame;
	int attX;
	int attY;
	int attAnchorX;
	int attAnchorY;
	
	public FileInfo (PosedCreature creature, int part) throws MultipleFilesDifferingOnlyByCaseDetectedWhenTheyShouldntException, IOException {
		//super-first, because this is pretty easy, you can construct the sprite pose number.
		this.frame = getFrame (part, creature.part[part].pose, creature.part[part].dirn, creature.expression);
		//now you have to construct the filename you are looking for:
		String pendingSpriteName = buildFilename(part, creature.part[part].gspcs, creature.age,  creature.part[part].slot);
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
	
//build filename
	public static String buildFilename (int part, int gspcs, int stage, char slot) {
		char x = 'a';
		x += part;
		return x + Integer.toString(gspcs) + Integer.toString(stage) + slot;
	}
	
//find image file, returns the real filename or null if not found. If isSprite is false, it assumes bodydata
	public static File findFile (String extName, Boolean isSprite) throws IOException {
		//System.out.println(extName);
		File returnFile = null;
		File targfile;
		for (int i = 0; i < Configgles.gamePaths.size(); i++)
		{
			if (isSprite) {
			targfile = Configgles.gamePaths.get(i).images.toFile(); 
			} else {
			targfile = Configgles.gamePaths.get(i).bodyData.toFile();
			}
			returnFile = new CaseInsensitiveDirectory(targfile).findPathCaseInsensitivelyOrNullIfNonexistant(extName);
			//break out of the loop once you found a file
			if (returnFile != null) {break;}
		}
		return returnFile;
	}
	
//this big return file or nearest file method
//If isSprite is false, it assumes bodydata
public static File getClosestFile (int part, int gspcs, int stage, char slot, Boolean isSprite) throws IOException {
	//start out by checking for the closest life stages:
	File returnFile = getClosestLifestageFile(part, gspcs, stage, slot, isSprite);
	if (returnFile != null) return returnFile;
	//If you're still here, we're going to have to start looking in different slots: 
	//gonna start by going backwards:
	char currentSlot = slot;
	while (currentSlot > 'a') {
		currentSlot--;
		//build a new filename with the new stage
		String name = buildFilename(part, gspcs, stage, currentSlot) + ((isSprite) ? ".c16" : ".att");
		//and check if it exists
		returnFile = findFile(name, isSprite);
		if (returnFile != null) return returnFile;
	}
	//still here? oi. Let's look forwards.
	currentSlot = slot;
	while (currentSlot < 'z') {
		currentSlot++;
		//build a new filename with the new stage
		String name = buildFilename(part, gspcs, stage, currentSlot) + ((isSprite) ? ".c16" : ".att");
		//and check if it exists
		returnFile = findFile(name, isSprite);
		if (returnFile != null) return returnFile;
	}
	//at this point we're actually gonna stop testing since we're only guessing at how the engine does this
	//and decide if these don't exist, to resort to norn slot a (should always work if C3 is installed)
	returnFile = getClosestLifestageFile(part, (gspcs > 3) ? 4 : 0, stage, 'a', isSprite);
	if (returnFile != null) return returnFile;

	//followed by norn slot d (should always work if DS is installed)
	returnFile = getClosestLifestageFile(part, (gspcs > 3) ? 4 : 0, stage, 'd', isSprite);
	if (returnFile != null) return returnFile;
	
	//if you get null, something's probably wronf
	return null;
}

public static File getClosestLifestageFile (int part, int gspcs, int stage, char slot, Boolean isSprite) throws IOException {
	//build the filename
	String name = buildFilename(part, gspcs, stage, slot) + ((isSprite) ? ".c16" : ".att");
	//see if the file exists as-is first
	File returnFile = findFile(name, isSprite);
	if (returnFile != null) return returnFile;
	//if you're still here, that file was not found. Boo.
	//So what we're gonna do first is go *down* in life stages and look there first
	int currentStage = stage;
	while (currentStage > 0) {
		currentStage--;
		//build a new filename with the new stage
		name = buildFilename(part, gspcs, currentStage, slot) + ((isSprite) ? ".c16" : ".att");
		//and check if it exists
		returnFile = findFile(name, isSprite);
		if (returnFile != null) return returnFile;
	}
	//if you're still here, no younger lifestages were found.
	//So now we're look for older stages and see if they exist
	currentStage = stage;
	while (currentStage < 6) {
		currentStage++;
		//build a new filename with the new stage
		name = buildFilename(part, gspcs, currentStage, slot) + ((isSprite) ? ".c16" : ".att");
		//and check if it exists
		returnFile = findFile(name, isSprite);
		if (returnFile != null) return returnFile;
	}
	//if you're still here, that didn't work either, sorry.
	//guess there's nothing in that slot
	return null;
}


}