package puppeteer;

public class CreatureInfo {

	public String[] availableSpecies = { "Norn", "Grendel", "Ettin", "Geat"};
	public String[] availableSlots = {"A", "B", "C", "D", "E", "F", "G", "H", "I", 
	"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", 
	"X" ,"Y", "Z"};
	public String[] dirn = {"Back", "Front", "Left", "Right"};
	public String[] speciesList = {"0: Male Norn", "1: Male Grendel", "2: Male Ettin", "3: Male Geat", "4: Female Norn", 
	"5: Female Grendel", "6: Female Ettin", "7: Female Geat"};
	public String[] bodyParts = {"A: Head", "B: Body", "C: Left thigh", "D: Left shin", "E: Left foot", "F: Right thigh", "G: Right shin",
			"H: Right foot", "I: Left humerus", "J: Left radius", "K: Right humerus", "L: Right radius", "M: Tail root", "N: Tail tip"
	};
	public String[] lifeStages = {"0: Baby", "1: Child", "2: Adolescent", "3: Youth", "4: Adult", "5: Old", "6: Senile"};
	public String[] expressions = {"0: None", "1: Happy", "2: Sad", "3: Angry", "4: Scared", "5: Tired"};
	
	//assuming 0 is male, 1 is female, return the gendered-species number that the breed slot parser understands
	//(equal to the index of String[] speciesList)
	public int GetSpcs(int mf, int spcs) {
		return 4 * mf + spcs;
	}
	
	public String interpretedFilename (String filename) {
		int part = filename.charAt(0) - 'a';
		int gspcs = Integer.parseInt(String.valueOf(filename.charAt(1)));
		int stage = Integer.parseInt(String.valueOf(filename.charAt(2)));
		char slot = filename.charAt(3);
//		System.out.println(part);
//		System.out.println(gspcs);
//		System.out.println(stage);
//		System.out.println(slot);
		return bodyParts[part] + " | " + speciesList[gspcs] + " | " + lifeStages[stage] + " | Slot: " + slot ;
	}
	
	public String interpretedParams (int part, int gspcs, int stage, char slot) {
		return bodyParts[part] + " | " + speciesList[gspcs] + " | " + lifeStages[stage] + " | Slot: " + slot ;
	}

}
