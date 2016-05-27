/**
 * This Class has Generates the Modulewise reports, captures the screenshots for failed test cases
 * 
 */
package com.mindtree.essence.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.mindtree.essence.utilities.EssencePOCUtils;

public class ModuleReports {
	/**
	 * @author Shankar
	 * 
	 */
	// public String s="Export_to_CSV";
	private static String HTMLFolderPath = EssencePOCUtils.getProjectpath()
			+ "HTMLReports";
	public static File ModuleReportFile;
	public static String popup = "</script><script>function basicPopup(url) { popupWindow = window.open(url,'popUpWindow','height=500,width=500,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes'); }</script>";
	public static int PASSCOUNT = 0, FAILCOUNT = 0;
	public static long PASSTIME = 0, FAILTIME = 0;
	public final static String date = getDate();
	public final static String timeInHours = getTime();
	public static String rePorterFolderPath = HTMLFolderPath + "/" + date + "/"
			+ "OverAll_Report_" + timeInHours;
	public static String PassHeader = "", passResultUpdate_EachTest = "",
			FailHeader = "", FailResultUpdate_EachTest = "";
	public static String Footer = "</br></head>";
	public static String BackButton = "<a  href=" + '\u0022'
			+ ".\\SummaryReport.html" + '\u0022'
			+ "> BACK TO SUMMARY REPORT</a>";
	public static String exportReport = "";
	public static String ModuleReportHeader = "";

	public static String getDate() {
		/**
		 * This Method Gets the Todays date.
		 */
		String getdate = new SimpleDateFormat("dd-MMM-YYYY").format(Calendar
				.getInstance().getTime());
		return getdate;
	}

	public static String getTime() {
		/**
		 * This method gets the Current time in dd-mm-YYYY-HH-MM format.
		 */
		String timeReportGenerated = new SimpleDateFormat("dd-MMM-YYYY-HH-mm")
				.format(Calendar.getInstance().getTime());
		return timeReportGenerated;
	}

	public static void createModuleHTMLFolderFile(String ModuleName)
			throws Exception {
		/**
		 * This method create the base folder for each module report HTML file.
		 */
		File HTMLFolder;
		String ModulePath;
		HTMLFolder = new File(rePorterFolderPath);
		HTMLFolder.mkdirs();
		ModulePath = HTMLFolder + "/" + ModuleName + ".html";
		ModuleReportFile = new File(ModulePath);
		ModuleReportFile.createNewFile();
	}

	public static void writeModuleHTMLFile(String passHeader2,
			String passUpdate2, String failHeader2, String failUpdate2,
			String Module, long moduleTimeTaken) throws Exception {
		/**
		 * This method writes the HTML report to HTML file of each module and
		 * also sends the data to overall module.
		 */
		String tableName = "";
		if (PASSCOUNT == 0) {
			tableName = "#failtable";
		} else {
			tableName = "#detailtable";
		}
		exportReport = "<script  src" + "="
				+ '\u0022'
				+ "http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"
				+ '\u0022'
				+ "></script><script src"
				+ "="
				+ '\u0022'
				+ "../../RequiredJS_File/html5csv.js"
				+ '\u0022'
				+ ""
				+ "></script>"
				+ "<script>"
				+ "$(document).ready(function() {"
				+ " $('"
				+ tableName
				+ " table').each(function() {var $table = $(this);var $button = $("
				+ '\u0022'
				+ "<button type='button'>"
				+ '\u0022'
				+ ");$button.text("
				+ '\u0022'
				+ "Export_to_CSV"
				+ '\u0022'
				+ ");"
				+ "$button.insertBefore($table);$button.click(function() {CSV.begin('table').download('"
				+ Module + timeInHours + ".csv" + "').go();});});})"
				+ "</script>";
		ModuleReportHeader = "<html>" + exportReport + popup;
		String OverAllLog = ModuleReportHeader + passHeader2 + passUpdate2
				+ "</tbody></table>" + failHeader2 + failUpdate2
				+ "</tbody></table><br></div>" + BackButton + "</body></html>";
		FileUtils.writeStringToFile(ModuleReportFile, OverAllLog);
		OverAllReport.upDateOverAllModule(Module, PASSCOUNT, FAILCOUNT,
				rePorterFolderPath, PASSTIME, FAILTIME, moduleTimeTaken);
		PASSCOUNT = 0;
		FAILCOUNT = 0;
		PASSTIME = 0;
		FAILTIME = 0;
		PassHeader = "";
		FailHeader = "";
		passResultUpdate_EachTest = "";
		FailResultUpdate_EachTest = "";
	}

	public static void PassFailHtmlStringBuilder(String className,
			ITestResult result, WebDriver driver, String description, long time)
			throws Exception {
		/**
		 * This method will run after each test Generates the report for each
		 * test and keeps on appending till all the tests completes the
		 * execution
		 */
		String TimeTaken = "";
		long time1 = time;
		TimeTaken = MilliSecondsToHMS.TimeConvert(time1);
		if (result.isSuccess()) {
			/**
			 * Pass header
			 */
			PassHeader = "<head>"
					+ "<meta http-equiv=Content-Type content=text/html; charset=windows-1252>"
					+ "<style>body{background-color: #FFFFF0; max-width: 960px; margin: 0px auto; font-family: Arial,Verdana,Helvetica,sans-serif; font-size: 14px;}table,th,td { border: none;}th, td {padding: 5px;text-align: left;}table{width: 100%;}a{text-decoration: none;font-size:14px;}h2{border-bottom: 2px solid #ccc;font-size: 24px;margin-bottom: 0;margin-top: 50px;padding-bottom: 10px;}td h3{font-weight: normal;}h3{ font-size: 14px;margin: 0;}table#t01 { width: 100%; background-color: 3333CC;}</style>"
					+ "</head>" + "<body>" + "<font color=green></font>"
					+ "<h2><font color=green><p1 align=center>PASS TESTS :"
					+ className
					+ "</p1></font>"
					+ "</h2>"
					+ "<br><style>th{background-color:66A11F;}</style>"
					+ "<div id=detailtable><table><colgroup><col width=50><col width=250><col width=100><col width=50>"
					+ "</colgroup><tbody>"
					+ "<tr style=background-color: #65A01F;>"
					+ "<th>"
					+ "<h3><font color=white>S.NO</font></h3>"
					+ "</th>"
					+ "<th style=width:40%>"
					+ "<h3><font color=white>EXECUTED TESTS</font></h3>"
					+ "</th>"
					+ "<th style=width:40%>"
					+ "<h3><font color=white>DESCRIPTION</font></h3>"
					+ "</th>"
					+ "<th>"
					+ "<h3><font color=white>STATUS</font></h3>"
					+ "</th>"
					+ "<th style=width:20%>"
					+ "<h3><font color=white>TIME TAKEN</font></h3>"
					+ "</th>"
					+ "</tr>";
			PASSCOUNT++;
			PASSTIME = PASSTIME + time;
			if (PASSCOUNT == 1) {
				/**
				 * Pass Results generation.
				 */
				passResultUpdate_EachTest = "<tr style=background-color:#DEDEDE><td hight=40><font color=black><h3>"
						+ PASSCOUNT
						+ "</h3></font></td><td style=width:40%><font color=black><h3>"
						+ result.getMethod()
								.toString()
								.substring(
										(result.getMethod().toString()
												.indexOf(".")) + 1,
										result.getMethod().toString()
												.indexOf("("))
						+ "</h3></font><font color=maroon></font></td>"
						+ "<td style=width:40%><font color=black><h3>"
						+ description
						+ "</h3></font><font color=maroon></font></td><td><font color=green><h3>PASS</h3></font><font color=maroon></font></td><td><font color=black><h3></font><font color=maroon></font>"
						+ TimeTaken + "</font></h3></font></td></tr>";
				;
			} else {
				/**
				 * Pass Result appending to previous pass results.
				 */
				String PassResultUpdate_EachTest_Append = "<tr style=background-color:#DEDEDE><td hight=40><font color=black><h3>"
						+ PASSCOUNT
						+ "</h3></font></td><td style=width:40%><font color=black><h3>"
						+ result.getMethod()
								.toString()
								.substring(
										(result.getMethod().toString()
												.indexOf(".")) + 1,
										result.getMethod().toString()
												.indexOf("("))
						+ "</h3></font><font color=maroon></font></td>"
						+ "<td style=width:40%><font color=black><h3>"
						+ description
						+ "</h3></font><font color=maroon></font></td><td><font color=green><h3>PASS</h3></font><font color=maroon></font></td><td><font color=black><h3></font><font color=maroon></font>"
						+ TimeTaken + "</font></h3></font></td></tr>";
				passResultUpdate_EachTest = passResultUpdate_EachTest
						+ PassResultUpdate_EachTest_Append;
			}
		} else {
			/**
			 * Fail result header and captures the screenshot for failed tests.
			 */
			FailHeader = "<style>body{background-color: #FFFFF0 ;}table, th,td { border: none;}th, td {padding: 5px;text-align: left;}table#t01 { width: 100%; background-color: 3333CC;}</style><font color=RED></font><h2><font color=RED><p1 align=center>FAILED TESTS:"
					+ className
					+ "</p1></font></h2></div><br><style>th{background-color:66A11F; text-align: center;}</style><div id=failtable><table><colgroup><col width=50><col width=250><col width=100><col width=50></colgroup><tbody><tr><th><h3><font color=white>S.NO</font></h3></th><th style=width:50%><h3><font color=white>EXECUTED TESTS</font></h3></th><th style=width:50%><h3><font color=white>DESCRIPTION</font></h3></th><th><h3><font color=white>STATUS</font></h3></th><th style=width:30%><h3><font color=white>TIME TAKEN</font></h3></th><th><h3><font color=white>SCREENSHOTS</font></h3></th></tr>";
			CaptureScreenShot.takeScreenShoot(driver, result.getMethod());
			FAILCOUNT++;
			FAILTIME = FAILTIME + time;
			String Screenshot = "";
			Screenshot = "."
					+ "\\"
					+ "Screenshots/"
					+ result.getMethod()
							.toString()
							.substring(
									(result.getMethod().toString().indexOf(".")) + 1,
									result.getMethod().toString().indexOf("("))
					+ timeInHours + ".png";
			if (FAILCOUNT == 1) {
				/**
				 * Fail report generation.
				 */
				FailResultUpdate_EachTest = "<tr style=background-color:#DEDEDE><td hight=50><font color=black><h3>"
						+ FAILCOUNT
						+ "</h3></font></td><td><font color=black><h3>"
						+ result.getMethod()
								.toString()
								.substring(
										(result.getMethod().toString()
												.indexOf(".")) + 1,
										result.getMethod().toString()
												.indexOf("("))
						+ "</h3></font><font color=maroon></font></td>"
						+ "<td style=width:40%><font color=black><h3>"
						+ description
						+ "</h3></font><font color=maroon></font></td><td><font color=red><h3>FAIL</h3></font><font color=maroon></font></td><td><font color=black><h3></font><font color=maroon></font>"
						+ TimeTaken
						+ "</font></h3></font></td><td><font color=white><h4><a onclick="
						+ '\u0022'
						+ "basicPopup(this.href); return false"
						+ '\u0022'
						+ " href="
						+ Screenshot
						+ ">Click Here For Screenshot</a><font color=white></font></h4></font></td></tr>";
				;
			} else {
				/**
				 * Fail Result appending to previous Fail results.
				 */
				String FailResultUpdate_Append = "<tr style=background-color:#DEDEDE><td hight=50><font color=black><h3>"
						+ FAILCOUNT
						+ "</h3></font></td><td><font color=black><h3>"
						+ result.getMethod()
								.toString()
								.substring(
										(result.getMethod().toString()
												.indexOf(".")) + 1,
										result.getMethod().toString()
												.indexOf("("))
						+ "</h3></font><font color=maroon></font></td>"
						+ "<td style=width:40%><font color=black><h3>"
						+ description
						+ "</h3></font><font color=maroon></font></td><td><font color=red><h3>FAIL</h3></font><font color=maroon></font></td><td><font color=black><h3></font><font color=maroon></font>"
						+ TimeTaken
						+ "</font></h3></font></td><td><font color=white><h4><a onclick="
						+ '\u0022'
						+ "basicPopup(this.href); return false"
						+ '\u0022'
						+ " href="
						+ Screenshot
						+ ">Click Here For Screenshot</a><font color=white></font></h4></font></td></tr>";
				;
				FailResultUpdate_EachTest = FailResultUpdate_EachTest
						+ FailResultUpdate_Append;
			}

		}
	}
}
