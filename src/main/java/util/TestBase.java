package util;


import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;


public class TestBase {
	
	@SuppressWarnings("rawtypes")
	public static AppiumDriver driver;
	public static int count = -1;
	public static int searchCount = 0;
	public static boolean skip = false;
	public static boolean fail = false;

	public static XlsReader AAxls = null;
	public static String sheetName = "Amazon AUS";
	public static boolean isInitialized = false;
	public static String Excelpath = "";
	public static XlsReader inputdata;
	public static String Errormessage = "";
	public static String EmailID = "";
	public static String CreatedPassword = "";
	
	public void initialize() throws Exception {

		if (!isInitialized) {

				AAxls = new XlsReader(System.getProperty("user.dir") + "//src//main//java//excels//TestData.xlsx");
				Excelpath = System.getProperty("user.dir") + "//src//main//java//excels//TestData.xlsx";	
			} else {
				System.out.println("Excel is not Configured");
			}
			isInitialized = true;
			try {
				FileUtils.cleanDirectory(new File(System.getProperty("user.dir")+"//src//testsnaps//"));
			}catch(Exception e) {
	
			}

		}
	
	 public WebDriver getDriver() {
	        return driver;
	    }

}
