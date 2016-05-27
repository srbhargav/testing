package com.mindtree.essence.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author M1027330
 *
 */



public class WebElementsLocator {

	WebDriver driver = null;

	public static String metaTag = "//meta";

	@FindBy(xpath = "//a[@href='http://www.health.com/health/service/newsletter/']")
	public static WebElement Newsletter;

	@FindBy(xpath = "//a[@href='http://www.essence.com/hair']")
	public static WebElement hair;

	@FindBy(xpath = "//a[@href='http://www.essence.com/hair/natural']")
	public static WebElement natural;
	
	
	@FindBy(xpath = "//div[@id='block-ti-ape-ess-channel-ti-ape-ess-sub-channel-beauty']/div/div/div/h2/a[2]")
	public static WebElement firstDiv;
	
	@FindBy(xpath = "//div/ul/li[2]/a/span")
	public static WebElement shareOption;

	@FindBy(xpath = "//div[@id='block-ti-ape-ess-social-tools-bottom-share-buttons']/div/div/div/div[3]/div/a")
	public static WebElement fbShareButton;

	@FindBy(xpath = "//input[@id='email']")
	public static WebElement fbEmailId;

	@FindBy(xpath = "//input[@id='pass']")
	public static WebElement fbPwd;

	@FindBy(xpath = "//label[@id='loginbutton']")
	public static WebElement fbSignUp;

	@FindBy(xpath = "//form[@id='platformDialogForm']/div[3]/div/table/tbody/tr/td[2]/button[2]")
	public static WebElement fbShare;

	@FindBy(xpath = "//span/div[2]/a")
	public static WebElement fbContent;

	@FindBy(xpath ="//h1[@class='post-header']")
	public static WebElement ContentTitle;
	
	@FindBy(xpath ="//h1[@class='title']")
	public static WebElement ContentTitleSide;
	
	
	@FindBy(xpath ="//h1[@class='post-header']")
	public static WebElement twitterContentTitle;
	

	@FindBy(xpath ="//div[@class='unclickableMask']")
	public static WebElement faceBookActualTitle;
	
	@FindBy(id ="u_0_e")
	public static WebElement actualTitle;

	@FindBy(xpath = "//form[@id='new-newsltr-signup']/input[10]")
	public static WebElement newsletterZipcode;

	@FindBy(xpath = "//form[@id='new-newsltr-signup']/input[11]")
	public static WebElement newsletterSignup;

	@FindBy(xpath = "//form[@id='new-newsltr-signup']/input[9]")
	public static WebElement newsletterEmail;

	@FindBy(xpath = "//a[@href='http://www.essence.com/beauty']")
	public static WebElement beauty;

	@FindBy(xpath = "//a[@href='http://www.essence.com/beauty/makeup']")
	public static WebElement makeUp;

	@FindBy(xpath = "//div[@id='block-ti-ape-ess-social-tools-bottom-share-buttons']/div/div/div/div[3]/div/a")
	public static WebElement twitterFirstDiv;

	@FindBy(xpath = "//div[@id='block-ti-ape-ess-social-tools-bottom-share-buttons']/div/div/div/div[3]/div[2]/a")
	public static WebElement twitterShareButton;

	@FindBy(xpath = "//input[@id='username_or_email']")
	public static WebElement twitterEmail;

	@FindBy(xpath = "//input[@id='password']")
	public static WebElement twitterPwd;

	@FindBy(xpath = "//input[@value='Log in and Tweet']")
	public static WebElement twitterSignUp;

	@FindBy(xpath = "//input[@value='Tweet']")
	public static WebElement twitterShare;

	@FindBy(xpath = "//p/a/span[3]")
	public static WebElement twitterActualContent;
	
	@FindBy(xpath = "//form[@id='update-form']/div[2]/span/textarea")
	public static WebElement twitterTextArea;
	
	@FindBy(xpath = "//a[contains(text(),'View it on Twitter')]")
	public static WebElement viewOnTwitter;
	
	@FindBy(xpath = "//div[@id='ad728x90_1']")
	public static WebElement topAd;

	@FindBy(xpath = "//div[@id='multi300x250_1']")
	public static WebElement centreAd;

	@FindBy(xpath = "//div[@id='multi300x250_2']")
	public static WebElement topRightAd;
}
 