package puppeteer;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import rebound.jagent.lib.c16.FromC16Converter;

public class FileLibrary
{
	Hashtable<String, GameFileInfo> files = new Hashtable<>();
	
	//make sure you add folders in the same order the game accesses them --
	//the first folders you add override any others
	public void addFolder(File folder)
	{
		
		// make sure the folder isn't empty
		if (folder.list() == null)
		{
			return;
		}
		
		for (String file : folder.list())
		{
			String lowerCaseName = file.toLowerCase();
			// make sure it doesn't already exist in the library:
			if (!this.files.containsKey(lowerCaseName))
			{
				//then take a stab at whether or not it's a valid creature file 
				//(in this case we're only grabbing characters with 8 character filenames)
				if (file.toString().length() == 8) {
				System.out.println(file.toString());
				this.files.put(lowerCaseName, new GameFileInfo(new File(folder, file)));
				}
			}
		}
	}
	
	public void clear() {
		this.files.clear();
	}
	
	public String[] getATTFile(File file) {
		GameFileInfo f = this.files.get(file);
		return f.getBodyData();
	}
	
	public FromC16Converter getSpriteFile(File file) {
		GameFileInfo f = this.files.get(file.getName().toString());
		System.out.println("looking for " + file);
		return f.getSpriteData();
	}
}
