package com.she.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentTest;
import com.wifispark.config.Config;

public class CreateInformationPage extends AirEmissionPage {

	public CreateInformationPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);

	}

	/*
	 * Web Elements
	 */

	protected By descriptionbox = By.cssSelector(
			".coolfieldset.collfieldset-applied.expanded>div>ul>li:nth-child(4)>span>.ui-wrapper>textarea");
	protected By selectdate = By.cssSelector(".fa.fa-calendar");
	protected By save_closebtn = By.xpath("//button[@value='Close']");

	/*
	 * Methods
	 */

	public CreateInformationPage enterDescription(String description) {
		if (isElementPresent(descriptionbox)) {
			waitForVisibilityOfElement(descriptionbox);
			driver.findElement(descriptionbox).click();
			driver.findElement(descriptionbox).clear();
			actions.moveToElement(driver.findElement(descriptionbox)).sendKeys(description).build().perform();
		}
		return this;
	}

	public CreateInformationPage selectDateFromDatePicker() {
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(selectdate));

		js.executeScript("arguments[0].click()", driver.findElement(selectdate));

		WebElement datewidgetform = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ui-datepicker-calendar")))
				.get(0);

		List<WebElement> columns = datewidgetform.findElements(By.tagName("td"));

		clickGivenDay(columns, getCurrentDay());

		return this;
	}

	public AirEmissionPage clickSave_And_Close_Btn() {

		try {
			driver.findElement(save_closebtn).click();
			waitForUrlToBe(Config.getUrl() + "/Environment/AirEmissions");

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Problem in saving new record data for Air emissions");
		}

		return new AirEmissionPage(driver, logger);
	}

}
