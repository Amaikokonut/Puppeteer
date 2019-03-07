package puppeteer.pathcaseinsensitivity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  *sigh* Windows, the great enabler XD
 */
public class CaseInsensitiveDirectory
{
	protected final /*@Nonnull*/ File dirCaseSENSITIVE;
	protected final /*@Nonnull*/ Map<String, String> lowercaseContentsToActualCase;
	
	public CaseInsensitiveDirectory(/*@Nonnull*/ File dirCaseSENSITIVE) throws IOException, MultipleFilesDifferingOnlyByCaseDetectedWhenTheyShouldntException
	{
		this.dirCaseSENSITIVE = dirCaseSENSITIVE;
		lowercaseContentsToActualCase = new HashMap<>();
		
		String[] childrenNames = dirCaseSENSITIVE.list();
		
		if (childrenNames == null)
			throw new IOException("Could not list directory: "+dirCaseSENSITIVE);
		
		for (String child : childrenNames)
		{
			String lowerChild = child.toLowerCase();
			
			if (lowercaseContentsToActualCase.containsKey(lowerChild))
			{
				String existing = lowercaseContentsToActualCase.get(lowerChild);
				throw MultipleFilesDifferingOnlyByCaseDetectedWhenTheyShouldntException.instVarargs(new File(dirCaseSENSITIVE, child), new File(dirCaseSENSITIVE, existing));
			}
			
			lowercaseContentsToActualCase.put(lowerChild, child);
		}
	}
	
	
	//@Nullable
	public File findPathCaseInsensitivelyOrNullIfNonexistant(/*@Nonnull*/ String subdirNameCaseInsensitive)
	{
		String childNameInCorrectCase = lowercaseContentsToActualCase.get(subdirNameCaseInsensitive.toLowerCase());
		
		return childNameInCorrectCase == null ? null : new File(dirCaseSENSITIVE, childNameInCorrectCase);
	}
}
