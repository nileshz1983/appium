package Temp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerJava {

	private AppiumDriverLocalService service;
	//private AppiumServiceBuilder builder;
	private DesiredCapabilities cap;
	
	public void startServer() throws MalformedURLException {
		//Set Capabilities
		cap = new DesiredCapabilities();
		cap.setCapability("noReset", "false");
	
		
		//Start the server with the builder
		AppiumServiceBuilder builder = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\nilesh\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.withArgument(GeneralServerFlag.LOG_LEVEL, "info")
				.usingAnyFreePort();
		
		service = builder.build();
		service.start();
		String appiumServiceUrl = service.getUrl().toString();
	    System.out.println("Appium Service Address : - "+ appiumServiceUrl);
	    
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("appium-version", "8.1.0");
	    capabilities.setCapability("platformName", "android");
	    capabilities.setCapability("deviceName", "vivaan");
	    capabilities.setCapability(CapabilityType.VERSION, "8.1.0");
	    capabilities.setCapability("appPackage", "com.ebay.mobile");
	    capabilities.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");

	    // Set job name
	    //capabilities.setCapability("name", "Appium Sample" + date);
	    //String appPath = System.getenv("android_app_path");
	    //assert appPath != null: "Path to Android app is not set";
	    //System.out.println("Android App path: "+ appPath);
	    //capabilities.setCapability("app", appPath);
	    AndroidDriver driver = new AndroidDriver(new URL(appiumServiceUrl), capabilities);

	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void stopServer() {
		service.stop();
	}

	public boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}	

	public static void main(String[] args) throws MalformedURLException {
		AppiumServerJava appiumServer = new AppiumServerJava();
		
		int port = 4723;
		if(!appiumServer.checkIfServerIsRunnning(port)) {
			appiumServer.startServer();
			appiumServer.stopServer();
		} else {
			System.out.println("Appium Server already running on Port - " + port);
		}
	}
}