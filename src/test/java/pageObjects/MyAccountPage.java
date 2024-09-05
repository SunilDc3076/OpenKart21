package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[contains(text(),'My Account')]") WebElement strmyacount;
	@FindBy(xpath="//aside//a[contains(text(),'Logout')]") WebElement btnlogout;
	
	public boolean isDiplayedstrMyAccount() {
		try {
		Boolean strmsg =strmyacount.isDisplayed();
		return strmsg;
	}catch(Exception e){
		return false;
	}
		
		
	}
	public void clkLogoutbtn() {
		btnlogout.click();
	}
}
