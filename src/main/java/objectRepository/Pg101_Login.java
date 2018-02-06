package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.LogStatus;

import FrameworkLibrary.BaseClass;

public class Pg101_Login extends BaseClass
{
	public static By Edt_UserName = By.id("txtUsername");
	public static By Edt_Password = By.xpath("//input[@id='txtPassword']");
	public static By Btn_Login = By.xpath("//input[@id='btnLogin']");

	public static void LaunchApplication()
	{
		driver=new FirefoxDriver();
		
		driver.get("http://testingmasters.com/hrm");
		test.log(LogStatus.PASS, "Application launched successfully");
	}
	
	public static void LoginToApplication()
	{
		driver.findElement(Pg101_Login.Edt_UserName).sendKeys(CurrentTestData.get("UserName"));
		test.log(LogStatus.PASS, "Able to enter username");
		
		driver.findElement(Pg101_Login.Edt_Password).sendKeys(CurrentTestData.get("Password"));
		test.log(LogStatus.PASS, "Able to enter password");
		
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		test.log(LogStatus.PASS, "Able to click on login");
	}
	
	
	
	
	
	


}
