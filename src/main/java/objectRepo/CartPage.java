package objectRepo;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.Screenshot;

public class CartPage {
	WebDriver d;
	
	@FindBy(xpath = "//a[@title='Cart']")
	private WebElement cartBtn;
	
	public CartPage(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d,this);
	}

	public WebElement getCartBtn() {
		return cartBtn;
	}
	
	public void productAvail() throws IOException {
		getCartBtn().click();
		if(getCartBtn().isDisplayed())
		new Screenshot().screenshotThePage(d, "cart_result");
	}
	
	public void productNotAvail() throws IOException {
		System.out.println("Product unavailable — could not be added to cart.");
		new Screenshot().screenshotThePage(d, "result");
	}

}
