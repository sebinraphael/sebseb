package actitime;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BeforeClassPractice {
	private WebDriver driver;
	
	
@Parameters({"usernames", "password"})
@BeforeMethod
public void befrMethod(@Optional("admin")String usernames, @Optional("manager")String password)
{
	Reporter.log("befor method==>", true);
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("http://localhost/login.do");
	driver.findElement(By.id("username")).sendKeys(usernames);
	driver.findElement(By.name("pwd")).sendKeys(password);
	driver.findElement(By.xpath("//div[.='Login ']")).click();
	
}
 

@AfterMethod
public void afterMethod()
{
	driver.close();
	Reporter.log("after method==>", true);
}
}