package tests;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//a super dirty way to generate tests that can be read by both the Creatures engine and java to determine if the 
//file-finding method is accurate
public class testGenerator
{
    public static void main(String[] args) throws IOException {
    	BufferedWriter out = new BufferedWriter(new FileWriter("limbtests.txt"));
    	String text = "";
    	int thisManyTests = 1000;
    	
    	while (thisManyTests > 0) {
    		thisManyTests--;
    		text += rand(0,13) + " " + rand(0,3) + " " + rand(0,1) + " " + rand(0,6) + " " + rand(0,25); 
    		if (thisManyTests > 0) text += "\r\n";
    	}
    	try {
    	    out.write(text);  
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    	    out.close();
    	}
    }
 
    public static String rand(int min,int max)
    {
        return Integer.toString(min + (int)(Math.random() * ((max - min) + 1)));
    }
    
}
