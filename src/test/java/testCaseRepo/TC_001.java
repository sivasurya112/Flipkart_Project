package testCaseRepo;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import objectRepo.CartPage;
import objectRepo.HomePage;
import objectRepo.ProductPage;

public class TC_001 extends BaseClass {
	
	@Test(retryAnalyzer = genericUtility.RertyAnalyzerImplementation.class)
	public void LE_001() throws InterruptedException, IOException {
		
		new HomePage(d).searchProduct();
		ProductPage Pp = new ProductPage(d);
		Pp.filterProduct();
		List<WebElement> offers = d.findElements(By.xpath("//div[@style='height: 84px; padding-top: 12px;']"));
		if(offers.get(0).isDisplayed())
		System.out.println(offers.size());
		if(Pp.getAddToCart().isDisplayed() && Pp.getAddToCart().isEnabled()) {
			Pp.getAddToCart().click();
			new CartPage(d).productAvail();
		}
		else {
			new CartPage(d).productNotAvail();
		}
		
		
		
		
	}

}
