package com.demoaut.newtours.scripts;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.demoaut.newtours.utilities.SeleniumWrapper;

/*
 * BaseTest class which is extend by every Test-Script class.
 */
public abstract class BaseTest 
{
	public SeleniumWrapper objSele = new SeleniumWrapper();
	
	public abstract void setData();
	
	@BeforeSuite
	@Parameters({"browserName"})
	public void init(String browserName)
	{
		objSele.setWebDriver(browserName);
	}
	
	@AfterSuite
	public void clean()
	{
		objSele.clean();
	}

}
