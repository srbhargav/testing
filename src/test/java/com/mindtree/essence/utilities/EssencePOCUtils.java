package com.mindtree.essence.utilities;
/**
 * @author M1027330
 *
 */
/*
 * Generic class is used for fetching all reusable methods
 */
import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;











import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class EssencePOCUtils {
	private static String projectpath;
	public static WriteLog writelogger = new WriteLog();
	public static org.apache.log4j.Logger logger = writelogger.getLogger();
	
	
	/**
	 * Used for verifying whether the webElement is present or not
	 */
	public static boolean isObjPresent(WebElement obj) {
		try {
			Assert.assertNotNull(obj);
			obj.isDisplayed();
			return true;
		} catch (Exception e) {
	logger.info(e.getMessage());
			return false;
		}
	}
	/**
	 * Used for verifying whether the webElement is selected or not
	 */
	public boolean isObjSelected(WebElement obj) {
		try {
			Assert.assertNotNull(obj);

			obj.isDisplayed();
			return true;
		} catch (Exception e) {
	logger.info(e.getMessage());

			return false;
		}
	}
	/**
	 * Used for verifying whether the webElement is enabled or not
	 */
	public boolean isObjEnabled(WebElement obj) {
		try {
			Assert.assertNotNull(obj);

			obj.isEnabled();
	logger.info(obj + " is Enabled");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * Used for click on the object of the webElement
	 */
	public boolean click(WebElement obj, WebDriver driver) throws Exception {
		try {
			if (isObjPresent(obj)) {
				obj.click();
		

				return true;

			} else {
		logger.info("Object Not Found " + obj);

				return false;
			}
		} catch (Exception e) {
	logger.info("Could not click on " + obj);

			return false;

		}
	}

	/**
	 * Used wherever Thread.sleep needs to be used for causing the delay. Value
	 * supplied is in milliseconds.
	 */
	public void delay() throws InterruptedException {

		Thread.sleep(3000);

	}

	/**
	 * Used for highlighting web element and clicking on it.
	 */

	public void highLightAndClick(WebElement Obj, WebDriver driver)
			throws Exception {

		highLight(driver, Obj);
		click(Obj, driver);

	}
	
	/**
	 * Used for highlighting web element and clicking on it.
	 */
	public void highLight(WebDriver driver, WebElement obj)
			throws InterruptedException {

		for (int i = 0; i < 5; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);", obj,
					"color: red; border: 3px solid red;");
			//Thread.sleep(500);
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);", obj,
					"color: green; border: 3px groove green;");
			//Thread.sleep(500);

		}

	}

	/**
	 * This Method does act as a wait condition(TIME) until the expected condition is reached
	 * 
	 */
	public void waitElementToLoad(WebElement we, WebDriver driver)
			throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOf(we));
		highLight(driver, we);

	}

	/**
	 * This Method does Mouse hover operation on web element.
	 * 
	 */
	public void moveAction(WebElement move, WebDriver driver) throws Exception {
		Actions actions = new Actions(driver);

		actions.moveToElement(move).perform();
	}
	/**
	 * This Method does  Switch from one browser handle to the present working window handle
	 */

	public void switchHandle(WebDriver driver) throws Exception {

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

	}
	/**
	 * This Method does getting the dimensions of the WebElement
	 * 
	 */
	public Dimension getDimension(WebDriver driver, WebElement obj) {
		Dimension size = null;
		if(obj.getSize()!=null){
		size = obj.getSize();
		logger.info("Dimensions=====>" +size);
		
		}
		return size;
		
	}
//	public static boolean getDimension(WebElement obj, Dimension size) {
//		Assert.assertNotNull(obj);
//
//		if (isObjPresent(obj)) {
//
//	logger.info(obj + " is of Size" + size);
//
//			return true;
//
//		} else {
//	logger.info(obj + " is not Selected");
//			return false;
//		}
//
//	}
	public Point moveToElement(WebElement element)
			throws AWTException, InterruptedException {

		Robot robot = new Robot();
		Point point = element.getLocation();
		//System.out.println(point);
		int x = point.getX();
		int y = point.getY();
//		System.out.println(" X point Location of add on webpage:  : " +point.getX());
//		System.out.println(" Y point Location of add on webpage:  : " +point.getY());
//		robot.mouseMove(x, y);
		Thread.sleep(2000);
		return point;
		

	}
	/**
	 * Tthis Method does check with the Popup that present when navigation Happens.	 * 
	 */
	public void checkAlert(WebDriver driver) {
	    try {
	    	logger.info("navigated to alert page");
	    	driver.findElement(By.xpath("//div[@id='P_popupInLayer']/img")).click();
	     
	    } catch (Exception e) {
	     logger.info("no alert is visible");
	    }
	}
	
	public static String getProjectpath() {
		File currDir = new File(".");
		projectpath = currDir.getAbsolutePath();
		projectpath = projectpath.substring(0, projectpath.length() - 1);
		projectpath = projectpath.replace('\\', '/');
		logger.info(projectpath);
		return projectpath;
	}
}
