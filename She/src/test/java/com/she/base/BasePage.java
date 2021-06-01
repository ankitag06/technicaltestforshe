package com.she.base;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BasePage {

	public WebDriver driver;
	protected JavascriptExecutor js;
	protected Actions actions;
	protected final int WebDriverWaittime = 20;
	protected static ExtentReports report;
	protected ExtentTest logger;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		wait = new WebDriverWait(driver, Duration.ofSeconds(WebDriverWaittime));
		js = (JavascriptExecutor) driver;
		actions = new Actions(driver);

	}

	public void waitForUrlToBe(String url) {
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public WebElement waitForVisibilityOfElement(By by) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public List<WebElement> waitForVisibilityOfAllElement(By by) {

		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));

	}

	protected boolean isElementPresent(By by) {
		try {

			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getCurrentDay() {

		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		// System.out.println("Today Int : " + todayInt + "\n");

		/*
		 * Integer to String Conversion
		 */
		String todayStr = Integer.toString(todayInt);
		// System.out.println("Today Str: " + todayStr + "\n");
		return todayStr;
	}

	public static void clickGivenDay(List<WebElement> elementList, String day) {

		// DatePicker is a table. Thus we can navigate to each cell
		// and if a cell matches with the current date then we will click it.

		/*
		 * elementList.stream() .filter(element -> element.getText().contains(day))
		 * .findFirst() .ifPresent(WebElement::click);
		 */

		for (WebElement cell : elementList) {
			String cellText = cell.getText();
			if (cellText.contains(day)) {
				cell.click();
				break;
			}
		}
	}
}
