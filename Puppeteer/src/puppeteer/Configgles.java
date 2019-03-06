package puppeteer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Configgles
{
	// case sensitivity drives me up the wall, possibly forever.
	public boolean exists(File dir, String filename)
	{
		String[] files = dir.list();
		for (String file : files)
			if (file.equals(filename))
				return true;
		return false;
	}
	
	public static String[] readGamePaths()
	{
		String[] x =
		{};
		return x;
	}
	
	public static void ListGamePaths()
	{
		int c = 0;
		for (Path i : gamePaths)
			readableGamePaths[c] = i.toString();
		c++; //lol
		{
			

		}
	}
	
	public static List<Path> gamePaths = new ArrayList<Path>();
	public static String[] readableGamePaths = {"none"};
	
	static String[] pathStatuses =
	{
			"Path added successfully!", "This is not a valid path", "This is not a valid game path", "This path is already in the list", "Something went wrong with the path checker method"
	};
	
	public static String pathStatus(String sPath) throws IOException
	{
		Path path;
		// first we check for validity
		try
		{
			path = Paths.get(sPath);
		}
		catch (Exception e)
		{
			return pathStatuses[1];
		}
		// Then we check if body data and images folders exist
		// this method is Very Bad and we need to replace it with one
		// that allows for different cases
		Path bd = Paths.get(path.toString() + "\\Body Data\\");
		Path img = Paths.get(path.toString() + "\\Images\\");
		boolean x = Files.exists(bd);
		boolean y = Files.exists(img);
		System.out.println(bd);
		System.out.println(img);
		System.out.println(x);
		System.out.println(y);
		
		if (x && y)
		{
			// now check if they are already in the list:
			for (Path i : gamePaths)
			{
				if (Files.isSameFile(path, i))
				{
					return pathStatuses[3];
				}
			}
			// Wow, you did it! This is a valid path!
			gamePaths.add(path);
			ListGamePaths();
			return pathStatuses[0];	
		}
		else
		{
			
			return pathStatuses[2];
			
		}
	}
	
	public static boolean pathIsValid(String path)
	{
		
		return false;
	}
}
