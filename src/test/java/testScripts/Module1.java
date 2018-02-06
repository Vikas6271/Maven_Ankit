package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import FrameworkLibrary.BaseClass;
import objectRepository.Pg101_Login;
import objectRepository.Pg102_Home;

public class Module1 extends BaseClass
{
	@Test
	public void TC101()
	{
		System.out.println("Executing TC101");
		
		Pg101_Login.LaunchApplication();
		Pg101_Login.LoginToApplication();
		
		Pg102_Home.NavigateToMyInfoPage();
	
		driver.quit();
		test.log(LogStatus.PASS, "Able to close the current browser");
	}
	
	@Test
	public void TC102()
	{
		System.out.println("Executing TC102");
		Pg101_Login.LaunchApplication();
		Pg101_Login.LoginToApplication();
		
		Pg102_Home.NavigateToMyInfoPage();
		
		
		
		
		
		
		
		
		
		
		
		
		driver.quit();
		test.log(LogStatus.PASS, "Able to close the current browser");
	}
	
	@Test
	public void TC103()
	{
		System.out.println("Executing TC103");
		driver=new FirefoxDriver();
		
		driver.get("http://testingmasters.com/hrm");
		test.log(LogStatus.PASS, "Application launched successfully");
		
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("ankit3");
		test.log(LogStatus.PASS, "Able to enter username");
		
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abcd1234");
		test.log(LogStatus.PASS, "Able to enter password");
		
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		test.log(LogStatus.PASS, "Able to click on login");
		
		
		driver.findElement(By.xpath("//b[text()='My Info']")).click();
		test.log(LogStatus.PASS, "Able to click on MyInfo");
		
		
		driver.quit();
		test.log(LogStatus.PASS, "Able to close the current browser");
	}
}
