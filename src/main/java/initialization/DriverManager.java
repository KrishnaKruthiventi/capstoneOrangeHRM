package initialization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal();
	private DriverManager() {}
	
	public static WebDriver getDriver() {
		if(driver.get() == null) {
			String browser = ConfigManager.getBrowser(0,0);
			
			switch(browser) {
			
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--disable-notifications");
				chromeOptions.addArguments("--start-maximized");
				WebDriver webDriver = new ChromeDriver(chromeOptions);
				driver.set(webDriver);
				break;
				
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--disable-notifications");
				firefoxOptions.addArguments("--start-maximized");
				webDriver = new FirefoxDriver(firefoxOptions);
				driver.set(webDriver);
				break;
				
			case "edge":
				WebDriverManager.edgedriver().setup();
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--disable-notifications");
				edgeOptions.addArguments("--start-maximized");
				webDriver = new EdgeDriver(edgeOptions);
				driver.set(webDriver);
				break;
				
			default:
				throw new IllegalArgumentException("Unsupported Browser: " + browser);
			}
		}
		
		return driver.get();
	}
	
	
	public static void quitDriver() {
		if(driver != null) {
			driver.get().quit();
			driver = null;
		}
	}
}
