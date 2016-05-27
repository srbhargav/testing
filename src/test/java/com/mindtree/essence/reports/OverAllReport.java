package com.mindtree.essence.reports;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterSuite;



public class OverAllReport {
	
	public static int totalsteps,passStep,failStep;
	public static String Header="";
	public static int ModuleCount=0;
	public static long TotalTimeTaken=0;
	public static String TotalTimeTakenString="";
	public static String	OverallExportReport="";
	public static String footer="</table></head></body></html>";
	public static String OverAllPath;
	public static String moduleReportHeader="<head><style>body{background-color: #FFFFF0  ;}table,th,td { border: none;}th, td {padding: 5px;text-align: left;}table#t01 { width: 100%; background-color: 3333CC;}</style></head><body><font color=black><h2><p1 align=center>All Module Summary Reports</p1></font></h2></div><br><style>th{background-color:00CCFF}</style><div id=detailtable><table><col width=50><col width=250><col width=100><col width=100><tr style=background-color:#CCCCFF><th><H3><font color=black>S.NO</font></H3></th><th><H3><font color=black>MODULE NAME</font></H3></th><th><H3><font color=GREEN>PASS</font></H3></th><th><H3><font color=RED>FAIL</font></H3></th><th><H3><font color=blue>TOTAL</font></H3></th><th><H3><font color=blue>TIME_TAKEN</font></H3></th></tr>";
	public static String updateModule="";
	public static void upDateOverAllModule(String ModuleName, int pASSCOUNT, int fAILCOUNT, String path,Long passtime, long failtime, long moduleTimeTaken){
		ModuleCount++;
		TotalTimeTaken=TotalTimeTaken+passtime+failtime;
		String	updateModuleNew="<tr style=background-color:#D2B48C><td hight=50><font color=black><H3>"+ModuleCount+"</H3></font></td><td><font color=black><H3><a href="+"."+"/"+ModuleName+".html"+">"+ModuleName+"</a></H3></font><font color=maroon></td><td><font color=green><H3>"+pASSCOUNT+"</H3></font><font color=maroon></td><td><font color=blue><H3><font color=red>"+fAILCOUNT +"</font></a></H3></td><td><font color=blue><H3><font color=blue>"+(pASSCOUNT+fAILCOUNT)+"</font></a></H3></td><td><font color=blue><H3><font color=blue>"+MilliSecondsToHMS.TimeConvert(moduleTimeTaken)+"</font></a></H3></td></tr>";
		OverallExportReport="<script src"+"="+'\u0022'+"http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"+'\u0022'+"></script><script src"+"="+'\u0022'+"../../RequiredJS_File/html5csv.js"+'\u0022'+""+"></script>"
				+"<script>"
				+"$(document).ready(function() {"
				+" $('#overviewtable table').each(function() {var $table = $(this);var $button = $("+'\u0022'+"<button type='button'>"+'\u0022'+");$button.text("+'\u0022'+"Export_to_CSV"+'\u0022'+");"
				+ "$button.insertBefore($table);$button.click(function() {CSV.begin('table').download('"+"OverAll_Report"+ModuleReports.timeInHours+".csv"+"').go();});});})"
				+"</script>";
				updateModule=updateModule+updateModuleNew;
				passStep=passStep+pASSCOUNT;
				failStep=failStep+fAILCOUNT;
				totalsteps=passStep+failStep;
				OverAllPath=path;
				System.out.println("Overall Path Read :"+OverAllPath);
				Header="<html><head>"+OverallExportReport+"<style>body{background-color: #FFFFF0; max-width: 960px; margin: 0px auto; font-family: Arial,Verdana,Helvetica,sans-serif; font-size: 14px;}table,th,td { border: none;}th, td {padding: 5px;text-align: left;}table{width: 100%;}a{text-decoration: none;font-size:14px;}h2{border-bottom: 2px solid #ccc;font-size: 24px;margin-bottom: 0;margin-top: 50px;padding-bottom: 10px;}td h3{font-weight: normal;}h3{ font-size: 14px;margin: 0;}table#t01 { width: 100%; background-color: 3333CC;}</style></head>"
						+"<body>"
						+ "<font color=black><h2>"
						+"<P1 align=center>Overview Report</P1>"
						+"</font></h2>"
						+"<div id=overviewtable><table><thead><tr style=background-color:#CCCCFF><td><font color=black><H2>MyRecipes</H2></td><td colspan=2><font color=black>Browser: Firefox<BR><font color=black>Time Taken for Execution in HMS: "+	MilliSecondsToHMS.TimeConvert(TotalTimeTaken)+"<BR><font color=black>Environment:  Test<BR><font color=black>Date of Test Execution:"+ModuleReports.date+"</td></tr><tr style=background-color:#D2B48C><td><font color=black><h3> Total Number of TestCases</h3></td><td><font color=green><h3>Total Pass Tests<h3></td><td><font color=red><h3>Total Failed Tests</h3></td></tr><tr></tr></font></td><tr style=background-color:#D2B48C><td align=center>"+totalsteps+"</td><td align=center>"+passStep+"</td><td align=center>"+failStep+"</a></td></tr></thead></table></div>";
	}
	@AfterSuite
	public static void createModuleHTMLFile() throws Exception
	{
		System.out.println("overallpath in creation :"+OverAllPath);
		String path = OverAllPath + "/"+"SummaryReport"+ ".html";
		File ReportFile1= new File(path);
		ReportFile1.createNewFile();
		FileUtils.writeStringToFile(ReportFile1, Header+moduleReportHeader+updateModule+footer);
		FolderToZip.ZipIt();
		//EmailReport.sendEmail();
	}

}
