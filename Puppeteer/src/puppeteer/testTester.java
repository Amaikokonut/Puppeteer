package puppeteer;

import java.io.File;
import java.io.IOException;

public class testTester
{
	
	public static void main() throws IOException
	{
		System.out.println("hi");
//THIS ASSUMES you already:
		//generated a limbtests.txt file with testGenerator
		//dropped that file into your Docking Station/Journal folder
		//run tests.cos
		//found "limbtestresults.txt" in your journal folder
		//dropped limbtestresults.txt back into your working directory
		//otherwise you're gonna get SO MANY ERRORS
		
		CreatureInfo creatureInfo = new CreatureInfo();
		ATTParser aTTParser = new ATTParser();
		File file = new File("limbtests.txt");
		String tests = (JavaSpecificBits.readAllTextUTF8(file));
		String[] testLines = JavaSpecificBits.splitlines(tests);
		
		File enginefile = new File("limbtestresults.txt");
		String enginetests = (JavaSpecificBits.readAllTextUTF8(enginefile));
		String[] enginetestLines = JavaSpecificBits.splitlines(enginetests);
		int index = 0;
		int winning = 0;
		
		for (String x : testLines) {
			//these are not ATTs but I guess we're testing the ATT Parser Today
			String[] elements = aTTParser.splitATTRow(x);
			int part = Integer.valueOf(elements[0]);
			int gspcs = creatureInfo.GetSpcs(Integer.valueOf(elements[2]), Integer.valueOf(elements[1]));
			int stage = Integer.valueOf(elements[3]);
			char slot = 'a';
			slot += Integer.valueOf(elements[4]);
			//this is currently broken until we reconfigure things
			File result = null; //new FileInfo.getClosestFile(part, gspcs, stage, slot, true);
			if (result != null) {
				//System.out.println(result.getName());

				
				if (result.getName().substring(0,4).equals(enginetestLines[index])) {
					winning++;
				} else {
					System.out.println("******failed test******");
					System.out.println(x);
					System.out.println("Wanted: " + creatureInfo.interpretedParams(part, gspcs, stage, slot));
		//			System.out.println(FileInfo.buildFilename(part, gspcs, stage, slot));
					System.out.println("You Got:" + creatureInfo.interpretedFilename(result.getName()));
					System.out.println((result.getName().substring(0,4)));
					System.out.println("Game Got:" + creatureInfo.interpretedFilename(enginetestLines[index]));
					System.out.println(enginetestLines[index]);
				}
				
			} else {
				System.out.println("Got: Null (this shouldn't happen :C)");
			}
			//System.out.println(FileInfo.getClosestFile(part, gspcs, stage, slot, true).getName());
			index++;
			
		}
		System.out.println("Game-True Matches: " + winning + "/" + (index));
		System.out.println("bye");
		
	}
	
	public static void compare() {
		
	}
}
