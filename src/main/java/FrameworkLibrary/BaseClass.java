package FrameworkLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass 
{
	public static WebDriver driver;
	public static ExtentReports report ;
	public static ExtentTest test;
	public static String CurrentFolderPath;
	public static String CurrentTestCasename;
	public static HashMap<String, String> CurrentTestData;
	public static String CurrentModuleName;
	public static String DataFilesPath;
	
	@BeforeSuite
	public void BeforeSuite()
	{
		System.out.println("Excuting beforesuite");
		
		// get current folder path
		CurrentFolderPath = System.getProperty("user.dir") + "\\Results";
		// create folder with date and time stamp
		DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
		String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
		DateTimeStamp = DateTimeStamp.replace(",", "");
		DateTimeStamp = DateTimeStamp.replace(" ", "_");
		DateTimeStamp = DateTimeStamp.replace(":", "-");
		CurrentFolderPath = CurrentFolderPath + "\\" + DateTimeStamp;
		File dir = new File(CurrentFolderPath);
		dir.mkdir();
		File dir1 = new File(CurrentFolderPath + "\\Screenshots");
		dir1.mkdir();
		
		report = new ExtentReports(CurrentFolderPath + "\\Summary.HTML");
	}
	
	@BeforeMethod
	public void BeforeMethod(Method m1)
	{
		CurrentTestCasename = m1.getName();
		CurrentModuleName = m1.getDeclaringClass().getSimpleName();
		DataFilesPath = System.getProperty("user.dir") + "\\DataFiles";
		
		CurrentTestData = elib_loadData(CurrentTestCasename, CurrentModuleName);
		
		
		System.out.println("Excuting before method " + CurrentTestCasename);
		test = report.startTest(CurrentTestCasename);
	}
	
	@AfterMethod
	public void AfterMethod(ITestResult result)
	{
		System.out.println("Excuting aftermethod");
		if(result.getStatus()!=1)
		{
			test.log(LogStatus.FAIL, "Failed to Execute step"
		                 + test.addScreenCapture(getscreenshot()));
		}
		report.endTest(test);	
		
		if(driver!=null)
		{
			driver.quit();
		}
	}
	@AfterSuite
	public void AfterSuite()
	{
		System.out.println("Excuting aftersuite");
		report.close();
	
		driver = new FirefoxDriver();
		driver.get(CurrentFolderPath + "\\Summary.HTML");
	}
	public static String getscreenshot()
	{
		String Dest = CurrentFolderPath + "\\Screenshots\\";
		DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
		String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
		DateTimeStamp = DateTimeStamp.replace(",", "");
		DateTimeStamp = DateTimeStamp.replace(" ", "_");
		DateTimeStamp = DateTimeStamp.replace(":", "-");
		
		Dest = Dest + CurrentTestCasename + DateTimeStamp +".png";
		
		TakesScreenshot ts =(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destinationfile = new File(Dest);
		
		try
		{
			FileUtils.copyFile(source, destinationfile);
		}
		catch(Exception e)
		{
			System.out.println("<<Exception>>  Unable to take screenshot");
		}
		
		return Dest; 
	}
	public HashMap<String,String> elib_loadData(String TestCaseName,String ModuleName)
	{
		HashMap<String,String> TestData = new HashMap<String,String>();
		try
		{		
			String FilePath = DataFilesPath + "\\" + ModuleName + ".xlsx";
			File f1 = new File(FilePath);
			FileInputStream fis = new FileInputStream(f1);
			XSSFWorkbook wb1 = new XSSFWorkbook(fis);
			
			XSSFSheet ws1 = wb1.getSheet("TestData");
			
			int rowcount = ws1.getLastRowNum();
			for(int r = 0;r<=rowcount;r++)
			{
				if(ws1.getRow(r).getCell(0)!=null)
				{
					String Excel_TestCaseName = ws1.getRow(r).getCell(0).getStringCellValue();
					if(Excel_TestCaseName.equalsIgnoreCase(TestCaseName))
					{
						int datarow = r;
						int valuerow = r + 1;
						int cellcount = ws1.getRow(datarow).getLastCellNum();
						for(int c = 0;c<cellcount;c++)
						{
							String ExcelFieldName="";
							String ExcelFieldValue="";
							if(ws1.getRow(datarow).getCell(c)!=null)
							ExcelFieldName = ws1.getRow(datarow).getCell(c).getStringCellValue();
							if(ws1.getRow(valuerow).getCell(c)!=null)
							ExcelFieldValue = ws1.getRow(valuerow).getCell(c).getStringCellValue();
							if(ExcelFieldName.length()>1)
							TestData.put(ExcelFieldName, ExcelFieldValue);
						}
						break;
					}
				}
			}
			fis.close();
			wb1.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while reading data of '" + TestCaseName + "'  TestCase and '" + ModuleName + "' Module");
		}
		return TestData;
	}
	
}
