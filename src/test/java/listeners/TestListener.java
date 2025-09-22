package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import initialization.DriverManager;
import utilities.LoggerUtil;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtil.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtil.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtil.info("Test failed: " + result.getName());
        
        WebDriver driver = DriverManager.getDriver();
        String filePath = "screenshots/errorPage_" + System.currentTimeMillis() + ".png";
		ScreenshotUtil.takeScreenshot(driver, filePath);
		System.out.println("Screenshot taken: " + filePath);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtil.info("Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        LoggerUtil.info("Test execution started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtil.info("Test execution finished: " + context.getName());
    }
}
