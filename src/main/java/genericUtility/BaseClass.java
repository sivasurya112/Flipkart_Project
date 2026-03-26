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
		d.manage().window().maximize();
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws InterruptedException {
		d.get("https://www.flipkart.com/");
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		try {
	        new LoginAlert(d).closeLoginPopup();
	    } catch (Exception e) {
	        // Never let @BeforeMethod crash — just log and continue
	        System.out.println("bmConfig: popup not found, continuing. " + e.getMessage());
	    }
	}
	@AfterClass(alwaysRun = true)
	public void acConfig() {
		d.quit();
	}

}
