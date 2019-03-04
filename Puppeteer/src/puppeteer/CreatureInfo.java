package puppeteer;

public class CreatureInfo {

	public static String[] availableSpecies = { "Norn", "Grendel", "Ettin", "Geat"};
	public static String[] availableSlots = {"A", "B", "C", "D", "E", "F", "G", "H", "I", 
	"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", 
	"X" ,"Y", "Z"};
	public static String[] dirn = {"Back", "Front", "Right", "Left"};
	public static String[] speciesList = {"0: Male Norn", "1: Male Grendel", "2: Male Ettin", "3: Male Geat", "4: Female Norn", 
	"5: Female Grendel", "6: Female Ettin", "7: Female Geat"};
	public static String[] bodyParts = {"A: Head", "B: Body", "C: Left thigh", "D: Left shin", "E: Left foot", "F: Right thigh", "G: Right shin",
			"H: Right foot", "I: Left humerus", "J: Left radius", "K: Right humerus", "L: Right radius", "M: Tail root", "N: Tail tip"
	};
	public static String[] lifeStages = {"0: Baby", "1: Child", "2: Adolescent", "3: Youth", "4: Adult", "5: Old", "6: Senile"};
	//look up all the expressions and fix this:
	public static String[] expressions = {"0: Happy", "1: Child", "2: Adolescent", "3: Youth", "4: Adult", "5: Old", "6: Senile"};
	
	//assuming 0 is male, 1 is female, return the gendered-species number that the breed slot parser understands
	//(equal to the index of String[] speciesList)
	public static int GetSpcs(int mf, int spcs) {
		return 4 * mf + spcs;
	}

}
