package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass {
	
 public static  WebDriver driver;
	public Logger logger;
	public Properties p;
@BeforeClass(groups={"sanity","regression","master"})
@Parameters({"os" ,"browser"})
public void setUp(String os ,String br) throws IOException {
//	//read property file
	FileReader file = new FileReader("./src//test//resources//config.properties");
	p= new Properties();
	p.load(file);
	logger = LogManager.getLogger(this.getClass());
	
	if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {	
		DesiredCapabilities cap = new DesiredCapabilities();
		
		//os and browser coming from xml file
		switch(os.toLowerCase()) {
		case "windows" :cap.setPlatform(Platform.WIN10); break;
		case "mac": cap.setPlatform(Platform.MAC); break;
            default : System.out.println("No Matching");   return;		
		}
		switch(br.toLowerCase()) {
		case  "chrome"	: cap.setBrowserName("chrome"); break;
		case  "edge"	: cap.setBrowserName("MicrosoftEdge"); break;
		default : System.out.println("**Remote Invalid BrowserName **"); return ;
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
	}
	//http://192.168.144.24:4444/ui/
	 if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
	switch(br.toLowerCase()) {
	case  "chrome"	: driver =new ChromeDriver(); break;
	case  "edge"	: driver =new EdgeDriver(); break;
	case  "firefox" :	driver =new FirefoxDriver(); break;
	default : System.out.println("** local Invalid BrowserName **"); return ;
	}
//	driver.get("https://tutorialsninja.com/demo/");
 }
	driver.manage().window().maximize();
	driver.get(p.getProperty("appURL"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.manage().deleteAllCookies();

}
	@AfterClass(groups={"sanity","regression","master"})
	public void tearDown() {
	
	driver.quit();
}
public String username(){
	String rndmstring = RandomStringUtils.randomAlphabetic(5);
	return rndmstring;		
}
public String number(){
	String rndmnumber = RandomStringUtils.randomNumeric(10);
	return rndmnumber;		
}
public String password() {
	String string = RandomStringUtils.randomAlphanumeric(3);
	String number = RandomStringUtils.randomNumeric(3);
	return (string+""+number);		
}
public String captureScreen(String name) throws IOException {
	String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
	File sourcefile = takeScreenShot.getScreenshotAs(OutputType.FILE);
	String targetFilePath = System.getProperty("user.dir")+"\\screenshots"+name+"_"+timestamp+".png";
	
	File targetFile = new File(targetFilePath);
	sourcefile.renameTo(targetFile);
	
	return targetFilePath;
}
}