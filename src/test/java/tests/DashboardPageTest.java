package tests;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import initialization.ConfigManager;
import initialization.DriverManager;
import pages.DashboardPage;
import utilities.LoggerUtil;

public class DashboardPageTest {
	
	@BeforeClass(alwaysRun = true)
	public void initialSetup() {
		LoggerUtil.info("Setting up test...");
		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.getImplicitWait()));
		LoginTest loginTest = new LoginTest();
		loginTest.testLoginPage();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		DriverManager.quitDriver();
		LoggerUtil.info("Finishing tests...");
	}
	
	@Test(priority = 1)
	public void seeTheMenu() {
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.displayMenu(); // Checking the Menu after the login
	}
	
	@Test(priority = 2)
	public void getDashBoardItems() {
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.dashboardElements(); // Verify dashboard elements after login
	}
	
	@Test(priority = 3)
	public void getQuickLaunchWidgets() {
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.quickLaunchWidgets(); // Verify quick launch widgets
	}
	
	@Test(priority = 4)
	public void testPrintPieChart() {
		DashboardPage dashboardPage = new DashboardPage();
	    List<Map<String, String>> listDetails = dashboardPage.getPieChartListDetails(); // Verify employee distribution chart

	    for (Map<String, String> slice : listDetails) {
	        LoggerUtil.info("Label: " + slice.get("label") + ", Color: " + slice.get("color"));
	    }
	}

	
	@Test(priority = 6)
	public void checkPendingLeaves() {
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.checkPendingLeaves("2025-01-09", "2025-31-12"); // Verify pending leave requests
	}
	
	@Test(priority = 5)
	public void getDashboardCustomization() {
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.getScreenshot(); // Verify dashboard customization
	}
}
