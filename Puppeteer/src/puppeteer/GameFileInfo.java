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
	
	public GameFileInfo(File filePath) {
		this.filePath = filePath;
		this.fileName = filePath.getName();

		//check for extensions
		String ext = "";
		if(fileName.contains("."))
		{
		    String parts[] = fileName.split("\\.");
		    ext = parts[parts.length - 1];
		} else {
			//if there's no extension, there's no value in this
			return;
		}
		
		if (ext.equalsIgnoreCase("att"))
		{
			try
			{
				this.bodyData = JavaSpecificBits.splitlines(JavaSpecificBits.readAllTextUTF8(filePath));
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
			}
			catch (IOException | FormatMismatchException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String[] getBodyData() {
		return bodyData;
	}
	
	public FromC16Converter getSpriteData() {
		return spriteData;
	}
}
