package FeedTestCases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;

import Utilities.GetScreenShot;
import Utilities.ProjectConfigurations;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.Sheet;
import jxl.Cell;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class First_Dummy_Class {

	
static String runEnv;	
static String ChromeDriverPath;	
String TestDataSheetPath  ;
String TestSheetName;
String UserName;
String Password;
WebDriver driver;
String browser;
String UserName1; 
ExtentHtmlReporter htmlReporter;
ExtentReports extent;
ExtentTest logger;
Date date;   

	
	@BeforeSuite	
	public void setup() throws IOException {
		
		System.out.println("setup Method ");
		runEnv=ProjectConfigurations.LoadProperties("RunEnv");
		
		
		TestDataSheetPath= System.getProperty("user.dir") + "//TestData//DriverExcel.xls" ;
		TestSheetName =ProjectConfigurations.LoadProperties("TestSheetName");
		browser = ProjectConfigurations.LoadProperties("browser");
		
		
	    if(browser.equalsIgnoreCase("chrome")) {
	    	WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
	    	
	    }
	    else if (browser.equalsIgnoreCase("firefox"))
		
	    {
	    	WebDriverManager.firefoxdriver().setup();
			WebDriver driver = new FirefoxDriver();
	    }
	    
	    else if (browser.equalsIgnoreCase("edge")) {
	    	
	    	
	    	WebDriverManager.edgedriver().setup();
	    	WebDriver driver = new EdgeDriver();
	    }
		
    
	//**************Test Result Path and Extent Reports  **********************************************
		
		String fileSuffix = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date()) +".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/Logs/testReport"+fileSuffix);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		logger = extent.createTest("MyFirstTest", "Sample description");
		   
	}
	
@AfterSuite
public void tearDown() throws IOException {
	
	driver.quit();
	extent.flush();
	
}
	



@AfterMethod
public void getResult(ITestResult result) throws IOException
{
    if(result.getStatus() == ITestResult.FAILURE)
    {
        String screenShotPath = GetScreenShot.capture(driver, "screenShotName");
        //logger.log(Status.FAIL, result.getThrowable());
        logger.log(Status.FAIL, "Snapshot below: " + logger.addScreenCaptureFromPath(screenShotPath));
    }
    
}
 
	
	
	
	 
@Test
	public  void test() throws IOException {
		// TODO Auto-generated method stub
		
	

	    
		  logger.log(Status.INFO, "This step shows usage of log(status, details)");
         
		
		
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
						   
						  
						  
						   test1(driver,logger);
						  
		
					   }
					   
					   
				   }
				   
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		

	}


@Test
public  void test1(WebDriver driver,ExtentTest logger ) {
	
	System.out.println("Executed Test1");
	 
	 driver.get("https://www.seleniumeasy.com/selenium-tutorials/manage-webdriverdriver-executables-using-webdrivermanager");
	String expectedTitle = driver.getTitle();
	
	String originalTitle = "Dummy";
	Assert.assertEquals(originalTitle, expectedTitle);
	
}
	
		 
	

	
}
