package com.cucumber.framework.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = { "classpath:featurefile/signup/SignUp.feature" }, glue = {
		"classpath:com.cucumber.framework.stepdefinition.signup"
		 }, plugin = {"html:target/cucumber-html-report"})
public class SignUpRunner  extends AbstractTestNGCucumberTests {

}
