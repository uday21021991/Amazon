package pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import util.TestBase;

public class InvokeApplication extends TestBase{
	
	@SuppressWarnings("rawtypes")
	public static void invokeapp() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "HNB2QS8W");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "8.0");
		caps.setCapability("app", "C:\\Users\\uday\\Downloads\\Amazon_shopping.apk");
//		caps.setCapability("appPackage", "com.amazon.mShop.android.shopping");
//		caps.setCapability("appActivity", "com.amazon.mShop.home.web.MShopWebGatewayActivity");
//		caps.setCapability("fullReset", true);
		
		try {
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		
	} catch (MalformedURLException e) {
		System.out.println(e.getMessage());
	}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
	}

}
