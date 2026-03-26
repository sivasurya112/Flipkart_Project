package genericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RertyAnalyzerImplementation implements IRetryAnalyzer{
	int c=1;
	int max=2;
	@Override
	public boolean retry(ITestResult result) {
		
		while(c<=max) {
			c++;
			return true;
		}
		return false;
	}

}
