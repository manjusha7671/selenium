package testbase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.google.common.io.Files;
//import testScripts.LoginLogout;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentReports;

import excel.ExcelReadW;



public class TestBase {
	public static Logger log = Logger.getLogger(TestBase.class);
	public WebDriver driver;
	public  ExtentReports ext;
	public ExtentHtmlReporter rep;
	public ExtentTest logger;
	public String username;
	
	
	public String[][] getExcelData(String excelName, String sheetName) {

		String excellocation = System.getProperty("user.dir")+"\\excel\\" + excelName;
		ExcelReadW readDataFromExcelSheet = new ExcelReadW();
		return readDataFromExcelSheet.getExcelData(excellocation, sheetName);
	}

	
	
	
	
	@AfterMethod
	 public void tearDown(ITestResult result) throws IOException {
	  if (result.getStatus() == ITestResult.FAILURE) {
		  logger.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
		  logger.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
	   String screenshotPath = System.getProperty("user.dir")+"\\screenshots\\"+username+".png";
	   logger.addScreenCaptureFromPath(screenshotPath);// adding screen shot
	  } else if (result.getStatus() == ITestResult.SKIP) {
		  logger.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
	  }
	  else if (result.getStatus() == ITestResult.SUCCESS) {
		  logger.log(Status.PASS, "Test Case PASSED IS " + result.getName());
	  }
	  
	 }
	

	
	
//	public WebElement waitForElement(WebDriver driver, long time,
//			WebElement element) {
//		WebDriverWait wait = new WebDriverWait(driver, time);
//		return wait.until(ExpectedConditions.elementToBeClickable(element));
//
//	}
	
	
//	public WebElement waitForElementWithPollingInterval(WebDriver driver,
//			long time, WebElement element) {
//		WebDriverWait wait = new WebDriverWait(driver, time);
//		wait.pollingEvery(5, TimeUnit.SECONDS);
//		wait.ignoring(NoSuchElementException.class);
//		return wait.until(ExpectedConditions.elementToBeClickable(element));
//
//	}

	public void implicitWait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public WebDriver getBrowser(String browser) {
		
			if (browser.equalsIgnoreCase("chrome")) {
					System.getProperty("Webdriver.gecko.driver",System.getProperty("user.dir")+"/drivers/chrome.exe");
				driver = new ChromeDriver();
				
			} else {
				System.getProperty("Webdriver.gecko.driver",System.getProperty("user.dir")+"");
				 driver = new FirefoxDriver();
				
			}
		
		return null;
	}
	
	public String getScreenshot(String imageName) throws IOException{
		if(imageName.equals("")){
			imageName="blank";
		}
		File image =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imagelocation = System.getProperty("user.dir")+"/screenshots/";
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		String actualImageName = imagelocation+imageName+".png";
		File destFile = new File(actualImageName);
		Files.copy(image, destFile);
		username = imageName+".png";
		return actualImageName;
				
	}
	
	
	
	

	




	
	
	
	
}
