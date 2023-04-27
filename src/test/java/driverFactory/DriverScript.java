package driverFactory;

import Utilities.ExcelFileUtil;

public class DriverScript {

	String inputpath ="./FileInput\\DataEngine.xlsx";
	String outputpath="./FileOutput/HybridResults.xlsx";
	
	public void startTest() throws Throwable{
		String Module_status="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//iterate all rows in master testcase sheet
		
		for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
		{
			
			if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule = xl.getCellData("MasterTestCases" , i, i);
			}
			else
			{
				xl.setCelldata("MasterTestCases", i, 3, "Block", outputpath);
			}
			
			
		}
		
		
	}
	
	
	
}
