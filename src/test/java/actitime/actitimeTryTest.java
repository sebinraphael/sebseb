package actitime;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class actitimeTryTest extends BeforeClassPractice{

		@Test
		public void loginaction()
		{
			
			Reporter.log("Test annotation==>", true);
//			WebDriverManager.chromedriver().setup();
//			WebDriver driver = new ChromeDriver();
//			driver.get("http://localhost/login.do");
//			
//			 String username = System.getProperty("username");
//			String password = System.getProperty("password");
			
//			driver.findElement(By.id("username")).sendKeys(username);
//			driver.findElement(By.name("pwd")).sendKeys(password);
//			driver.findElement(By.xpath("//div[.='Login ']")).click();	
		}

}