package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import initialization.ConfigManager;
import utilities.LoggerUtil;

public class LoginPage extends BasePage{
	
	@FindBy(name = "username")
	private WebElement username; // Username field in the login page
	
	@FindBy(name = "password")
	private WebElement password; // Password field in the login page
	
	@FindBy(tagName = "button")
	private WebElement loginBtn; // Login Button in the login page
	
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	private WebElement errMsg; // Error message which comes after entering invalid details
	
	@FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'])[1]")
	private WebElement userNameErrMsg; // username error msg when we didn't give input
	
	@FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'])[2]")
	private WebElement passwordErrMsg; // password error msg when we didn't give input
	
	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
	private WebElement userDropDown; // dropdown for clicking logout btn
	
	@FindBy(xpath = "//a[@href='/web/index.php/auth/logout']")
	private WebElement logoutBtn; // logout button which appears after clicking dropdown
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getURL() {
		return driver.getCurrentUrl();
	}
	
	public void navigateToLoginPage() {
		driver.get(ConfigManager.getBaseUrl());
		LoggerUtil.info("Navigated to website: " + getTitle());
	}
	
	public void login(String userName, String passWord) {
		type(username, userName, "UserName");
		type(password, passWord, "PassWord");
		click(loginBtn, "Login Button");
	}
	
	public void invalidUsername(String userName, String passWord) {
		login(userName, passWord);
		getText(errMsg, "Error Message");
	}
	
	public void invalidPassword(String userName, String passWord) {
		login(userName, passWord);
		getText(errMsg, "Error Message");
	}
	
	public void blankUserNameAndBlankPassword(String userName, String passWord) {
		login(userName, passWord);
		getText(userNameErrMsg, "Blank UserName Message");
		getText(passwordErrMsg, "Blank Password Message");
	}
	
	public void logout() {
		click(userDropDown, "User DropDown");
		click(logoutBtn, "Logout Btn");
	}
}
