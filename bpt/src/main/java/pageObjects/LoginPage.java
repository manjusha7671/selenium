package pageObjects;

import java.io.IOException;



import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class LoginPage {
 static WebDriver driver;
	
	private static Logger  log = Logger.getLogger(LoginPage.class);
	
	@FindBy(id = "user_name")
	WebElement  userName;
	@FindBy(id = "user_pass")
	WebElement  password;
	@FindBy(id = "login_button")
	WebElement  signin;
	
	public LoginPage(WebDriver driver){
		
		this.driver= driver;
		PageFactory.initElements(driver, this);		
	}
	
	public  HomePage enterUnPwd(String usern,String pwd){
		log.info("Entering UserName");
		userName.sendKeys(usern);
		password.sendKeys(pwd);
		signin.click();
		return new HomePage(driver);
		
	}
	


}
