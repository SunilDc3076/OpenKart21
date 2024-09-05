package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
    ExtentSparkReporter sparkreport; 
    ExtentReports report;
    ExtentTest test;
    String repName;

public void onStart(ITestContext context) {
//	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.ss");
//	Date dt = new Date();
//	String currentdatetimstamp=df.format(dt);
	
	String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	repName="Test-Report-"+timestamp+".html";
	
	
	sparkreport = new ExtentSparkReporter(".\\reports\\"+ repName);
	sparkreport.config().setDocumentTitle("opencart Automation Report");
	sparkreport.config().setReportName("opencart Funtional testing");
	sparkreport.config().setTheme(Theme.DARK);
	
	report = new ExtentReports();
	report.attachReporter(sparkreport);
	report.setSystemInfo("Application", "opencart");
	report.setSystemInfo("Module", "Admin");
	report.setSystemInfo("Sub Module", "customers");
	report.setSystemInfo("user Name ", System.getProperty("user.name"));
	report.setSystemInfo("Environemnt", "QA");
	
	String os = context.getCurrentXmlTest().getParameter("os");
	report.setSystemInfo("Operating System", os);
	
	String browser = context.getCurrentXmlTest().getParameter("browser");
	report.setSystemInfo("Browser", browser);
	
    List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
    if(!includeGroups.isEmpty()) {
    	report.setSystemInfo("Groups", includeGroups.toString());
    }
   	
}

public void onTestSuccess(ITestResult result){
	
	test = report.createTest(result.getClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.PASS, result.getName() +" IS PASSED");
}
public void onTestFailure(ITestResult result) {
	test = report.createTest(result.getClass().getName());
	test.assignCategory(result.getMethod().getGroups());
test.log(Status.FAIL,result.getName() + "  IS FAILED");
test.log(Status.INFO, result.getThrowable().getMessage());
   
   try {
     String imgPath = new BaseClass().captureScreen(result.getName());
	 test.addScreenCaptureFromPath(imgPath);
   } catch(IOException e) {
	   e.printStackTrace();
   }
   
     
}
public void onTestSkipped(ITestResult result) {
	test = report.createTest(result.getClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.SKIP,result.getName() + "Test Is Skipped");
	test.log(Status.INFO, result.getThrowable().getMessage());
}
public void onFinish(ITestContext context) {
	
	report.flush();
	String pathofExtentReport =System.getProperty("user.dir")+"\\reports\\"+repName;
   File extentreport = new File(pathofExtentReport);
   try {
	   Desktop.getDesktop().browse(extentreport.toURI());
	   
   }catch(IOException e) {
	   e.printStackTrace();
   }
}
}
