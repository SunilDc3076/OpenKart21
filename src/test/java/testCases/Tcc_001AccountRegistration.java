package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ResistrationPage;
import testBase.BaseClass;

public class Tcc_001AccountRegistration extends BaseClass{
	    
	 
	@Test(groups ={"regression","master"})
	public void Account_registrationTest() {
		
		logger.info(" ****** Starting AccountRegistration Test ******");
		try {
		HomePage hp =new HomePage(driver);	
		hp.clkAccount();
		logger.info(" Clicked on MyAccount Tab");
		hp.clkRegister();
		logger.info(" Clicked on Rgister Tab");
		ResistrationPage register = new ResistrationPage(driver);
		logger.info("Providing All Details");
		register.txtUserName(username());
		register.txtLastName(username());
		register.txtEmail(username()+"@gmail.com");
		String password =password();
		register.txtPassword(password);
		register.txtcnfrmpaswrd(password);
		register.txtTelephone(number());
		register.chkPrycplcy();
		register.btnCntune();
		logger.info("validating Expected Message");
		System.out.println(register.getMsg());
		Assert.assertEquals(register.getMsg(),"Your Account Has Been Created!");	
		}
		catch(Exception e) {
			logger.error("Test Failed...");
			logger.debug(" Clicked on Account");
			Assert.fail();
		}
		logger.info("**** Finshed Test********");
	}
	

	

}
