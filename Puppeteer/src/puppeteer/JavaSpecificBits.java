package puppeteer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JavaSpecificBits
{
	//@Nonnull
	public static String readAllTextUTF8(File file) throws IOException
	{
		return readAllText(file, StandardCharsets.UTF_8);
	}
	
	//@Nonnull
	public static String readAllText(File file, Charset encoding) throws IOException
	{
		if (encoding == null)
			encoding = Charset.defaultCharset();
		
		Reader in = new InputStreamReader(new FileInputStream(file), encoding);
		
		try
		{
			return readAllToString(in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (IOException exc)
			{
			}
		}
	}
	
	public static String readAllToString(Reader in) throws IOException
	{
		StringWriter buff = new StringWriter();
		pump(in, buff);
		return buff.toString();
	}
	
	public static long pump(Reader in, Writer out) throws IOException
	{
		return pump(in, out, 4096);
	}
	
	public static long pump(Reader in, Writer out, int bufferSize) throws IOException
	{
		long total = 0;
		char[] buffer = new char[bufferSize];
		int amt = in.read(buffer);
		while (amt >= 0)
		{
			total += amt;
			out.write(buffer, 0, amt);
			amt = in.read(buffer);
		}
		return total;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static String[] splitlines(String s)
	{
		return splitlines(s, WhatToDoWithEmpties.LeaveInEmpties); //leaving them in keeps more information and allows us to reconstitute the original input; which is many important for (eg source code) rewriters! 0,0     the lossless option is usually a good default option methinks ^^
	}
	
	
	public static String[] splitlines(String s, WhatToDoWithEmpties whatToDoWithEmpties)
	{
		//Todo support all three newlines, "\n", "\r", and "\r\n" more efficiently ^^''
		if (s.indexOf('\r') != -1)
			s = universalNewlines(s);
		
		return split(s, '\n', -1, whatToDoWithEmpties);
	}
	
	//@PossiblySnapshotPossiblyLiveValue  //Future-proofing ^^'
	public static char[] universalNewlinesCharArrayOPC(char[] oldc)
	{
		//Todo redo this with a better algorithm that marks position ranges and uses System.arraycopy over them, instead of doing it character-by-character :>
		
		int oldLength = oldc.length;
		
		
		//Check that it needs to actually be modified! XD
		{
			boolean clean = true;
			
			for (char c : oldc)
			{
				if (c == '\r')
				{
					clean = false;
					break;
				}
			}
			
			
			//I think this's pretty self-explanatory code right here! ^w^  XD    maybe :D?
			if (clean)
				return oldc;
		}
		
		
		char[] newc = new char[oldLength];
		
		
		char c = 0;
		int e = 0;
		for (int i = 0; i < oldc.length; i++)
		{
			c = oldc[i];
			if (c == '\r')
			{
				if (i < oldc.length-1)
				{
					if (oldc[i+1] == '\n')
						i++; //skip the '\n' in "\r\n"
				}
				newc[e] = '\n'; //"\r" alone gets converted just like "\r\n"
				e++;
			}
			else
			{
				//'\n' just goes straight through
				newc[e] = c;
				e++;
			}
		}
		
		int newLength = e;
		
		return newLength == oldLength ? newc : slice(newc, 0, newLength);
	}
	
	
	//@PossiblySnapshotPossiblyLiveValue  //Future-proofing ^^'
	public static String universalNewlines(String original)
	{
		char[] ca0 = original.toCharArray();
		char[] ca1 = universalNewlinesCharArrayOPC(ca0);
		
		if (ca1 == ca0)
			return original;
		else
			return new String(ca1);
	}
	
	
	
	
	public static enum WhatToDoWithEmpties
	{
		LeaveInEmpties,
		LeaveOutEmpties,
	}
	
	
	
	
	
	
	
	
	
	/**
	 * @param limitInMaximumNumberOfDelimiterSplits use -1 for no-limit :33
	 */
	public static String[] split(String original, char delimiter, int limitInMaximumNumberOfDelimiterSplits, WhatToDoWithEmpties whatToDoWithEmpties)
	{
		if (whatToDoWithEmpties == null)
			throw new NullPointerException();
		
		
		boolean leaveInEmpties = whatToDoWithEmpties == WhatToDoWithEmpties.LeaveInEmpties;
		
		char[] data = original.toCharArray();
		ArrayList<String> tokens = new ArrayList<String>(6);
		int tokenStart = 0;
		for (int i = 0; i < data.length && (limitInMaximumNumberOfDelimiterSplits == -1 || tokens.size() < limitInMaximumNumberOfDelimiterSplits); i++)
		{
			if (data[i] == delimiter)
			{
				if (leaveInEmpties || i - tokenStart != 0)
				{
					tokens.add(new String(data, tokenStart, i - tokenStart));
				}
				
				tokenStart = i + 1; //Token doesn't include the delimiter
			}
		}
		
		tokens.add(new String(data, tokenStart, data.length - tokenStart));
		
		return tokens.toArray(new String[tokens.size()]);
	}
	
	
	
	
	
	//@ThrowAwayValue
	public static char[] slice(char[] array, int inclusiveLowBound, int exclusiveHighBound)
	{
		if (inclusiveLowBound == 0 && exclusiveHighBound == array.length)
			return array.clone();
		else
			return sliceR(array, inclusiveLowBound, exclusiveHighBound);
	}
	
	
	//@PossiblySnapshotPossiblyLiveValue
	public static char[] sliceR(char[] array, int inclusiveLowBound, int exclusiveHighBound)
	{
		int len = array.length;
		
		if (inclusiveLowBound == 0 && exclusiveHighBound == len)
			return array;  //Hence the PossiblyLiveValue part ;>
		
		if (inclusiveLowBound != len) //leave it if == length!!
			inclusiveLowBound = progmod(inclusiveLowBound, len);
		
		if (exclusiveHighBound != len) //leave it if == length!!
			exclusiveHighBound = progmod(exclusiveHighBound, len);
		
		//Swap if incorrect order, to be nice :3
		if (exclusiveHighBound < inclusiveLowBound)
		{
			int t = exclusiveHighBound;
			exclusiveHighBound = inclusiveLowBound;
			inclusiveLowBound = t;
		}
		
		assert inclusiveLowBound >= 0;
		assert inclusiveLowBound <= len;
		assert exclusiveHighBound >= 0;
		assert exclusiveHighBound <= len;
		
		char[] slice = new char[exclusiveHighBound - inclusiveLowBound];
		
		System.arraycopy(array, inclusiveLowBound, slice, 0, slice.length);
		
		return slice;
	}
	
	
	
	public static int progmod(int index, int highBound)
	{
		if (highBound == 0)
			//throw new DivisionByZeroException();
			throw new ArithmeticException();
		
		//does this work? is it fasters? :>
		return (index % highBound + highBound) % highBound;
		//edit: seems to! :D!
		
		//		if (index >= 0)
		//			return index % highBound;
		//		else //if (n < 0)
		//			return index - (floorDivision(index, highBound)*highBound);
	}
}
