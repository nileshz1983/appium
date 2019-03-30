package com.qtpselenium.pom.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.qtpselenium.pom.flipkart.pages.base.BasePage;
import com.qtpselenium.pom.flipkart.util.FKConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MobileDisplayPage  extends BasePage {
	
	
	
	@FindBy(id=FKConstants.TOP_HEADING)
	AndroidElement topHeading;
	
	@FindBy(xpath=FKConstants.BRAND)
	AndroidElement brandHeading;
	
	@FindBy(xpath=FKConstants.OS_HEADING)
	AndroidElement osHeading;
	
	@FindBy(xpath=FKConstants.APPLY_SEARCH_PATH)
	AndroidElement applySearchButton;
	
	
	public MobileDisplayPage(AndroidDriver<AndroidElement> aDriver, ExtentTest test){
		 super(aDriver,test);
	}
	
	
	public SearchResultPage applyFilter(String price, String brand, String os, String size){
		test.log(LogStatus.INFO, "Applying filter");
		if(price!=""){
			String prices[] = price.split("\\|");
			for(int i=0;i<prices.length;i++){
				Assert.assertTrue(isElementPresent("//android.widget.TextView[@text='"+prices[i]+"']"), "Could not find price "+prices[i]);
				aDriver.findElement(By.xpath("//android.widget.TextView[@text='"+prices[i]+"']")).click();
			}
		}
		
		if(brand!=""){
			int width =aDriver.manage().window().getSize().width;
			int y=topHeading.getLocation().y;
			aDriver.swipe(width-100, y, width/2, y, 2000);
			int xBrand= brandHeading.getLocation().x;
			if(xBrand > width/2){
				aDriver.swipe(width-100, y, width/2, y, 2000);
			}
			String brands[] = brand.split("\\|");
			for(int i=0;i<brands.length;i++){
				aDriver.findElement(By.xpath("//android.widget.TextView[@text='"+brands[i]+"']")).click();

			}
		}
		
		if(os!=""){
			int width =aDriver.manage().window().getSize().width;
			int y=topHeading.getLocation().y;
			aDriver.swipe(width-100, y, width/2, y, 2000);
			int xOS= osHeading.getLocation().x;
			if(xOS > width/2){
				aDriver.swipe(width-100, y, width/2, y, 2000);
			}
			String osList[] = os.split("\\|");
			for(int i=0;i<osList.length;i++){
				aDriver.findElement(By.xpath("//android.widget.TextView[@text='"+osList[i]+"']")).click();
			}
		}
		int height =aDriver.manage().window().getSize().height;

		aDriver.swipe(300, height-10, 300, height/2, 2000);
		
		applySearchButton.click();
		SearchResultPage searchResultPage  = new SearchResultPage(aDriver,test);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),searchResultPage);
		test.log(LogStatus.INFO, "Applied filter succussfully");
		return searchResultPage;

		
	}
	
	
}
