package genericUtility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class Screenshot {
	
	public void screenshotThePage(WebDriver d,String name) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)d;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\screenshots\\"+name+".png");
		Files.copy(src, dest);
	}

}
