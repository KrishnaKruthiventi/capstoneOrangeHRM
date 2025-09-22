package tests;

import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import initialization.ConfigManager;
import initialization.DriverManager;
import pages.AdminPage;
import utilities.LoggerUtil;

public class AdminPageTest {
	
	@BeforeClass
	public void initialSetup() {
		LoggerUtil.info("Setting up test...");
		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.getImplicitWait()));
		LoginTest loginTest = new LoginTest();
		loginTest.testLoginPage();
	}
	
	@AfterClass
	public void tearDown() {
		DriverManager.quitDriver();
		LoggerUtil.info("Finishing tests...");
	}
	
	@Test
	public void addUserFunctionality() throws InterruptedException {
		AdminPage adminPage = new AdminPage();
		adminPage.goToAdminPage();
		adminPage.addUser("James", "James", "JamesButler1"); // Verify user management functionality
		Thread.sleep(2000);
	}
	
	@Test
	public void removeUser() throws InterruptedException {
		AdminPage adminPage = new AdminPage();
		adminPage.goToAdminPage();
		addUserFunctionality();
		adminPage.goToAdminPage();
		adminPage.removeUser("James"); // Verify user management functionality
		Thread.sleep(2000);
	}
	
	@Test
	public void checkJobTitles(){
		AdminPage adminPage = new AdminPage();
		adminPage.goToAdminPage();
		adminPage.jobTitlesManagement(); // Verify job titles management
	}
	
	@Test
	@Parameters({"jobTitle"})
	public void addAndRemoveJobTitle(String jobTitle) throws InterruptedException {
		AdminPage adminPage = new AdminPage();
		adminPage.goToAdminPage();
		adminPage.addJobRole(jobTitle);
		adminPage.deleteJobRole(jobTitle); // Verify job titles management
		Thread.sleep(2000);
	}
	
	@Test
	public void checkPayGrades() {
		AdminPage adminPage = new AdminPage();
		adminPage.goToAdminPage();
		adminPage.payGradesManagement(); // Verify pay grades management
	}
	
	@DataProvider(name = "payGradeData")
	public Object[][] payGradeDataProvider(){
		return new Object[][] {
			{"Grade 6"},
			{"Grade 7"},
			{"Grade 8"}
		};
	}
	
	@Test(dataProvider = "payGradeData")
	public void addAndRemovePayGrade(String payGrade) throws InterruptedException {
		AdminPage adminPage = new AdminPage();
		adminPage.goToAdminPage();
		adminPage.addJobGrade(payGrade);
		adminPage.deleteJobGrade(payGrade); //Verify pay grades management
		Thread.sleep(2000);
	}
	
	@Test
	public void checkEmployementStatus() {
		AdminPage adminPage = new AdminPage();
		adminPage.goToAdminPage();
		adminPage.employementStatusManagement();; // Verify employment status management
	}
	
	@Test
	@Parameters({"employmentStatus"})
	public void addAndRemoveEmployementStatus(String employmentStatus) throws InterruptedException {
		AdminPage adminPage = new AdminPage();
		adminPage.goToAdminPage();
		adminPage.addEmployementStatus(employmentStatus);
		adminPage.deleteEmployementStatus(employmentStatus); // Verify employment status management
		Thread.sleep(2000);
	}
	
	@Test
	public void getOrganizationStructure() {
		AdminPage adminPage = new AdminPage();
		adminPage.goToAdminPage();
//		adminPage.checkOrganizationStructure();
		adminPage.printFullOrgTree();
	}
}
