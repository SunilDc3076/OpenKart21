package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.Dataproviders;

public class Tcc_003LoginDDT extends BaseClass {

	@Test(dataProvider="logindata",dataProviderClass=Dataproviders.class,groups={"master"})
	public void loginDDT(String email, String pwd ,String exp){
		System.out.println(email);
		try {
		logger.info("***STARTED TCC003 DDTEST****");
		HomePage hp= new HomePage(driver); 
		logger.info("***Clicked On ACCOUNT***");
		hp.clkAccount();
		hp.clkLogin();
		logger.info("***PROVIDING LOGIN DETAILS****");
		LoginPage lp = new LoginPage(driver);
		lp.txtEmailAddress(email);
		lp.txtInputpaswrd(pwd);
		lp.clickLoginbtn();
		logger.info("***VALIDATING MYACCOUNT PAGE****");
		MyAccountPage mp = new MyAccountPage(driver);
//		  Assert.assertTrue(mp.isDiplayedstrMyAccount());
		boolean targetPage =mp.isDiplayedstrMyAccount();
		  if(exp.equalsIgnoreCase("Valid")) {
			  if(targetPage==true) {
				 
				  logger.info("***CLICKED ON LOGOUT ****");
					mp.clkLogoutbtn(); 
					 Assert.assertTrue(true);
			  }
			  else
			  {
				  Assert.assertTrue(false);
			  }
		  }if(exp.equalsIgnoreCase("Invalid")) {
			  if(targetPage==true) {
				  mp.clkLogoutbtn();
				  Assert.assertTrue(false);
			  }else {
				  Assert.assertTrue(true);
			  }
		  }
	}
		catch(Exception e) {
		
		logger.info("***FINISHED Tcc003****");
		Assert.fail();
		}
	}
}



