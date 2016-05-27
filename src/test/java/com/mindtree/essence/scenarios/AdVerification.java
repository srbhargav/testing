package com.mindtree.essence.scenarios;

/**
 * @author M1027330
 *
 */

import java.util.ArrayList;
import java.util.List;





import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.mindtree.essence.utilities.EssencePOCUtils;
import com.mindtree.essence.utilities.WebElementsLocator;
import com.mindtree.essence.utilities.WriteLog;

public class AdVerification {

	public static EssencePOCUtils element = new EssencePOCUtils();
	public static WriteLog writelogger = new WriteLog();
	public static org.apache.log4j.Logger logger = writelogger.getLogger();

	public static boolean addVerification(WebDriver driver) throws Exception,
			InterruptedException {

		/*
		 * Get all the add present in a list
		 */
		List<WebElement> addSelList = new ArrayList<WebElement>();
		addSelList.add(WebElementsLocator.topAd);
		addSelList.add(WebElementsLocator.centreAd);
		addSelList.add(WebElementsLocator.topRightAd);

		/*
		 * iterate through each add if it is present and get the size of the add
		 * and location of the add
		 */

		for (WebElement addSelected : addSelList) {
			WebElement selectedElement = addSelected;
			if (selectedElement.equals(WebElementsLocator.topAd)
					&& selectedElement.isDisplayed()) {

				System.out.println("**Top Rail Add Dimensions :   :  **");
				element.getDimension(driver, selectedElement);
				System.out
						.println("Dimension of add in terms of Height :    : "
								+ element.getDimension(driver, selectedElement)
										.getHeight());
				System.out.println("Dimension of add in terms of width:     :"
						+ element.getDimension(driver, selectedElement)
								.getWidth());
				element.moveToElement(selectedElement);
				System.out.println(" X point Location of ad on webpage:  : "
						+ element.moveToElement(selectedElement).getX());
				System.out.println(" Y point Location of ad on webpage:  : "
						+ element.moveToElement(selectedElement).getY());
				System.out.println("***********************************");
				try {
					
					Assert.assertEquals(element.moveToElement(addSelected)
							.getX(), selectedElement.getLocation().getY(),
							"Top rail ad Location of Y-axis has been Changed  :");

				} catch (AssertionError e) {
					System.out.println("DIMENSION OR LOCATION OF AD HAS BEEN CHANGED");
					System.out.println(e.getMessage()
							+ "<----------------------------assertion error");
					System.out.println();
				}

			} else if (selectedElement.equals(WebElementsLocator.centreAd)
					&& selectedElement.isDisplayed()) {

				System.out.println("  **  Center Rail Add Dimensions  **  ");
				element.getDimension(driver, selectedElement);
				System.out
						.println("Dimension of add in terms of Height :    : "
								+ element.getDimension(driver, selectedElement)
										.getHeight());
				System.out.println("Dimension of add in terms of width:     :"
						+ element.getDimension(driver, selectedElement)
								.getWidth());
				element.moveToElement(selectedElement);
				
				System.out.println(" X point Location of ad on webpage:  : "
						+ element.moveToElement(selectedElement).getX());
				System.out.println(" Y point Location of ad on webpage:  : "
						+ element.moveToElement(selectedElement).getY());
				System.out.println("");
				try {
					Assert.assertEquals(
							element.getDimension(driver, selectedElement)
									.getWidth(), selectedElement.getSize()
									.getHeight(),
							"Centre rail ad Length Dimension has been Changed  :");
					

				} catch (AssertionError e) {
					System.out.println("DIMENSION OR LOCATION OF AD HAS BEEN CHANGED");
					System.out.println(e.getMessage()
							+ "<----------------------------assertion error");
					System.out.println("***********************************");
				}
			} else if (selectedElement.equals(WebElementsLocator.topRightAd)
					&& selectedElement.isDisplayed()) {
				System.out.println(" **   right-Bottom Trail Add Dimensions   **");
				element.getDimension(driver, selectedElement);
				System.out
						.println("Dimension of add in terms of Height :    : "
								+ element.getDimension(driver, selectedElement)
										.getHeight());
				System.out.println("Dimension of add in terms of width:     :"
						+ element.getDimension(driver, selectedElement)
								.getWidth());
				element.moveToElement(selectedElement);
				System.out.println(" X point Location of ad on webpage:  : "
						+ element.moveToElement(selectedElement).getX());
				System.out.println(" Y point Location of ad on webpage:  : "
						+ element.moveToElement(selectedElement).getY());
				System.out.println("***********************************");
			
				try {
					
					Assert.assertEquals(element.moveToElement(addSelected)
							.getX(), selectedElement.getLocation().getY(),
							"right-Bottom rail ad Location of Y-axis has been Changed  :");

				} catch (AssertionError e) {
					System.out.println("DIMENSION OR LOCATION OF AD HAS BEEN CHANGED");
					System.out.println(e.getMessage()
							+ "<----------------------------assertion error");
				}

			}
		}
		return true;

	}

}
