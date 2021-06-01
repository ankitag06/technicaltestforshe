package com.she.base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.wifispark.config.Config;

public class TestBase {

	public WebDriver driver;	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;
	public static ExtentTest logger;

	@BeforeSuite
	public void ExtentReport() {

		htmlReporter = new ExtentHtmlReporter(".\\Reports\\SheTest.html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);

	}

	@BeforeMethod
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		System.out.println("========== Browser launched");
		driver.get(Config.getUrl());		
		System.out.println("========== Application Launched");

	}

	@AfterMethod(alwaysRun = true)
	public void closeApplication(ITestResult result) throws IOException {
		System.out.println("########## Test Case Finished :" + result.getName());

		if (result.getStatus() == ITestResult.FAILURE) {

			try {
				logger.fail(result.getMethod().getQualifiedName() + "_FAILED",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(result)).build());
				logger.fail(result.getThrowable());
			} catch (IOException e) {

				System.out.println("Problem in Report failure step :" + e.getMessage());
			}

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.pass(result.getMethod().getQualifiedName() + "_PASSED");

		}

		else if (result.getStatus() == ITestResult.FAILURE) {

			logger.skip(result.getMethod().getQualifiedName() + "_SKIPPED");
			logger.skip(result.getThrowable());

		}

		driver.quit();
		System.out.println("========== Browser closed");
	}

	@AfterSuite
	public void flushReport() {

		report.flush();

	}

	private String captureScreenshot(ITestResult result) {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);
		String screenshotpath = System.getProperty("user.dir") + result.getMethod().getQualifiedName() + ".png";

		try {
			FileHandler.copy(src, new File(screenshotpath));
		} catch (IOException e) {

			System.out.println("Unable to capture screensot :" + e.getMessage());
		}
		return screenshotpath;
	}

}
