package tests;

import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import initialization.ConfigManager;
import initialization.DriverManager;
import pages.LeavePage;
import utilities.LoggerUtil;

public class LeavePageTest {
	
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
	
	@Test(groups = "applyLeave")
	public void applyLeaveTest() throws InterruptedException {
		LeavePage leavePage = new LeavePage();
		leavePage.goToLeavePage();
		leavePage.applyLeave(); // Verify apply leave functionality
	}
	
	@Test(groups = "myLeave")
	public void myLeaveList() throws InterruptedException {
		LeavePage leavePage = new LeavePage();
		leavePage.goToLeavePage();
		leavePage.myLeave("2025-01-09", "2025-31-12"); // Verify my leave list
	}
	
	@Test(groups = "entitlements")
	public void addEntitlementTest() throws InterruptedException {
		LeavePage leavePage = new LeavePage();
		leavePage.goToLeavePage();
		leavePage.addEntitlement("J", "5.00"); // Verify leave entitlements
		Thread.sleep(5000);
	}
	
	@Test(groups = "entitlements")
	public void employeeEntitlementsTest() throws InterruptedException {
		LeavePage leavePage = new LeavePage();
		leavePage.goToLeavePage();
		leavePage.verifyEmployeeEntitlements("J"); // Verify leave entitlements
		Thread.sleep(5000);
	}
	
	@Test(groups = "entitlements")
	public void myEntitlementsTest() throws InterruptedException {
		LeavePage leavePage = new LeavePage();
		leavePage.goToLeavePage();
		leavePage.checkMyEntitlements(); // Verify leave entitlements
		Thread.sleep(2000);
	}
	
	@Test(groups = "leaveReports")
	public void getLeaveEntitlementsAndUsageReport() throws InterruptedException {
		LeavePage leavePage = new LeavePage();
		leavePage.goToLeavePage();
		leavePage.leaveEntitlementsAndUsageReport("J"); // Verify leave reports
		Thread.sleep(5000);
	}
	
	@Test(groups = "leaveReports")
	public void getMyLeaveEntitlementsAndUsageReport() throws InterruptedException {
		LeavePage leavePage = new LeavePage();
		leavePage.goToLeavePage();
		leavePage.myLeaveEntitlementsAndUsageReport(); // Verify leave reports
		Thread.sleep(5000);
	}
	
	@Test(groups = "assignLeave")
	public void assignLeaveTest() throws InterruptedException {
		LeavePage leavePage = new LeavePage();
		leavePage.goToLeavePage();
		leavePage.assignLeave("J", "2025-15-09"); // Verify leave assign functionality
		Thread.sleep(2000);
	}
}
