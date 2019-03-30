package com.qtpselenium.pom.flipkart.pages.base;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.qtpselenium.pom.flipkart.pages.SignUpPage;
import com.qtpselenium.pom.flipkart.pages.TopMenu;
import com.qtpselenium.pom.flipkart.util.FKConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.org.apache.xerces.internal.dom.DeferredTextImpl;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {
	public AndroidDriver<AndroidElement> aDriver;
	public ExtentTest test;
	public SignUpPage sign;
	int level=0;


	public BasePage(AndroidDriver<AndroidElement> aDriver,ExtentTest test) {
		
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), this);
		this.aDriver = aDriver;
		this.test=test;
	}
	
	/*public SignUpPage getMenu(){
		return sign;
	}*/
	
	public boolean isElementPresent(String locator){
		
		test.log(LogStatus.INFO, "Finding presence of element "+locator);
		int s=aDriver.findElements(By.xpath(locator)).size();
		if(s==0)
			return false;
		else
			return true;
	}
	
	public boolean verifyText(String locator,String expectedText){
		return false;
	}
	
	/*****************************Reporting functions*****************************************/
	
	public void reportPass(String passMsg){
		 test.log(LogStatus.PASS, passMsg);
	}
	
	public void reportFail(String failureMsg){
		 takeScreenshot();
		 test.log(LogStatus.FAIL, failureMsg);
		 Assert.fail(failureMsg);
	}
	
	public void takeScreenshot(){
		// decide the file name
		Date d = new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
		String path=FKConstants.SCREENSHOT_PATH+screenshotFile;
		// take screenshot
		File scrFile = ((TakesScreenshot)aDriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//add screenshot to report
		test.log(LogStatus.INFO,"Snapshot below: ("+screenshotFile+")"+
				 test.addScreenCapture(path));
	}
	
	public Node initXML() {
		aDriver.findElement(By.xpath(FKConstants.NAVIGATION_MENU_IMAGE)).click();
		File fXmlFile = new File(System.getProperty("user.dir")+"//menu.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder=null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc=null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();

		
		Node baseNode = doc.getChildNodes().item(0);
		return baseNode;
	}
	
	public  void verifyChildValues(Node currentNode) {

		  
		
		if(! (currentNode instanceof DeferredTextImpl)){
			Element e = (Element)currentNode;

			if(!e.getAttribute("id").equals("")){ // expanded/clicked
				
					if(!e.getAttribute("id").equals("mainmenu")){
						boolean res=verifyElement(e.getAttribute("id"));
						System.out.println("Level - "+level+".Verifying Node "+e.getAttribute("id")+" -result -> "+ res);
						if(!res)
							reportFail("Item not found in menu - "+e.getAttribute("id"));
		
						aDriver.findElement(By.xpath("//android.widget.TextView[@text='"+e.getAttribute("id")+"']")).click();
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						level++;
						
					}
			
			Node child = currentNode.getFirstChild();
			while(child!=null){
				verifyChildValues(child);
				child=child.getNextSibling();
			}
			System.out.println();
			level--;
			if(level==0){
				aDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
				aDriver.findElement(By.xpath(FKConstants.NAVIGATION_MENU_IMAGE)).click();

			}
		
			
		}else{// value is present
			
			boolean res=verifyElement(e.getTextContent());
			System.out.println("Level - "+level+". Verifying "+e.getTextContent()+" -result -> "+ res);
			if(!res)
				reportFail("Item not found in menu - "+e.getTextContent());
			
				currentNode = currentNode.getNextSibling();
				verifyChildValues(currentNode);			
		}
		}
		
	}
	
	public boolean verifyElement(String nextElement){
		System.out.println("Verifying - "+nextElement);
			
		
		List<AndroidElement> e = null;
		if(level==0){
			e= aDriver.findElementsByAndroidUIAutomator("UiSelector().resourceId(\"com.flipkart.android:id/flyout_parent_title\")");
		}else {
			e= aDriver.findElementsByAndroidUIAutomator("UiSelector().resourceId(\"com.flipkart.android:id/title\")");
		}
				
				
		boolean scroll=true;		
		String lastBeforeScroll="x";
		String lastAfterScroll="y";
		while(scroll){
			// finding element
			for(AndroidElement a:e){
				if(a.getText().equals(nextElement)){
					return true;
				}
			}
			
			if(lastAfterScroll.equals(lastBeforeScroll)){ // end of the list
				System.out.println("Not found");
				return false; //not found
			}
			
			
			int h = aDriver.manage().window().getSize().height;
			lastBeforeScroll = e.get(e.size()-1).getText();
			aDriver.swipe(300, h-100, 300, h/2, 4000);
		
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(level==0){
				e= aDriver.findElementsByAndroidUIAutomator("UiSelector().resourceId(\"com.flipkart.android:id/flyout_parent_title\")");
			}else {
				e= aDriver.findElementsByAndroidUIAutomator("UiSelector().resourceId(\"com.flipkart.android:id/title\")");
			}
			lastAfterScroll = e.get(e.size()-1).getText();
			
			
		}
	//	System.out.println("------**----------------------------");
		for(AndroidElement a:e){
			//System.out.println(a.getText());
			if(a.getText().equals(nextElement)){
				return true;
			}
		}
		
		return false;
	}
	
}
