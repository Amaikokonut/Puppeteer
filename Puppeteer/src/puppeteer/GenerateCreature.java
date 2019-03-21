package puppeteer;

import java.io.IOException;
import puppeteer.SpriteCollectionComponent.DisplayedSprite;
import rebound.jagent.lib.FormatMismatchException;
import rebound.jagent.lib.c16.FromC16Converter;

public class GenerateCreature
{
	public static void DrawCreatureFromScratch(PosedCreature it) {
		//Output your ATT info to the window
		Puppeteer.updateAttInfo(FileInfoToReadableString (it));
		
		//really bad code for testing purposes-- make it nice next
		FromC16Converter sprite = new FromC16Converter();
		try
		{
			sprite.read(it.part[0].fileInfo.sprite);
		}
		catch (IOException | FormatMismatchException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FromC16Converter sprite2 = new FromC16Converter();
		try
		{
			sprite2.read(it.part[1].fileInfo.sprite);
		}
		catch (IOException | FormatMismatchException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int baseX = 100;
		int baseY = 0;
		DisplayedSprite head = new DisplayedSprite(sprite.getFrames()[it.part[0].fileInfo.frame], baseX, baseY);
		DisplayedSprite body = new DisplayedSprite(sprite2.getFrames()[it.part[1].fileInfo.frame], 
		baseX + it.part[0].fileInfo.attFromX - it.part[1].fileInfo.attToHeadX, baseY + it.part[0].fileInfo.attFromY - it.part[1].fileInfo.attToHeadY);
		Puppeteer.sprites.clear();
		Puppeteer.sprites.add(body);
		Puppeteer.sprites.add(head);
		Puppeteer.updateSprite();
	} 

	//this is long and bad and wet but I'm tired
	public static String FileInfoToReadableString(PosedCreature it) {
		//PosedCreature it = Puppeteer.creature;
		String text = "Sprite and ATT Info: \n";
		int x = 0;
		text += "**" + CreatureInfo.bodyParts[x] + "**: " +  it.part[x].fileInfo.sprite.getName() 
		+ isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.sprite.getName());
		text += " frame " + it.part[x].fileInfo.frame;
		text += "\n";
		text += "Body Data: " +  it.part[x].fileInfo.att.getName() 
		+ isItDifferent(it.part[0].fileInfo.pendingName, it.part[x].fileInfo.att.getName());
		text += "\n";
		text += "Connects to body at " + it.part[x].fileInfo.attFromX + ", " + it.part[x].fileInfo.attFromY;
		text += "\n";
		text += "Connects to mouth at " + it.part[x].fileInfo.attToX + ", " + it.part[x].fileInfo.attToY;
		text += "\n\n";
		
		x++;
		text += "**" + CreatureInfo.bodyParts[x] + "**: " +  it.part[x].fileInfo.sprite.getName() 
		+ isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.sprite.getName());
		text += " frame " + it.part[x].fileInfo.frame;
		text += "\n";
		text += "Body Data: " +  it.part[x].fileInfo.att.getName() 
		+ isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.att.getName());
		text += "\n";
		text += "Connects to head at " + it.part[x].fileInfo.attToHeadX + ", " + it.part[x].fileInfo.attToHeadY;
		text += "\n";
		text += "Connects to left arm at " + it.part[x].fileInfo.attToLeftArmX + ", " + it.part[x].fileInfo.attToLeftArmY;
		text += "\n";
		text += "Connects to right arm at " + it.part[x].fileInfo.attToRightArmX + ", " + it.part[x].fileInfo.attToRightArmY;
		text += "\n";
		text += "Connects to left leg at " + it.part[x].fileInfo.attToLeftLegX + ", " + it.part[x].fileInfo.attToLeftLegY;
		text += "\n";
		text += "Connects to right leg at " + it.part[x].fileInfo.attToRightLegX + ", " + it.part[x].fileInfo.attToRightLegY;
		text += "\n";
		text += "Connects to tail at " + it.part[x].fileInfo.attToTailX + ", " + it.part[x].fileInfo.attToTailY;
		text += "\n\n";
		
		x++;
		String[] connectsFrom = {"body","nothing","body", "left thigh", "left shin", "body", "right thigh", "right shin", 
				"body", "left humerus", "body", "right humerus", "body", "tail root"}; 
		String[] connectsTo = {"mouth","everything","left shin", "left foot", "ground", "right shin", "right foot", "ground", "left radius", "held item", "right radius", "held item", "tail tip", "air" }; 
		
		for (x = 2; x < 14; x++) {
		
		text += "**" + CreatureInfo.bodyParts[x] + "**: " +  it.part[x].fileInfo.sprite.getName() 
		+ isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.sprite.getName());
		text += " frame " + it.part[x].fileInfo.frame;
		text += "\n";
		text += "Body Data: " +  it.part[x].fileInfo.att.getName() 
		+ isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.att.getName());
		text += "\n";
		text += "Connects to "+ connectsFrom[x] + " at " + it.part[x].fileInfo.attFromX + ", " + it.part[x].fileInfo.attFromY;
		text += "\n";
		text += "Connects to "+ connectsTo[x] + " at " + it.part[x].fileInfo.attToX + ", " + it.part[x].fileInfo.attToY;
		text += "\n\n";
		}
		return text;
	} 
	
	
//compares two filenames to tell you why they are different 	
	public static String whyIsItDifferent(String pendingName, String finalName) {
		String reasons = "(";
		int pGspcs = Integer.parseInt(String.valueOf(pendingName.charAt(1)));
		int pStage = Integer.parseInt(String.valueOf(pendingName.charAt(2)));
		int pSlot = pendingName.charAt(3);
		
		int fGspcs = Integer.parseInt(String.valueOf(finalName.charAt(1)));
		int fStage = Integer.parseInt(String.valueOf(finalName.charAt(2)));
		int fSlot = finalName.charAt(3);
		
		if (pGspcs != fGspcs); {
			if (pGspcs - fGspcs == 4) {
				reasons += "G";
			} else if (pGspcs - fGspcs > 4) {
				reasons += "GSp";
			} else if (pGspcs - fGspcs < 4) {	
				reasons += "Sp";
			}
		}
		
		if (pStage != fStage) {
			reasons += "Ls";
		}
		
		if (pSlot != fSlot) {
			reasons += "Sl";
		}
		
		reasons += ")";
		
		return reasons;
	}

//returns a blank string if the filenames match, "*" and why it's different if they don't
	public static String isItDifferent(String pendingName, String name) {
		String result = "";
		String finalName = name.substring(0,4).toLowerCase();
		if (!pendingName.equals(finalName)) {
			result += "* " +  whyIsItDifferent(pendingName, finalName);
		}
		return result;
	}
	
//this is stuff that actually has to do with sprites
	public static void updateOneSprite() {
		
	}
}
