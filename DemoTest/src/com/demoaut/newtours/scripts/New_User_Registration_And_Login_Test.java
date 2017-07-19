package com.demoaut.newtours.scripts;

import org.testng.Assert;
import org.testng.annotations.*;

import com.demoaut.newtours.pages.FlightFinderPage;
import com.demoaut.newtours.pages.HomePage;
import com.demoaut.newtours.pages.RegisterPage;
import com.demoaut.newtours.utilities.TestDataReader;
import com.google.gson.JsonObject;

/*
 * Create new user by filling registration form and then perform login action with new user.
 */
public class New_User_Registration_And_Login_Test extends BaseTest 
{
	private HomePage homePage;
	private RegisterPage registerPage;
	private FlightFinderPage flightFinderPage;
	private JsonObject testData;
	
	@Override
	@BeforeTest
	public void setData() 
	{
		testData = TestDataReader.loadTestData(New_User_Registration_And_Login_Test.class.getName().replace("com.demoaut.newtours.scripts.", ""));	
	}
	
	@Test
	@Parameters({"URL"})
	public void test(String url)
	{
		//Open home page and click on 'Register' link to open register page. 
		homePage = new HomePage();
		System.out.println("Open home page url : " + url);
		homePage.open(url);
		System.out.println("Click on 'Register' link to open register page");
		homePage.clickOnRegisterLink();
		
		//Fill and submit registration form on opened register page.
		registerPage = new RegisterPage();
		System.out.println("Fill and submit registration form on opened register page");
		registerPage.fillRegistrationForm(testData);
		registerPage.clickOnSubmit();
		
		//Again open home page and sign-In by given user-name and password which flight finder page.
		System.out.println("Again open home page");
		homePage.open(url);
		System.out.println("sign-In by user-name: " + testData.get("userName").getAsString() + " password : " + testData.get("password").getAsString());
		homePage.signIn(testData.get("userName").getAsString(),testData.get("password").getAsString());
		
		//On flight finder page verifying flight details section displayed or not.
		System.out.println("On flight finder page verifying flight details section displayed or not.");
		flightFinderPage = new FlightFinderPage();
		Assert.assertTrue(flightFinderPage.verifyFlightDetailsDisplayedOrNot(), "Flight details section not displayed");	
	}
	
	
}
