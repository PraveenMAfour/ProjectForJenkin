package com.demoaut.newtours.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

/*
 * SeleniumWrapper class provide all method related to selenium.
 */
public class SeleniumWrapper {

	public static WebDriver driver = null;
	
	public static String currentDir = System.getProperty("user.dir");
	
	public void setWebDriver(String browserName)
	{
		if(browserName.toLowerCase().equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",currentDir+"/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.toLowerCase().equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", currentDir+"/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else if(browserName.toLowerCase().equals("phantomjs"))
		{
			driver = new PhantomJSDriver();
		}
		else
		{
			driver = new FirefoxDriver();
		}
	}
	
	public void click(String locator)
	{
		try
		{
			WebElement webEle = driver.findElement(By.xpath(locator));
			
			if(webEle.isDisplayed())
			{
				webEle.click();
			}
			else
			{
				System.out.println("Element not displyed on UI for locator: " + locator);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException("Unable to perform click operation : "+ e.getMessage());
		}
	}
	
	
	public void openUrl(String url)
	{
		try
		{
			driver.get(url);
		}
		catch(Exception e)
		{
			throw new RuntimeException("Unable to perform open url operation : "+ e.getMessage());
		}
	}
	
	public void inputText(String locator, String text)
	{
		try
		{
			WebElement webEle = driver.findElement(By.xpath(locator));
			
			if(webEle.isDisplayed())
			{
				webEle.clear();
				webEle.sendKeys(text);
			}
			else
			{
				System.out.println("Element not displyed on UI for locator: " + locator);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException("Unable to enter text click operation : "+ e.getMessage());
		}
	}
	
	public void selectByVisibleTextFromDropDown(String locator, String text)
	{
		try
		{
			Select objSelect = new Select(driver.findElement(By.xpath(locator)));
			
			objSelect.selectByVisibleText(text);
		}
		catch(Exception e)
		{
			throw new RuntimeException("Unable to select by visible text from dropdown : "+ e.getMessage());
		}
	}
	
	public boolean isDisplayed(String locator)
	{
		try
		{
			return driver.findElement(By.xpath(locator)).isDisplayed();
		}
		catch(Exception e)
		{
			throw new RuntimeException("Unable to check element is displayed : "+ e.getMessage());
		}
	}
	
	public void clean()
	{
		try
		{
			if(driver!= null)
			{
				driver.quit();
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException("Unable to perform clean operation : "+ e.getMessage());
		}
	}
	
	
	
}
