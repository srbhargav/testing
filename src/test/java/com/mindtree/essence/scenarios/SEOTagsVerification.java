package com.mindtree.essence.scenarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mindtree.essence.scenarios.tag.ExcelResultData;
import com.mindtree.essence.scenarios.tag.KeyValuePojo;
import com.mindtree.essence.scenarios.tag.MetaComparision;
import com.mindtree.essence.scenarios.tag.MetaTag;
import com.mindtree.essence.utilities.EssencePOCUtils;
import com.mindtree.essence.utilities.WebElementsLocator;
import com.mindtree.essence.utilities.WriteLog;

public class SEOTagsVerification {

	public static WriteLog writelogger = new WriteLog();
	public static org.apache.log4j.Logger logger = writelogger.getLogger();
//	private static boolean testStatus;
	private static int noOfTure;
	private static int noOfFalse;

	private static CellStyle titleStyle;
	private static CellStyle headerStyle;
	private static CellStyle bottomRowStyle;
	private static CellStyle invalidDataHighlightStyle;

	private static Set<MetaTag> loadUrl(WebDriver driver) throws IOException {
		Set<MetaTag> tagsRead = new HashSet<MetaTag>();
		List<WebElement> selectedElements = new ArrayList<WebElement>();
		selectedElements.addAll(driver.findElements(By.xpath(WebElementsLocator.metaTag)));

		logger.info("==>" + selectedElements.size());

		MetaTag mt = new MetaTag();
		for (WebElement selEle : selectedElements) {
			mt = new MetaTag(selEle.getAttribute("name"), selEle.getAttribute("property"), selEle.getAttribute("content"), selEle.getAttribute("http-equiv"));
			tagsRead.add(mt);
		}
		return tagsRead;
	}

	private static List<MetaComparision> comparisionsList = null;
	private static List<ExcelResultData> consolidatedCompResultData = null;

	private static Set<MetaTag> url1TagsRead;
	private static Set<KeyValuePojo> url1KeyAttribValueAttribSet;
	private static String url1;
	public static void loadUrl1(String url, WebDriver driver) throws IOException {
		url1 = url;
		url1TagsRead = loadUrl(driver);
	}

	private static Set<MetaTag> url2TagsRead;
	private static Set<KeyValuePojo> url2KeyAttribValueAttribSet;
	private static String url2;
	public static void loadUrl2(String url, WebDriver driver) throws IOException {
		url2 = url;
		url2TagsRead = loadUrl(driver);
	}

	public static boolean isNullOrEmpty (String str) {
		return (str==null||str.equals("")) ? true : false;
	}

	public static String convertToValueStr (String str) {
		return (str==null) ? null : "=\"" + str + "\"";
	}

	public static void loadKeyValueData(int position) {
		KeyValuePojo kvPojo = new KeyValuePojo();

		for (MetaTag metaTag : url1TagsRead) {
			kvPojo = new KeyValuePojo(metaTag.getAttribValue(position), metaTag.getContent());
			if (!(isNullOrEmpty(kvPojo.getKey())) && kvPojo.getValue()!=null) {
				url1KeyAttribValueAttribSet.add(kvPojo);
			}
		}
		for (MetaTag metaTag : url2TagsRead) {
			kvPojo = new KeyValuePojo(metaTag.getAttribValue(position), metaTag.getContent());
			if (!(isNullOrEmpty(kvPojo.getKey())) && kvPojo.getValue()!=null) {
				url2KeyAttribValueAttribSet.add(kvPojo);
			}
		}
	}

	public static ArrayList<ExcelResultData> compareSelectedAttribute(Integer[] position) {
		// Following logic is to remove duplicate
		// Comparing Property and identifying unique values
		KeyValuePojo kvPojo = new KeyValuePojo();
		HashSet<String> keyStrSet = null;
		HashSet<String> valueStrSet = null;
		HashSet<String> value2StrSet = null;
		HashSet<String> removeValueStr = null;

		ArrayList<ExcelResultData> excelData = new ArrayList<ExcelResultData>();
		Map<String, HashSet<String>> keyAttribValueAttribsMap = new HashMap<String, HashSet<String>>();
		Map<String, HashSet<String>> tempKeyAttribValueAttribsMap = new HashMap<String, HashSet<String>>();

		url1KeyAttribValueAttribSet = new HashSet<KeyValuePojo>();
		url2KeyAttribValueAttribSet = new HashSet<KeyValuePojo>();
		for (int i = 0; i < position.length; i++) {
			loadKeyValueData(position[i]);
		}

		for (KeyValuePojo keyValuePojo : url1KeyAttribValueAttribSet) {
			valueStrSet = new HashSet<String>();
			if (keyAttribValueAttribsMap.containsKey(keyValuePojo.getKey())) {
				valueStrSet = keyAttribValueAttribsMap.get(keyValuePojo.getKey());
			}
			valueStrSet.add(keyValuePojo.getValue());
			keyAttribValueAttribsMap.put(keyValuePojo.getKey(), valueStrSet);
		}
		keyStrSet = new HashSet<String>();
		for (String str : keyAttribValueAttribsMap.keySet()) {
			keyStrSet.add(str);
		}
//		keyStrSet = (HashSet<String>) keyAttribValueAttribMap.keySet();
		for (String ky : keyStrSet) {
			valueStrSet = keyAttribValueAttribsMap.get(ky);
			removeValueStr = new HashSet<String>();
			for (String valStr : valueStrSet) {
				kvPojo = new KeyValuePojo(ky, valStr);
				if (url2KeyAttribValueAttribSet.contains(kvPojo)) {
					url2KeyAttribValueAttribSet.remove(kvPojo);
					excelData.add(new ExcelResultData(convertToValueStr(kvPojo.getKey()), convertToValueStr(kvPojo.getValue()), convertToValueStr(kvPojo.getValue()), "TRUE"));
					noOfTure++;
					removeValueStr.add(valStr);
				}
			}
			for (String valStr : removeValueStr) {
				valueStrSet.remove(valStr);
			}
			keyAttribValueAttribsMap.put(ky, valueStrSet);
		}
		for (KeyValuePojo keyValuePojo : url2KeyAttribValueAttribSet) {
			valueStrSet = new HashSet<String>();
			if (tempKeyAttribValueAttribsMap.containsKey(keyValuePojo.getKey())) {
				valueStrSet = tempKeyAttribValueAttribsMap.get(keyValuePojo.getKey());
			}
			valueStrSet.add(keyValuePojo.getValue());
			tempKeyAttribValueAttribsMap.put(keyValuePojo.getKey(), valueStrSet);
		}
		keyStrSet = new HashSet<String>();
		for (String str : keyAttribValueAttribsMap.keySet()) {
			keyStrSet.add(str);
		}
		for (String str : tempKeyAttribValueAttribsMap.keySet()) {
			keyStrSet.add(str);
		}
//		keyStrSet = (HashSet<String>) keyAttribValueAttribMap.keySet();
//		keyStrSet.addAll((HashSet<String>) tempKeyAttribValueAttribMap.keySet());
		for (String ky : keyStrSet) {
			logger.info("ky: " + ky);
			boolean firstRow = false;
			valueStrSet = new HashSet<String>();
			value2StrSet = new HashSet<String>();
			if (keyAttribValueAttribsMap.containsKey(ky)) {
				valueStrSet = keyAttribValueAttribsMap.get(ky);
			}
			if (tempKeyAttribValueAttribsMap.containsKey(ky)) {
				value2StrSet = tempKeyAttribValueAttribsMap.get(ky);
			}
			ArrayList<String> valueStrLst = new ArrayList<String>(valueStrSet);
			ArrayList<String> value2StrLst = new ArrayList<String>(value2StrSet);
			for (int i = 0; ((i < valueStrLst.size()) || (i < value2StrLst.size())); i++) {
				if (!firstRow) {
					excelData.add(new ExcelResultData(convertToValueStr(ky), (i<valueStrLst.size()?convertToValueStr(valueStrLst.get(i)):""), (i<value2StrLst.size()?convertToValueStr(value2StrLst.get(i)):""), "FALSE"));
					noOfFalse++;
//					testStatus = false;
					firstRow = true;
				} else {
					excelData.add(new ExcelResultData("", (i<valueStrLst.size()?convertToValueStr(valueStrLst.get(i)):""), (i<value2StrLst.size()?convertToValueStr(value2StrLst.get(i)):""), ""));
				}
			}
		}

		logger.debug("excelData:");
		for (ExcelResultData dat : excelData) {
			logger.debug(dat);
		}

		return excelData;
	}

	public static void loadExcelCellStyles(HSSFWorkbook workbook) {
		Font boldFont = workbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		titleStyle = workbook.createCellStyle();
		titleStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setFont(boldFont);
		headerStyle = workbook.createCellStyle();
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setFont(boldFont);
		bottomRowStyle = workbook.createCellStyle();
		bottomRowStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		invalidDataHighlightStyle = workbook.createCellStyle();
		invalidDataHighlightStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
		invalidDataHighlightStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	}

	public static boolean compareSEOTags(WebDriver driver, List<String[]> seoTagComparisionURLs) {
		comparisionsList = new ArrayList<MetaComparision>();
		comparisionsList.add(new MetaComparision("Property Comparision", "property", "content", new Integer[] {MetaTag.ATTRIB_PROPERTY}));
		comparisionsList.add(new MetaComparision("Name Comparision", "name", "content", new Integer[] {MetaTag.ATTRIB_NAME}));
		comparisionsList.add(new MetaComparision("Property/Name Comparision", "property/name", "content", new Integer[] {MetaTag.ATTRIB_PROPERTY, MetaTag.ATTRIB_NAME}));

//		testStatus = true;
		consolidatedCompResultData = new ArrayList<ExcelResultData>();

		HSSFWorkbook workbook = new HSSFWorkbook();
		loadExcelCellStyles(workbook);

		HSSFSheet metaTagCompConsolidatedSheet = workbook.createSheet("MetaTagComparision");

		int rownum = 0;
		Row row = null;
		row = metaTagCompConsolidatedSheet.createRow(rownum++);
		createRow(row, "", "", "", "");
		row = metaTagCompConsolidatedSheet.createRow(rownum++);
		createRow(row, "SEO Tags Comparision", "", "", "");
		setRowStyle(titleStyle, row);
		row = metaTagCompConsolidatedSheet.createRow(rownum++);
		createRow(row, "URL 1", "URL 2", "# of TRUE", "# of FALSE");
		setRowStyle(headerStyle, row);

		try {
			for (int i = 0; i < seoTagComparisionURLs.size(); i++) {
				compareSEOTagsFor2Urls(driver, workbook, "Comparision_" + Integer.toString(i+1), seoTagComparisionURLs.get(i)[0], seoTagComparisionURLs.get(i)[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		for (ExcelResultData exConComData : consolidatedCompResultData) {
			row = metaTagCompConsolidatedSheet.createRow(rownum++);
			createRow(row, exConComData.getCol1(), exConComData.getCol2(), exConComData.getCol3(), exConComData.getCol4());
		}
		row = metaTagCompConsolidatedSheet.createRow(rownum++);
		createRow(row, "", "", "", "");
		setRowStyle(bottomRowStyle, row);
		metaTagCompConsolidatedSheet.autoSizeColumn(0);
		metaTagCompConsolidatedSheet.autoSizeColumn(1);
		metaTagCompConsolidatedSheet.autoSizeColumn(2);
		metaTagCompConsolidatedSheet.autoSizeColumn(3);
		try {
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy_MM_dd-hh_mm_ss");
			String outputExcelFilePath = EssencePOCUtils.getProjectpath() + "testData/seotagOUTPUT-" + ft.format(new Date()) + ".xls";
			File file = new File(outputExcelFilePath);
			FileOutputStream outputExcelFile = new FileOutputStream(file);
			workbook.write(outputExcelFile);
			workbook.close();
			outputExcelFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

//		return testStatus;
		return true;
	}

	public static void compareSEOTagsFor2Urls(WebDriver driver, HSSFWorkbook workbook, String sheetName, String url_1, String url_2) throws IOException {
		driver.navigate().to(url_1);
		loadUrl1(url_1, driver);
		driver.navigate().to(url_2);
		loadUrl2(url_2, driver);
		noOfTure = 0;
		noOfFalse = 0;
		logger.info("compareSEOTagsFor2Urls: url1TagsRead: " + url1TagsRead.size());
		logger.info("compareSEOTagsFor2Urls: url2TagsRead: " + url2TagsRead.size());

		logger.info(" SEO - inside Individual Comparision Sheet creation..");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		HSSFSheet individualCompSheet = workbook.createSheet(sheetName);

		int rownum = 0;
		Row row = null;
///////////////////////////
		for (MetaComparision comparision : comparisionsList) {
		row = individualCompSheet.createRow(rownum++);
		createRow(row, "", "", "", "");
		row = individualCompSheet.createRow(rownum++);
			createRow(row, comparision.getCompTitle(), url1, url2, "");
		setRowStyle(titleStyle, row);
		row = individualCompSheet.createRow(rownum++);
			createRow(row, comparision.getCompKeyPropertyNames(), comparision.getCompValuePropertyName(), comparision.getCompValuePropertyName(), "Result");
		setRowStyle(headerStyle, row);
			for (ExcelResultData data : compareSelectedAttribute(comparision.getCompKeyPropertyPositions())) {
			row = individualCompSheet.createRow(rownum++);
			createDataRow(row, (String)data.getCol1(), (String)data.getCol2(), (String)data.getCol3(), (String)data.getCol4());
		}
		row = individualCompSheet.createRow(rownum++);
		createRow(row, "", "", "", "");
		setRowStyle(bottomRowStyle, row);
		}
///////////////////////////
		individualCompSheet.autoSizeColumn(0);
		individualCompSheet.autoSizeColumn(1);
		individualCompSheet.autoSizeColumn(2);
		individualCompSheet.autoSizeColumn(3);

		consolidatedCompResultData.add(new ExcelResultData(url_1, url_2, Integer.toString(noOfTure), Integer.toString(noOfFalse)));
		logger.info("Excel Sheet '" + sheetName + "' written successfully..");
	}

	public static void createRow(Row row, String col0, String col1, String col2, String col3) {
		Cell cell = row.createCell(0);
		cell.setCellValue(col0);
		cell = row.createCell(1);
        cell.setCellValue(col1);
		cell = row.createCell(2);
        cell.setCellValue(col2);
		cell = row.createCell(3);
        cell.setCellValue(col3);
	}

	public static void createDataRow(Row row, String col0, String col1, String col2, String col3) {
		Cell cell = row.createCell(0);
		cell.setCellValue(col0);
		cell = row.createCell(1);
        cell.setCellValue(col1);
		cell = row.createCell(2);
        cell.setCellValue(col2);
		cell = row.createCell(3);
        cell.setCellValue(col3);
        if (col3!=null && col3.equalsIgnoreCase("FALSE")) {
        	cell.setCellStyle(invalidDataHighlightStyle);
		}
	}

	public static void setRowStyle(CellStyle style, Row row) {
		row.getCell(0).setCellStyle(style);
		row.getCell(1).setCellStyle(style);
		row.getCell(2).setCellStyle(style);
		row.getCell(3).setCellStyle(style);
	}

}
