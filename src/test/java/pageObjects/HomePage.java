package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		}

	 @FindBy(xpath="//a[@title='My Account']") WebElement lnk_myaccount; 
	 @FindBy(xpath="//a[contains(text(),'Register')]") WebElement lnk_register; 
	 @FindBy(xpath="//a[contains(text(),'Login')]") WebElement lnk_login;
	 
	public void clkAccount(){
		lnk_myaccount.click();
	 }
	
	public void clkRegister(){
		lnk_register.click();
	 }
	public void clkLogin(){
		lnk_login.click();
	 }
}
