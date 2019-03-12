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
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
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
			savePathsToFile();
			return pathStatuses[0];
		}
		else
		{
			
			return pathStatuses[2];
			
		}
	}
	
	// this should save everything in the gamePaths list to file
	public static void savePathsToFile()
	{
		
		Properties prop = new Properties();
		OutputStream output = null;
		
		try
		{
			
			output = new FileOutputStream("paths.properties");
			
			if (gamePaths.size() > 0)
			{
				// set the properties value
				prop.setProperty("count", Integer.toString(gamePaths.size()));
				int c = 0;
				for (int i = 0; i < gamePaths.size(); i++)
				{
					prop.setProperty("path" + c, gamePaths.get(i).main.toString());
					c++; // lol
				}
			}
			else
			{
				prop.setProperty("count", "0");
			}
			prop.store(output, null);
		}
		catch (IOException io)
		{
			io.printStackTrace();
		}
		finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
		}
	}
	
	// aaaand this should load everything from the properties file into the gamePaths list...
	public static void loadPathsFromFile() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("paths.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			int count = Integer.parseInt(prop.getProperty("count"));
			
			if (count > 0) {
				int c = 0;
				for (int i = 0; i < count; i++)
				{
					System.out.println(pathStatus(prop.getProperty("path" + c)));
					c++; // lol
				}
			} else {
				System.out.println("No paths to load");
			}

		} catch (IOException ex) {
			//we should probably do something here...you're always gonna get this the first time 
			//you start the program, since there's no properties file yet
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

	

