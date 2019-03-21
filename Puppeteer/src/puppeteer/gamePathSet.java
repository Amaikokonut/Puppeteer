package puppeteer;

import java.io.IOException;
import java.nio.file.Path;
import puppeteer.pathcaseinsensitivity.CaseInsensitiveDirectory;

// I have no idea what I'm doing with this-- I might not even use this?
public class gamePathSet
{
	Path main;
	Path bodyData;
	Path images;
	CaseInsensitiveDirectory bodyDataCID;
	CaseInsensitiveDirectory imagesCID;
	
	public gamePathSet(Path path, Path bdpath, Path imgpath) {
		this.main = path;
		this.bodyData = bdpath;
		this.images = imgpath;
		//adding these proper objects:
		try
		{
			this.bodyDataCID = new CaseInsensitiveDirectory(bdpath.toFile());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			this.imagesCID = new CaseInsensitiveDirectory(imgpath.toFile());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
