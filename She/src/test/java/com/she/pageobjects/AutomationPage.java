package com.she.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentTest;
import com.wifispark.config.Config;

public class AutomationPage extends LoginPage {

	public AutomationPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);

	}

	/*
	 * Web Elements
	 */

	protected By module = By.cssSelector(".headercenter>.she-nav-menu>.js-she-dropdown-menu>.she-has-submenu");
	protected By envsubmenu = By.cssSelector(
			".she-has-submenu.js-she-dropdown-open.she-dropdown-open>.she-nav-modules>li:nth-child(10)>a>.fa.fa-chevron-right");
	protected By airemissionmenu = By.cssSelector(
			".she-has-submenu.js-she-dropdown-open.she-dropdown-open>.she-nav-modules>li:nth-child(10)>ul>li:nth-child(2)>a:nth-child(1)");

	/*
	 * Methods
	 */

	public AutomationPage clickModule() {
		if (isElementPresent(module)) {
			waitForVisibilityOfElement(module);
			driver.findElement(module).click();

		}
		return this;
	}

	public AirEmissionPage clickSubMenuOption() throws Exception {
		if (isElementPresent(envsubmenu)) {
			wait.until(ExpectedConditions.elementToBeClickable(envsubmenu));
			js.executeScript("arguments[0].click()", driver.findElement(envsubmenu));

		}

		if (isElementPresent(airemissionmenu)) {
			wait.until(ExpectedConditions.elementToBeClickable(airemissionmenu));
			driver.findElement(airemissionmenu).click();
		}

		waitForUrlToBe(Config.getUrl() + "/Environment/AirEmissions/Page/1");

		return new AirEmissionPage(driver, logger);
	}

}
