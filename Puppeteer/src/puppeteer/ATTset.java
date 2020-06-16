package puppeteer;

import java.io.File;
import java.io.IOException;

public class ATTset
{
	int[][] atts = new int[16][12];
	ATTParser aTTParser = new ATTParser();
	
	public void read(File filePath)
	{
		try
		{
			String[] lines = JavaSpecificBits.splitlines(JavaSpecificBits.readAllTextUTF8(filePath));
			//System.out.println(lines);
			int x = 0;
			int y = 0;
			for (String line : lines)
			{
				for (String att : aTTParser.splitATTRow(line))
				{
					if (!att.equals(""))
					{
						//System.out.println("trying to get row " + x + " column " + y + ":" + att) ;
						atts[x][y] = Integer.valueOf(att);
						//System.out.println(atts[x][y]);
						y++;
					}
					
				}
				x++;
				y = 0;
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("That file didn't work");
		}
	}
	
	public ATTPoseSet get(int pose) {
		//part 1 is the body and has more atts
		//you can check if you're a body part by seeing if you have 
		//atts in higher slots than normal
		if (atts[pose][4] == 0 && atts[pose][5] == 0) {
		return new ATTPoseSet(atts[pose][0],atts[pose][1],atts[pose][2],atts[pose][3]);
		} else {
			return new ATTPoseSet(atts[pose][0],atts[pose][1],atts[pose][2],atts[pose][3],atts[pose][4],atts[pose][5],atts[pose][6],atts[pose][7],atts[pose][8],atts[pose][9],atts[pose][10],atts[pose][11]);	
		}
	}
	
}
