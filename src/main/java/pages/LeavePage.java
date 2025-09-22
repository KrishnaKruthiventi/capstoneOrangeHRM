package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ScreenshotUtil;
import utilities.WaitUtils;

public class LeavePage extends BasePage {

	@FindBy(xpath = "//a[@href='/web/index.php/leave/viewLeaveModule']")
	private WebElement leavePage; // Leave module located on the left side

	@FindBy(xpath = "//a[contains(text(), 'Apply')]")
	private WebElement applyLeavePage; // Apply leave button located on top of the Leave page

	@FindBy(xpath = "//div[@class='orangehrm-card-container']")
	private WebElement insideApplyLeavePage; // Page after clicking apply leave button

	@FindBy(xpath = "//a[contains(text(), 'My Leave')]")
	private WebElement myLeavePage; // Page after clicking my leave button

	@FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[1]")
	private WebElement fromDateField; // Input field for from date in my leave page

	@FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
	private WebElement toDateField; // Input field for to date in my leave page

	@FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[2]")
	private WebElement leaveType; // Leave Type field in my leave page

	@FindBy(xpath = "(//div[@class='oxd-select-option'])[5]")
	private WebElement leaveField; // Leave selection

	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
	private WebElement searchBtn; // Search button in my leave

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span']")
	private WebElement noOfRecords; // No of Records for my leave in my leave page

	@FindBy(xpath = "//span[contains(text(), 'Entitlements')]")
	private WebElement entitlementsBtn; // Entitlements Button present in Leave Module

	@FindBy(xpath = "//a[contains(text(), 'Add Entitlements')]")
	private WebElement addEntitlementBtn; // Add Entitlement button inside entitlementsBtn

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	private WebElement employeeNameField; // Employee name field in Add Entitlement page

	@FindBy(xpath = "(//div[@class='oxd-autocomplete-option'])[1]")
	private WebElement employeeName; // Employee name selection from dropdown

	@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
	private WebElement leaveTypeField; // leave Type field in Add Entitlement page

	@FindBy(xpath = "//span[contains(text(), 'US - Personal')]")
	private WebElement leaveTypeOption; // options inside leave type

	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	private WebElement entitlementField; // Entitlement field in Add Entitlement page

	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
	private WebElement saveOrSearchBtn; // Save btn in Add Entitlement page

	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-button-margin']")
	private WebElement confirmBtn; // Confirm btn after clicking save button

	@FindBy(xpath = "//a[contains(text(), 'Employee Entitlements')]")
	private WebElement employeeEntitlementsBtn; // Employee Entitlements button inside entitlementsBtn

	@FindBy(xpath = "(//span[@class='oxd-text oxd-text--span'])[2]")
	private WebElement records; // No of records in Employee Entitlements page

	@FindBy(xpath = "//a[contains(text(), 'My Entitlements')]")
	private WebElement myEntitlementsBtn; // My Entitlements button inside entitlementsBtn

	@FindBy(xpath = "//span[contains(text(), 'US - Personal')]")
	private WebElement leaveTypeSelection; // leave type selection in My Entitlements page

	@FindBy(xpath = "//a[contains(text(), 'Assign Leave')]")
	private WebElement assignLeaveBtn; // Assign leave button in Leave page

	@FindBy(xpath = "//span[contains(text(), 'Reports ')]")
	private WebElement reportsBtn; // Reports Button present in Leave Module

	@FindBy(xpath = "(//a[@class='oxd-topbar-body-nav-tab-link'])[1]")
	private WebElement leaveEntitlesAndUsageReportBtn; // Leave Entitles And Usage Report Button inside Reports Button

	@FindBy(xpath = "(//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input'])[2]")
	private WebElement employeeRadioBtn; // Employee Radio Btn inside Leave Entitles And Usage Report page

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-text--count']")
	private WebElement noOfRecordsField; // No of records field inside Leave Entitles And Usage Report page

	@FindBy(css = ".colPinStart .rgRow > .rgCell")
	private List<WebElement> leaveTypes; // First column of the table leave types

	@FindBy(css = ".rgCol .rgRow")
	private List<WebElement> valueRows; // Rows in Leave Entitles And Usage Report page
	
	@FindBy(xpath = "//a[contains(text(), 'My Leave Entitlements and Usage Report')]")
	private WebElement myLeaveEntitlementsAndUsageReportBtn; // My Leave Entitlements And Usage Report Report Button inside Reports Button
	
	@FindBy(css = ".rgCol .rgHeaderCell .header-content")
	private List<WebElement> valueHeaders; // Table headers


	public void goToLeavePage() {
		click(leavePage, "Leave");
	}

	public void applyLeave() throws InterruptedException {
		click(applyLeavePage, "Apply");
		WaitUtils.waitForElementToBeVisible(driver, insideApplyLeavePage);
		Thread.sleep(5000);
		ScreenshotUtil.takeScreenshot(driver, "screenshots/leave_" + System.currentTimeMillis() + ".png");
	}

	public void myLeave(String fromDate, String toDate) throws InterruptedException {
		click(myLeavePage, "My Leave");
		type(fromDateField, fromDate, "From Date");
		type(toDateField, toDate, "To Date");
		click(leaveType, "Leave Type");
		click(leaveField, "Leave Selection");
		click(searchBtn, "Search");
		Thread.sleep(2000);
		getText(noOfRecords, "Records");
	}

	public void addEntitlement(String name, String entitlement) throws InterruptedException {
		click(entitlementsBtn, "Entitlements");
		click(addEntitlementBtn, "Add Entitlement");
		type(employeeNameField, name, "Employee Name");
		Thread.sleep(2000);
		click(employeeName, "Employee");
		click(leaveTypeField, "Leave Type");
		click(leaveTypeOption, "Leave Option");
		type(entitlementField, entitlement, "Entitlement");
		Thread.sleep(2000);
		click(saveOrSearchBtn, "Save");
		click(confirmBtn, "Confirm");
	}

	public void verifyEmployeeEntitlements(String name) throws InterruptedException {
		click(entitlementsBtn, "Entitlements");
		click(employeeEntitlementsBtn, "Employee Entitlements");
		type(employeeNameField, name, "Employee Name");
		Thread.sleep(2000);
		click(employeeName, "Employee");
		click(leaveTypeField, "Leave Type");
		click(leaveTypeOption, "Leave Option");
		Thread.sleep(2000);
		click(saveOrSearchBtn, "Save");
		getText(records, "Records");
	}

	public void checkMyEntitlements() {
		click(entitlementsBtn, "Entitlements");
		click(myEntitlementsBtn, "My Entitlements");
		click(leaveTypeField, "Leave Type");
		click(leaveTypeSelection, "Leave Type");
		click(saveOrSearchBtn, "Search");
	}

	public void assignLeave(String name, String date) throws InterruptedException {
		click(entitlementsBtn, "Entitlements");
		click(assignLeaveBtn, "Assign Leave");
		type(employeeNameField, name, "Employee Name");
		Thread.sleep(2000);
		click(employeeName, "Employee");
		click(leaveTypeField, "Leave Type");
		click(leaveTypeOption, "Leave Option");
		type(fromDateField, date, "From Date");
		click(saveOrSearchBtn, "Search");
		click(confirmBtn, "Confirm");
	}

	public void leaveEntitlementsAndUsageReport(String name) throws InterruptedException {
		click(reportsBtn, "Reports");
		click(leaveEntitlesAndUsageReportBtn, "Leave Entitlements and Usage Report");
		click(employeeRadioBtn, "Employee Radio");
		type(employeeNameField, name, "Employee Name");
		Thread.sleep(2000);
		click(employeeName, "Employee");
		click(saveOrSearchBtn, "Search");
		getText(noOfRecordsField, "No of Records");
		System.out.println("Table Content: ");

		for (int i = 0; i < leaveTypes.size(); i++) {
			System.out.print(leaveTypes.get(i).getText() + "\t");
			List<WebElement> valueCells = valueRows.get(i).findElements(By.className("rgCell"));

			for (WebElement cell : valueCells) {
				System.out.print(cell.getText() + "\t");
			}
			System.out.println();
		}
	}
	
	public void myLeaveEntitlementsAndUsageReport() {
		click(reportsBtn, "Reports");
		click(myLeaveEntitlementsAndUsageReportBtn, "My Leave Entitlement And Usage Report");
		click(saveOrSearchBtn, "Search");
		getText(noOfRecordsField, "No of Records");
		
        System.out.print("Leave Type\t");
        for (WebElement header : valueHeaders) {
            System.out.print(header.getText() + "\t");
        }
        System.out.println();

        for (int i = 0; i < leaveTypes.size() && i < valueRows.size(); i++) {
            System.out.print(leaveTypes.get(i).getText() + "\t");
            List<WebElement> cells = valueRows.get(i).findElements(By.className("rgCell"));

            for (WebElement cell : cells) {
                System.out.print(cell.getText() + "\t");
            }
            System.out.println(); 
        }
	}
}
