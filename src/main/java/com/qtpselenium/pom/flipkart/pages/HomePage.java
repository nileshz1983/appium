package com.qtpselenium.pom.flipkart.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.pom.flipkart.pages.base.BasePage;
import com.qtpselenium.pom.flipkart.util.FKConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends BasePage {
	
	
	@FindBy(xpath=FKConstants.NAVIGATION_MENU_IMAGE)
	public AndroidElement navigationMenuImage;
	@FindBy(id=FKConstants.SEARCH_FIELD1)
	public AndroidElement searchField1;
	@FindBy(id=FKConstants.SEARCH_FIELD2)
	public AndroidElement searchField2;
	
	
	
	public HomePage(AndroidDriver<AndroidElement> aDriver, ExtentTest test){
	 super(aDriver,test);
	}
	
	public Object selectFromNavigationMenu(String navigationItems){
		test.log(LogStatus.INFO, "Selecting from navigation menu");
		navigationMenuImage.click();
		String[] items = navigationItems.split("\\|");
		for(int i=0;i<items.length;i++){
			aDriver.findElement(By.xpath("//android.widget.TextView[@text='"+items[i]+"']")).click();
		}
		test.log(LogStatus.INFO, "Selected item successfully from menu");
		
		// decision
		if(aDriver.findElements(By.xpath(FKConstants.RIGHT_MOBILE)).size()!=0){
		// on mobile displaypage	
			MobileDisplayPage mDisplaypage= new MobileDisplayPage(aDriver,test);
			PageFactory.initElements(new AppiumFieldDecorator(aDriver),mDisplaypage);
			return mDisplaypage;
		}
		
		return null;
	}
	
	public void gotoCart(){
		
	}

	public void search(String data) {
		searchField1.click();
		searchField2.sendKeys(data);
		//aDriver.pressKeyCode(AndroidKeyCode.ENTER);
		searchField2.sendKeys(Keys.ENTER);
		aDriver.pressKeyCode(84);


	}
	
	
	
}
