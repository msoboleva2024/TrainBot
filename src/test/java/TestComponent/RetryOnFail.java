package TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryOnFail implements IRetryAnalyzer {

	
	int count = 0;
	int maxTry=1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if (count<maxTry)
		{
			count++;
			System.out.println("Retry of Test started");
			
			return true;
	}
		
		return false;
	}

}
