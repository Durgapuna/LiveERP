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
		public static String Actul="";
		public static WebDriver startBrowser() throws Throwable{
			
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
	System.out.println("Browser is not matching");	
	}
return driver;
}
public static void openUrl(WebDriver driver)
{
	driver.get(conpro.getProperty("Url"));
	
}
public static void waitForElement(WebDriver driver,String LocatorType,String LocatorValue,String waitTime)	
{
WebDriverWait mywait = new WebDriverWait(driver,Integer.parseInt(waitTime));
if(LocatorType.equalsIgnoreCase("name"))
		{
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
		}
else if(LocatorType.equalsIgnoreCase("xpath"))
{
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
}
else if(LocatorType.equalsIgnoreCase("id"))
{
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
}
else 
{
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));

}

}
//method fpr textboxes
public static void typeAction(WebDriver driver,String LocatorType, String LocatorValue,String testData) {
	
	
	if(LocatorType.equalsIgnoreCase("id")) {
		
		driver.findElement(By.id(LocatorValue)).clear();
		driver.findElement(By.id(LocatorValue)).sendKeys(testData);
	}
	else if(LocatorType.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(LocatorValue)).clear();
		driver.findElement(By.xpath(LocatorValue)).sendKeys(testData);
	}}
	//method for bttons,radio,che3ckbox and images
	public static void clickAction(WebDriver driver,String Locatortype,String LocatorValue)
	{
		if(Locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).click();
			
		}
		else if(Locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		}
		else if(Locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		}
	}
	public static void validateTitle (WebDriver driver,String Expected_Title )
	{
    String Actual_Title = driver.getTitle();
    try {
    Assert.assertEquals(Expected_Title, Actual_Title,"Title is not matching");
    }catch(Throwable t)
    {
    	System.out.println(t.getMessage());
    }
	}
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}

	public static void mouseClick(WebDriver driver)
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath("")));
		
	}
	public static void categoryTable(WebDriver driver, String ExpectedData) throws Throwable
	{
		//if search already displayed no need to click search panel
		if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
		driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
 driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys(ExpectedData);
 Thread.sleep(3000);
 driver.findElement(By.xpath(conpro.getProperty("search-button")));
 
 
 
 
 
 
	
	





	}
}