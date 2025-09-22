package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import initialization.DriverManager;
import utilities.LoggerUtil;
import utilities.WaitUtils;

public class BasePage {
	
	protected WebDriver driver;
	
	public BasePage() {
		this.driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	protected void click(WebElement element, String elementName) {
		try {
			WaitUtils.waitForElementToBeClickable(driver, element);
			element.click();
			LoggerUtil.info("Clicked element: " + elementName);
		} catch(Exception e) {
			LoggerUtil.error("Failed to click element: " + elementName);
			throw e;
		}
	}
	
	protected void type(WebElement element, String text, String elementName) {
		try {
			WaitUtils.waitForElementToBeVisible(driver, element);
			element.clear();
			element.sendKeys(text);
			LoggerUtil.info("Successfully typed value as: " + text + " to element " + elementName);
		} catch(Exception e) {
			LoggerUtil.error("Failed to type: " + text + " to element " + elementName);
			throw e;
		}
	}
	
	protected String getText(WebElement element, String elementName) {
		try {
			WaitUtils.waitForElementToBeVisible(driver, element);
			String text = element.getText();
			LoggerUtil.info("Successfully read the value as: " + text + " from element " + elementName);
			return text;
		} catch(Exception e) {
			LoggerUtil.error("Failed to read the value from the element: " + elementName);
			throw e;
		}
	}
	
	protected boolean isElementDisplayed(WebElement element, String elementName) {
		try {
			WaitUtils.waitForElementToBeVisible(driver, element);
			boolean isDisplayed = element.isDisplayed();
			LoggerUtil.info("Successfully displayed element: " + elementName);
			return isDisplayed;
		} catch(Exception e) {
			LoggerUtil.error("Element not displayed: " + elementName);
			throw e;
		}
	}
}
