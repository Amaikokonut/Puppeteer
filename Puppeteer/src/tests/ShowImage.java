package tests;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ShowImage
extends Panel
{
	
	private static final long serialVersionUID = 1L;
	BufferedImage image;
	
	public ShowImage(String filename)
	{
		File input = new File(filename);
		try
		{
			image = ImageIO.read(input);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}
	
}