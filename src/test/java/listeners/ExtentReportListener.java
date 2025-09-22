package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import utilities.ScreenshotUtil;
import initialization.DriverManager;

import java.io.File;


public class ExtentReportListener implements ITestListener {

    private ExtentReports extent;
    private ExtentTest test;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        String reportPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        extentTest.get().log(Status.INFO, "Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        extentTest.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        WebDriver driver = DriverManager.getDriver();
        String filePath = "screenshots" + File.separator + "errorPage_" + System.currentTimeMillis() + ".png";
		ScreenshotUtil.takeScreenshot(driver, filePath);
		System.out.println("Screenshot taken: " + filePath);
		
		extentTest.get().addScreenCaptureFromPath(filePath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
