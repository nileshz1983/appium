package com.qtpselenium.pom.flipkart;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.testng.SkipException;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.qtpselenium.pom.flipkart.base.BaseTest;
import com.qtpselenium.pom.flipkart.pages.HomePage;
import com.qtpselenium.pom.flipkart.util.DataUtil;
import com.relevantcodes.extentreports.LogStatus;

public class SideBarMenuTest extends BaseTest {
	String testName="SideBarMenuTest";
	
	@Test
	public void sideBarMenuTest() throws InterruptedException{
		test = rep.startTest(testName);
		test.log(LogStatus.INFO, "Starting the test ");
		
		if(!DataUtil.isExecutable(xls, testName)){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode was NO");
			throw new SkipException("Skipping the test as Runmode was NO");
		}
		// execute
		test.log(LogStatus.PASS, "Test Passed");
		
		launchApp();
		test.log(LogStatus.INFO, "App Launched successfully ");
	     
		HomePage homePage = new HomePage(aDriver,test);
		
		Node baseNode = homePage.initXML();
		homePage.verifyChildValues(baseNode);
		
		
	}
	
	
}
