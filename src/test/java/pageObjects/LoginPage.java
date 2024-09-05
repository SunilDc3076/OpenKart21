package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		}

	@FindBy(xpath="//input[@id='input-email']") WebElement txt_inputemail ;
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_inputpaswrd;
	@FindBy(xpath="//input[@value='Login']") WebElement btn_login;
	
	public void txtEmailAddress(String email) {
		txt_inputemail.sendKeys(email);
	}
	public void txtInputpaswrd(String pasword) {
		txt_inputpaswrd.sendKeys(pasword);
	}
	public void clickLoginbtn() {
		btn_login.click();
	}
}
