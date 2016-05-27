package com.mindtree.essence.testResults;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mindtree.essence.reports.ModuleReports;
import com.mindtree.essence.scenarios.AdVerification;
import com.mindtree.essence.scenarios.CaptureOmnitureTags;
import com.mindtree.essence.scenarios.SEOTagsVerification;
import com.mindtree.essence.scenarios.SocialWebsiteNavigation;
import com.mindtree.essence.setupEnv.SetupEnvironment;
import com.mindtree.essence.utilities.WebElementsLocator;
import com.mindtree.essence.utilities.WriteLog;

public class EssencePOCMain {

	private static WebDriver driver = null;
	public static String browserUrl = null;
	public static WriteLog writelogger = new WriteLog();
	public static org.apache.log4j.Logger logger = writelogger.getLogger();

	public static long ModuleTimeTaken;
	public static long timetakenEachTest;
	public static String description = "";

	@BeforeClass
	public void setUp() throws Exception {

		logger.info("Starting with the environment Setup ");
		SetupEnvironment.setEnv();
		logger.info("Connecting with the required Driver ");
		driver = SetupEnvironment.getDriver();
		logger.info("selected the required driver to proceed further--->" + driver.toString());
		browserUrl = SetupEnvironment.getURL1();
		logger.info("URL to which Operations to be done---> :" + browserUrl.toString());
		PageFactory.initElements(driver, WebElementsLocator.class);
		driver.get(browserUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(3000);
	}

	@AfterClass
	public void tearDown() throws Exception {
		SetupEnvironment.tearDown();

		logger.info("Exit of LOGIN PAGE module...............");
	}

	@Test(priority = 0)
	public void SEOTagsVerfication() throws Exception

	{

		description = "verifying seoTags";

		System.out.println("SEO Tags Verification Started");
		Assert.assertTrue(SEOTagsVerification.compareSEOTags(driver, SetupEnvironment.readSeoTagComparisionURLs()), "completed");
		System.out.println("SEO Tags Verification Completed");
	}

	@Test(priority = 1)
	public void adVerification() throws Exception

	{
		description = "Verifucations of Ad in a webpage";
		driver.navigate().to(browserUrl);

		// AdVerification.addVerification(driver);
		System.out.println("Ad Verification Started");
		Assert.assertTrue(AdVerification.addVerification(driver), "Ad Verification completed");
		System.out.println("Ad Verification Completed");
	}

	@Test(priority = 2)
	public void webAnalytics() throws Exception

	{

		description = "Capturing of Omniture tags On webpage";
		driver.navigate().to(browserUrl);

		System.out.println("Capturing Omniture tags Started");
		CaptureOmnitureTags.readUTaghomePageTags(driver);
		System.out.println("Capturing Omniture tags Completed");
		// Assert.assertEquals(CaptureOmnitureTags.readUTaghomePageTags(driver),
		// " Capturing Omniture tags completed");
	}

	@Test(priority = 3)
	public void NavigationFacebook() throws Exception

	{

		description = "verifying SocialWebsiteNavigation of facebbok";
		driver.navigate().to(browserUrl);

		System.out.println("facebook Navigation Started");
		SocialWebsiteNavigation.facebookNavigation(driver);
		System.out.println("facebook Navigation Completed");
		// Assert.assertTrue(SocialWebsiteNavigation.facebookNavigation(driver),
		// "facebbok Navigation Completed");
	}

	@Test(priority = 4)
	public void twitterNavigation() throws Exception

	{

		description = "verifying SocialWebsiteNavigation of newsLetter";
		driver.navigate().to(browserUrl);

		System.out.println("twitter navigation Started");
		SocialWebsiteNavigation.twitterNavigation(driver);
		System.out.println("twitter navigation Completed");
		// Assert.assertTrue(SocialWebsiteNavigation.twitterNavigation(driver),
		// " twitter navigation Completed");
	}

	@AfterMethod(alwaysRun = true)

	public void updateLogAfterEachTest(ITestResult result) throws Exception {
		timetakenEachTest = result.getEndMillis() - result.getStartMillis();
		ModuleTimeTaken = ModuleTimeTaken + timetakenEachTest;
		ModuleReports.PassFailHtmlStringBuilder(getClass().getSimpleName(), result, driver, description,
				timetakenEachTest);
		timetakenEachTest = 0;
		description = "";
	}

	@AfterClass(alwaysRun = true)
	public void writeModuleHTMLLog() throws Exception {
		ModuleReports.createModuleHTMLFolderFile(getClass().getSimpleName());
		ModuleReports.writeModuleHTMLFile(ModuleReports.PassHeader, ModuleReports.passResultUpdate_EachTest,
				ModuleReports.FailHeader, ModuleReports.FailResultUpdate_EachTest, getClass().getSimpleName(),
				ModuleTimeTaken);
		ModuleTimeTaken = 0;
		SetupEnvironment.tearDown();
	}
}
