package puppeteer;

import java.util.ArrayList;
import java.util.List;

public class CreaturePoseLibrary
{
	public static List<String> poseList = new ArrayList<>();
	public static List<String> poseStrings = new ArrayList<>();
	
	public static void addCreaturePose(String name, String poseString)
	{
		poseList.add(name);
		poseStrings.add(poseString);
	}
	
	static
	{
		// default genetic poses as stolen from the chichi genome
		addCreaturePose("none", "XXXXXXXXXXXXXXXX");
		addCreaturePose("Reach low near", "??1312312010100X");
		addCreaturePose("Reach lowish near", "??2212212010210X");
		addCreaturePose("Reach highish near", "??22122121233XXX");
		addCreaturePose("Reach high near", "??32102101233XXX");
		addCreaturePose("Reach low medium", "?011121120111XXX");
		addCreaturePose("Reach lowish medium", "?011121120112XXX");
		addCreaturePose("Reach highish medium", "?121121101223XXX");
		addCreaturePose("Reach high medium", "?221101100133XXX");
		addCreaturePose("Reach low far", "?001121120222XXX");
		addCreaturePose("Reach lowish far", "?021121120222XXX");
		addCreaturePose("Reach highish far", "?221121121223XXX");
		addCreaturePose("Reach high far", "?321101101233XXX");
		addCreaturePose("Stand (Baby)", "??2022021120100X");
		addCreaturePose("Stand (Child)", "??2212122011210X");
		addCreaturePose("Normal 1 (Baby) ", "??0122101001200X");
		addCreaturePose("Normal 1 (Child)", "??2201122011100X");
		addCreaturePose("Normal 1 (Old)", "??1121222010100X");
		addCreaturePose("Normal 2 (Baby) ", "?20101322110131X");
		addCreaturePose("Normal 2 (Child)", "??2321000011101X");
		addCreaturePose("Normal 2 (Old)", "??1000222010110X");
		addCreaturePose("Normal 3 (Baby) ", "??0101122120001X");
		addCreaturePose("Normal 3 (Child)", "??2122201010102X");
		addCreaturePose("Normal 3 (Old)", "??1101021010031X");
		addCreaturePose("Normal 4 (Baby) ", "??0322101011101X");
		addCreaturePose("Normal 4 (Child)", "??2000321010000X");
		addCreaturePose("Normal 4 (Old)", "??1222101010020X");
		addCreaturePose("Pained 1 (Baby)", "!00122101001200X");
		addCreaturePose("Pained 1 (Child)", "!01301122232331X");
		addCreaturePose("Pained 1 (Youth)", "!01222001232300X");
		addCreaturePose("Pained 2 (Baby)", "?00101322110101X");
		addCreaturePose("Pained 2 (Child)", "!0132100023231XX");
		addCreaturePose("Pained 2 (Youth)", "!01122102232330X");
		addCreaturePose("Pained 3 (Baby)", "!10101122120031X");
		addCreaturePose("Pained 3 (Child)", "!01122301232331X");
		addCreaturePose("Pained 3 (Youth)", "!01001222232310X");
		addCreaturePose("Pained 4 (Baby)", "!00322101011131X");
		addCreaturePose("Pained 4 (Child)", "!01000321232310X");
		addCreaturePose("Pained 4 (Youth)", "!01102122232300X");
		addCreaturePose("Tired 1", "?00201122000030X");
		addCreaturePose("Tired 2", "?00322000010101X");
		addCreaturePose("Tired 3", "?00122101000031X");
		addCreaturePose("Tired 4", "?00001322010130X");
		addCreaturePose("Scared 1 (Baby)", "!10122301111210X");
		addCreaturePose("Scared 1", "!00102122333331X");
		addCreaturePose("Scared 2 (Baby)", "!20101322110201X");
		addCreaturePose("Scared 2", "!0022200133333XX");
		addCreaturePose("Scared 3 (Baby)", "!20301122121120X");
		addCreaturePose("Scared 3", "!00122102333331X");
		addCreaturePose("Scared 4 (Baby)", "!20322101021002X");
		addCreaturePose("Scared 4", "!00001222333330X");
		addCreaturePose("Anger 1 (Baby)", "?10122301111210X");
		addCreaturePose("Anger 1 (Child)", "?12301122001130X");
		addCreaturePose("Anger 1 (Youth)", "?13301122000130X");
		addCreaturePose("Anger 2 (Baby)", "?20101322010201X");
		addCreaturePose("Anger 2 (Child)", "?02322000001203X");
		addCreaturePose("Anger 2 (Youth)", "?13322000000203X");
		addCreaturePose("Anger 3 (Baby)", "?20301122121120X");
		addCreaturePose("Anger 3 (Child)", "?12122301110031X");
		addCreaturePose("Anger 3 (Youth)", "?12122301110031X");
		addCreaturePose("Anger 4 (Baby)", "?20322101021102X");
		addCreaturePose("Anger 4 (Child)", "?12000322120003X");
		addCreaturePose("Anger 4 (Youth)", "?13000322120003X");
		addCreaturePose("To camera", "1421221221111XXX");
		addCreaturePose("Express general need", "1121223011100XXX");
		addCreaturePose("Express pain", "1022122122323XXX");
		addCreaturePose("Express hunger", "1402122120202XXX");
		addCreaturePose("Express tired", "1412122121111XXX");
		addCreaturePose("Express lonely", "242222111222300X");
		addCreaturePose("Express fear", "1422122122323XXX");
		addCreaturePose("Toe tap 1", "242222122121111X");
		addCreaturePose("Toe tap 2", "242223122121131X");
		addCreaturePose("Angry 5", "243122122021201X");
		addCreaturePose("Angry 6", "X431223120222XXX");
		addCreaturePose("Swing arms 1", "X122122121212XXX");
		addCreaturePose("Swing arms 2", "X222122120100XXX");
		addCreaturePose("Shiver 1 (Baby)", "X00202202221221X");
		addCreaturePose("Shiver 1 (Child)", "X03222222232321X");
		addCreaturePose("Shiver 2 (Baby)", "X10202202222231X");
		addCreaturePose("Shiver 2 (Child)", "X13212212232331X");
		addCreaturePose("Slap Norn 1", "?12222222012220X");
		addCreaturePose("Retreat 1 (Baby)", "?10011311110010X");
		addCreaturePose("Retreat 1", "?13332000233330X");
		addCreaturePose("Retreat 2 (Baby)", "?10212100011121X");
		addCreaturePose("Retreat 2", "?13312122133310X");
		addCreaturePose("Retreat 3 (Baby)", "?10311011001131X");
		addCreaturePose("Retreat 3", "?13000332233301X");
		addCreaturePose("Retreat 4 (Baby)", "?10100212100100X");
		addCreaturePose("Retreat 4", "?13122312333310X");
		addCreaturePose("Flee 1", "!421011222222XXX");
		addCreaturePose("Flee 2", "!422210002222XXX");
		addCreaturePose("Flee 3", "!421221012222XXX");
		addCreaturePose("Flee 4", "!120002212222XXX");
		addCreaturePose("Rest", "??0301301020200X");
		addCreaturePose("Sleep", "X003023020202XXX");
		addCreaturePose("East", "??22122120112XXX");
		addCreaturePose("West", "??22122120112XXX");
		addCreaturePose("Bored 1 (Baby)", "?20122101001210X");
		addCreaturePose("Bored 1 (Child)", "?22201122011110X");
		addCreaturePose("Bored 1 (Old)", "?021222220001XXX");
		addCreaturePose("Bored 2 (Baby)", "?20101322110101X");
		addCreaturePose("Bored 2 (Child)", "?22321000001111X");
		addCreaturePose("Bored 2 (Old)", "?021002220100XXX");
		addCreaturePose("Bored 3 (Baby)", "?20101122120030X");
		addCreaturePose("Bored 3 (Child)", "?22122201001201X");
		addCreaturePose("Bored 3 (Old)", "?022231220100XXX");
		addCreaturePose("Bored 4 (Baby)", "?20322101011101X");
		addCreaturePose("Bored 4 (Child)", "?22000321010000X");
		addCreaturePose("Bored 4 (Old)", "?022221000001XXX");
		addCreaturePose("Throw 1", "?013123121212XXX");
		addCreaturePose("Throw 2", "??31101103333XXX");
		addCreaturePose("Kick 1", "?011221000022XXX");
		addCreaturePose("Kick 2", "?231113332300XXX");
		addCreaturePose("Drum 1", "?122212210133XXX");
		addCreaturePose("Drum 2", "?132222220222XXX");
		addCreaturePose("Sneeze 1", "233222222110010X");
		addCreaturePose("Sneeze 2", "212222212111201X");
		addCreaturePose("Eat 1", "?122122122222XXX");
		addCreaturePose("Eat 2", "?222122121212XXX");
		addCreaturePose("Startled", "?13001321011330X");
		addCreaturePose("Kiss", "?221101102222XXX");
		addCreaturePose("Dead", "?!0300300020200X");
		addCreaturePose("Limp 1 (Baby)", "?00120122010100X");
		addCreaturePose("Limp 1", "?01101120010000X");
		addCreaturePose("Limp 2 (Baby)", "?10101122110021X");
		addCreaturePose("Limp 2", "?01322101010000X");
		addCreaturePose("Limp 3 (Baby)", "?10331122110002X");
		addCreaturePose("Limp 3", "?02210320020101X");
		addCreaturePose("Limp 4 (Baby)", "?00320122010100X");
		addCreaturePose("Limp 4", "?01000120000100X");
		addCreaturePose("Stagger 1 (Baby)", "?00122222110013X");
		addCreaturePose("Stagger 1", "?02122201232213X");
		addCreaturePose("Stagger 2 (Baby)", "?10333032231112X");
		addCreaturePose("Stagger 2", "?11301122232212X");
		addCreaturePose("Stagger 3 (Baby)", "?00321302231110X");
		addCreaturePose("Stagger 3", "?03321000231110X");
		addCreaturePose("Stagger 4 (Baby)", "?00202321232213X");
		addCreaturePose("Stagger 4", "?01000321232213X");
		addCreaturePose("Downhill 1 (Baby)", "??0201222011110X");
		addCreaturePose("Downhill 1 (Adolescent)", "??0001120100031X");
		addCreaturePose("Downhill 1 (Old)", "??0201222011100X");
		addCreaturePose("Downhill 2 (Baby)", "??0321200001101X");
		addCreaturePose("Downhill 2 (Adolescent)", "??0301120010031X");
		addCreaturePose("Downhill 2 (Old)", "??0321200001110X");
		addCreaturePose("Downhill 3 (Baby)", "??0222201000020X");
		addCreaturePose("Downhill 2 (Adolescent)", "??0120001001031X");
		addCreaturePose("Downhill 3 (Old)", "??0222201000030X");
		addCreaturePose("Downhill 4 (Baby)", "??0200321100001X");
		addCreaturePose("Downhill 4 (Adolescent)", "??0120301000100X");
		addCreaturePose("Downhill 4 (Old)", "??0200321100031X");
		addCreaturePose("Uphill 1 (Baby)", "??0000121100000X");
		addCreaturePose("Uphill 1 (Adolescent)", "??2000121100000X");
		addCreaturePose("Uphill 1 (Old)", "??0000121100030X");
		addCreaturePose("Uphill 2 (Baby)", "??0301120002210X");
		addCreaturePose("Uphill 2 (Adolescent)", "??2301120002210X");
		addCreaturePose("Uphill 2 (Old)", "??0301120002231X");
		addCreaturePose("Uphill 3 (Baby)", "??0121000001120X");
		addCreaturePose("Uphill 3 (Adolescent)", "??2121000220020X");
		addCreaturePose("Uphill 3 (Old)", "??0121000001130X");
		addCreaturePose("Uphill 4 (Baby)", "??0120301000000X");
		addCreaturePose("Uphill 4 (Adolescent)", "??2120301000000X");
		addCreaturePose("Uphill 4 (Old)", "??0120301000000X");
		addCreaturePose("Run 1 ", "?22232000001211X");
		addCreaturePose("Run 2", "?22100232120031X");
		addCreaturePose("Run 3", "?22122301110131X");
		addCreaturePose("Run 4", "?22302122010030X");
		addCreaturePose("Cough 1", "?23122122000000X");
		addCreaturePose("Cough 2", "?33122122011200X");
		addCreaturePose("Cough 3", "?13122122022300X");
		addCreaturePose("Cough 4", "?23122122022300X");
		addCreaturePose("Pushed 1", "?13222222000011X");
		addCreaturePose("Pushed 2", "?33233233020220X");
		addCreaturePose("Pushed 3", "?12232222020232X");
		addCreaturePose("Pushed 4", "?13122122111111X");
		addCreaturePose("Sneeze 3", "?00232232232230X");
		addCreaturePose("Lay Egg 1", "X12302302121231X");
		addCreaturePose("Lay Egg 2", "X3130230223232XX");
		addCreaturePose("Lay Egg 3", "X403023020202XXX");
		addCreaturePose("Lay Egg 4", "X42302302020210X");
		addCreaturePose("Slap Norn 2", "?12122122000121X");
		addCreaturePose("Slap Norn 3", "?11212212000231X");
		addCreaturePose("Slap Norn 4", "?12222222002220X");
		addCreaturePose("Slap Norn 5", "?13222222003321X");
		addCreaturePose("Falling 1", "X13333333333300X");
		addCreaturePose("Falling 2", "X33311333232330X");
		addCreaturePose("Impact 1", "X03333333222200X");
		addCreaturePose("Impact 2", "X03310310232300X");
		addCreaturePose("Impact 3", "X03302302333300X");
		addCreaturePose("Impact 4", "X11302302121200X");
		addCreaturePose("Impact 5", "X11122122101000X");
		addCreaturePose("Yawn 1", "X33222222112200X");
		addCreaturePose("Yawn 2", "X42222222112300X");
		addCreaturePose("Yawn 3", "X13222222112300X");
		addCreaturePose("Dance 1", "X30200000212300X");
		addCreaturePose("Dance 2", "X32110300233330X");
		addCreaturePose("Dance 3", "X23121333120022X");
		addCreaturePose("Dance 4", "X32300110332330X");
		addCreaturePose("Dance 5", "X23333121001222X");
		addCreaturePose("Dance 6", "X30000200232100X");
		addCreaturePose("Pushed behind 1", "X12222222000011X");
		addCreaturePose("Pushed behind 2", "X10022022010020X");
		addCreaturePose("Pushed behind 3", "X01111211222222X");
		addCreaturePose("Pushed behind 4", "X13022011101033X");
		addCreaturePose("Running 1 (Baby)", "?20011212120101X");
		addCreaturePose("Running 1 (Child)", "?22222001001200X");
		addCreaturePose("Running 2 (Baby)", "?20012222120021X");
		addCreaturePose("Running 2 (Child)", "?22122102110131X");
		addCreaturePose("Running 3 (Baby)", "?20212011100101X");
		addCreaturePose("Running 3 (Child)", "?22001222120001X");
		addCreaturePose("Running 4 (Baby)", "?20222012111000X");
		addCreaturePose("Running 4 (Child)", "?22102122010000X");
		addCreaturePose("Sexy 1", "?12000222100000X");
		addCreaturePose("Sexy 2", "?12101122011120X");
		addCreaturePose("Sexy 3", "?12222000000130X");
		addCreaturePose("Sexy 4", "?12122101001100X");
		addCreaturePose("Vomit 1", "?00201122122233X");
		addCreaturePose("Vomit 2", "?00322000222211X");
		addCreaturePose("Vomit 3", "?00122101231233X");
		addCreaturePose("Vomit 4", "?00001322222211X");
		addCreaturePose("Pick up 1", "??0133133000000X");
		addCreaturePose("Creep 1", "?03333000000000X");
		addCreaturePose("Creep 2", "?10322001111110X");
		addCreaturePose("Creep 3", "?03122300000000X");
		addCreaturePose("Creep 4", "?03000333000000X");
		addCreaturePose("Creep 5", "?03300122000000X");
		addCreaturePose("Jive 1", "XXX032200000000X");
		addCreaturePose("Jive 2", "XXX032200000000X");
		addCreaturePose("Jive 3", "XXX232222010231X");
		addCreaturePose("Jive 4", "XXX032200002200X");
		addCreaturePose("Jive 5", "XXX032200003300X");
		addCreaturePose("Vomit 5", "00122201221233X");
		addCreaturePose("Teeter 1", "X13122122000000X");
		addCreaturePose("Teeter 2", "X01112112111100X");
		addCreaturePose("Teeter 3", "X00010010111122X");
		addCreaturePose("Teeter 4", "X03010010222222X");
		addCreaturePose("Teeter 5", "X33221221010122X");
		addCreaturePose("Totter 1", "X12222222010122X");
		addCreaturePose("Totter 2", "X33221221010122X");
		addCreaturePose("Totter 3", "X33212212222222X");
		addCreaturePose("Totter 4", "X33323323232322X");
	}
	
	public static void setCreaturePose(int pose, PosedCreature creature)
	{
		String[] poseString = poseStrings.get(pose).split("");
		for (int i = 0; i < 16; i++)
		{
			System.out.println(poseString[i]);
			// we're going to ignore any direction/pose that's not a specific one
			if (!poseString[i].equals("X") && !poseString[i].equals("!") && !poseString[i].equals("?"))
			{
				// okay, it's a number
				System.out.println(i);
				System.out.println("Passes");
				int x = Integer.parseInt(poseString[i]);	
				//if this is the first character of the string, we're dealing with the direction:
				if (i == 0) {
					//because for some reason the directions for the genetics are different from 
					//the directions for the sprites...
					int[] correctedPoses = {0, 1, 3, 2};
					creature.UpdateDirn(correctedPoses[x]);
				} else {
					//the rest of the characters are dealing with the body parts poses:
					
					//special case
					//some head strings seem to be = 4... this seems to mean face the camera no matter the dirn?
					if (x > 3) {
						creature.part[i-1].UpdateDirn(1);
						creature.part[i-1].UpdatePose(1);
					} else {
					
					//sub 1 from i to make sure it aligns to an actual body part
					creature.part[i-1].UpdatePose(x);
					}
				}
			}
			
		}
		
	}
}
