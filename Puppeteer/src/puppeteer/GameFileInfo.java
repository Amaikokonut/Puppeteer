package puppeteer;
import java.io.File;
import java.io.IOException;
import rebound.jagent.lib.FormatMismatchException;
import rebound.jagent.lib.c16.FromC16Converter;

public class GameFileInfo
{
	File filePath;
	String fileName;
	FromC16Converter spriteData = new FromC16Converter();
	String[] bodyData;
	Boolean isLoaded = false;
	String ext = "";
	
	public GameFileInfo(File filePath) {
		this.filePath = filePath;
		this.fileName = filePath.getName();

		//check for extensions
		if(fileName.contains("."))
		{
		    String parts[] = fileName.split("\\.");
		    this.ext = parts[parts.length - 1];
		}
	}
	
	public String[] getBodyData() {
		if (!isLoaded) {
			load();
		}
		return bodyData;
	}
	
	public FromC16Converter getSpriteData() {
		if (!isLoaded) {
			load();
		}
		return spriteData;
	}
	
	public void load() {
		if (ext.equalsIgnoreCase("att"))
		{
			try
			{
				this.bodyData = JavaSpecificBits.splitlines(JavaSpecificBits.readAllTextUTF8(filePath));
				isLoaded = true;
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (ext.equalsIgnoreCase("c16"))
		{
			try
			{
				spriteData.read(filePath);
				isLoaded = true;
			}
			catch (IOException | FormatMismatchException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
