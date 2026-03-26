package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginAlert {

	@FindBy(xpath = "//div[@class='Blh9Gf']")
	private WebElement login;
	@FindAll({@FindBy(xpath = "//span[@role='button']"),@FindBy(xpath = "//span[text()='✕']") })
	private WebElement crossMark;
	
	public LoginAlert(WebDriver d) {
		PageFactory.initElements(d,this);
	}

	public WebElement getLogin() {
		return login;
	}
	public WebElement getCrossMark() {
		return crossMark;
	}
	
	public void closeLoginPopup() {
		if(getCrossMark().isDisplayed())
			getCrossMark().click();
	}

	
	
	
	
}
