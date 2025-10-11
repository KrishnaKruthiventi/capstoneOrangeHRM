package tests;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import initialization.ConfigManager;
import initialization.DriverManager;
import pages.LoginPage;
import utilities.LoggerUtil;

public class LoginTest {
	
	@BeforeMethod(alwaysRun = true)
	public void initialSetup() {
		LoggerUtil.info("Setting up test...");
		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.getImplicitWait()));
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		DriverManager.quitDriver();
		LoggerUtil.info("Finishing tests...");
	}
	
	@Test
	public void testLoginPage() {
		LoginPage loginPage = new LoginPage();
		loginPage.navigateToLoginPage();
		String userName;
		String passWord;
		
		String userNeed = "logout";
		
		switch (userNeed) {
		case "correct": // Verify successful login with valid credentials
			userName = ConfigManager.getValidUsername();
			passWord = ConfigManager.getValidPassword();
			loginPage.login(userName, passWord);
//			Assert.assertEquals(loginPage.getURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
			break;
		
		case "incorrectUserName": // Verify login with invalid username
			userName = ConfigManager.getProperty("invalid.username1");
			passWord = ConfigManager.getValidPassword();
			loginPage.invalidUsername(userName, passWord);
			Assert.assertEquals(loginPage.getURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			break;
			
		case "incorrectPassword": // Verify login with invalid password
			userName = ConfigManager.getValidUsername();
			passWord = ConfigManager.getProperty("invalid.password1");
			loginPage.invalidPassword(userName, passWord);
			Assert.assertEquals(loginPage.getURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			break;
			
		case "blankSpaces": // Verify login with empty credentials
			userName = ConfigManager.getProperty("invalid.username2");
			passWord = ConfigManager.getProperty("invalid.password2");
			loginPage.blankUserNameAndBlankPassword(userName, passWord);
			Assert.assertEquals(loginPage.getURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			break;
			
		case "logout": // Verify logout functionality
			userName = ConfigManager.getValidUsername();
			passWord = ConfigManager.getValidPassword();
			loginPage.login(userName, passWord);
			Assert.assertEquals(loginPage.getURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
			loginPage.logout();
			Assert.assertEquals(loginPage.getURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		}
		
	}
	
	
}
