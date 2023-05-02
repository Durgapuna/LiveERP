package commonFunctions;
import java.io.FileInputStream;

import java.util.Properties;



import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class FunctionLibrary {

public static WebDriver driver;

public static Properties conpro;

public static String Expected="";

public static String Actual ="";

//method for launch browser

public static WebDriver startBrowser()throws Throwable

{

conpro = new Properties();

conpro.load(new FileInputStream("./PropertyFile/Environment.properties"));

if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))

{

driver = new ChromeDriver();

driver.manage().window().maximize();

driver.manage().deleteAllCookies();

}

else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))

{

driver = new FirefoxDriver();

driver.manage().deleteAllCookies();

}

else

{

System.out.println("Browser value is not matching");

}

return driver;

}

//method for launch url

public static void openUrl(WebDriver driver)

{

driver.get(conpro.getProperty("Url"));

}

//method for wait for element

public static void waitForElement(WebDriver driver,String LocatorType,String LocatorValue,String waitTime)

{

WebDriverWait myWait = new WebDriverWait(driver,Integer.parseInt(waitTime));

if(LocatorType.equalsIgnoreCase("name"))

{

myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));

}

else if(LocatorType.equalsIgnoreCase("xpath"))

{

myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));

}

else if(LocatorType.equalsIgnoreCase("id"))

{

myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));

}

}

//method for textboxes

public static void typeAction(WebDriver driver,String LocatorType,String LocatorValue,String TestData)

{

if(LocatorType.equalsIgnoreCase("id"))

{

driver.findElement(By.id(LocatorValue)).clear();

driver.findElement(By.id(LocatorValue)).sendKeys(TestData);

}

else if(LocatorType.equalsIgnoreCase("xpath"))

{

driver.findElement(By.xpath(LocatorValue)).clear();

driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);

}

else if(LocatorType.equalsIgnoreCase("name"))

{

driver.findElement(By.name(LocatorValue)).clear();

driver.findElement(By.name(LocatorValue)).sendKeys(TestData);

}

}

//method for bttons,radio,checkbox,links and images

public static void clickAction(WebDriver driver,String LocatorType,String LocatorValue)

{

if(LocatorType.equalsIgnoreCase("xpath"))

{

driver.findElement(By.xpath(LocatorValue)).click();

}

else if(LocatorType.equalsIgnoreCase("name"))

{

driver.findElement(By.name(LocatorValue)).click();

}

else if(LocatorType.equalsIgnoreCase("id"))

{

driver.findElement(By.xpath(LocatorValue)).click();

}

}
public static void validateTitle(WebDriver driver,String Expected_Title)

{

String Actual_Title= driver.getTitle();

try {

Assert.assertEquals(Expected_Title, Actual_Title,"Title is Not Matching");

}catch(Throwable t)

{

System.out.println(t.getMessage());

}

}

//method for closing browser

public static void closeBrowser(WebDriver driver)

{

driver.quit();

}
public static void mouseClick(WebDriver driver)
{
	Actions ac = new Actions(driver);
	ac.moveToElement(driver.findElement(By.xpath("//a[starts-with(text(),'Stock Items ')]"))).perform();
	ac.moveToElement(driver.findElement(By.xpath("(//a[contains(.,'Stock Categories')])[2]"))).click().perform();
}
public static void CategoryTable(WebDriver driver,String ExpectedData) 
{
	//if search textbox already dispalyed no need to click search panel
	if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
		driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
	driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys(ExpectedData);
	driver.findElement(By.xpath(conpro.getProperty("search-button"))).click();
	
	String Actualdata=driver.findElement(By.xpath("//td[@class='ewTableLastCol ewTableBorderBottom']")).getText();
	System.out.println(ExpectedData+"      "+Actualdata);
	
	Assert.assertEquals(ExpectedData, Actualdata, "Category Name Not Matching");
	
	

	
}
}