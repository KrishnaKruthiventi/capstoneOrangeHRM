package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.LoggerUtil;
import utilities.ScreenshotUtil;
import utilities.WaitUtils;

public class DashboardPage extends BasePage {

	@FindBy(xpath = "//ul[@class='oxd-main-menu']")
	private List<WebElement> itemList; // Main Menu present in the side after logging in

	@FindBy(xpath = "//div[@class='oxd-sheet oxd-sheet--rounded oxd-sheet--white orangehrm-dashboard-widget']/div/div/p")
	private List<WebElement> dashboardItems; // Dashboard widgets header names list 

	@FindBy(xpath = "//div[@class='oxd-sheet oxd-sheet--rounded oxd-sheet--white orangehrm-dashboard-widget emp-leave-chart']/div/div/p")
	private WebElement leaveCard; // Employees on leave today card present in dashboard page

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p --text']")
	private List<WebElement> quickLaunchWidgets; // Widgets present in the Quick launch card

	@FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[3]")
	private WebElement leaveOption; // Leave option present in the menu (left side bar)

	@FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[1]")
	private WebElement fromDate; // From date in the leave list

	@FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
	private WebElement toDate; // To date in the leave list

	@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
	private WebElement leaveStatus; // Show leave status select bar

	@FindBy(xpath = "//i[@class='oxd-icon bi-x --clear']")
	private WebElement closeIcon; // close icon for already selected pending approval option in select bar

	@FindBy(xpath = "(//div[@class='oxd-select-option'])[3]")
	private WebElement pendingApprovalBtn; // Pending approval option inside the select bar

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitBtn; // Search button after filling the form

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span']")
	private WebElement records; // No of records fetched after clicking search button

	@FindBy(css = "ul.oxd-chart-legend > li")
	private List<WebElement> listItems; // Pie chart items in the dashboard page

	public void displayMenu() {
		for (WebElement item : itemList) {
			LoggerUtil.info(item.getText());
		}
	}

	public void dashboardElements() {
		for (WebElement item : dashboardItems) {
			LoggerUtil.info(item.getText());
		}
		LoggerUtil.info(leaveCard.getText());
	}

	public void quickLaunchWidgets() {
		for (WebElement widget : quickLaunchWidgets) {
			LoggerUtil.info(widget.getText());
		}
	}

	public void checkPendingLeaves(String fromdate, String todate) {
		click(leaveOption, "Leave Button");
		type(fromDate, fromdate, "From Date");
		type(toDate, todate, "To Date");
		click(closeIcon, "Close Button");
		click(leaveStatus, "Leave Status");
		click(pendingApprovalBtn, "Pending Approval");
		click(submitBtn, "Submit");
		getText(records, "Records");
	}

	public List<Map<String, String>> getPieChartListDetails() {
		List<Map<String, String>> listDetails = new ArrayList<>();

		for (WebElement item : listItems) {
			String color = item.findElement(By.cssSelector("span.oxd-chart-legend-key"))
					.getCssValue("background-color");
			String label = item.findElement(By.cssSelector("span.oxd-text")).getAttribute("title");

			Map<String, String> slice = new HashMap<>();
			slice.put("label", label);
			slice.put("color", color);
			listDetails.add(slice);
		}

		return listDetails;
	}
	
	public void getScreenshot() {
		WaitUtils.waitForElementToBeVisible(driver, leaveCard);
		ScreenshotUtil.takeScreenshot(driver, "screenshots/dashboard_" + System.currentTimeMillis() + ".png");
	}
}
