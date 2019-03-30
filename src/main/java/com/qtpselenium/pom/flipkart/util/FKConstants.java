package com.qtpselenium.pom.flipkart.util;

public class FKConstants {

	// App parameters
	public static final String APK_PATH = System.getProperty("user.dir")+"\\apk\\Flipkart_6.11.apk";
	public static final String DEVICE_NAME ="vivaan"; //XT1033
	public static final String DEVICE_VERSION = "8.1.0";
	public static final String PLATFORM = "Android";
	//http://localhost:4444/wd/hub
	public static final String HUB_URL = "http://127.0.0.1:4723/wd/hub";
	
	public static final String REPORT_PATH = "D:\\report\\";
	public static final String XLS_PATH = System.getProperty("user.dir")+"//data//TestData.xlsx";

	// elements
	public static final String NAVIGATION_MENU_IMAGE = "//android.widget.ImageButton[@content-desc='Open Drawer']";
	public static final String RIGHT_MOBILE = "//android.widget.TextView[@text='Find the right Mobile']";
	public static final String TOP_HEADING = "com.flipkart.android:id/filter_widget_title_text";
	public static final String BRAND = "//android.widget.TextView[@text='BRAND']";
	public static final String OS_HEADING = "//android.widget.TextView[@text='OPERATING SYSTEM']";
	public static final String APPLY_SEARCH_PATH ="//android.widget.Button[@text='APPLY AND SEARCH']";
	public static final String PROD_NAMES = "com.flipkart.android:id/product_list_product_item_main_text";
	public static final String PROD_PRICES = "com.flipkart.android:id/product_list_product_item_price";
	public static final String SEARCH_FIELD1 = "com.flipkart.android:id/search_widget_textbox";
	public static final String SEARCH_FIELD2 = "com.flipkart.android:id/search_autoCompleteTextView";
	public static final String MOBILE_NO = "//android.widget.EditText[contains(@resource-id,'mobileNo')]";
	public static final String SIGN_UP = "//android.widget.Button[contains(@resource-id,'btn_msignup')]";
	
	public static final String SCREENSHOT_PATH = "F:\\screenshots\\";
	
	

}
