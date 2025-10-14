package utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import initialization.ConfigManager;

public class WaitUtils {

	protected static WebDriverWait wait;
	
	public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getExplicitWait()));
		wait.until(ExpectedConditions.visibilityOf(element));
		LoggerUtil.trace("Element got visible!!");
	}
	
	public static void waitForAllElementsToBeVisible(WebDriver driver, List<WebElement> elements) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getExplicitWait()));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		LoggerUtil.trace("All Elements were loaded!!");
	}
	
	public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getExplicitWait()));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		LoggerUtil.trace("Element got clicked!!");
	}
	
	public static void waitForTextToBePresentInElement(WebDriver driver, WebElement element, String text) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getExplicitWait()));
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		LoggerUtil.trace("Element got displayed!!");
	}
	
	public static void waitForAlert(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getExplicitWait()));
		wait.until(ExpectedConditions.alertIsPresent());
		LoggerUtil.trace("Alert got displayed!!");
	}
}
