package com.qtpselenium.pom.flipkart;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.qtpselenium.pom.flipkart.base.BaseTest;
import com.qtpselenium.pom.flipkart.pages.HomePage;
import com.qtpselenium.pom.flipkart.util.DataUtil;
import com.qtpselenium.pom.flipkart.util.FKConstants;
import com.qtpselenium.pom.flipkart.util.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

public class ApplyFilterTest extends  BaseTest {

	String testName="ApplyFilterTest";
	
	@Test
	public void applyFilterTest() throws InterruptedException{
		test = rep.startTest(testName);
		test.log(LogStatus.INFO, "Starting the test ");
		// check the runmode
		
		if(!DataUtil.isExecutable(xls, testName)){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode was NO");
			//throw new SkipException("Skipping the test as Runmode was NO");
		}
			// execute
		test.log(LogStatus.PASS, "Test Passed");
	}
	
	
}
