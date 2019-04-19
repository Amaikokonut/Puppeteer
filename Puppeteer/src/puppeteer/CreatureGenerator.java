package puppeteer;

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
			// don't forget to add your offsets!
			position.add(new XY(it.part[part].x, it.part[part].y));
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
			//this is basically the formula of getFrame 
			int partFrame = 4 * (3 - it.part[i].dirn) + it.part[i].pose;
			String attFile = creatureFileSet.get(i).attAvailable;
			System.out.println(attFile + " should exist");
			attCreatureSet.add(gamePaths.fileLibrary.getATTPoseSet(attFile, partFrame));
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
		//this is basically the formula of getFrame 
		int partFrame = 4 * (3 - it.part[part].dirn) + it.part[part].pose;
		String attFile = creatureFileSet.get(part).attAvailable;
		attCreatureSet.set(part, gamePaths.fileLibrary.getATTPoseSet(attFile, partFrame));
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
}
	
