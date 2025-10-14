package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PIMPage extends BasePage{
	
	@FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']")
	private WebElement pimPage; // PIM module located on the left side
	
	@FindBy(xpath = "//a[contains(text(), 'Add Employee')]")
	private WebElement addEmployeeBtn; // Add Employee Btn available in PIM page
	
	@FindBy(name = "firstName")
	private WebElement firstNameField; // First name field in Add Employee Page
	
	@FindBy(name = "middleName")
	private WebElement middleNameField; // Middle name field in Add Employee Page
	
	@FindBy(name = "lastName")
	private WebElement lastNameField; // Last name field in Add Employee Page
	
	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	private WebElement employeeIdField; // Employee Id field in Add Employee Page
	
	@FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
	private WebElement toggleBtn; // Create Login Details toggle button in Add Employee Page
	
	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
	private WebElement userNameField; // Username field in Add Employee Page
	
	@FindBy(xpath = "(//input[@type='password'])[1]")
	private WebElement passwordField; // Password field in Add Employee Page
	
	@FindBy(xpath = "(//input[@type='password'])[2]")
	private WebElement confirmPasswordField; // Confirm Password field in Add Employee Page
	
	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
	private WebElement saveBtn; // Save Button in Add Employee Page
	
	@FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
	private WebElement employeeNameField; // Employee Name field present in EmployeeListPage
	
	@FindBy(xpath = "(//div[@class='oxd-autocomplete-option'])[1]")
	private WebElement employeeName; // List displayed after giving hints
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchBtn; // Search btn available on Employee List Page
	
	@FindBy(xpath = "//i[@class='oxd-icon bi-trash']")
	private WebElement deleteIconBtn; // Delete Icon container in the results tab
	
	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")
	private WebElement confirmDeleteBtn; // Confirm Delete Button displayed after clicking delete icon
	
	@FindBy(xpath = "(//span[@class='oxd-text oxd-text--span'])[1]")
	private WebElement noOfRecords; // No of records available for given input employee name
	
	@FindBy(xpath = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']")
	private List<WebElement> employeeCards; // List of available employees
	
	@FindBy(css = ".oxd-table-header .oxd-table-header-cell")
	private List<WebElement> headers; // Headers for employee table
	
	@FindBy(css = ".oxd-table-body .oxd-table-row")
	private List<WebElement> rows; // Rows which appear for employee search
	
	public void goToPIMPage() {
		click(pimPage, "PIM");
	}
	
	public void clickAddEmployeeBtn() {
		click(addEmployeeBtn, "Add Employee");
	}
	
	public void addEmployeeDetails(String firstName, String middleName, String lastName, String employeeId, String userName, String passWord) throws InterruptedException {
		type(firstNameField, firstName, "First Name");
		type(middleNameField, middleName, "Middle Name");
		type(lastNameField, lastName, "Last Name");
		type(employeeIdField, employeeId, "Employee Id");
		click(toggleBtn, "Toggle Button");
		type(userNameField, userName, "User Name");
		type(passwordField, passWord, "Password");
		type(confirmPasswordField, passWord, "Confirm Password");
		Thread.sleep(2000);
		click(saveBtn, "Save");
	}
	
	public void deleteEmployee(String name) {
		type(employeeNameField, name, "Employee Name");
		click(employeeName, "Employee");
		click(searchBtn, "Search");
		click(deleteIconBtn, "Delete Icon");
		click(confirmDeleteBtn, "Confirm Delete Button");
	}
	
	public void employeeSearch(String name) throws InterruptedException {
		type(employeeNameField, name, "Employee Name");
		Thread.sleep(2000);
		click(employeeName, "Employee");
		click(searchBtn, "Search");
		getText(noOfRecords, "No Of Records");
		
		
//		for(WebElement employeeCard: employeeCards) {
//			LoggerUtil.info(employeeCard.getText());
//		}

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector(".oxd-table-cell"));
            for (int i = 0; i < headers.size() && i < cells.size(); i++) {
                String headerText = headers.get(i).getText().trim();
                String cellText = cells.get(i).getText().trim();

                System.out.println(headerText + " - " + cellText);
            }

            System.out.println(); 
        }
	}
	
}
