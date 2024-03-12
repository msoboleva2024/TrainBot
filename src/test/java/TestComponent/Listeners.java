package TestComponent;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	@Override
	 public void onTestStart(ITestResult result) {
		   
		test = extent.createTest(result.getMethod().getMethodName());
		
		
		//unique Thread Id
		extentTest.set(test);
		  }
	
	@Override
		  public  void onTestSuccess(ITestResult result) {
		    // not implemented
		if (Arrays.toString(result.getParameters())!="[]")
		//test.log(Status.PASS, "Test passed.");
	extentTest.get().log(Status.PASS, "Test passed. Parameters:  "+ Arrays.toString(result.getParameters()));	
	
		else
			extentTest.get().log(Status.PASS, "Test passed");
	}

	@Override 
		  public void onTestFailure(ITestResult result) {
		    // not implemented
		
		
		//test.fail(result.getThrowable());
	
		extentTest.get().fail(result.getThrowable());
		String filePath = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			
		} catch (IOException e) {
			
		e.printStackTrace();	
		}
		
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		  }

	@Override
		  public void onTestSkipped(ITestResult result) {
		    // not implemented
		  }

	@Override
		  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    // not implemented
		  }

	@Override 
		  public void onTestFailedWithTimeout(ITestResult result) {
		    onTestFailure(result);
		  }

	@Override
		  public void onStart(ITestContext context) {
		    // not implemented
		  }
	
	@Override
		  public void onFinish(ITestContext context) {
		    // not implemented
		
		extent.flush();
		  }
	
}
