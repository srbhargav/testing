package com.mindtree.essence.scenarios;

/**
 * @author M1027330
 *
 */
/* WebAnalytics class is used to to get all the omniture tags in essence homepage and print it in the excel
 * 
 */
import java.util.HashMap;




import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.mindtree.essence.utilities.EssencePOCUtils;
import com.mindtree.essence.utilities.ReadExcelValues;
import com.mindtree.essence.utilities.WriteLog;

public class CaptureOmnitureTags {
	public static String description = null;
	public static HashMap<String, String> homePageTags = null;

	public static String OmnitureExcelFilePath = EssencePOCUtils
			.getProjectpath() + "testData/omniture2.xlsx";
	public static WriteLog writelogger = new WriteLog();
	public static org.apache.log4j.Logger logger = writelogger.getLogger();

	public static HashMap<String, String> readUTaghomePageTags(WebDriver driver)
			throws Exception {

		homePageTags = new HashMap<String, String>();

		Thread.sleep(20000);
		logger.info("read_utag_data tag homePageTags");
		logger.info("Start of read_utag_datatag homePageTags method");
		// ReadExcelValues.writeToEelfile("channel",excelFilePath,"A11");
		logger.debug("read_utag_data tag homePageTags" + driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		logger.debug("read_utag_data tag homePageTags" + js);
		
		String utag_data_channel = (String) js.executeScript("return utag_data.channel");
		logger.info("utag_data_channel :" + utag_data_channel);
		
		String utag_data_content_id = (String) js.executeScript("return utag_data.content_id");
		logger.debug("utag_data.content_id  :" + utag_data_content_id);
		
		String utag_data_friendly_url = (String) js.executeScript("return utag_data.friendly_url");
		logger.debug("utag_data.friendly_url  :" + utag_data_friendly_url);
		
		String utag_data_page_name = (String) js.executeScript("return utag_data.page_name");
		logger.debug("utag_data.prop8  :" + utag_data_page_name);
		
		String utag_data_publish_date = (String) js.executeScript("return utag_data.publish_date");
		logger.debug("utag_data.publish_date  :" + utag_data_publish_date);
		
		String utag_data_site_display_format = (String) js.executeScript("return utag_data.site_display_format");
		logger.debug("utag_data.site_display_format  :" + utag_data_site_display_format);
		
		String utag_data_site_section1 = (String) js.executeScript("return utag_data.site_section1");
		logger.debug("utag_data.site_section1  :" + utag_data_site_section1);
		
		String utag_data_tag_keywords = (String) js.executeScript("return utag_data.tag_keywords");
		logger.debug("utag_data.tag_keywords  :" + utag_data_tag_keywords);
		
		String utag_data_template_type = (String) js.executeScript("return utag_data.template_type");
		logger.debug("utag_data.template_type  :" + utag_data_template_type);
		/*
		String utag_data_prop26 = (String) js.executeScript("return utag_data.prop26");
		logger.debug("utag_data.prop26  :" + utag_data_prop26);
		
		String s_code = (String) js.executeScript("return s_code");
		logger.debug("s_code  :" + s_code);*/
		


		logger.info("End of readUtaghomePageTags method");

		// Populating the string values to build a string array
		
		homePageTags.put("utag_data.pageName", utag_data_channel.toUpperCase());
		homePageTags.put("utag_data.prop1  :", utag_data_content_id);
		homePageTags.put("utag_data.friendly_url  :", utag_data_friendly_url);
		homePageTags.put("utag_data.page_name  :", utag_data_page_name);
		homePageTags.put("utag_data.publish_date  :", utag_data_publish_date);
		homePageTags.put("utag_data.site_display_format  :", utag_data_site_display_format);
		homePageTags.put("utag_data.site_section1 :", utag_data_site_section1);
		homePageTags.put("utag_data.tag_keywords  :", utag_data_tag_keywords);
		homePageTags.put("utag_data.template_type  :", utag_data_template_type);
		
		ReadExcelValues.writeToExcelelFile(homePageTags, OmnitureExcelFilePath,
				0);
		return homePageTags;

	}

}
