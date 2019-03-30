package com.qtpselenium.pom.flipkart.base;
//https://drive.google.com/open?id=0B6ou87zaWpBkUnJnV2lMbkQ3Tjg
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.qtpselenium.pom.flipkart.util.AndroidDriverSingleton;
import com.qtpselenium.pom.flipkart.util.ExtentManager;
import com.qtpselenium.pom.flipkart.util.FKConstants;
import com.qtpselenium.pom.flipkart.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {

	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;
	public WebDriver driver;
	public AndroidDriver<AndroidElement> aDriver;
	public Xls_Reader xls = new Xls_Reader(FKConstants.XLS_PATH);
	
	private AppiumDriverLocalService service;

	public void launchApp() throws InterruptedException{
		File app = new File(FKConstants.APK_PATH);
		//Start the server with the builder
		AppiumServiceBuilder builder = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\nilesh\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.withArgument(GeneralServerFlag.LOG_LEVEL, "info")
				.usingAnyFreePort();
		
		service = builder.build();
		service.start();
		String appiumServiceUrl = service.getUrl().toString();
	    System.out.println("Appium Service Address : - "+ appiumServiceUrl);
		
	     DesiredCapabilities capabilities =DesiredCapabilities.android();
	 	 capabilities.setCapability("noReset", "false");
	     capabilities.setCapability("deviceName",FKConstants.DEVICE_NAME);
	     capabilities.setCapability("platformVersion", FKConstants.DEVICE_VERSION);
	     capabilities.setCapability("platformName",FKConstants.PLATFORM);
		 capabilities.setCapability("app", app.getAbsolutePath());

		try {
			test.log(LogStatus.INFO, "Launching app ");
			//driver = new AndroidDriver<AndroidElement>(new URL(appiumServiceUrl), capabilities);
			driver = AndroidDriverSingleton.getInstance(new URL(appiumServiceUrl), capabilities);
		   aDriver = (AndroidDriver<AndroidElement>)driver;
		} catch (MalformedURLException e) {
			test.log(LogStatus.FAIL, "Application did not launch "+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Application did not launch"+ e.getMessage());
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
	}
	
	
	@AfterMethod
	public void quit(){
		if(rep!=null){
			rep.endTest(test);
			rep.flush();
		}
		if(driver!=null)
			driver.quit();
		
	}
	
}

