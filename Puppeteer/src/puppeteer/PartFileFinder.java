package puppeteer;

import java.io.File;

public class PartFileFinder
{
	FileLibrary fileLibrary;

	public PartFileFinder(FileLibrary fileLibrary) {
		this.fileLibrary = fileLibrary;
	}
	
//probably the main function here:	
	public FileSet getFileSet(PosedPart posedPart){
		//step 1: build the filename
		String baseFilename = buildFilename(posedPart);
		//step 2: get the wanted filenames
		String spriteWanted = baseFilename + ".c16";
		String attWanted = baseFilename + ".att";
		//step 3: search for the sprite file:
		String spriteAvailable = getClosestFile(posedPart, "c16");
		String attAvailable = getClosestFile(posedPart, "att");
		return new FileSet(spriteWanted, spriteAvailable, attWanted, attAvailable);
		
	}
	
	public String buildFilename(PosedPart posedPart) {
		return buildFilename(posedPart.part, posedPart.gspcs, posedPart.age, posedPart.slot);
	}
	
	public String buildFilename(int part, int gspcs, int stage, char slot)
	{
		char x = 'a';
		x += part;
		return x + Integer.toString(gspcs) + Integer.toString(stage) + slot;
	}
	
	// find image file, returns the real filename or null if not found.
	public String findFile(String fileName)
	{
		if (fileLibrary.contains(fileName)) {
			return null;
		} else
		return fileName;
	}
	
	public String getClosestFile(PosedPart posedPart, String ext) {
		return getClosestFile(posedPart.part, posedPart.gspcs, posedPart.age, posedPart.slot, ext);
	}
	
	// this big return file or nearest file method
	// If isSprite is false, it assumes bodydata
	public String getClosestFile(int part, int gspcs, int stage, char slot, String ext)
	{
		// start out by checking for the closest life stages:
		String returnFile = getClosestLifestageFile(part, gspcs, stage, slot, ext);
		if (returnFile != null)
			return returnFile;
		// If you're still here, we're going to have to start looking in different slots:
		// gonna start by going backwards:
		char currentSlot = slot;
		while (currentSlot > 'a')
		{
			currentSlot--;
			returnFile = getClosestLifestageFile(part, gspcs, stage, currentSlot, ext);
			if (returnFile != null)
				return returnFile;
		}
		// still here? oi. Let's look forwards.
		currentSlot = slot;
		while (currentSlot < 'z')
		{
			currentSlot++;
			returnFile = getClosestLifestageFile(part, gspcs, stage, currentSlot, ext);
			if (returnFile != null)
				return returnFile;
		}
		// at this point we're actually gonna stop testing since we're only guessing at how the engine does this
		// and decide if these don't exist, to resort to norn slot a (should always work if C3 is installed)
		returnFile = getClosestLifestageFile(part, (gspcs > 3) ? 4 : 0, stage, 'a', ext);
		if (returnFile != null)
			return returnFile;
		
		// followed by norn slot d (should always work if DS is installed)
		returnFile = getClosestLifestageFile(part, (gspcs > 3) ? 4 : 0, stage, 'd', ext);
		if (returnFile != null)
			return returnFile;
		
		// if you get null, something's probably wrong
		return null;
	}
	
	public String getClosestLifestageFile(int part, int gspcs, int stage, char slot, String ext)
	{
		// build the filename
		String name = buildFilename(part, gspcs, stage, slot) + "." + ext;
		// see if the file exists as-is first
		String returnFile = findFile(name);
		if (returnFile != null)
			return returnFile;
		// if you're still here, that file was not found.
		// if you're a female, check for a male file (how unfair))
		if (gspcs > 3)
		{
			returnFile = getMaleFiles(part, gspcs, stage, slot, ext);
			if (returnFile != null)
				return returnFile;
		}
		// So what we're gonna do first is go *down* in life stages and look there first
		int currentStage = stage;
		while (currentStage > 0)
		{
			currentStage--;
			// build a new filename with the new stage
			name = buildFilename(part, gspcs, currentStage, slot) +  "." + ext;
			// and check if it exists
			returnFile = findFile(name);
			if (returnFile != null)
				return returnFile;
			
			// if you're a female, check for a male file (how unfair))
			if (gspcs > 3)
			{
				returnFile = getMaleFiles(part, gspcs, currentStage, slot, ext);
				if (returnFile != null)
					return returnFile;
			}
		}
		// if you're still here, no younger lifestages were found.
		// So now we're look for older stages and see if they exist
		currentStage = stage;
		while (currentStage < 6)
		{
			currentStage++;
			// build a new filename with the new stage
			name = buildFilename(part, gspcs, currentStage, slot) +  "." + ext;
			// and check if it exists
			returnFile = findFile(name);
			if (returnFile != null)
				return returnFile;
		}
		// if you're a female, check for a male file (how unfair))
		if (gspcs > 3)
		{
			returnFile = getMaleFiles(part, gspcs, currentStage, slot, ext);
			if (returnFile != null)
				return returnFile;
		}
		// if you're still here, that didn't work either, sorry.
		// guess there's nothing in that slot
		return null;
	}
	
	// apparently this is something we have to do
	public String getMaleFiles(int part, int gspcs, int stage, char slot, String ext)
	{
		// build a male filename
		String name = buildFilename(part, (gspcs - 4), stage, slot) +  "." + ext;
		// and check if it exists
		String returnFile = findFile(name);
		if (returnFile != null)
			return returnFile;
		return null;
	}
	
}
