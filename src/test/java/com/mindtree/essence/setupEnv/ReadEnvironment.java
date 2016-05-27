package com.mindtree.essence.setupEnv;

/**
 * @author M1027330
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mindtree.essence.utilities.WriteLog;

public class ReadEnvironment {

	public static WriteLog writelogger = new WriteLog();
	public static org.apache.log4j.Logger logger = writelogger.getLogger();

	public String readBrowserName(String inputFile) throws IOException {
		return readFromExcelFile(inputFile, 0);
	}

	public String readHomePageURL(String inputFile) throws IOException {
		return readFromExcelFile(inputFile, 1);
	}

	public List<String[]> readSeoTagComparisionURLs(String inputFile) throws IOException {
		String url1 = "";
		String url2 = "";
		List<String[]> seoTagComparisionUrlList = new ArrayList<String[]>();
		File inputWorkbook = new File(inputFile);
		FileInputStream FileInput = new FileInputStream(inputWorkbook);
		HSSFWorkbook workBook = new HSSFWorkbook(FileInput);

		HSSFSheet inputSheet = workBook.getSheet(workBook.getSheetName(0));
		logger.info("inputSheet.getLastRowNum(): " + inputSheet.getLastRowNum());
		for (int i = 5; i <= inputSheet.getLastRowNum(); i++) {
			url1 = inputSheet.getRow(i).getCell(0).getStringCellValue();
			url2 = inputSheet.getRow(i).getCell(1).getStringCellValue();
			logger.info("i: " + i + "\t url1: " + url1 + "\t url2: " + url2);
			if ((url1!=null && !url1.equals("")) && (url2!=null && !url2.equals(""))) {
				seoTagComparisionUrlList.add(new String[]{url1, url2});
			}
		}

		workBook.close();

		return seoTagComparisionUrlList;
	}

	private String readFromExcelFile(String inputFile, int position) throws IOException {
		String strData = "";
		File inputWorkbook = new File(inputFile);
		FileInputStream FileInput = new FileInputStream(inputWorkbook);
		HSSFWorkbook workBook = new HSSFWorkbook(FileInput);

		strData = workBook.getSheet(workBook.getSheetName(0)).getRow(1).getCell(position).getStringCellValue();

		logger.info("strData: " + strData);
		workBook.close();

		return strData;
	}

}
