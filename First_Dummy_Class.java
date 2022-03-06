package FeedTestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;
import Utilities.ProjectConfigurations;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.Sheet;
import jxl.Cell;




public class First_Dummy_Class {

	
static String runEnv;	
static String ChromeDriverPath;	
String TestDataSheetPath  ;
String TestSheetName;
String UserName;
String Password;



	
	@BeforeTest	
	public void setup() throws IOException {
		
		System.out.println("setup Method ");
		runEnv=ProjectConfigurations.LoadProperties("RunEnv");
		
		//TestDataSheetPath=ProjectConfigurations.LoadProperties("TestDataSheetPath");
		TestDataSheetPath= System.getProperty("user.dir") + "//TestData//DriverExcel.xls" ;
		TestSheetName =ProjectConfigurations.LoadProperties("TestSheetName");
		String UserName; 
	}
    
	//**********************Extent Report ************************
	
	//builds a new report using the html template 
    ExtentHtmlReporter htmlReporter;
    
    ExtentReports extent;
    //helps to generate the logs in test report.
    ExtentTest test;
    
	
	
	
	 
	@Test
	public  void test() throws IOException {
		// TODO Auto-generated method stub
		
		//*******************************************************************************
		
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/Logs/testReport.html");

		//initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		//To add system or environment info by using the setSystemInfo method.
		
		  ExtentTest logger = extent.createTest("MyFirstTest", "Sample description");

	        // log(Status, details)
		  logger.log(Status.INFO, "This step shows usage of log(status, details)");

	        // info(details)
		  logger.info("This step shows usage of info(details)");
		  
		
		System.out.println("Om Naamaah Shivaay ");
		
		System.out.println(runEnv);
		System.out.println(ChromeDriverPath);
		
		
		//************************************
		
		String TestKeyWord = "TC001";
		String KeyWord= null;
		
		
		int r =0;
	
			
			int rows =0;
			System.out.println(TestDataSheetPath);
			Workbook wrk1;
			try {
				wrk1 = Workbook.getWorkbook(new File(TestDataSheetPath));
				
				   Sheet sheet1 = wrk1.getSheet(TestSheetName);
				   rows=sheet1.getRows();
				   
				   System.out.println("Number Of Rows in Test Data file is : " + rows);
				   
				   Cell [] FirstRow = sheet1.getRow(0);
				   
				   Map<String ,Integer> map = new HashMap<String , Integer>();
				   
				   for(int i =0;i<FirstRow.length;i++) {
					   
					   map.put(FirstRow[i].getContents().trim(),i);
				   }
				   
				   //*******************************************
				   System.out.println(map);
				   
				   for(r=1;r<rows;r++) {
					   
					   
					   KeyWord = sheet1.getCell(map.get("KeyWord"),r).getContents().trim();
					   
					   System.out.println(KeyWord);
					   if(KeyWord.equalsIgnoreCase(TestKeyWord)) {
						   
						   System.out.println("keyword" + KeyWord);
						   	

						   UserName= sheet1.getCell(map.get("UserName"),r).getContents().trim();
						   Password= sheet1.getCell(map.get("Password"),r).getContents().trim();
						   
						   System.out.println("UserName: " + UserName);
						   System.out.println("Password : " + Password);
					   }
					   
					   
					   
					   
				   }
				   
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			extent.flush();
		

	}

}
