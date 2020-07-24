package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class HomePage {
//	HomePage homep;
//	LoginPage loginpage;
	

static WebDriver driver;
	
//	private static Logger  log = Logger.getLogger(LoginPage.class);
	
	@FindBy(xpath = "//*[@id='user_information_account']/div[2]/span")
	WebElement  userloggedname;
	
	@FindBy(xpath="//*[@id='profile_menu']/a/img")
	WebElement  settings;
	
	@FindBy(linkText="Sign Out")
	WebElement  signout;
	
	public HomePage(WebDriver driver){
//		super();
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public  LoginPage verifyUserandSignOut  (){
		
		
		System.out.println(userloggedname.getText());
		settings.click();
		
		signout.click();
		
		return new LoginPage(driver);
	      
			}
	
}
