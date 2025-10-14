package initialization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private DriverManager() {}
	
	public static WebDriver getDriver() {
		if(driver.get() == null) {
			String browser = ConfigManager.getBrowser(0,0);
			
			switch(browser) {
			
			case "chrome": // For chrome browser
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--disable-notifications");
				chromeOptions.addArguments("--start-maximized");
				WebDriver webDriver = new ChromeDriver(chromeOptions);
				driver.set(webDriver);
				break;
				
			case "firefox": // For firefox browser
				WebDriverManager.firefoxdriver().setup();
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("dom.webnotifications.enabled", false);
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setProfile(profile);
				webDriver = new FirefoxDriver(firefoxOptions);
//				firefoxOptions.addArguments("--start-maximized");
				webDriver.manage().window().maximize();
				driver.set(webDriver);
				break;
				
			default:
				throw new IllegalArgumentException("Unsupported Browser: " + browser);
			}
		}
		
		return driver.get();
	}
	
	
	public static void quitDriver() {
		if(driver.get() != null) {
			driver.get().quit();
			driver.remove();
			driver = null;
		}
	}
}
