package com.she.test;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.she.base.PageClassObjects;

public class RecordDeletionTest extends PageClassObjects {
	
	/*
	 * Create two records, delete first record and verify it's deleted
	 * 
	 * imp note : Run test, refresh Reports folder , open it 
	 * 
	 */
	
	@Test
	public void verifyRecordDeleted(Method method) throws Exception
	{		
		logger = report.createTest(method.getName(), "Enter two records and verify first record is delted");		
		logger.log(Status.INFO, "Test Started");
		
		login().submitLogin();		
		logger.log(Status.INFO, "Login sucessfully");
		
		AutomationPage().clickModule().clickSubMenuOption();		
		logger.log(Status.INFO, "Clicked on Sub-Menu under Module");
		
		AirEmissionPage().clickNewRecordBtn();		
		logger.log(Status.INFO, "clicked on new record button");
		
		CreateInformationPage().enterDescription("first Ankita's test")		
		.selectDateFromDatePicker().clickSave_And_Close_Btn();		
		logger.log(Status.INFO, "First record is created and saved");	
		
		AirEmissionPage().clickNewRecordBtn();
		logger.log(Status.INFO, "Clicked again on New Record button to create another record");
		
		CreateInformationPage().enterDescription("Second Ankita's test").selectDateFromDatePicker().clickSave_And_Close_Btn();
		logger.log(Status.INFO, "Second record is created and saved");
		
		 Boolean status = AirEmissionPage().deleteRecord("first Ankita's test").checkIsRecordDeleted("first Ankita's test");
		 Assert.assertFalse(status, "Record found");
		logger.log(Status.INFO, "First Record deleted");
		
	}

}
