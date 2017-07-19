package com.demoaut.newtours.pages;

import com.demoaut.newtours.objectRepo.Locators;
import com.demoaut.newtours.utilities.SeleniumWrapper;
import com.google.gson.JsonObject;

/*
 * HomePage class contains methods which perform action on home page UI. 
 */
public class SeleniumEasyTestHomePage 
{
	private static SeleniumWrapper objSele;
	
	public SeleniumEasyTestHomePage()
	{
		objSele = new SeleniumWrapper();
	}
	
	public void open(String url)
	{
		objSele.openUrl(url);
	}
	
	/*
	 * Perform sign-In action with given userName and password.
	 */
	public void clickAndVerifyBoardName(JsonObject boardName)
	{
		System.out.println("Need to do this task");
	}
}
