package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{

	ExtentSparkReporter sparkReporter;
	ExtentReports extent;
	ExtentTest test;
	
	String rpName;
	
	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		rpName = "Test-Report" + timestamp+ ".html";
		ITestListener.super.onStart(context);
		
		//Configure extent spark reporter
		 sparkReporter = new ExtentSparkReporter( ".\\reports\\"+ rpName);
		 
		 sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
		sparkReporter.config().setReportName("Pet Store User API");
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		 //Configure extent report
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 extent.setSystemInfo("Application", "Pest Store Users API");
		 extent.setSystemInfo("Operation", System.getProperty("os.name"));
		 extent.setSystemInfo("Environment", "QA");
		 extent.setSystemInfo("Username", System.getProperty("user.name"));
		 extent.setSystemInfo("Tester", "VienNguyen");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		
		ITestListener.super.onFinish(context);
		extent.flush();
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		ITestListener.super.onTestSuccess(result);
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		ITestListener.super.onTestFailure(result);
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
}
