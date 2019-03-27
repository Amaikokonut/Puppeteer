package puppeteer;

import java.util.ArrayList;
import java.util.List;

public class CreaturePose
{
	public static List<String> creaturePoseList = new ArrayList<>();
	public static List<String> creaturePoseStrings = new ArrayList<>();
	String name;
	String poseString;

	public void addCreaturePose(String name, String poseString) {
		creaturePoseList.add(name);
		creaturePoseStrings.add(poseString);
	}
	 public void initializeDefaultPoses() {
		 //default genetic poses as stolen from the chichi genome
		 addCreaturePose("none", "XXXXXXXXXXXXXXXX");
		 addCreaturePose("Reach low near", "");
		 addCreaturePose("Reach lowish near", "");
		 addCreaturePose("Reach highish near", "");
		 addCreaturePose("Reach high near", "");
		 addCreaturePose("Reach low medium", "");
		 addCreaturePose("Reach lowish medium", "");
		 addCreaturePose("Reach high medium", "");
		 addCreaturePose("Reach highish medium", "");
		 addCreaturePose("Reach high medium", "");
		 addCreaturePose("Reach low far", "");
		 addCreaturePose("Reach lowish far", "");
		 addCreaturePose("Reach highish far", "");
		 addCreaturePose("Reach high far", "");
		 addCreaturePose("Stand", "");
		 addCreaturePose("Normal 1", "");
		 addCreaturePose("Normal 2", "");
		 addCreaturePose("Normal 3", "");
		 addCreaturePose("Normal 4", "");
		 addCreaturePose("Pained 1", "");
		 addCreaturePose("Pained 2", "");
		 addCreaturePose("Pained 3", "");
		 addCreaturePose("Pained 4", "");
		 addCreaturePose("Tired 1", "");
		 addCreaturePose("Tired 2", "");
		 addCreaturePose("Tired 3", "");
		 addCreaturePose("Tired 4", "");
		 addCreaturePose("Scared 1", "");
		 addCreaturePose("Scared 2", "");
		 addCreaturePose("Scared 3", "");
		 addCreaturePose("Scared 4", "");
		 addCreaturePose("Anger 1", "");
		 addCreaturePose("Anger 2", "");
		 addCreaturePose("Anger 3", "");
		 addCreaturePose("Anger 4", "");
		 addCreaturePose("To camera", "");
		 addCreaturePose("Express general need", "");
		 addCreaturePose("Express pain", "");
		 addCreaturePose("Express hunger", "");
		 addCreaturePose("Express tired", "");
		 addCreaturePose("Express lonely", "");
		 addCreaturePose("Express fear", "");
		 addCreaturePose("Toe tap 1", "");
		 addCreaturePose("Toe tap 2", "");
		 addCreaturePose("Angry 5", "");
		 addCreaturePose("Angry 6", "");
		 addCreaturePose("Swing arms 1", "");
		 addCreaturePose("Swing arms 2", "");
		 addCreaturePose("Shiver 1", "");
		 addCreaturePose("Shiver 2", "");
		 addCreaturePose("Slap Norn 1", "");
		 addCreaturePose("Retreat 1", "");
		 addCreaturePose("Retreat 2", "");
		 addCreaturePose("Retreat 3", "");
		 addCreaturePose("Retreat 4", "");
		 addCreaturePose("Flee 1", "");
		 addCreaturePose("Flee 2", "");
		 addCreaturePose("Flee 3", "");
		 addCreaturePose("Flee 4", "");
		 addCreaturePose("Rest", "");
		 addCreaturePose("Sleep", "");
		 addCreaturePose("East", "");
		 addCreaturePose("West", "");
		 addCreaturePose("Bored 1", "");
		 addCreaturePose("Bored 2", "");
		 addCreaturePose("Bored 3", "");
		 addCreaturePose("Bored 4", "");
		 addCreaturePose("Throw 1", "");
		 addCreaturePose("Throw 2", "");
		 addCreaturePose("Kick 1", "");
		 addCreaturePose("Kick 2", "");
		 addCreaturePose("Drum 1", "");
		 addCreaturePose("Drum 2", "");
		 addCreaturePose("Sneeze 1", "");
		 addCreaturePose("Sneeze 2", "");
		 addCreaturePose("Eat 1", "");
		 addCreaturePose("Eat 2", "");
		 addCreaturePose("Startled", "");
		 addCreaturePose("Kiss", "");
		 addCreaturePose("Dead", "");
		 addCreaturePose("Limp 1", "");
		 addCreaturePose("Limp 2", "");
		 addCreaturePose("Limp 3", "");
		 addCreaturePose("Limp 4", "");
		 addCreaturePose("Stagger 1", "");
		 addCreaturePose("Stagger 2", "");
		 addCreaturePose("Stagger 3", "");
		 addCreaturePose("Stagger 4", "");
		 addCreaturePose("Downhill 1", "");
		 addCreaturePose("Downhill 2", "");
		 addCreaturePose("Downhill 3", "");
		 addCreaturePose("Downhill 4", "");
		 addCreaturePose("Uphill 1", "");
		 addCreaturePose("Uphill 2", "");
		 addCreaturePose("Uphill 3", "");
		 addCreaturePose("Uphill 4", "");
		 addCreaturePose("Run 1 ", "");
		 addCreaturePose("Run 2", "");
		 addCreaturePose("Run 3", "");
		 addCreaturePose("Run 4", "");
		 addCreaturePose("Cough 1", "");
		 addCreaturePose("Cough 2", "");
		 addCreaturePose("Cough 3", "");
		 addCreaturePose("Cough 4", "");
		 addCreaturePose("Pushed 1", "");
		 addCreaturePose("Pushed 2", "");
		 addCreaturePose("Pushed 3", "");
		 addCreaturePose("Pushed 4", "");
		 addCreaturePose("Sneeze 3", "");
		 addCreaturePose("Lay Egg 1", "");
		 addCreaturePose("Lay Egg 2", "");
		 addCreaturePose("Lay Egg 3", "");
		 addCreaturePose("Lay Egg 4", "");
		 addCreaturePose("Slap Norn 2", "");
		 addCreaturePose("Slap Norn 3", "");
		 addCreaturePose("Slap Norn 4", "");
		 addCreaturePose("Slap Norn 5", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 addCreaturePose("", "");
		 
		 
	 }
	
	

}
