package driverFactory;
import commonFunctions.FunctionLibrary;

import utilities.ExcelFileUtil;



public class DriverScript extends FunctionLibrary {

String inputpath ="./FileInput/DataEngine.xlsx";

String outputpath ="./FileOutput/HybridResults.xlsx";

public void startTest()throws Throwable

{

String Module_Status="";

//call excelfile util class methods

utilities.ExcelFileUtil xl = new utilities.ExcelFileUtil(inputpath);

//iterate all rows in mastertestcases sheet

for(int i=1;i<=xl.rowCount("MasterTestCases");i++)

{

if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))

{

//store corresponding sheet into variable

String TCModule =xl.getCellData("MasterTestCases", i, 1);

//iterate all rows in TCModule sheet

for(int j=1;j<=xl.rowCount(TCModule);j++)

{

//call all cells

String Description =xl.getCellData(TCModule, j, 0);

String ObjectType =xl.getCellData(TCModule, j, 1);

String LocatorType =xl.getCellData(TCModule, j, 2);

String LocatorValue = xl.getCellData(TCModule, j, 3);

String TestData =xl.getCellData(TCModule, j, 4);

try {

if(ObjectType.equalsIgnoreCase("startBrowser"))

{

driver =FunctionLibrary.startBrowser();

}

else if(ObjectType.equalsIgnoreCase("openUrl"))

{

FunctionLibrary.openUrl(driver);

}

else if(ObjectType.equalsIgnoreCase("waitForElement"))

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
else if (ObjectType.equalsIgnoreCase("mouseClick"))
		{
	FunctionLibrary.mouseClick(driver);
		}
else if (ObjectType.equalsIgnoreCase("CategoryTable"))
{
	FunctionLibrary.CategoryTable(driver, TestData);
}
//write as pass into status cell TCModule

xl.setCelldata(TCModule, j, 5, "Pass", outputpath);

Module_Status="True";

}catch(Exception e)

{

System.out.println(e.getMessage());

//write as fail into status cell TCModule

xl.setCelldata(TCModule, j, 5, "Fail", outputpath);

Module_Status ="False";

}

if(Module_Status.equalsIgnoreCase("True"))

{

xl.setCelldata("MasterTestCases", i, 3, "Pass", outputpath);

}

else

{

xl.setCelldata("MasterTestCases", i, 3, "Fail", outputpath);

}

}

}

else

{

//write as Blocked which are falg to N

xl.setCelldata("MasterTestCases", i, 3, "Blocked", outputpath);

}

}

}

}


