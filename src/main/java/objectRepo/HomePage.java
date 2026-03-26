package objectRepo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(name = "q")
	private WebElement searchBar;
	
	public HomePage(WebDriver d) {
		PageFactory.initElements(d,this);
	}

	public WebElement getSearchBar() {
		return searchBar;
	}
	
	public void searchProduct() {
		getSearchBar().sendKeys("Bluetooth Speakers",Keys.ENTER);
	}
	
}
