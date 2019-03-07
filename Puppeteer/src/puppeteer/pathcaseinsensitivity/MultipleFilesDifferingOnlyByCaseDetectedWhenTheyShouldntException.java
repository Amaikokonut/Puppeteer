package puppeteer.pathcaseinsensitivity;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * :}
 */
public class MultipleFilesDifferingOnlyByCaseDetectedWhenTheyShouldntException
extends IOException
{
	private static final long serialVersionUID = 1L;
	
	protected final Set<File> filesThatConflictWithEachOther;
	
	public MultipleFilesDifferingOnlyByCaseDetectedWhenTheyShouldntException(/*@ImmutableValue @Nonnull*/ Set<File> filesThatConflictWithEachOther)
	{
		super(makemsg(filesThatConflictWithEachOther));
		this.filesThatConflictWithEachOther = filesThatConflictWithEachOther;
	}
	
	public static MultipleFilesDifferingOnlyByCaseDetectedWhenTheyShouldntException instVarargs(File... filesThatConflictWithEachOtherA)
	{
		Set<File> filesThatConflictWithEachOther = new HashSet<>();
		for (File f : filesThatConflictWithEachOtherA)
			filesThatConflictWithEachOther.add(f);
		return new MultipleFilesDifferingOnlyByCaseDetectedWhenTheyShouldntException(Collections.unmodifiableSet(filesThatConflictWithEachOther));
	}
	
	public Set<File> getFilesThatConflictWithEachOther()
	{
		return filesThatConflictWithEachOther;
	}
	
	
	
	private static String makemsg(Set<File> filesThatConflictWithEachOther)
	{
		StringBuilder b = new StringBuilder("{");
		
		boolean first = true;
		
		for (File f : filesThatConflictWithEachOther)
		{
			if (first)
				first = false;
			else
			{
				b.append(", ");
			}
			
			b.append(f.getAbsolutePath());
		}
		
		b.append("}");
		
		return b.toString();
	}
}
