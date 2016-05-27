package com.mindtree.essence.reports;
/**
 *  
 *  This Class contains test methods for Capturing the screenshot  of behavior of MyRecipe site for a user who has not logged in as 
 *  a registered user,that is as Anonymous User 
 *  
 */


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestNGMethod;


public class CaptureScreenShot {
	public static String screenShotFile="";
	public static void takeScreenShoot(WebDriver driver,ITestNGMethod testMethod) throws Exception {
		File screenShotFolder;
		String screenshotTimeTake = ModuleReports.timeInHours;
		String methodName = testMethod.getMethodName();
		if (driver instanceof TakesScreenshot) {
			File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				screenShotFolder = new File(ModuleReports.rePorterFolderPath+"/"+"Screenshots");
				screenShotFolder.mkdirs();
				FileUtils.copyFile(tempFile, new File(screenShotFolder + "/"+ methodName + screenshotTimeTake + ".png"));
				screenShotFile= screenShotFolder + "/"+ methodName + screenshotTimeTake + ".png";}
			catch (IOException e) {
				System.out.println("Exception found while catpturing the screenshot:");
				e.printStackTrace();
			}
		}
	}
	public static String getScreenShotFile() {
		return screenShotFile;
	}
	}


