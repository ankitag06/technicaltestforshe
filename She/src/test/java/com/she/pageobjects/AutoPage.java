package com.she.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class AutoPage extends LoginPage{

	public AutoPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		
	}
	

	/*
	 * Web Elements
	 */
	
	protected By module = By.cssSelector(".headercenter>.she-nav-menu>.js-she-dropdown-menu>.she-has-submenu");
	protected By envsubmenu = By.cssSelector(".she-has-submenu.js-she-dropdown-open.she-dropdown-open>.she-nav-modules>li:nth-child(10)>a");
	protected By airemissionmenu = By.cssSelector(".she-has-submenu.js-she-dropdown-open.she-dropdown-open>.she-nav-modules>li:nth-child(10)>ul>li:nth-child(2)>a:nth-child(1)");
	
	
	
	/*
	 * Methods
	 */
	 
	
	public AutoPage clickModule()
	{
		if(isElementPresent(module))
		{
			driver.findElement(module).click();
		}
		return this;
	}
	
	
	public AirEmissionPage clickSubMenuOPtion()
	{
		if(isElementPresent(envsubmenu))
		{
			driver.findElement(envsubmenu).click();
		}
		
		driver.findElement(airemissionmenu).click();
		
		return new AirEmissionPage(driver, logger);
	}


}
