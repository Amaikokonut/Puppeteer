package puppeteer;

public class ATTPoseSet
{
	public int fromX;
	public int fromY;
	public int toX;
	public int toY;
	// these are only used in the Body part:
	public int toHeadX;
	public int toHeadY;
	public int toLeftLegX;
	public int toLeftLegY;
	public int toRightLegX;
	public int toRightLegY;
	public int toLeftArmX;
	public int toLeftArmY;
	public int toRightArmX;
	public int toRightArmY;
	public int toTailX;
	public int toTailY;
	
	public ATTPoseSet(int fromX, int fromY, int toX, int toY)
	{
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
	}
	
	// not sure how okay this is but I'm tired??
	//maybe I should make a totally separate class for this
	//but it's literally just used ONCE and agh.
	public ATTPoseSet(int toHeadX, int toHeadY, int toLeftLegX, int toLeftLegY, int toRightLegX, int toRightLegY, int toLeftArmX, int toLeftArmY, int toRightArmX, int toRightArmY, int toTailX, int toTailY)
	{
		this.toHeadX = toHeadX;
		this.toHeadY = toHeadY;
		this.toLeftLegX = toLeftLegX;
		this.toLeftLegY = toLeftLegY;
		this.toRightLegX = toRightLegX;
		this.toRightLegY = toRightLegY;
		this.toLeftArmX = toLeftArmX;
		this.toLeftArmY = toLeftArmY;
		this.toRightArmX = toRightArmX;
		this.toRightArmY = toRightArmY;
		this.toTailX = toTailX;
		this.toTailY = toTailY;
	}
}
