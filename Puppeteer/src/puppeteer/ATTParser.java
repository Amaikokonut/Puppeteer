package puppeteer;

import puppeteer.JavaSpecificBits.WhatToDoWithEmpties;

public class ATTParser
{
	public String[] splitATTRow(String line)
	{
		return JavaSpecificBits.split(line, ' ', -1, WhatToDoWithEmpties.LeaveInEmpties);  //Change to LeaveOutEmpties to allow multiple spaces to count as a single space :3
	}
}
