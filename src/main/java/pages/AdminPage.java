package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.LoggerUtil;
import utilities.WaitUtils;

public class AdminPage extends BasePage{
	
	@FindBy(xpath = "//a[@href='/web/index.php/admin/viewAdminModule']")
	private WebElement adminPage; // Admin which is located on the left side
	
	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
	private WebElement addUserBtn; // Add user button present in the page after opening Admin page
	
	@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
	private WebElement userRoleBtn; // User Role drop down which is visible after clicking Add button 
	
	@FindBy(xpath = "(//div[@class='oxd-select-option'])[2]")
	private WebElement adminRole; // Admin option present in the dropdown
	
	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	private WebElement employeeName; // Employee name field in the same page
	
	@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
	private WebElement statusBtn; // Status drop down which is visible after clicking Add button 
	
	@FindBy(xpath = "(//div[@class='oxd-select-option'])[2]")
	private WebElement enabledBtn; // Enabled option present in the dropdown
	
	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	private WebElement userNameField; // user name field 
	
	@FindBy(xpath = "(//div[@class='oxd-autocomplete-option'])[1]")
	private WebElement userName; // username selection from the dropdown
	
	@FindBy(xpath = "(//input[@type='password'])[1]")
	private WebElement passwordField; // password field in the same page
	
	@FindBy(xpath = "(//input[@type='password'])[2]")
	private WebElement confirmPasswordField; // confirm password field in the same page
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveBtn; // Save btn 
	
	@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
	private WebElement userRoleField; // user role select option in user management direct page
	
	@FindBy(xpath = "(//div[@class='oxd-select-option'])[2]")
	private WebElement userRole; // option selecting from the drop down options
	
	@FindBy(xpath = "(//button[@type='button'])[7]")
	private WebElement deleteBtn; // Delete icon present in the records of users
	
	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")
	private WebElement confirmDeleteBtn; // confirm delete button which appears after clicking delete icon
	
	@FindBy(xpath = "(//span[@class='oxd-topbar-body-nav-tab-item'])[2]")
	private WebElement jobsBtn; // Jobs Dropdown button available in Admin Page
	
	@FindBy(xpath = "//a[contains(text(), 'Job Titles')]")
	private WebElement jobTitlesBtn; // Job Titles button which will be visible after clicking Job Dropdown
	
	@FindBy(css = "div[data-v-6c07a142]")
	private List<WebElement> job_pay_employementTitles; // Available Job Roles / pay grades / Employement status  
	
	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
	private WebElement job_pay_employementTitleAddBtn; // Add Job Title / Pay Grade / Employee Status Button which is located in Job Titles page
	
	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	private WebElement job_pay_employementTitleInputField; // Input field for Job Title / Pay Grade / Employee Status
	
	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
	private WebElement job_pay_employementTitleSaveBtn; //Save button present in New Job Title / New Pay grade / Employee Status creation page
	
	@FindBy(xpath = "//a[contains(text(), 'Pay Grades')]")
	private WebElement payGradesBtn; // Pay Grades button which will be visible after clicking Job Dropdown
	
	@FindBy(xpath = "//a[contains(text(), 'Employment Status')]")
	private WebElement employementStatusBtn; // Employement status button which will be visible after clicking Job Dropdown
	
	@FindBy(xpath = "//span[contains(text(), 'Organization ')]")
	private WebElement organizationBtn; // Organization button present in the Admin page
	
	@FindBy(xpath = "//a[contains(text(), 'Structure')]")
	private WebElement structureBtn; // Structure Button present in the organization drop down
	
	@FindBy(xpath = "//button[@class='oxd-icon-button']")
	private List<WebElement> buttons; // expand buttons present in the structure page 
	
	@FindBy(xpath = "//div[@class='org-name']")
	private List<WebElement> structureElements; // Structure of the organization
	
	@FindBy(css = "div.org-container > ul.oxd-tree-view")
	private WebElement rootUl; // Tree view
	
	@FindBy(xpath = "./li")
	private List<WebElement> nodes; //  List elements for structure
	
	
	public void goToAdminPage() {
		click(adminPage, "Admin");
	}
	
	public void addUser(String employee, String user, String password) throws InterruptedException {
		click(addUserBtn, "Add Button");
		click(userRoleBtn, "User Role");
		click(adminRole, "Admin");
		type(employeeName, employee, "Employee");
		Thread.sleep(2000);
		click(userName, "User");
		click(statusBtn, "Status");
		click(enabledBtn, "Enabled");
		type(userNameField, user, "UserName");
		type(passwordField, password, "Password");
		type(confirmPasswordField, password, "Confirm Password");
		click(saveBtn, "Save");
		Thread.sleep(2000);
	}
	
	public void removeUser(String user) throws InterruptedException {
		type(userNameField, user, "User Name");
		click(userRoleField, "User");
		click(userRole, "User Role");
		click(saveBtn, "Search");
		Thread.sleep(2000);
		click(deleteBtn, "Delete User");
		click(confirmDeleteBtn, "Delete");
	}
	
	public void jobTitlesManagement(){
		click(jobsBtn, "Jobs");
		click(jobTitlesBtn, "Job Titles");
		WaitUtils.waitForAllElementsToBeVisible(driver, job_pay_employementTitles);
		for(WebElement jobTitle: job_pay_employementTitles) {
			LoggerUtil.info(jobTitle.getText());
		}
	}
	
	public void addJobRole(String role) throws InterruptedException {
		click(jobsBtn, "Jobs");
		click(jobTitlesBtn, "Job Titles");
		click(job_pay_employementTitleAddBtn, "Add Job");
		type(job_pay_employementTitleInputField, role, "New Job Role");
		Thread.sleep(2000);
		click(job_pay_employementTitleSaveBtn, "Save");
	}
	
	public void deleteJobRole(String role) throws InterruptedException {
		WaitUtils.waitForAllElementsToBeVisible(driver, job_pay_employementTitles);
		int count = 1;
		for(WebElement jobTitle: job_pay_employementTitles) {
			if(jobTitle.getText().equals(role)) {
				LoggerUtil.info("Deleting the value: " + jobTitle.getText());
				WebElement element = driver.findElement(By.xpath("(//i[@class='oxd-icon bi-trash'])" + "[" + count + "]"));
				click(element, "Delete");
				Thread.sleep(2000);
				click(confirmDeleteBtn, "Delete");
				break;
			}
			count++;
		}
	}
	
	public void payGradesManagement() {
		click(jobsBtn, "Jobs");
		click(payGradesBtn, "Pay Grades");
//		WaitUtils.waitForAllElementsToBeVisible(driver, payGrades);
		for(WebElement grade: job_pay_employementTitles) {
			if(grade.getText().startsWith("Grade")) {
				LoggerUtil.info(grade.getText());
			}
		}
	}
	
	public void addJobGrade(String grade) throws InterruptedException {
		click(jobsBtn, "Jobs");
		click(payGradesBtn, "Pay Grades");
		click(job_pay_employementTitleAddBtn, "Add Pay Grade");
		type(job_pay_employementTitleInputField, grade, "New Pay Grade");
		Thread.sleep(2000);
		click(job_pay_employementTitleSaveBtn, "Save");
	}
	
	public void deleteJobGrade(String grade) throws InterruptedException {
		click(jobsBtn, "Jobs");
		click(payGradesBtn, "Pay Grades");
//		WaitUtils.waitForAllElementsToBeVisible(driver, payGrades);
		int count = 1;
		for(WebElement payGrade: job_pay_employementTitles) {
			if(payGrade.getText().equals(grade)) {
				LoggerUtil.info("Deleting the value: " + payGrade.getText());
				WebElement element = driver.findElement(By.xpath("(//i[@class='oxd-icon bi-trash'])" + "[" + count + "]"));
				click(element, "Delete");
				Thread.sleep(2000);
				click(confirmDeleteBtn, "Delete");
				break;
			}
			if(payGrade.getText().startsWith("Grade")) count++;
		}
	}
	
	public void employementStatusManagement(){
		click(jobsBtn, "Jobs");
		click(employementStatusBtn, "Employement Status");
		WaitUtils.waitForAllElementsToBeVisible(driver, job_pay_employementTitles);
		for(WebElement employementStatus: job_pay_employementTitles) {
			LoggerUtil.info(employementStatus.getText());
		}
	}
	
	public void addEmployementStatus(String employementStatus) throws InterruptedException {
		click(jobsBtn, "Jobs");
		click(employementStatusBtn, "Employement Status");
		click(job_pay_employementTitleAddBtn, "Add Employement Status");
		type(job_pay_employementTitleInputField, employementStatus, "New Employement Status");
		Thread.sleep(2000);
		click(job_pay_employementTitleSaveBtn, "Save");
	}
	
	public void deleteEmployementStatus(String employementStatus) throws InterruptedException {
		click(jobsBtn, "Jobs");
		click(employementStatusBtn, "Employement Status");
		WaitUtils.waitForAllElementsToBeVisible(driver, job_pay_employementTitles);
		int count = 1;
		for(WebElement employementStatusRole: job_pay_employementTitles) {
			if(employementStatusRole.getText().equals(employementStatus)) {
				LoggerUtil.info("Deleting the value: " + employementStatusRole.getText());
				WebElement element = driver.findElement(By.xpath("(//i[@class='oxd-icon bi-trash'])" + "[" + count + "]"));
				click(element, "Delete");
				Thread.sleep(2000);
				click(confirmDeleteBtn, "Delete");
				break;
			}
			count++;
		}
	}
	
	public void checkOrganizationStructure() {
		click(organizationBtn, "Organization");
		click(structureBtn, "Structure");
		WaitUtils.waitForAllElementsToBeVisible(driver, structureElements);
		
		int size = buttons.size();
		
		for(int i = 1; i < size; i++) {
			WebElement button = buttons.get(i);
			click(button, "Expand Button");
		}
		
		size = structureElements.size();
		for(int i = 0; i < size; i++) {
			WebElement structure = structureElements.get(i);
			if(i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 9) System.out.println("   " + structure.getText());
			else System.out.println(structure.getText());
		}
		
	}
	
	public void printTree(WebElement rootUl, int level) {
        for (WebElement node : nodes) {
            WebElement nameDiv = node.findElement(By.cssSelector("div.org-name"));
            System.out.println("  ".repeat(level) + "- " + nameDiv.getText().trim());

            List<WebElement> childUls = node.findElements(By.xpath("./ul"));
            if (!childUls.isEmpty()) {
                printTree(childUls.get(0), level + 1);
            }
        }
    }
	
	public void printFullOrgTree() {
		click(organizationBtn, "Organization");
		click(structureBtn, "Structure");
		WaitUtils.waitForAllElementsToBeVisible(driver, structureElements);
		
		int size = buttons.size();
		
		for(int i = 1; i < size; i++) {
			WebElement button = buttons.get(i);
			click(button, "Expand Button");
		}
		
        System.out.println("Organizational Tree:");
        printTree(rootUl, 0);
    }
}
