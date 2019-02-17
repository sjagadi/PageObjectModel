package com.banking.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.banking.base.BaseTest;

public class CustomListeners extends BaseTest implements ITestListener
{
	public ExtentHtmlReporter extentHtmlRep;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	public void onTestStart(ITestResult result) 
	{
		extentTest = extentReports.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) 
	{
		extentTest.log(Status.PASS, "TEST CASE PASSED IS: " + result.getName());
	}

	public void onTestFailure(ITestResult result) 
	{
		extentTest.log(Status.FAIL, "TEST CASE FAILED IS: " + result.getName());
		extentTest.log(Status.FAIL, "TEST CASE FAILED IS: " + result.getThrowable());
		try 
		{
			String screenshotPath = CustomListeners.getScreenshot(driver, result.getName());
			extentTest.addScreenCaptureFromPath(screenshotPath);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileHandler.copy(source, finalDestination);
		return destination;
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, "TEST CASE SKIPPED IS: " + result.getName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {


	}

	public void onStart(ITestContext context) {
		
		extentHtmlRep = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
		//Title of the document
		extentHtmlRep.config().setDocumentTitle("Automation Report");
		//Name of the report
		extentHtmlRep.config().setReportName("Automation Execution Report");
		//Setting the theme
		extentHtmlRep.config().setTheme(Theme.DARK);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlRep);
		extentReports.setSystemInfo("hostName", "LocalHost");
		extentReports.setSystemInfo("OS", "Windows 10");
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("Tester Name", "Santoshkumar Jagadi");
		extentReports.setSystemInfo("Browser", "Google Chrome");
	}

	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}