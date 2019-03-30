package com.qtpselenium.pom.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.pom.flipkart.pages.base.BasePage;
import com.qtpselenium.pom.flipkart.util.FKConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignUpPage extends BasePage {
	
	@FindBy(xpath=FKConstants.MOBILE_NO)
	public AndroidElement MOBILE_NO;
	@FindBy(xpath=FKConstants.SIGN_UP)
	public AndroidElement signup;
	//public AndroidDriver<AndroidElement> aDriver;
	public ExtentTest test;
	 //private AndroidDriver<AndroidElement> aDriver;
	   
	 public SignUpPage(AndroidDriver<AndroidElement> aDriver, ExtentTest test){
		 super(aDriver,test);
		}

	
	public void enterMobile(String mobileNo)
	{
		//test.log(LogStatus.INFO, "Finding presence of element "+MOBILE_NO);	
		AndroidElement ele=  aDriver.findElement(By.xpath(FKConstants.MOBILE_NO));
		ele.isDisplayed();
		System.out.println(	"ele --"+ele.isDisplayed());
		ele.sendKeys(mobileNo);		
		
	}
	
	public void clickSignBtn()
	{
		//test.log(LogStatus.INFO, "Finding presence of element "+ signup);	
		AndroidElement ele=  aDriver.findElement(By.xpath(FKConstants.SIGN_UP));
		ele.isDisplayed();
		signup.click();
	}
}
