package com.demoaut.newtours.scripts;

import org.testng.Assert;
import org.testng.annotations.*;

import com.demoaut.newtours.pages.FlightFinderPage;
import com.demoaut.newtours.pages.HomePage;
import com.demoaut.newtours.pages.RegisterPage;
import com.demoaut.newtours.pages.SeleniumEasyTestHomePage;
import com.demoaut.newtours.utilities.TestDataReader;
import com.google.gson.JsonObject;

/*
 * Create new user by filling registration form and then perform login action with new user.
 */
public class Verify_SeleniumEasy_DemoHomePage_Test extends BaseTest 
{
	private SeleniumEasyTestHomePage homePage;
	private JsonObject testData;
	
	@Override
	@BeforeTest
	public void setData() 
	{
		testData = TestDataReader.loadTestData(Verify_SeleniumEasy_DemoHomePage_Test.class.getName().replace("com.demoaut.newtours.scripts.", ""));	
	}
	
	@Test
	@Parameters({"URL"})
	public void test(String url)
	{
		//Open home page and click on 'Register' link to open register page. 
		homePage = new SeleniumEasyTestHomePage();
		System.out.println("Open home page url : " + url);
		homePage.open(url);
		System.out.println("Click and verify board name");
		homePage.clickAndVerifyBoardName(testData);	
	}
	
	
}
