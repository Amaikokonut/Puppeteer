package puppeteer;

import java.io.File;
import java.io.IOException;

public class testTester
{
	
	public static void main() throws IOException
	{
		System.out.println("hi");
//assumes you already generated a limbtests.txt file with testGenerator
		File file = new File("limbtests.txt");
		String tests = (JavaSpecificBits.readAllTextUTF8(file));
		String[] testLines = JavaSpecificBits.splitlines(tests);
		
		for (String x : testLines) {
			//these are not ATTs but I guess we're testing the ATT Parser Today
			String[] elements = ATTParser.splitATTRow(x);
			//System.out.println(x);
			int part = Integer.valueOf(elements[0]);
			int gspcs = CreatureInfo.GetSpcs(Integer.valueOf(elements[2]), Integer.valueOf(elements[1]));
			int stage = Integer.valueOf(elements[3]);
			char slot = 'a';
			slot += Integer.valueOf(elements[4]);
			System.out.println("******test******");
			System.out.println("Wanted: " + CreatureInfo.interpretedParams(part, gspcs, stage, slot));
			System.out.println(FileInfo.buildFilename(part, gspcs, stage, slot));
			File result = FileInfo.getClosestFile(part, gspcs, stage, slot, true);
			if (result != null) {
				//System.out.println(result.getName());
				System.out.println("Got:    " + CreatureInfo.interpretedFilename(result.getName()));
				
			} else {
				System.out.println("Got: Null (this shouldn't happen :C)");
			}
			System.out.println(FileInfo.getClosestFile(part, gspcs, stage, slot, true).getName());
			
			
		}
		
		System.out.println("bye");
		
	}
	
	public static void compare() {
		
	}
}
