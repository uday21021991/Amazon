package tests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.HomePage;
import pages.InvokeApplication;
import pages.ProductListPage;
import util.TestBase;
import util.TestUtil;
import util.XlsReader;
import extentReports.ExtentManager;
import extentReports.ExtentTestManager;



public class Orderproducts extends TestBase{
	
	@BeforeTest
	public void checkTestSkip() throws Exception {
		count = -1;
		initialize();
		if (!TestUtil.isTestCaseRunnable(AAxls, sheetName)) {
			throw new SkipException("Skipping Test Case AOT as runmode set to NO");// reports
		}
	}
	
	@Test(dataProvider = "getTestData")
	public void orderProducts(XlsReader exceldata) throws Exception {
		inputdata = exceldata;
		count++;
		
		ExtentTestManager.startTest(TestUtil.getdata("Scenario"), TestUtil.getdata("Scenario"));
		
		if (!TestUtil.getdata("RunMode").equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no " + count);
		}

		System.out.println("-----------scenario " + TestUtil.getdata("Scenario") + " is started-------------");
		
		switch (TestUtil.getdata("Iteration")) {

		case "1":
			InvokeApplication.invokeapp();
			new HomePage().chrngeCuntry();
			new ProductListPage().searchProduct(TestUtil.getdata("ProductName"));
			new ProductListPage().addtoCart();
			new ProductListPage().updateQty(TestUtil.getdata("Product QTY"));
			new ProductListPage().buyproduct();
			new HomePage().login(TestUtil.getdata("username"),TestUtil.getdata("password"));

			break;
			
		case "2":
			InvokeApplication.invokeapp();
			new HomePage().chrngeCuntry();
			new ProductListPage().searchProduct(TestUtil.getdata("ProductName"));
			new ProductListPage().addtoCart();
			new ProductListPage().updateQty(TestUtil.getdata("Product QTY"));
			new ProductListPage().buyproduct();
			new HomePage().login(TestUtil.getdata("username"),TestUtil.getdata("password"));

			break;
			
		case "3":
			InvokeApplication.invokeapp();
			new HomePage().chrngeCuntry();
			new ProductListPage().searchProduct(TestUtil.getdata("ProductName"));
			new ProductListPage().addtoCart();
			new ProductListPage().updateQty(TestUtil.getdata("Product QTY"));
			new ProductListPage().deleteProduct();

			break;
			
		default:
			System.out.println("Given Scenario is not avilable in lit");
			break;
		
		}
		
		System.out.println("-----------scenario " + TestUtil.getdata("Scenario") + " is Ended-------------");
	}
	
	@AfterMethod
	public void reportDataSetResult() throws UnsupportedEncodingException {
		
		if (skip) {
			TestUtil.reportDataSetResult(AAxls, sheetName, count + 2, "SKIP", "");
		 	ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
		} 
		else if (fail) {
			TestUtil.reportDataSetResult(AAxls, sheetName, count + 2, "FAIL", Errormessage);
			String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).
		            getScreenshotAs(OutputType.BASE64);

		        //ExtentReports log and screenshot operations for failed tests.
		        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
		            ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		} 
		else {
			TestUtil.reportDataSetResult(AAxls, sheetName, count + 2, "PASS", "");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
		}
		
		try {
			driver.quit();
		}catch(Exception e) {}

		ExtentTestManager.endTest();
        
		skip = false;
		fail=false;
	}

	@AfterTest
	public void endtest() {
		ExtentManager.getReporter().flush();
	}

	@DataProvider
	public static Iterator<Object[]> getTestData() throws IOException {

		ArrayList<Object[]> data = new ArrayList<Object[]>();
		System.out.println("number of rows "+ AAxls.getRowCount(sheetName));
		for (int i = 1; i < AAxls.getRowCount(sheetName); i++) {
			data.add(new Object[] { new XlsReader(Excelpath, sheetName, i) });
		}
		return data.iterator();
	}

}
