package com.she.base;

import com.she.pageobjects.LoginPage;

import com.she.pageobjects.AutomationPage;
import com.she.pageobjects.CreateInformationPage;
import com.she.pageobjects.AirEmissionPage;

public class PageClassObjects extends TestBase {
	
	public LoginPage login()
	{
		return new LoginPage(driver, logger);
	}
	
	public AutomationPage AutomationPage()
	{
		return new AutomationPage(driver, logger);
	}
	
	public AirEmissionPage AirEmissionPage()
	{
		return new AirEmissionPage(driver, logger);
	}
	
	public CreateInformationPage CreateInformationPage()
	{
		return new CreateInformationPage(driver, logger);
	}
	
}
