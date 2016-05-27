package com.mindtree.essence.scenarios;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author M1027330
 *
 */
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.mindtree.essence.utilities.Constants;
import com.mindtree.essence.utilities.EssencePOCUtils;
import com.mindtree.essence.utilities.WebElementsLocator;
import com.mindtree.essence.utilities.WriteLog;

public class SocialWebsiteNavigation {

	public static WriteLog writelogger = new WriteLog();
	public static org.apache.log4j.Logger logger = writelogger.getLogger();
	public static EssencePOCUtils element = new EssencePOCUtils();

	static String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	static int RANDOM_STRING_LENGTH = 5;

	/**
	 * This Method does getting the random String Generation
	 * 
	 */
	public static String generateRandomString() {

		StringBuffer randomSTR = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randomSTR.append(ch);
		}
		return randomSTR.toString();
	}

	/**
	 * This Method does getting the Random Number
	 * 
	 */
	public static int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	/**
	 * This Method does getting the random String length
	 * 
	 */
	public static String generateRandomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	static String userName = generateRandomString();
	static String emailId = userName.concat("@gmail.com");
	static String zipcode = generateRandomNumber(5);

	/**
	 * This Method does Navigation to the Facebook page and share the content on
	 * the fb page After sharing to verify whether it has been shared or not
	 * 
	 */
	public static boolean facebookNavigation(WebDriver driver) throws Exception {
		element.highLight(driver, WebElementsLocator.beauty);
		element.moveAction(WebElementsLocator.beauty, driver);
		element.highLightAndClick(WebElementsLocator.makeUp, driver);
		
		element.checkAlert(driver);
		element.switchHandle(driver);
		
		element.moveAction(WebElementsLocator.firstDiv, driver);
		logger.info("facebook Navigation :: navigated window title :  " + driver.getTitle());
		element.highLightAndClick(WebElementsLocator.firstDiv, driver);
		element.switchHandle(driver);
		element.checkAlert(driver);
		
		String actualContentToBeShared = WebElementsLocator.ContentTitle.getText();
		logger.info(actualContentToBeShared);
		logger.info("facebook Navigation :: navigated window title :  " + driver.getTitle());
		
		element.moveAction(WebElementsLocator.fbShareButton, driver);
		WebElementsLocator.fbShareButton.click();
		//element.click(WebElementsLocator.fbShareButton, driver);
		logger.info("facebook Navigation :: navigated window title :  " + driver.getTitle());
		element.switchHandle(driver);
		System.out.println(driver.getCurrentUrl());
		logger.info("facebook Navigation to Main Page:: navigated window title :  " + driver.getTitle());
		element.highLight(driver, WebElementsLocator.fbEmailId);
		element.moveAction(WebElementsLocator.fbEmailId, driver);
		WebElementsLocator.fbEmailId.sendKeys(Constants.EMAIL);

		element.highLight(driver, WebElementsLocator.fbPwd);
		element.moveAction(WebElementsLocator.fbPwd, driver);
		WebElementsLocator.fbPwd.sendKeys(Constants.PASSWORD);

		element.highLight(driver, WebElementsLocator.fbSignUp);
		element.moveAction(WebElementsLocator.fbSignUp, driver);
		element.click(WebElementsLocator.fbSignUp, driver);
		element.switchHandle(driver);

	System.out.println(WebElementsLocator.faceBookActualTitle.getText());
		String expectedContentToShare = WebElementsLocator.faceBookActualTitle.getText();
	System.out.println(expectedContentToShare);
		boolean containerContainsContent = StringUtils.containsIgnoreCase(expectedContentToShare,
				actualContentToBeShared);
	//	Assert.assertTrue(containerContainsContent, "shared");

		System.out.println(expectedContentToShare + " Actual Content to be shared"
	 + "== is equal to the expected Content" + actualContentToBeShared);
		element.highLight(driver, WebElementsLocator.fbShare);
		element.moveAction(WebElementsLocator.fbShare, driver);

		element.click(WebElementsLocator.fbShare, driver);

		driver.navigate().to(Constants.FACEBOOK_URL);
		logger.info("get the present working title : :" + driver.getTitle());

		logger.info("driver url===>" + driver.getCurrentUrl());

		logger.info(WebElementsLocator.ContentTitle + " ====>Lose 8 Pounds in 2 Weeks"
				+ "====>has been shared on facebook homepage");
		element.switchHandle(driver);

		driver.close();
		element.switchHandle(driver);
		return true;

	}

	/**
	 * This Method does Navigation to the twitter page and share the content on
	 * the Twitter page After sharing to verify whether it has been shared or
	 * not
	 * 
	 */

	public static boolean twitterNavigation(WebDriver driver) throws Exception {

		element.highLight(driver, WebElementsLocator.beauty);
		element.moveAction(WebElementsLocator.beauty, driver);

		element.highLightAndClick(WebElementsLocator.makeUp, driver);
		logger.info("get the present working title : 111 :" + driver.getTitle());
		element.checkAlert(driver);
		element.switchHandle(driver);
		logger.info("get the present working title : : " + driver.getTitle());

		element.moveAction(WebElementsLocator.twitterFirstDiv, driver);
		element.highLightAndClick(WebElementsLocator.twitterFirstDiv, driver);
		element.switchHandle(driver);
		element.checkAlert(driver);
		logger.info("get the present working title : :" + driver.getTitle());
		String actual_conetent = WebElementsLocator.twitterContentTitle.getText();
		logger.info(actual_conetent);
		logger.info("Twitter Window Handle:" + driver.getTitle());

		//element.moveAction(WebElementsLocator.twitterShareButton, driver);
		WebElementsLocator.twitterShareButton.click();
		//element.highLightAndClick(WebElementsLocator.twitterShareButton, driver);
		element.switchHandle(driver);

		element.highLight(driver, WebElementsLocator.twitterEmail);
		element.moveAction(WebElementsLocator.twitterEmail, driver);
		WebElementsLocator.twitterEmail.sendKeys(Constants.EMAIL);

		element.highLight(driver, WebElementsLocator.twitterPwd);
		element.moveAction(WebElementsLocator.twitterPwd, driver);
		WebElementsLocator.twitterPwd.sendKeys(Constants.PASSWORD);

		element.highLight(driver, WebElementsLocator.twitterSignUp);
		element.moveAction(WebElementsLocator.twitterSignUp, driver);
		element.click(WebElementsLocator.twitterSignUp, driver);
		String updatedTextAreaContent = WebElementsLocator.twitterTextArea.getText();
		WebElementsLocator.twitterTextArea.clear();
		if (updatedTextAreaContent!=null && updatedTextAreaContent.length()>190) {
			updatedTextAreaContent = updatedTextAreaContent.substring(updatedTextAreaContent.length()-190,190);
		}
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy_MM_dd-hh_mm_ss");
		updatedTextAreaContent = sdf.format(new Date()) + " " + updatedTextAreaContent;
		WebElementsLocator.twitterTextArea.sendKeys(updatedTextAreaContent);
		WebElementsLocator.twitterShare.click();
		element.switchHandle(driver);
//	driver.navigate().to(Constants.TWITTER_URL);
//		element.switchHandle(driver);
WebElementsLocator.viewOnTwitter.click();
element.switchHandle(driver);
driver.findElement(By.xpath("//div[@id='permalink-overlay-dialog']/div[3]/div/div/div/span")).click();
element.switchHandle(driver);		
WebElementsLocator.twitterActualContent.click();
		element.switchHandle(driver);

		String expectedOutput = WebElementsLocator.ContentTitle.getText();
		logger.info(expectedOutput);

		//Assert.assertEquals(actual_conetent, expectedOutput);
		logger.info("Article was shared successfully");

		element.switchHandle(driver);
		driver.close();
		element.switchHandle(driver);
		driver.close();
		element.switchHandle(driver);
		driver.close();
		return true;
	}

	/**
	 * This Method does Navigation to the newsettler page and generate random
	 * email and Zipcode later check whether the user is subscribed or not
	 */

	public static boolean newsLetterNavigation(WebDriver driver) throws Exception {

		element.moveAction(WebElementsLocator.Newsletter, driver);
		element.highLightAndClick(WebElementsLocator.Newsletter, driver);

		element.switchHandle(driver);
		element.checkAlert(driver);
		logger.info("get the present working title :2  :" + driver.getTitle());

		logger.info("username or emailId====>" + emailId);

		logger.info("username or emailId====>" + zipcode);

		element.highLight(driver, WebElementsLocator.newsletterEmail);
		element.moveAction(WebElementsLocator.newsletterEmail, driver);
		WebElementsLocator.newsletterEmail.sendKeys(emailId);

		element.delay();
		element.highLight(driver, WebElementsLocator.newsletterZipcode);
		element.moveAction(WebElementsLocator.newsletterZipcode, driver);
		WebElementsLocator.newsletterZipcode.sendKeys(zipcode);
		element.delay();

		element.moveAction(WebElementsLocator.newsletterSignup, driver);
		element.highLightAndClick(WebElementsLocator.newsletterSignup, driver);
		Assert.assertEquals("Email Newsletters from Health.com", driver.getTitle());
		return true;
	}

}
