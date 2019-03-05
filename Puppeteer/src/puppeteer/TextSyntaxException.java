package puppeteer;

public class TextSyntaxException
extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public TextSyntaxException()
	{
		super();
	}
	
	public TextSyntaxException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public TextSyntaxException(String message)
	{
		super(message);
	}
	
	public TextSyntaxException(Throwable cause)
	{
		super(cause);
	}
}
