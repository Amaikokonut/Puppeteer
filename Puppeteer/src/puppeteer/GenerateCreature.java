package puppeteer;


public class GenerateCreature
{
	public static void DrawCreatureFromScratch(PosedCreature it) {
		//Output your ATT info to the window
		Puppeteer.updateAttInfo(FileInfoToReadableString (it));
	} 

	//finish this later
	public static String FileInfoToReadableString(PosedCreature it) {
		//PosedCreature it = Puppeteer.creature;
		String text = "Sprite and ATT Info:";
		text += "Head connects to body at " + it.part[0].fileInfo.attToHeadX + ", " + it.part[0].fileInfo.attToHeadY;
		return text;
	} 
}
