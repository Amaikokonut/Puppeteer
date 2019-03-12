package puppeteer;

import java.nio.file.Path;

// I have no idea what I'm doing with this-- I might not even use this?
public class gamePathSet
{
	Path main;
	Path bodyData;
	Path images;
	
	public gamePathSet(Path path, Path bdpath, Path imgpath) {
		this.main = path;
		this.bodyData = bdpath;
		this.images = imgpath;
		
	}
}
