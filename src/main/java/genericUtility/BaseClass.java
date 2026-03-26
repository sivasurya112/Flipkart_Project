package genericUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import objectRepo.LoginAlert;

public class BaseClass {
	public WebDriver d;
	@BeforeClass(alwaysRun = true)
	public void bcConfig() {
		d=new ChromeDriver();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		d.manage().window().maximize();
		d.get("https://www.flipkart.com/");
	}
	
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws InterruptedException {
		LoginAlert la = new LoginAlert(d);
		la.closeLoginPopup();
	}
	@AfterClass(alwaysRun = true)
	public void acConfig() {
		d.quit();
	}

}
