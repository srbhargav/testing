/**
 * This class is for reading excel using Apache poi sheet by referring to the excel path and sheet number
 */


package com.mindtree.essence.utilities;

/**
 * @author M1027330
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;







public class ReadExcelValues {

	/**
	 * This Method takes the below parameters and returns the value in cellRef.
	 * 
	 * @param xlFilePath
	 * @param xLSheetNumber
	 * @param cellRef
	 * @return
	 * @throws Exception
	 * 
	 */

	public static String cellFormulaValue = null;
	public static String cellValue = null;
	public static WriteLog writelogger = new WriteLog();
	public static org.apache.log4j.Logger logger = writelogger.getLogger();

	public static String getExcelValues(String xlFilePath, int xLSheetNumber,
			String cellRef) throws Exception {

		{
	logger.info("File: " + xlFilePath);

			FileInputStream file = new FileInputStream(new File(xlFilePath));

			// Create Workbook instance holding reference to .xls file
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			FormulaEvaluator evaluator = workbook.getCreationHelper()
					.createFormulaEvaluator();
			HSSFSheet sheet = workbook.getSheetAt(xLSheetNumber);
			String sheetName = workbook.getSheetAt(xLSheetNumber)
					.getSheetName();
	logger.info("Sheet Name is" + sheetName);

			CellReference cellReference = new CellReference(cellRef);
			Row row = sheet.getRow(cellReference.getRow());
			Cell cell = row.getCell(cellReference.getCol());
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					cellValue = String.valueOf(cell.getBooleanCellValue())
							.trim();
					return cellValue;

				case Cell.CELL_TYPE_NUMERIC:
					cellValue = String.valueOf(cell.getNumericCellValue())
							.trim();
					return cellValue;

				case Cell.CELL_TYPE_STRING:
					cellValue = cell.getStringCellValue().trim();
					return cellValue;

				case Cell.CELL_TYPE_BLANK:
					break;

				case Cell.CELL_TYPE_ERROR:
			logger.info(cell.getErrorCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					cellValue = cell.getRichStringCellValue().toString();
					return cellValue;

				}
			}

			return cellValue;
		}
	}
	public static int writeToExcelelFile(HashMap<String, String> Data,
			String FilePath, int rows) throws Exception {

		
logger.info("Inside the writetoExcel 2");
		FileOutputStream fileOut = new FileOutputStream(FilePath);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet worksheet = workbook.createSheet("sheet1");
		
		Row header = worksheet.createRow(rows);
		Set<String> keys = Data.keySet();
		
		worksheet.createRow(rows);
		rows = worksheet.getLastRowNum() + rows;
logger.info("rows:" + rows);

		HSSFCell cell =null;
		if (cell == null) {

			for (String key : keys) {
		logger.info("Omniture tag content:  :" + keys);

				XSSFRow row1 = worksheet.createRow(rows);
				XSSFCell cellA1 = row1.createCell(0);
				cellA1.setCellValue((key));

				XSSFCell cellB1 = row1.createCell(1);
				if (Data.get(key) != null) {
					cellB1.setCellValue(Data.get(key));

				} else {
					cellB1.setCellValue("null");

				}
								rows++;
				

			}

		}
logger.info("number of rows filled :" + rows);
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
			return rows;

	}


}
