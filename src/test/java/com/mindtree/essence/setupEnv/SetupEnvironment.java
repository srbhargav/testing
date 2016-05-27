package com.mindtree.essence.setupEnv;

/**
 * @author M1027330
 *
 */

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.mindtree.essence.utilities.EssencePOCUtils;
import com.mindtree.essence.utilities.WriteLog;

public class SetupEnvironment {
	private static String selectedURL = null;
	private static List<String[]> seoTagComparisionURLs = null;
	public static WriteLog writelogger = new WriteLog();
	public static org.apache.log4j.Logger logger = writelogger.getLogger();
	public static String chromeDriverPath;
	public static ReadEnvironment setUpInputExcelFile = new ReadEnvironment();
	public static String chromeForWin = "./lib/chromedriver.exe";
	public static String chromeForMac = "./lib/chromedriver_mac";

	private static String EnvironmentPath = EssencePOCUtils.getProjectpath()
			+ "testData/DomainList1.xls";
	private static WebDriver driver = null;
	//public static String chromeDriverPath;

	public static void setEnv() throws IOException {

		logger.info("Path for the environmental setup-->" + EnvironmentPath);
		
		
		String browser = setUpInputExcelFile.readBrowserName(EnvironmentPath);
		logger.info("browser selected for the Operation---> :" + browser);
		// Read the URLs.
		selectedURL = setUpInputExcelFile.readHomePageURL(EnvironmentPath);
		logger.info(" URL selected--->" + selectedURL);
		seoTagComparisionURLs = setUpInputExcelFile.readSeoTagComparisionURLs(EnvironmentPath);
		
		if(getPlatform()== "MAC")
        {
        	chromeDriverPath=chromeForMac;	
		}
	else {
				chromeDriverPath=chromeForWin;
		}
	


		if (browser.contains("FIREFOX")) {
			ProfilesIni allProfile = new ProfilesIni();
			FirefoxProfile myProfile = allProfile.getProfile("default");
			driver = new FirefoxDriver(myProfile);
			driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			logger.info("Firefox Driver Object is ready");
		} else if (browser.contains("IE")) {
			System.setProperty("webdriver.ie.driver.silent", "true");
			System.setProperty("webdriver.ie.driver",
					EssencePOCUtils.getProjectpath()
							+ "./browsers/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();
			capabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			logger.info("IE Driver object is ready");

		}

		else  if (browser.contains("CHROME")){
			System.out.println("chrome established");
			System.setProperty("webdriver.chrome.driver",
						chromeDriverPath);

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			System.out.println("capabilities==" +capabilities);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
		
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			System.out.println(driver);
		}
		
		else{
				driver=new SafariDriver();
				driver.manage().window().maximize();
				}

			
		
	}

	
	private static String getPlatform() 
	{
	    Platform current = Platform.getCurrent();
	    System.out.println("The platform is "+current);
	    String Platform=current.toString();
	    return Platform;
	  }

	public static WebDriver getDriver() throws IOException {
		return driver;
	}

	public static String getURL1() throws IOException {
		return selectedURL;
	}

	public static List<String[]> readSeoTagComparisionURLs() throws IOException {
		return seoTagComparisionURLs;
	}
	
	public static void tearDown() throws Exception {

		logger.info("************************Closing Browsers*************************");
		// driver.quit();
		driver.close();
	}

}
