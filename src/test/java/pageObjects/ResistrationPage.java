package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResistrationPage extends BasePage {

	public ResistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@name='firstname']") WebElement txt_userrname;
	@FindBy(xpath="//input[@name='lastname']") WebElement txt_lastname;
	@FindBy(xpath="//input[@name='email']") WebElement txt_email;
	@FindBy(xpath="//input[@name='telephone']") WebElement txt_telephone;
	@FindBy(xpath="//input[@name='password']") WebElement txt_password;
	@FindBy(xpath="//input[@name='confirm']") WebElement txt_confirm;
	@FindBy(xpath="//input[@name='agree']") WebElement chk_prvcplcy;
	@FindBy(xpath="//input[@type='submit']") WebElement btn_continue;
	@FindBy(xpath="//div[@id='content']/h1") WebElement msg_cnfmmsg;
	
	public void txtUserName(String name) {
		txt_userrname.sendKeys(name);;
	}
	public void txtLastName(String name) {
		txt_lastname.sendKeys(name);
	}
	public void txtEmail(String name) {
		txt_email.sendKeys(name);
		
	}
	public void txtTelephone(String name) {
		txt_telephone.sendKeys(name);
	}
	public void txtPassword(String name) {
		txt_password.sendKeys(name);
	}
	public void txtcnfrmpaswrd(String name) {
		txt_confirm.sendKeys(name);
	}
//	public void txtcnfrm(String name) {
//		txt_confirm.sendKeys(name);
//	}
	public void chkPrycplcy() {
		chk_prvcplcy.click();
	}
	public void btnCntune() {
		btn_continue.click();
	}
	
	public String getMsg() {
		
		try {
			return msg_cnfmmsg.getText();
		}
		catch(Exception e) {
			return e.getMessage();
			}
		
		}
	
}
