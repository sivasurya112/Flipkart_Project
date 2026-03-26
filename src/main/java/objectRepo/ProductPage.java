package objectRepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.MultipleWindows;

public class ProductPage {
	WebDriver d;
	@FindBy(xpath = "//div[text()='Brand']")
	private WebElement brand;
	@FindBy(xpath = "//div[@title='4★ & above']")
	private WebElement customerRating;
	@FindBy(xpath = "(//a[@class='pIpigb'])[1]")
	private WebElement firstProd;
	@FindBy(xpath = "//div[text()='boAt']")
	private WebElement boatCkeck;
	@FindBy(xpath = "//div[text()='Price -- Low to High']")
	private WebElement priceFilter;
	@FindBy(xpath = "//div[text()='Add to cart']")
	private WebElement addToCart;
	
	public ProductPage(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d, this);
	}
	WebDriverWait wait= new WebDriverWait(d, Duration.ofSeconds(10));
	
	
	
	public WebElement getAddToCart() {
		return addToCart;
	}

	public WebElement getPriceFilter() {
		return priceFilter;
	}
	
	public WebElement getBoatCkeck() {
		return boatCkeck;
	}
	
	public WebElement getBrand() {
		return brand;
	}


	public void setBrand(WebElement brand) {
		this.brand = brand;
	}


	public WebElement getCustomerRating() {
		return customerRating;
	}
	
	
	public WebElement getFirstProd() {
		return firstProd;
	}


	public void filterProduct() throws InterruptedException {
		getBrand().click();
		wait.until(ExpectedConditions.elementToBeClickable(boatCkeck)).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(getCustomerRating())).click();
		getPriceFilter().click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(getFirstProd())).click();
		new MultipleWindows().switchTab(d);
		
	}
	
	public void clickCustomer() {
		
		wait.until(ExpectedConditions.visibilityOf(getCustomerRating())).click();
		
	}
	
	public void clickFisrtProd() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(getFirstProd())).click();
	}

}
