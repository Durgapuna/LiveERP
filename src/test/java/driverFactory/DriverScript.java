package driverFactory;

import Utilities.ExcelFileUtil;
import commonFunctions.FunctionLibrary;

public class DriverScript extends FunctionLibrary {

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
				for(int j=1;j<=xl.rowCount(TCModule);j++)
				{
					String Description =xl.getCellData(TCModule, j, 0);
					String ObjectType =xl.getCellData(TCModule, j, 1);
					String LocatorType = xl.getCellData(TCModule, j, 2);
					String LocatorValue = xl.getCellData(TCModule, j, 3);
					String TestData = xl.getCellData(TCModule, j, 4);
					
					try {
						if(ObjectType.equalsIgnoreCase("startBrowse"))
						{
							driver =  FunctionLibrary.startBrowser();
							
						}
						else if (ObjectType.equalsIgnoreCase("openUrl"))
						{
							FunctionLibrary.openUrl(driver);
						}
						else if (ObjectType.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, TestData);
						}
						else if(ObjectType.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, LocatorType, LocatorValue, TestData);
						}
						else if(ObjectType.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
						}
						else if(ObjectType.equalsIgnoreCase("validateTitle"))
						{
							FunctionLibrary.validateTitle(driver, TestData);
						}
						else if(ObjectType.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver);
						}
						//write as pass into status cell TCModule
						xl.setCelldata(TCModule, j, 5, "Pass", outputpath);
						Module_status="True";					
						} catch (Exception e) {
                        System.out.println(e.getMessage());
                        xl.setCelldata(TCModule, j, 5, "Fail", outputpath);
                        Module_status="Fail";

                        }
					if(Module_status.equalsIgnoreCase("True"))
					{
						
					}
					else
					{
						
					}
					
				}
				
				
			}
			
			else
			{
				xl.setCelldata("MasterTestCases", i, 3, "Block", outputpath);
			}
			
			
		}
		
		
		
	}
	
	
	
}
