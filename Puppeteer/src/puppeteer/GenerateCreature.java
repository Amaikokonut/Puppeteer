package puppeteer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import puppeteer.SpriteCollectionComponent.DisplayedSprite;
import rebound.jagent.lib.FormatMismatchException;
import rebound.jagent.lib.c16.FromC16Converter;

public class GenerateCreature
{
	public static void DrawCreatureFromScratch(PosedCreature it)
	{
		// DON'T DO THIS if there are no files!! Problems will totally happen
		if (Configgles.gamePaths.size() == 0)
		{
			return;
		}
		// Output your ATT info to the window
		Puppeteer.updateAttInfo(FileInfoToReadableString(it));
		
		// really bad code for testing purposes that will never run
		//remove this
		if (false) { 
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
		
		// head
		int headX = 100;
		int headY = 0;
		DisplayedSprite head = new DisplayedSprite(headConv.getFrames()[it.part[0].fileInfo.frame], headX, headY);
		
		// body
		int bodyX = headX + it.part[0].fileInfo.attFromX - it.part[1].fileInfo.attToHeadX;
		int bodyY = headY + it.part[0].fileInfo.attFromY - it.part[1].fileInfo.attToHeadY;
		DisplayedSprite body = new DisplayedSprite(bodyConv.getFrames()[it.part[1].fileInfo.frame], bodyX, bodyY);
		
		// left thigh
		int LThighX = bodyX + it.part[1].fileInfo.attToLeftLegX - it.part[2].fileInfo.attFromX;
		int LThighY = bodyY + it.part[1].fileInfo.attToLeftLegY - it.part[2].fileInfo.attFromY;
		DisplayedSprite lThigh = new DisplayedSprite(LThighConv.getFrames()[it.part[1].fileInfo.frame], LThighX, LThighY);
		
		// left shin
		int LShinX = LThighX + it.part[2].fileInfo.attToX - it.part[3].fileInfo.attFromX;
		int LShinY = LThighY + it.part[2].fileInfo.attToY - it.part[3].fileInfo.attFromY;
		DisplayedSprite lShin = new DisplayedSprite(LShinConv.getFrames()[it.part[1].fileInfo.frame], LShinX, LShinY);
		
		// left foot
		int LFootX = LShinX + it.part[3].fileInfo.attToX - it.part[4].fileInfo.attFromX;
		int LFootY = LShinY + it.part[3].fileInfo.attToY - it.part[4].fileInfo.attFromY;
		DisplayedSprite lFoot = new DisplayedSprite(LFootConv.getFrames()[it.part[1].fileInfo.frame], LFootX, LFootY);
		
		// right thigh
		int RThighX = bodyX + it.part[1].fileInfo.attToRightLegX - it.part[5].fileInfo.attFromX;
		int RThighY = bodyY + it.part[1].fileInfo.attToRightLegY - it.part[5].fileInfo.attFromY;
		DisplayedSprite rThigh = new DisplayedSprite(RThighConv.getFrames()[it.part[1].fileInfo.frame], RThighX, RThighY);
		
		// right shin
		int RShinX = RThighX + it.part[5].fileInfo.attToX - it.part[6].fileInfo.attFromX;
		int RShinY = RThighY + it.part[5].fileInfo.attToY - it.part[6].fileInfo.attFromY;
		DisplayedSprite rShin = new DisplayedSprite(RShinConv.getFrames()[it.part[1].fileInfo.frame], RShinX, RShinY);
		
		// right foot
		int RFootX = RShinX + it.part[6].fileInfo.attToX - it.part[7].fileInfo.attFromX;
		int RFootY = RShinY + it.part[6].fileInfo.attToY - it.part[7].fileInfo.attFromY;
		DisplayedSprite rFoot = new DisplayedSprite(RFootConv.getFrames()[it.part[1].fileInfo.frame], RFootX, RFootY);
		
		// left arm
		int LHumerusX = bodyX + it.part[1].fileInfo.attToLeftArmX - it.part[8].fileInfo.attFromX;
		int LHumerusY = bodyY + it.part[1].fileInfo.attToLeftArmY - it.part[8].fileInfo.attFromY;
		DisplayedSprite lHumerus = new DisplayedSprite(LHumerusConv.getFrames()[it.part[1].fileInfo.frame], LHumerusX, LHumerusY);
		
		// left hand
		int LRadiusX = LHumerusX + it.part[8].fileInfo.attToX - it.part[9].fileInfo.attFromX;
		int LRadiusY = LHumerusY + it.part[8].fileInfo.attToY - it.part[9].fileInfo.attFromY;
		DisplayedSprite lRadius = new DisplayedSprite(LRadiusConv.getFrames()[it.part[1].fileInfo.frame], LRadiusX, LRadiusY);
		
		// right arm
		int RHumerusX = bodyX + it.part[1].fileInfo.attToRightArmX - it.part[10].fileInfo.attFromX;
		int RHumerusY = bodyY + it.part[1].fileInfo.attToRightArmY - it.part[10].fileInfo.attFromY;
		DisplayedSprite rHumerus = new DisplayedSprite(RHumerusConv.getFrames()[it.part[1].fileInfo.frame], RHumerusX, RHumerusY);
		
		// right hand
		int RRadiusX = RHumerusX + it.part[10].fileInfo.attToX - it.part[11].fileInfo.attFromX;
		int RRadiusY = RHumerusY + it.part[10].fileInfo.attToY - it.part[11].fileInfo.attFromY;
		DisplayedSprite rRadius = new DisplayedSprite(RRadiusConv.getFrames()[it.part[1].fileInfo.frame], RRadiusX, RRadiusY);
		
		// tail root
		int TailRootX = bodyX + it.part[1].fileInfo.attToTailX - it.part[12].fileInfo.attFromX;
		int TailRootY = bodyY + it.part[1].fileInfo.attToTailY - it.part[12].fileInfo.attFromY;
		DisplayedSprite tailRoot = new DisplayedSprite(TailRootConv.getFrames()[it.part[1].fileInfo.frame], TailRootX, TailRootY);
		
		// tail tip
		int TailTipX = TailRootX + it.part[12].fileInfo.attToX - it.part[13].fileInfo.attFromX;
		int TailTipY = TailRootY + it.part[12].fileInfo.attToY - it.part[13].fileInfo.attFromY;
		DisplayedSprite tailTip = new DisplayedSprite(TailTipConv.getFrames()[it.part[1].fileInfo.frame], TailTipX, TailTipY);
		
		Puppeteer.sprites.clear();
		// order according to body part so you can easily change this later!!
		
		Puppeteer.sprites.add(head); // 0
		Puppeteer.sprites.add(body); // 1
		Puppeteer.sprites.add(lThigh); // 2
		Puppeteer.sprites.add(lShin); // 3
		Puppeteer.sprites.add(lFoot); // 4
		Puppeteer.sprites.add(rThigh); // 5
		Puppeteer.sprites.add(rShin); // 6
		Puppeteer.sprites.add(rFoot); // 7
		Puppeteer.sprites.add(lHumerus); // 8
		Puppeteer.sprites.add(lRadius); // 9
		Puppeteer.sprites.add(rHumerus); // 10
		Puppeteer.sprites.add(rRadius); // 11
		Puppeteer.sprites.add(tailRoot); // 12
		Puppeteer.sprites.add(tailTip); // 13
		
		}
		
		//new way of doing this:
		Puppeteer.sprites.clear();
		for (int i = 0; i < 14; i++) {
			Puppeteer.sprites.add(null);
			}
		updatePart(1, it, 50, 50, Puppeteer.sprites);
		Puppeteer.updateSprite(layerSpritesByDirn(it.dirn, Puppeteer.sprites));
	}
	
	// this is long and bad and wet but I'm tired
	public static String FileInfoToReadableString(PosedCreature it)
	{
		// PosedCreature it = Puppeteer.creature;
		String text = "Sprite and ATT Info: \n";
		int x = 0;
		text += "**" + CreatureInfo.bodyParts[x] + "**: " + it.part[x].fileInfo.sprite.getName() + isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.sprite.getName());
		text += " frame " + it.part[x].fileInfo.frame;
		text += "\n";
		text += "Body Data: " + it.part[x].fileInfo.att.getName() + isItDifferent(it.part[0].fileInfo.pendingName, it.part[x].fileInfo.att.getName());
		text += "\n";
		text += "Connects to body at " + it.part[x].fileInfo.attFromX + ", " + it.part[x].fileInfo.attFromY;
		text += "\n";
		text += "Connects to mouth at " + it.part[x].fileInfo.attToX + ", " + it.part[x].fileInfo.attToY;
		text += "\n\n";
		
		x++;
		text += "**" + CreatureInfo.bodyParts[x] + "**: " + it.part[x].fileInfo.sprite.getName() + isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.sprite.getName());
		text += " frame " + it.part[x].fileInfo.frame;
		text += "\n";
		text += "Body Data: " + it.part[x].fileInfo.att.getName() + isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.att.getName());
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
		String[] connectsFrom =
		{
				"body", "nothing", "body", "left thigh", "left shin", "body", "right thigh", "right shin", "body", "left humerus", "body", "right humerus", "body", "tail root"
		};
		String[] connectsTo =
		{
				"mouth", "everything", "left shin", "left foot", "ground", "right shin", "right foot", "ground", "left radius", "held item", "right radius", "held item", "tail tip", "air"
		};
		
		for (x = 2; x < 14; x++)
		{
			
			text += "**" + CreatureInfo.bodyParts[x] + "**: " + it.part[x].fileInfo.sprite.getName() + isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.sprite.getName());
			text += " frame " + it.part[x].fileInfo.frame;
			text += "\n";
			text += "Body Data: " + it.part[x].fileInfo.att.getName() + isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.att.getName());
			text += "\n";
			text += "Connects to " + connectsFrom[x] + " at " + it.part[x].fileInfo.attFromX + ", " + it.part[x].fileInfo.attFromY;
			text += "\n";
			text += "Connects to " + connectsTo[x] + " at " + it.part[x].fileInfo.attToX + ", " + it.part[x].fileInfo.attToY;
			text += "\n\n";
		}
		return text;
	}
	
	// compares two filenames to tell you why they are different
	public static String whyIsItDifferent(String pendingName, String finalName)
	{
		String reasons = "(";
		int pGspcs = Integer.parseInt(String.valueOf(pendingName.charAt(1)));
		int pStage = Integer.parseInt(String.valueOf(pendingName.charAt(2)));
		int pSlot = pendingName.charAt(3);
		
		int fGspcs = Integer.parseInt(String.valueOf(finalName.charAt(1)));
		int fStage = Integer.parseInt(String.valueOf(finalName.charAt(2)));
		int fSlot = finalName.charAt(3);
		
		if (pGspcs != fGspcs)
			;
		{
			if (pGspcs - fGspcs == 4)
			{
				reasons += "G";
			}
			else if (pGspcs - fGspcs > 4)
			{
				reasons += "GSp";
			}
			else if (pGspcs - fGspcs < 4)
			{
				reasons += "Sp";
			}
		}
		
		if (pStage != fStage)
		{
			reasons += "Ls";
		}
		
		if (pSlot != fSlot)
		{
			reasons += "Sl";
		}
		
		reasons += ")";
		
		return reasons;
	}
	
	// returns a blank string if the filenames match, "*" and why it's different if they don't
	public static String isItDifferent(String pendingName, String name)
	{
		String result = "";
		String finalName = name.substring(0, 4).toLowerCase();
		if (!pendingName.equals(finalName))
		{
			result += "* " + whyIsItDifferent(pendingName, finalName);
		}
		return result;
	}
	
	// this is stuff that actually has to do with sprites
	public static void updateOneSprite()
	{
		
	}
	
	public static FromC16Converter makeOneSprite(PosedCreature it, int part)
	{
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
	
	// this orders your sprites so they layer/draw in the right order
	// this assumes they are already ordered according to body part index!
	public static List<DisplayedSprite> layerSpritesByDirn(int dirn, List<DisplayedSprite> unlayeredSprites)
	{
		List<DisplayedSprite> layeredSprites = new ArrayList<>();
		// here is how to order them-- remember lower layer part sprites are drawn first
		int[] order;
		int[] orderBack =
		{
				2, 5, 0, 1, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13
		};
		int[] orderFront =
		{
				13, 12, 5, 6, 7, 2, 3, 4, 8, 10, 1, 0, 9, 11
		};
		int[] orderLeft =
		{
				10, 11, 7, 6, 5, 12, 13, 1, 0, 2, 3, 4, 8, 9
		};
		int[] orderRight =
		{
				8, 9, 4, 3, 2, 12, 13, 1, 0, 5, 6, 7, 10, 11
		};
		
		if (dirn == 0)
		{
			order = orderBack;
		}
		else if (dirn == 1)
		{
			order = orderFront;
		}
		else if (dirn == 2)
		{
			order = orderLeft;
		}
		else
		{
			order = orderRight;
		}
		
		for (int i : order)
		{
			layeredSprites.add(Puppeteer.sprites.get(i));
		}
		return layeredSprites;
		
	}
	
	public static void drawErrorSprite()
	{
		// if there aren't any directories, make that default sprite
		BufferedImage default404Sprite = null;
		
		try
		{
			default404Sprite = ImageIO.read((Puppeteer.class.getResource("norn.png")));
		}
		catch (IOException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		DisplayedSprite defaultSprite = new DisplayedSprite(default404Sprite, 0, 0);
		List<DisplayedSprite> defaultSprites = new ArrayList<>();
		defaultSprites.add(defaultSprite);
		Puppeteer.updateSprite(defaultSprites);
	}
	
	// update an existing part (and make sure all your kids are updated)
	public static void updatePart(int part, PosedCreature it, int toX, int toY, List<DisplayedSprite> sprites)
	{
		// make your converter:
		FromC16Converter partConv = makeOneSprite(it, part);
		// everything that's not the body:
		if (part != 1)
		{
			// your toX/Y should be the point at which you need to attach your FromX/Y to (basically your parent's position +
			// their toX/Y)
			int myX = toX - it.part[part].fileInfo.attFromX;
			int myY = toY - it.part[part].fileInfo.attFromY;
			DisplayedSprite sprite = new DisplayedSprite(partConv.getFrames()[it.part[part].fileInfo.frame], myX, myY);
			sprites.set(part, sprite);
			
			// now it's your responsibility to call your kid (if you have one) and tell them to update too
			int[] partsThatHaveKids =
			{
					1, 2, 3, 5, 6, 8, 10, 12
			};
			
			if (IntStream.of(partsThatHaveKids).anyMatch(i -> i == part)) {
				int myToX = myX + it.part[part].fileInfo.attToX;
				int myToY = myY + it.part[part].fileInfo.attToY;
				
				int kidPart = part + 1;
				
				updatePart(kidPart, it, myToX, myToY,sprites);
			}
			
		//the body part is its own beast, mostly because it has so many kids:
		} else if (part == 1) {
			//toX/y is probably 0,0, or just the general... positioning point of the thing in general
			DisplayedSprite sprite = new DisplayedSprite(partConv.getFrames()[it.part[part].fileInfo.frame], toX, toY);
			sprites.set(part, sprite);
			//the big part is calling all six of your kids:
			//part 0, the head:
			int kidX = toX + it.part[part].fileInfo.attToHeadX;
			int kidY = toY + it.part[part].fileInfo.attToHeadY;
			updatePart(0, it, kidX, kidY,sprites);
			// 2, left leg
			kidX = toX + it.part[part].fileInfo.attToLeftLegX;
			kidY = toY + it.part[part].fileInfo.attToLeftLegY;
			updatePart(2, it, kidX, kidY,sprites);
			// 5, right leg
			kidX = toX + it.part[part].fileInfo.attToRightLegX;
			kidY = toY + it.part[part].fileInfo.attToRightLegY;
			updatePart(5, it, kidX, kidY,sprites);
			// 8, left arm
			kidX = toX + it.part[part].fileInfo.attToLeftArmX;
			kidY = toY + it.part[part].fileInfo.attToLeftArmY;
			updatePart(8, it, kidX, kidY,sprites);
			// 10, right arm
			kidX = toX + it.part[part].fileInfo.attToRightArmX;
			kidY = toY + it.part[part].fileInfo.attToRightArmY;
			updatePart(10, it, kidX, kidY,sprites);
			// 12, tail
			kidX = toX + it.part[part].fileInfo.attToTailX;
			kidY = toY + it.part[part].fileInfo.attToTailY;
			updatePart(12, it, kidX, kidY,sprites);
					
		}
		
	}
}
