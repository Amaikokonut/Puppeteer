/*
 * Created on Apr 12, 2006
 * 	by the powerful Eclipse(c)
 */
package rebound.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

//TODO Test cases

/**
 * This class locates aspects of the physical location of a java class (obviously it won't work if the class was dynamically created, but you probably won't have to worry about that).<br>
 * <br>
 * Note: This class has no extra-jre dependencies.
 * @author RProgrammer (RProgrammer)
 */
public class Compass
//implements JavaNamespace
{
	/**
	 * This method acquires the local filename of the containing Jarfile, or the directory containing the top-level package (/usr/john/src/com/highway/Tester.class is "/usr/john/src").<br>
	 * If the class is not local (like an applet) this method returns <code>null</code>.<br>
	 * You should always check .isDirectory to make sure the classloader that loaded the class isn't funky.<br>
	 * @param c The class to look from
	 */
	public static File getLocalHabitat(Class c)
	{
		//Test if getLocalClassFile works
		File local = getLocalClassFile(c);
		if (local != null)
		{
			File habitat = local.getParentFile();
			int packageCount = countChar(c.getName(), '.');
			
			for (int i = 0; i < packageCount; i++)
				habitat = habitat.getParentFile();
			
			return habitat;
		}
		
		//If not...
		URL u = getClassFile(c);
		
		//Jars, we can handle
		if (u != null && u.getProtocol().equalsIgnoreCase("jar"))
		{
			String body = u.getPath();
			String jar = null;
			int ex = body.indexOf("!");
			if (ex != -1)
				jar = body.substring(0, ex);
			else
				jar = body;
			
			if (jar.toLowerCase().startsWith("file:"))
				jar = jar.substring(5);
			
			try
			{
				jar = URLDecoder.decode(jar, "UTF-8");
			}
			catch (UnsupportedEncodingException exc)
			{
				throw new AssertionError("UTF-8 unsupported");
			}
			
			try
			{
				File jarfile = new File(jar);
				return jarfile.getParentFile();
			}
			catch (Exception exc)
			{
				return null;
			}
		}
		else
			return null; //Remote we cannot
	}
	
	
	
	/**
	 * Gets the jar file containing the given class.<br>
	 * Returns <code>null</code> if it is loose in the filesystem or remote.<br>
	 * You should always check .isFile to make sure the classloader that loaded the class isn't funky.<br>
	 */
	public static File getJarFile(Class c)
	{
		URL u = getClassFile(c);
		
		//Jars, we can handle
		if (u != null && u.getProtocol().equalsIgnoreCase("jar"))
		{
			String body = u.getPath();
			String jar = null;
			int ex = body.indexOf("!");
			if (ex != -1)
				jar = body.substring(0, ex);
			else
				jar = body;
			
			if (jar.toLowerCase().startsWith("file:"))
				jar = jar.substring(5);
			else
				return null;
			
			try
			{
				jar = URLDecoder.decode(jar, "UTF-8");
			}
			catch (UnsupportedEncodingException exc)
			{
				throw new AssertionError("UTF-8 unsupported");
			}
			
			try
			{
				return new File(jar);
			}
			catch (Exception exc)
			{
				return null;
			}
		}
		else
			return null; //Remote we cannot
	}
	
	
	
	/**
	 * The actual loose (non-jarred) local class file, or <code>null</code>.<br>
	 * You should always check .isFile to make sure the classloader that loaded the class isn't funky.<br>
	 */
	public static File getLocalClassFile(Class c)
	{
		try
		{
			return new File(getClassFile(c).toURI()).getCanonicalFile();
		}
		catch (Exception exc)
		{
			return null;
		}
	}
	
	
	/**
	 * Duh.<br>
	 */
	public static boolean isInJar(Class c)
	{
		URL u = getClassFile(c);
		if (u == null)
			return false;
		return u.getProtocol().equalsIgnoreCase("jar");
	}
	
	
	/**
	 * Determines whether our class is (a loose local file) || (in a local jarfile) :>
	 * As opposed to an Applet, or a dynamically created class.
	 */
	public static boolean isLocal(Class c)
	{
		URL u = getClassFile(c);
		String protocol = u.getProtocol();
		if (protocol.equalsIgnoreCase("file"))
			return true;
		else if (protocol.equalsIgnoreCase("jar"))
		{
			String body = u.getPath();
			String jarurl = null;
			int ex = body.indexOf("!");
			if (ex != -1)
				jarurl = body.substring(0, ex);
			else
				jarurl = body;
			try
			{
				return new URL(jarurl).getProtocol().equalsIgnoreCase("file");
			}
			catch (MalformedURLException exc)
			{
				return jarurl.startsWith("file");
			}
		}
		else
			return false;
	}
	
	
	
	
	
	/**
	 * This works for local <i>and</i> remote jarred classes (but probably not dynamic ones).<br>
	 * It returns the URL of the Jar file, or null if it is not in a jar.<br>
	 */
	public static URL getRemoteHabitat(Class c)
	{
		URL u = getClassFile(c);
		if (u != null && u.getProtocol().equalsIgnoreCase("jar"))
		{
			//Get jar file URL
			String body = u.getPath();
			String jarurl = null;
			int ex = body.indexOf("!");
			if (ex != -1)
				jarurl = body.substring(0, ex);
			else
				jarurl = body;
			
			//Find parent
			int lastSlash = jarurl.lastIndexOf("/");
			if (lastSlash == -1)
				lastSlash = jarurl.lastIndexOf("\\");
			
			String parentU = null;
			if (lastSlash != -1)
				parentU = jarurl.substring(0, lastSlash+1);
			else
				parentU = jarurl;
			
			
			try
			{
				return new URL(parentU);
			}
			catch (MalformedURLException exc)
			{
				return null;
			}
		}
		else
			return null;
	}
	
	
	
	/**
	 * Gets a URL for the given class's file, should return <code>null</code> if the class was dynamically created.<br>
	 */
	public static URL getClassFile(Class c)
	{
		return c.getResource(c.getSimpleName()+".class");
	}
	
	
	
	
	
	
	
	//<Out of VM
	/**
	 * This method acquires the local filename of the containing Jarfile, or the directory containing the top-level package (/usr/john/src/com/highway/Tester.class is "/usr/john/src").<br>
	 * If the class is not local (like an applet) this method returns <code>null</code>.<br>
	 * You should always check .isDirectory to make sure the classloader that loaded the class isn't funky.<br>
	 * @param classFile the .class or .java file to start from
	 * @param packageName the package of the class which this file represents
	 */
	public static File getLocalHabitat(File classFile, String packageName)
	{
		//Test if getLocalClassFile works
		File local = classFile;
		File habitat = local.getParentFile();
		int packageCount = countChar(packageName, '.')+1;
		
		for (int i = 0; i < packageCount; i++)
			habitat = habitat.getParentFile();
		
		return habitat;
	}
	//Out of VM>
	
	
	
	
	
	
	
	
	//Utility
	
	//Counts number of $C chars in a string
	private static int countChar(String str, char c)
	{
		int count = 0;
		if (str != null)
			for (int i = 0; i < str.length(); i++)
				if (str.charAt(i) == c)
					count++;
		return count;
	}
}
