package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class Tcc_002LoginTest extends BaseClass {

	@Test(groups={"sanity","regression","master"})
	public void login_Test() {
		try {
		logger.info(" ****** Starting LoginTest Test ******");
		HomePage  hp = new HomePage(driver);
		hp.clkAccount();
		hp.clkLogin();
		LoginPage lp = new  LoginPage( driver);
		logger.info(" ****** Entering LoginDetails ******");	
		lp.txtEmailAddress(p.getProperty("userEmail"));
		lp.txtInputpaswrd(p.getProperty("userpassword"));
		logger.info(" ****** clicked on Loginbtn ******");
		lp.clickLoginbtn();
		MyAccountPage mp =new MyAccountPage(driver); 
		logger.info(" ****** Validating Displayed Message  ******");
		Assert.assertTrue(mp.isDiplayedstrMyAccount());
		logger.info(" ****** Finished Test  ******");
		mp.clkLogoutbtn();
		}catch (Exception e) {
			logger.info("Test Failed ");
			Assert.fail();
		}
		}
}
