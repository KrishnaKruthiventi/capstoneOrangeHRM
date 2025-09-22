package tests;

import java.time.Duration;
import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import initialization.ConfigManager;
import initialization.DriverManager;
import pages.PIMPage;
import utilities.LoggerUtil;

public class PIMPageTest {
	
	@BeforeMethod(alwaysRun = true)
	public void initialSetup() {
		LoggerUtil.info("Setting up test...");
		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.getImplicitWait()));
		LoginTest loginTest = new LoginTest();
		loginTest.testLoginPage();
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		DriverManager.quitDriver();
		LoggerUtil.info("Finishing tests...");
	}
	
	@Test(groups = "addAndDeleteEmployee")
	public void addAndDeleteEmployeeFunctionality() throws InterruptedException {
		PIMPage pimPage = new PIMPage();
		pimPage.goToPIMPage();
		pimPage.clickAddEmployeeBtn();
		Random rand = new Random();
		int min = 1000, max = 9999;
		int randomNum = rand.nextInt(max - min + 1) + min;
		String employeeId = String.valueOf(randomNum);
		pimPage.addEmployeeDetails("First", "Middle", "Last", employeeId, "fimila", "Password@1"); // Verify adding new employee
		pimPage.goToPIMPage();
		pimPage.deleteEmployee("First");
		Thread.sleep(2000);
	}
	
	@Test(groups = "employeeSearch")
	public void verifyEmployeeSearch() throws InterruptedException {
		PIMPage pimPage = new PIMPage();
		pimPage.goToPIMPage();
		pimPage.employeeSearch("J"); // Verify employee search functionality & Verify employee list view
	}
}
