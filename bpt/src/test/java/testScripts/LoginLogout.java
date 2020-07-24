package testScripts;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;






import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testbase.TestBase;




public class LoginLogout extends TestBase{
	
	HomePage homep;
	LoginPage loginpage;


	
	@DataProvider
	public Object[][] testData(){
		String[][] data = getExcelData("Login.xlsx", "Credentials");
		return data;
	}
	@BeforeTest
	public void setExtent(){
		rep = new ExtentHtmlReporter("./Extentreports/reportstest.html");
		rep.config().setDocumentTitle("Automation Report");
		rep.config().setReportName("Functional Testing"); 
		  rep.config().setTheme(Theme.DARK);
		  
		  ext = new ExtentReports();
		  ext.attachReporter(rep);
		  
		  driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get("http://training.openspan.com/");
			implicitWait(20);
			loginpage = new LoginPage(driver);
			homep = new HomePage(driver);
	}
	
//	@BeforeTest
//	public void beforeTest()  {
//		
////		getBrowser("chrome");
//		
//	}
	
	@Test(dataProvider = "testData")
	public void logintestandLogout(String username,String password) throws IOException{
	
      logger =  ext.createTest(username+" login test");

			loginpage.enterUnPwd(username, password);
			getScreenshot(username);
			homep.verifyUserandSignOut();

	}
	
	@AfterTest
	public void endReport(){
		ext.flush();
		driver.quit();
	}

	
	
}
