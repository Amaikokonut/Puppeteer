package puppeteer;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import puppeteer.SpriteCollectionComponent.DisplayedSprite;
import rebound.jagent.lib.FormatMismatchException;
import rebound.jagent.lib.c16.FromC16Converter;

public class CreatureGenerator
{
	Configgles gamePaths;
	List<ATTPoseSet> attCreatureSet = new ArrayList<>(14);
	List<FileSet> creatureFileSet = new ArrayList<>(14);
	List<DisplayedSprite> unlayeredSprites = new ArrayList<>(14);
	List<DisplayedSprite> layeredSprites = new ArrayList<>(14); //might not need this?
	PartFileFinder partFileFinder;
	int[] partsThatHaveKids =
	{
			1, 2, 3, 5, 6, 8, 10, 12
	};
	
	public CreatureGenerator(Configgles gamePaths)
	{
		this.gamePaths = gamePaths;
		partFileFinder = new PartFileFinder(gamePaths.fileLibrary);
	}
	
	// the main two methods here
	public List<DisplayedSprite> MakeNewCreatureAndGetSprites(PosedCreature it)
	{
		//make a blank set of sprites
		unlayeredSprites = new ArrayList<>(14);
		for (int i = 0; i < 14; i++)
		{
			unlayeredSprites.add(null);
		}
		//repopulate attCreatureSet and creatureFileSet from scratch
		populateCreatureSets(it);
		//update the body part and the rest will cascade, like magic~
		UpdatePartinSprites(it, 1);
		//layer and return-- you did it :>
		return layerSpritesByDirn(it.dirn, unlayeredSprites);
	}
	
	// this might cause problems if MakeNewCreature has never been called, maybe add a check for that?
	public List<DisplayedSprite> UpdatePartAndGetSprites(PosedCreature it, int part)
	{
		UpdatePartinSprites(it, part);
		// layer your sprites by dirn
		return layerSpritesByDirn(it.dirn, unlayeredSprites);
	}
	
	
	void UpdatePartinSprites(PosedCreature it, int part) {
		updatePartInCreatureSets(it, part);
		// get your own sprite
		BufferedImage img = getPosedPartAsImage(it, part);
		XY position;
		// only if you're not the body part-- the body part has no parents
		if (part != 1)
		{
			// get your From atts
			XY myATTs = new XY(attCreatureSet.get(part).fromX, attCreatureSet.get(part).fromY);
			// get your parent's absolute position and add their 'to' atts to get your relative position
			position = getATTPointfromParent(unlayeredSprites, part, it);
			// actually subtract your from atts, that's your real position
			position.subtract(myATTs);
		}
		else
		{
			// you are the body part, your position is 50,50
			position = new XY(50, 50);
		}
		// place yourself into unlayered sprites
		DisplayedSprite updatedSprite = new DisplayedSprite(img, position.getX(), position.getY());

		System.out.println("time to set part " + part );
		//the actual setting finally happens here
		unlayeredSprites.set(part, updatedSprite);
		
		// if you have kids you will have to update them too!
		if (part == 1) {
		//the body has the most kids:
			UpdatePartinSprites(it, 0);
			UpdatePartinSprites(it, 2);
			UpdatePartinSprites(it, 5);
			UpdatePartinSprites(it, 8);
			UpdatePartinSprites(it, 10);
			UpdatePartinSprites(it, 12);
		}
		else if (hasAKid(part))
			//anyone else that has kids will just be part + 1
		{
			UpdatePartinSprites(it, part + 1);
		}
	}
	
	// consider making... more generic versions of this? Might not be needed though.
	BufferedImage getPosedPartAsImage(PosedCreature it, int part)
	{
		String spritefile = creatureFileSet.get(part).spriteAvailable;
		int pose = getFrame(it.part[part]);
		BufferedImage partSprite = gamePaths.fileLibrary.getSpriteFile(spritefile).getFrames()[pose];
		return partSprite;
	}
	
	// this gets the sprite file frame for the part/pose/direction/expression in question.
	// expression only really matters if the part in question is the head (part 0)
	public int getFrame(PosedPart posedPart)
	{
		return getFrame(posedPart.part, posedPart.pose, posedPart.dirn, posedPart.expression, posedPart.eyes);
	}
	
	public int getFrame(int part, int pose, int dirn, int expression, int eyes)
	{
		int frame = 4 * (3 - dirn) + pose;
		// if we're dealing with the head part, we need to account for facial expression and eyes
		if (part == 0)
		{
			frame += expression * 32;
			frame += eyes == 1 ? 16 : 0;
		}
		return frame;
	}
	
	// methods that deal with with attCreatureSet and creatureFileSet
	void populateATTSet(PosedCreature it)
	{
		attCreatureSet.clear();
		for (int i = 0; i < 14; i++)
		{
			int partPose = it.part[i].pose;
			String attFile = creatureFileSet.get(i).attAvailable;
			System.out.println(attFile + " should exist");
			attCreatureSet.add(gamePaths.fileLibrary.getATTPoseSet(attFile, partPose));
		}
	}
	
	void populateFileSet(PosedCreature it)
	{
		creatureFileSet.clear();
		for (int i = 0; i < 14; i++)
		{
			creatureFileSet.add(partFileFinder.getFileSet(it.part[i]));
		}
	}
	
	void updatePartInATTSet(PosedCreature it, int part)
	{
		int partPose = it.part[part].pose;
		String attFile = creatureFileSet.get(part).attAvailable;
		attCreatureSet.set(part, gamePaths.fileLibrary.getATTPoseSet(attFile, partPose));
	}
	
	void updatePartInFileSet(PosedCreature it, int part)
	{
		creatureFileSet.set(part, partFileFinder.getFileSet(it.part[part]));
	}
	
	void populateCreatureSets(PosedCreature it)
	{
		populateFileSet(it);
		populateATTSet(it);
	}
	
	void updatePartInCreatureSets(PosedCreature it, int part)
	{
		updatePartInFileSet(it, part);
		updatePartInATTSet(it, part);
	}
	
	//
	public XY getATTPointfromParent(List<DisplayedSprite> sprites, int part, PosedCreature it)
	{
		int parentPart = getParentPart(part);
		// get where your parent is in the sprite
		XY parentPoint = getPositionInSprite(parentPart, sprites);
		// add the parents' atts as they are relevant to you
		parentPoint.add(getATTPointtoChild(parentPart, part, sprites, it));
		return parentPoint;
	}
	
	public int getParentPart(int part)
	{
		if (part == 0 || part == 2 || part == 5 || part == 8 || part == 10 || part == 12)
		{
			return 1;
		}
		else if (part > 1 && part < 14)
		{
			return part - 1;
		}
		else
		{
			return -1;
		}
	}
	
	public int getChildPart(int part)
	{
		if (hasAKid(part))
		{
			return part + 1;
		}
		else
		{
			return -1;
		}
	}
	
	public XY getPositionInSprite(int part, List<DisplayedSprite> sprites)
	{
		return new XY(sprites.get(part).getX(), sprites.get(part).getY());
	}
	
	public XY getATTPointtoChild(int parentPart, int childPart, List<DisplayedSprite> sprites, PosedCreature it)
	{
		if (parentPart == 1)
		{
			if (childPart == 2)
			{
				return new XY(attCreatureSet.get(parentPart).toLeftLegX, attCreatureSet.get(parentPart).toLeftLegY);
			}
			else if (childPart == 0)
			{
				return new XY(attCreatureSet.get(parentPart).toHeadX, attCreatureSet.get(parentPart).toHeadY);
			}
			else if (childPart == 5)
			{
				return new XY(attCreatureSet.get(parentPart).toRightLegX, attCreatureSet.get(parentPart).toRightLegY);
			}
			else if (childPart == 8)
			{
				return new XY(attCreatureSet.get(parentPart).toLeftArmX, attCreatureSet.get(parentPart).toLeftArmY);
			}
			else if (childPart == 10)
			{
				return new XY(attCreatureSet.get(parentPart).toRightArmX, attCreatureSet.get(parentPart).toRightArmY);
			}
			else if (childPart == 12)
			{
				return new XY(attCreatureSet.get(parentPart).toTailX, attCreatureSet.get(parentPart).toTailY);
			}
			else // this should never happen
			{
				return new XY(0, 0);
			}
		}
		else
		
		{
			return new XY(attCreatureSet.get(parentPart).toX, attCreatureSet.get(parentPart).toY);
		}
	}
	
	public boolean hasAKid(int part)
	{
		return IntStream.of(partsThatHaveKids).anyMatch(x -> x == part);
	}
	
	// this orders your sprites so they layer/draw in the right order
	// this assumes they are already ordered according to body part index!
	public List<DisplayedSprite> layerSpritesByDirn(int dirn, List<DisplayedSprite> unlayeredSprites)
	{
		List<DisplayedSprite> layeredSprites = new ArrayList<>();
		// here is how to order them-- remember lower layer part sprites are drawn first
		int[] order;
		int[] orderBack =
		{
				4, 3, 7, 6, 8, 9, 2, 5, 10, 11, 1, 0, 12, 13
		};
		int[] orderFront =
		{
				13, 12, 5, 2, 3, 4, 8, 10, 1, 6, 7, 3, 4, 9, 11, 0
		};
		int[] orderLeft =
		{
				10, 11, 6, 7, 5, 12, 13, 1, 0, 2, 3, 4, 8, 9
		};
		int[] orderRight =
		{
				8, 9, 3, 4, 2, 12, 13, 1, 0, 5, 6, 7, 10, 11
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
			layeredSprites.add(unlayeredSprites.get(i));
		}
		return layeredSprites;
		
	}
	
	
	// Older Methods are below This Line >> _______________________________________
	
	public List<DisplayedSprite> getUnlayeredSpritesFromCreature(PosedCreature it)
	{
		// Send back the error sprite if there's nothing to draw from
		
		if (gamePaths.gamePaths.size() == 0)
		{
			return getErrorSprite();
		}
		
		// new way of doing this:
		List<DisplayedSprite> newSprites = new ArrayList<>(14);
		for (int i = 0; i < 14; i++)
		{
			newSprites.add(null);
		}
		// you only need to generate part 1 (body) and the rest will follow
		// since they are all attached to the body!
		generatePart(1, it, 50, 50, newSprites);
		return newSprites;
	}
	
	// this needs to totally be rewritten
	public String FileInfoToReadableString(PosedCreature it)
	{
		CreatureInfo creatureInfo = new CreatureInfo();
		// PosedCreature it = Puppeteer.creature;
		String text = "Sprite and ATT Info: \n";
		int x = 0;
		text += "**" + creatureInfo.bodyParts[x] + "**: " + it.part[x].fileInfo.sprite.getName() + isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.sprite.getName());
		text += " frame " + it.part[x].fileInfo.frame;
		text += "\n";
		text += "Body Data: " + it.part[x].fileInfo.att.getName() + isItDifferent(it.part[0].fileInfo.pendingName, it.part[x].fileInfo.att.getName());
		text += "\n";
		text += "Connects to body at " + it.part[x].fileInfo.attFromX + ", " + it.part[x].fileInfo.attFromY;
		text += "\n";
		text += "Connects to mouth at " + it.part[x].fileInfo.attToX + ", " + it.part[x].fileInfo.attToY;
		text += "\n\n";
		
		x++;
		text += "**" + creatureInfo.bodyParts[x] + "**: " + it.part[x].fileInfo.sprite.getName() + isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.sprite.getName());
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
			
			text += "**" + creatureInfo.bodyParts[x] + "**: " + it.part[x].fileInfo.sprite.getName() + isItDifferent(it.part[x].fileInfo.pendingName, it.part[x].fileInfo.sprite.getName());
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
	public String whyIsItDifferent(String pendingName, String finalName)
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
	public String isItDifferent(String pendingName, String name)
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
	public void updateOneSprite()
	{
		
	}
	
	public FromC16Converter makeOneSprite(PosedCreature it, int part)
	{
		
		return gamePaths.fileLibrary.getSpriteFile(it.part[part].fileInfo.sprite);
		
	}
	

	
	public List<DisplayedSprite> getErrorSprite()
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
		return defaultSprites;
	}
	
	// update an existing part (and make sure all your kids are updated)
	public void generatePart(int part, PosedCreature it, int toX, int toY, List<DisplayedSprite> sprites)
	{
		// update yourself to remember where your attachment to your parent is
		it.part[part].fileInfo.parentToX = toX;
		it.part[part].fileInfo.parentToY = toY;
		// make your converter:
		FromC16Converter partConv = makeOneSprite(it, part);
		// everything that's not the body:
		if (part != 1)
		{
			// your toX/Y should be the point at which you need to attach your FromX/Y to (basically your parent's position +
			// their toX/Y) + your offset, if any.
			int myX = toX - it.part[part].fileInfo.attFromX + it.part[part].x;
			int myY = toY - it.part[part].fileInfo.attFromY + it.part[part].y;
			DisplayedSprite sprite = new DisplayedSprite(partConv.getFrames()[it.part[part].fileInfo.frame], myX, myY);
			sprites.set(part, sprite);
			
			// now it's your responsibility to call your kid (if you have one) and tell them to update too
			int[] partsThatHaveKids =
			{
					1, 2, 3, 5, 6, 8, 10, 12
			};
			
			if (IntStream.of(partsThatHaveKids).anyMatch(i -> i == part))
			{
				int myToX = myX + it.part[part].fileInfo.attToX;
				int myToY = myY + it.part[part].fileInfo.attToY;
				
				int kidPart = part + 1;
				
				generatePart(kidPart, it, myToX, myToY, sprites);
			}
			
			// the body part is its own beast, mostly because it has so many kids:
		}
		else if (part == 1)
		{
			// toX/y is probably 0,0, or just the general... positioning point of the thing in general
			DisplayedSprite sprite = new DisplayedSprite(partConv.getFrames()[it.part[part].fileInfo.frame], toX, toY);
			sprites.set(part, sprite);
			// the big part is calling all six of your kids:
			// part 0, the head:
			int kidX = toX + it.part[part].fileInfo.attToHeadX;
			int kidY = toY + it.part[part].fileInfo.attToHeadY;
			generatePart(0, it, kidX, kidY, sprites);
			// 2, left leg
			kidX = toX + it.part[part].fileInfo.attToLeftLegX;
			kidY = toY + it.part[part].fileInfo.attToLeftLegY;
			generatePart(2, it, kidX, kidY, sprites);
			// 5, right leg
			kidX = toX + it.part[part].fileInfo.attToRightLegX;
			kidY = toY + it.part[part].fileInfo.attToRightLegY;
			generatePart(5, it, kidX, kidY, sprites);
			// 8, left arm
			kidX = toX + it.part[part].fileInfo.attToLeftArmX;
			kidY = toY + it.part[part].fileInfo.attToLeftArmY;
			generatePart(8, it, kidX, kidY, sprites);
			// 10, right arm
			kidX = toX + it.part[part].fileInfo.attToRightArmX;
			kidY = toY + it.part[part].fileInfo.attToRightArmY;
			generatePart(10, it, kidX, kidY, sprites);
			// 12, tail
			kidX = toX + it.part[part].fileInfo.attToTailX;
			kidY = toY + it.part[part].fileInfo.attToTailY;
			generatePart(12, it, kidX, kidY, sprites);
			
		}
		
	}
	
	// probably called by something when a user changes literally One Body Part
	public List<DisplayedSprite> UpdateAndDisplayPart(int part, PosedCreature it, List<DisplayedSprite> unlayeredSprites)
	{
		// System.out.println("Drawing part " + part + " relative to " + it.part[part].fileInfo.parentToX + "," + it.part[part].fileInfo.parentToY);
		generatePart(part, it, it.part[part].fileInfo.parentToX, it.part[part].fileInfo.parentToY, unlayeredSprites);
		return unlayeredSprites;
		
	}
}
	

