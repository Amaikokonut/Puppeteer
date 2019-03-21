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
		FromC16Converter headConv = makeOneSprite(it, 0);
		FromC16Converter bodyConv = makeOneSprite(it, 1);
		FromC16Converter LThighConv = makeOneSprite(it, 2);
		FromC16Converter LShinConv = makeOneSprite(it, 3);
		FromC16Converter LFootConv = makeOneSprite(it, 4);
		FromC16Converter RThighConv = makeOneSprite(it, 5);
		FromC16Converter RShinConv = makeOneSprite(it, 6);
		FromC16Converter RFootConv = makeOneSprite(it, 7);
		FromC16Converter LHumerusConv = makeOneSprite(it, 8);
		FromC16Converter LRadiusConv = makeOneSprite(it, 9);
		FromC16Converter RHumerusConv = makeOneSprite(it, 10);
		FromC16Converter RRadiusConv = makeOneSprite(it, 11);
		FromC16Converter TailRootConv = makeOneSprite(it, 12);
		FromC16Converter TailTipConv = makeOneSprite(it, 13);
		
		
		//head
		int headX = 100;
		int headY = 0;
		DisplayedSprite head = new DisplayedSprite(headConv.getFrames()[it.part[0].fileInfo.frame], headX, headY);
		
		//body
		int bodyX = headX + it.part[0].fileInfo.attFromX - it.part[1].fileInfo.attToHeadX;
		int bodyY = headY + it.part[0].fileInfo.attFromY - it.part[1].fileInfo.attToHeadY;
		DisplayedSprite body = new DisplayedSprite(bodyConv.getFrames()[it.part[1].fileInfo.frame], bodyX, bodyY);
		
		//left thigh
		int LThighX = bodyX + it.part[1].fileInfo.attToLeftLegX - it.part[2].fileInfo.attFromX;
		int LThighY = bodyY + it.part[1].fileInfo.attToLeftLegY - it.part[2].fileInfo.attFromY;
		DisplayedSprite lThigh = new DisplayedSprite(LThighConv.getFrames()[it.part[1].fileInfo.frame], LThighX, LThighY);
		
		//left shin
		int LShinX = LThighX + it.part[2].fileInfo.attToX - it.part[3].fileInfo.attFromX;
		int LShinY = LThighY + it.part[2].fileInfo.attToY - it.part[3].fileInfo.attFromY;
		DisplayedSprite lShin = new DisplayedSprite(LShinConv.getFrames()[it.part[1].fileInfo.frame], LShinX, LShinY);
		
		//left foot
		int LFootX = LShinX + it.part[3].fileInfo.attToX - it.part[4].fileInfo.attFromX;
		int LFootY = LShinY + it.part[3].fileInfo.attToY - it.part[4].fileInfo.attFromY;
		DisplayedSprite lFoot = new DisplayedSprite(LFootConv.getFrames()[it.part[1].fileInfo.frame], LFootX, LFootY);
		
		//right thigh
		int RThighX = bodyX + it.part[1].fileInfo.attToRightLegX - it.part[5].fileInfo.attFromX;
		int RThighY = bodyY + it.part[1].fileInfo.attToRightLegY - it.part[5].fileInfo.attFromY;
		DisplayedSprite rThigh = new DisplayedSprite(RThighConv.getFrames()[it.part[1].fileInfo.frame], RThighX, RThighY);
		
		//right shin
		int RShinX = RThighX + it.part[5].fileInfo.attToX - it.part[6].fileInfo.attFromX;
		int RShinY = RThighY + it.part[5].fileInfo.attToY - it.part[6].fileInfo.attFromY;
		DisplayedSprite rShin = new DisplayedSprite(RShinConv.getFrames()[it.part[1].fileInfo.frame], RShinX, RShinY);
		
		//right foot
		int RFootX = RShinX + it.part[6].fileInfo.attToX - it.part[7].fileInfo.attFromX;
		int RFootY = RShinY + it.part[6].fileInfo.attToY - it.part[7].fileInfo.attFromY;
		DisplayedSprite rFoot = new DisplayedSprite(RFootConv.getFrames()[it.part[1].fileInfo.frame], RFootX, RFootY);
		
		Puppeteer.sprites.clear();
		
		//order render depends on things
		if (it.dirn == 0) {
			Puppeteer.sprites.add(lThigh);
			Puppeteer.sprites.add(rThigh);
			
			Puppeteer.sprites.add(body);
			Puppeteer.sprites.add(head);
			
			Puppeteer.sprites.add(lShin);
			Puppeteer.sprites.add(lFoot);
			Puppeteer.sprites.add(rShin);
			Puppeteer.sprites.add(rFoot);
			
		} else if (it.dirn == 1) {
			Puppeteer.sprites.add(rThigh);
			Puppeteer.sprites.add(rShin);
			Puppeteer.sprites.add(rFoot);
			Puppeteer.sprites.add(lFoot);
			Puppeteer.sprites.add(lShin);
			Puppeteer.sprites.add(lFoot);
			Puppeteer.sprites.add(lThigh);
			Puppeteer.sprites.add(body);
			Puppeteer.sprites.add(head);
		
		} else if (it.dirn == 2) {
			Puppeteer.sprites.add(rFoot);
			Puppeteer.sprites.add(rShin);
			Puppeteer.sprites.add(rThigh);
			Puppeteer.sprites.add(body);
			Puppeteer.sprites.add(head);
			Puppeteer.sprites.add(lThigh);
			Puppeteer.sprites.add(lShin);
			Puppeteer.sprites.add(lFoot);
			
			
		} else if (it.dirn == 3) {
			Puppeteer.sprites.add(lFoot);
			Puppeteer.sprites.add(lShin);
			Puppeteer.sprites.add(lThigh);
			Puppeteer.sprites.add(body);
			Puppeteer.sprites.add(head);
			Puppeteer.sprites.add(rThigh);
			Puppeteer.sprites.add(rShin);
			Puppeteer.sprites.add(rFoot);
			
		}
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
	
	public static FromC16Converter makeOneSprite(PosedCreature it, int part) {
		FromC16Converter sprite = new FromC16Converter();
		try
		{
			sprite.read(it.part[part].fileInfo.sprite);
		}
		catch (IOException | FormatMismatchException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sprite;
	}
	
	
}
