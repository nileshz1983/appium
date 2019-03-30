package com.qtpselenium.pom.flipkart.util;

import java.net.URL;

import bsh.Capabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidDriverSingleton {
	
	public static AppiumDriver driver;

	   public static AppiumDriver getInstance(URL  url,org.openqa.selenium.Capabilities cap ) {
	     if (driver == null) {
	       driver = new AndroidDriver(url, cap);
	     }
	     return driver;
	   }


}
