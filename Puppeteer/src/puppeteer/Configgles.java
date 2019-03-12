package puppeteer;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import puppeteer.pathcaseinsensitivity.CaseInsensitiveDirectory;

public class Configgles
{
	
	public static List<gamePathSet> gamePaths = new ArrayList<gamePathSet>();
	public static String[] readableGamePaths =
	{
			"none"
	};
	
	public static void ListGamePaths()
	{
		if (gamePaths.size() > 0)
		{
			readableGamePaths = new String[gamePaths.size()];
			int c = 0;
			for (int i = 0; i < gamePaths.size(); i++)
			{
				readableGamePaths[c] = gamePaths.get(i).main.toString();
				c++; // lol
			}
		}
		else
		{
			readableGamePaths[0] = "none";
		}
	}
	
	static String[] pathStatuses =
	{
			"Path added successfully!", "This is not a valid path", "This is not a valid game path", "This path is already in the list", "Something went wrong with the path checker method"
	};
	
	public static String pathStatus(String sPath) throws IOException
	{
		Path path;
		System.out.println(sPath);
		// first we check for validity
		try
		{
			path = Paths.get(sPath);
			if (!Files.exists(path) || sPath.isEmpty())
			{
				return pathStatuses[1];
			}
		}
		catch (Exception e)
		{
			return pathStatuses[1];
		}
		File bd = new CaseInsensitiveDirectory(path.toFile()).findPathCaseInsensitivelyOrNullIfNonexistant("body data");
		File img = new CaseInsensitiveDirectory(path.toFile()).findPathCaseInsensitivelyOrNullIfNonexistant("images");
		boolean x = bd == null ? false : true;
		boolean y = img == null ? false : true;
		System.out.println(bd);
		System.out.println(img);
		System.out.println(x);
		System.out.println(y);
		
		if (x && y)
		{
			// now check if they are already in the list:
			for (gamePathSet i : gamePaths)
			{
				if (Files.isSameFile(path, i.main))
				{
					return pathStatuses[3];
				}
			}
			// Wow, you did it! This is a valid path!
			
			gamePaths.add(new gamePathSet(path, bd.toPath(), img.toPath()));
			ListGamePaths();
			return pathStatuses[0];
		}
		else
		{
			
			return pathStatuses[2];
			
		}
	}
}
