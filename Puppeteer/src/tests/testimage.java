package tests;

import java.awt.Panel;
import javax.swing.JFrame;

public class testimage
{
	public static void main(String args[]) throws Exception
	{
		JFrame frame = new JFrame("Display image");
		Panel panel = new ShowImage("backnorn.JPG");
		frame.getContentPane().add(panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
