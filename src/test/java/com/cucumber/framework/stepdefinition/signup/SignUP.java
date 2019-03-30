package com.cucumber.framework.stepdefinition.signup;

import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.pom.flipkart.base.BaseTest;
import com.qtpselenium.pom.flipkart.pages.SignUpPage;
import com.relevantcodes.extentreports.LogStatus;

//import com.cucumber.framework.PageObject.AddUserDetailsPage;


import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignUP extends BaseTest  {
	
	//AddUserDetailsPage userPage;
	String testName="FindRightMobileTest";
	
	@Before
    public void beforeScenario() throws InterruptedException{
		
		 {
			test = rep.startTest(testName);
			test.log(LogStatus.INFO, "Starting the test ");
		    // Write code here that turns the phrase above into concrete actions
			launchApp();
			test.log(LogStatus.INFO, "App Launched successfully ");
		}

    }	
	
	@When("^enter mobile \"([^\"]*)\"$")
	public void enter_the_mobile_no(String mobileNo) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions

		SignUpPage sign= new SignUpPage(aDriver, test);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),sign);
		sign.enterMobile(mobileNo);
		
	}

	@When("^click on sign up button$")
	public void click_on_sign_up_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		SignUpPage sign= new SignUpPage(aDriver, test);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),sign);	
		sign.clickSignBtn();
	}

	@Then("^sign up should be successful$")
	public void sign_up_should_be_successful() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

}
