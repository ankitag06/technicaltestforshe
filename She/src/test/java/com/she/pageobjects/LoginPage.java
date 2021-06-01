package com.she.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.she.base.BasePage;
import com.wifispark.config.Config;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);

	}

	/*
	 * 
	 * Web Elements
	 */

	protected By username = By.cssSelector("#username");
	protected By password = By.cssSelector("#password");
	protected By login = By.cssSelector("#login");
	/*
	 * Methods
	 */

	public AutomationPage submitLogin() throws Exception {
		if (isElementPresent(username)) {
			driver.findElement(username).clear();
			driver.findElement(username).sendKeys(Config.getUsername());
		}
		if (isElementPresent(password)) {
			driver.findElement(password).clear();
			driver.findElement(password).sendKeys(Config.getPassword());
		}
		if (isElementPresent(login)) {
			driver.findElement(login).click();
		}

		return new AutomationPage(driver, logger);
	}

}
