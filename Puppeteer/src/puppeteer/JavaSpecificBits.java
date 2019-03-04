package puppeteer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public class JavaSpecificBits
{
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
}
