package com.qtpselenium.pom.flipkart.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;









import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qtpselenium.pom.flipkart.pages.base.BasePage;
import com.qtpselenium.pom.flipkart.util.FKConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchResultPage  extends BasePage {
		
	public SearchResultPage(AndroidDriver<AndroidElement> aDriver,ExtentTest test) {
		 super(aDriver,test);
	}

	public void selectAndValidateMobile(String mobileName,String brand,String priceRange) {
		test.log(LogStatus.INFO, "Scrolling and selecting mobile - "+ mobileName);
		WebDriverWait wait = new WebDriverWait(aDriver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FKConstants.PROD_NAMES)));
		
		List<AndroidElement> names =aDriver.findElements(By.id(FKConstants.PROD_NAMES));
		List<AndroidElement> prices=aDriver.findElements(By.id(FKConstants.PROD_PRICES));

		int yEnd = names.get(0).getLocation().y;
		int yStart = names.get(1).getLocation().y;
		int yBefore=0;
		int yAfter=1;
		while(true){
			if(yAfter >yBefore){
				System.out.println(names.get(0).getText() + " --- "+ prices.get(0).getText());
				// to check if mobile is from brand
				if(!names.get(0).getText().contains(brand))
					reportFail("Mobile name not found");
				String price=prices.get(0).getText();
				
				int priceDisplayed=Integer.parseInt(price.split(" ")[1].replace(",", ""));		
				String temp[] = priceRange.split("\\|");
				boolean found=false;
				for(int j=0;j<temp.length;j++){
					System.out.println(temp[j]);
					String temp2[]=temp[j].split(" ");
					int lowerLimit = Integer.parseInt(temp2[1]);
					int upperLimit =  Integer.parseInt(temp2[4]);
					if(priceDisplayed>=lowerLimit & priceDisplayed<=upperLimit){
					 	found=true;
						break;
					}
				}
				if(!found)
					reportFail( "Price is not in price range "+priceDisplayed);
				
				if(names.get(0).getText().equals(mobileName)){
					test.log(LogStatus.PASS, "Mobile found in the list -"+mobileName);
					names.get(0).click();
					break; //while
				}
				
			}
			yBefore = prices.get(1).getLocation().y;
			aDriver.swipe(300, yStart, 300, yStart-50, 1000);
			yAfter= prices.get(1).getLocation().y;		
			
			if(yAfter == yBefore){// end
				test.log(LogStatus.FAIL, "Mobile  not found in the list -"+mobileName);
				reportFail("Mobile  not found in the list -"+mobileName);
			}
		}
		

	
		//test.log(LogStatus.INFO, "Scrolled and selected mobile successfully- "+ mobileName);
		
	}

	

}
