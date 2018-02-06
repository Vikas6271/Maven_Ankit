package objectRepository;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import FrameworkLibrary.BaseClass;

public class Pg102_Home extends BaseClass
{
	public static By Lnk_Leave = By.xpath("//b[text()='Leave']");
	public static By Lnk_Time = By.xpath("//b[text()='Time']");
	public static By Lnk_MyInfo = By.xpath("//b[text()='My Info']");
	public static By Lnk_Performance = By.xpath("//b[text()='Performance']");
	public static By Lnk_Dashboard = By.xpath("//b[text()='Dashboard']");
	public static By Lnk_Directory = By.xpath("//b[text()='Directory']");
	
	public static By Lnk_LogoutBar = By.xpath("//a[@id='welcome']");
	public static By Lnk_Logout = By.xpath("//a[text()='Logout']");


	public static void  NavigateToMyInfoPage()
	{
		driver.findElement(By.xpath("//b[text()='My Info']")).click();
		test.log(LogStatus.PASS, "Able to click on MyInfo");
	}




}
