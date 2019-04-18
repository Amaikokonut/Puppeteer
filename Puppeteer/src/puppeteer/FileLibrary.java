package puppeteer;

import java.io.File;
import java.util.Hashtable;
import java.util.Set;
import rebound.jagent.lib.c16.FromC16Converter;

public class FileLibrary
{
	Hashtable<String, GameFileInfo> files = new Hashtable<>();
	
	// make sure you add folders in the same order the game accesses them --
	// the first folders you add override any others
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
				if (isACreatureFile(file.toString()))
				{
					//System.out.println(file.toString());
					this.files.put(lowerCaseName, new GameFileInfo(new File(folder, file)));
				}
			}
		}
	}
	
	public void clear()
	{
		this.files.clear();
	}
	
	public ATTset getATTFile(File file)
	{
		GameFileInfo f = this.files.get(file.getName().toString());
		//System.out.println("looking for " + file);
		return f.getBodyData();
	}
	
	public ATTset getATTFile(String file)
	{
		if (contains(file)) {
			//System.out.println("this file exists");
		GameFileInfo f = this.files.get(file);
		return f.getBodyData();
	} else
	{
		System.out.println(file + ": this file doesn't exist--something is wrong");
		return null;
		}
	}
	
	//not sure if this is needed
	public ATTPoseSet getATTPoseSet(File file, int pose) {
		return getATTFile(file).get(pose);
	}
	
	public ATTPoseSet getATTPoseSet(String file, int pose) {
		return getATTFile(file).get(pose);
	}
	
	public FromC16Converter getSpriteFile(File file)
	{
		GameFileInfo f = this.files.get(file.getName().toString());
		//System.out.println("looking for " + file);
		return f.getSpriteData();
	}
	
	public FromC16Converter getSpriteFile(String file)
	{
		GameFileInfo f = this.files.get(file);
		//System.out.println("looking for " + file);
		return f.getSpriteData();
	}
	
	public Boolean isACreatureFile(String filename)
	{
		boolean r = false;
		// first make sure the filename is the right length
		if (filename.length() == 8)
		{
			// then check the characters
			char a = filename.charAt(0);
			char b = filename.charAt(1);
			char c = filename.charAt(2);
			char d = filename.charAt(3);
			if (Character.isLetter(a) && Character.isDigit(b) && Character.isDigit(c) && Character.isLetter(d))
			{
				r = true;
			}
		}
		return r;
	}
	
	public void clearCachedFiles() {
        Set<String> keys = this.files.keySet();
        for(String key: keys){
        	GameFileInfo f = this.files.get(key);
        	f.unload();
        }
	}
	
	public boolean contains(String fileName) {
		if (this.files.containsKey(fileName)) {
			return true;
		} else {
			return false;
		}
	} 
}
