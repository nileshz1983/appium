package com.qtpselenium.pom.flipkart;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.pom.flipkart.base.BaseTest;
import com.qtpselenium.pom.flipkart.pages.HomePage;
import com.qtpselenium.pom.flipkart.pages.MobileDisplayPage;
import com.qtpselenium.pom.flipkart.pages.SearchResultPage;
import com.qtpselenium.pom.flipkart.util.DataUtil;
import com.qtpselenium.pom.flipkart.util.ExtentManager;
import com.qtpselenium.pom.flipkart.util.FKConstants;
import com.qtpselenium.pom.flipkart.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FindRightMobileTest extends BaseTest {
	
	String testName="FindRightMobileTest";
	
	@Test(dataProvider="getData")
	public void findRightMobileTest(Hashtable<String,String> data) throws InterruptedException{
		test = rep.startTest(testName);
		test.log(LogStatus.INFO, "Starting the test ");
		
		if(!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode was NO");
			throw new SkipException("Skipping the test as Runmode was NO");
		}
		
		launchApp();
		test.log(LogStatus.INFO, "App Launched successfully ");
	     
		HomePage homePage = new HomePage(aDriver,test);
		
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),homePage);
		if(!homePage.isElementPresent(FKConstants.NAVIGATION_MENU_IMAGE)){
			homePage.reportFail("Navigation menu not present");
		}
		
		Object resultPage = homePage.selectFromNavigationMenu("Electronics|Mobiles");
		if(resultPage==null)
			homePage.reportFail("Could not load mobiles section from menu");
		
		if(!(resultPage instanceof MobileDisplayPage))
			homePage.reportFail("Result page was not mobile page");

		
		// reach
		test.log(LogStatus.INFO, "Reached mobile display page ");
		MobileDisplayPage mDisplayPage=(MobileDisplayPage) resultPage;
		
		String price=data.get("Pricing");
		String brand=data.get("Brand");
		String os=data.get("OS");
		String size=data.get("Size");
		
		SearchResultPage searchResultPage =mDisplayPage.applyFilter(price,brand,os,size);
	
		//searchResultPage.selectAndValidateMobile(data.get("Mobile"),brand,price);
		
		searchResultPage.reportPass("Test Passed");
		
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName, xls);
	}
	
	
}
