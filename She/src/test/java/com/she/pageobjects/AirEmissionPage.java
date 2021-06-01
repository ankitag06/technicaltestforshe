package com.she.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.wifispark.config.Config;

public class AirEmissionPage extends AutomationPage {

	public AirEmissionPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);

	}

	/*
	 * 
	 * Web Elements
	 */

	protected By newrecordbtn = By.cssSelector(".btn.btn-large.btn-success.create_record>i");
	protected List<WebElement> descriptionslist = driver.findElements(By.cssSelector(
			".list_layout.StatusBars.clearfix.ui-selectee.blocked>.information>.list_information.clearfix>li:nth-child(5)>a"));
	protected List<WebElement> caretlist = driver.findElements(By.cssSelector(
			".list_layout.StatusBars.clearfix.ui-selectee.blocked>.manage>.btn-group>.btn.btn-large.dropdown-toggle>.caret"));
	protected List<WebElement> deletelist = driver.findElements(By.cssSelector(
			".list_layout.StatusBars.clearfix.ui-selectee.blocked>.manage>.btn-group>.dropdown-menu>li:nth-child(5)>.deleteDialog"));
	protected By confirmbtn = By.cssSelector(".ui-dialog-buttonset>button:nth-child(1)");
	/*
	 * Methods
	 */

	public CreateInformationPage clickNewRecordBtn() throws Exception {
		if (isElementPresent(newrecordbtn)) {
			try {
				waitForVisibilityOfElement(newrecordbtn);
				driver.findElement(newrecordbtn).click();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return new CreateInformationPage(driver, logger);
	}

	public AirEmissionPage deleteRecord(String descriptiontodelete) throws Exception {
		try {
			for (int i = 0; i <= descriptionslist.size(); i++) {
				String value = descriptionslist.get(i).getText();
				if (value.equalsIgnoreCase(descriptiontodelete)) {
					caretlist.get(i).click();
					deletelist.get(i).click();
					driver.findElement(confirmbtn).click();
					System.out.println("Record deleted");
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Status.FAIL, "Record not found");
			System.out.println("Record not found");
		}
		waitForUrlToBe(Config.getUrl() + "/Environment/AirEmissions/Page/1");
		return this;

	}

	public Boolean checkIsRecordDeleted(String deleteddescription) {
		Boolean flag = false;
		descriptionslist = driver.findElements(By.cssSelector(
				".list_layout.StatusBars.clearfix.ui-selectee.blocked>.information>.list_information.clearfix>li:nth-child(5)>a"));
		wait.until(ExpectedConditions.visibilityOfAllElements(descriptionslist));
		try {
			for (int i = 0; i <= descriptionslist.size(); i++) {
				String value = descriptionslist.get(i).getText();
				if (!value.equalsIgnoreCase(deleteddescription)) {

					return flag;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			logger.log(Status.FAIL, "Problem in Record Visibility And Invisibility");
		}

		return true;
	}

}
