package com.flipkart.genericUtility;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author daniel
 *
 */
public class WebDriverUtility {
	private WebDriver driver;
	private WebDriverWait wait;
	private Select sel;


	/**
	 * This  method is used to launch the browser, maximize, implicitly wait and open application
	 * @param browser
	 * @param url
	 * @return
	 */
	public WebDriver launchApplication( String browser, String url)
	{
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browser.equals("ie")){
			driver = new InternetExplorerDriver();
			WebDriverManager.chromedriver().setup();
		}
		else
		{
			System.out.println("Please enter valid browser name");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}


	/**
	 * This method used to handle drop down by select class using getOptions
	 * @param ele
	 * @return
	 */
	public List<WebElement> handleDropdown(WebElement ele)
	{
		Select sel = new Select(ele);
		List<WebElement> value = sel.getOptions();
		return value;
	}

	/**
	 * This method used to handle drop down by select class using selectByVisibleText
	 * @param ele
	 * @param text
	 */
	public void handleDropdown(WebElement ele,String text)
	{
		sel = new Select(ele);
		sel.selectByVisibleText(text);
	}


	/**
	 * This method used to handle drop down by select class using selectByIndex
	 * @param ele
	 * @param index
	 */
	public void handleDropdown(WebElement ele,int index)
	{
		sel = new Select(ele);
		sel.selectByIndex(index);
	}


	/**
	 * This method used to handle drop down by select class using selectByValue
	 * @param name
	 * @param ele
	 */
	public void handleDropdown( String name, WebElement ele)
	{
		sel = new Select(ele);
		sel.selectByValue(name);
	}


	/**
	 * this method will wait for particular element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToLoad(WebElement element) {

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}


	public void waitForElementtoInvissible(WebElement element) {

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**'
	 * This method will wait for particular element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebElement element) {

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will perform double click over a particular element
	 * @param driver
	 * @param element
	 */
	public void doubleClickOn(WebElement element) {

		Actions a = new Actions(driver);
		a.doubleClick(element).perform();
	}

	/**
	 * This method will perform mouse hover action on a particular element
	 * @param driver
	 * @param target
	 */
	public void mouseHoverOn(WebElement target)
	{
		Actions a = new Actions(driver);
		a.moveToElement(target).perform();
	}

	/**
	 * This method will perform mouse hover action over the offset
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void mouseHoverOn(int x , int y ) 
	{
		Actions a = new Actions(driver);
		a.moveByOffset(x, y).perform();
	}

	/**
	 * This method will perform right click on the page
	 * @param driver
	 */
	public void rightClickOn() {
		Actions a = new Actions(driver);
		a.contextClick().perform();
	}

	/**
	 * This method will perform right click on a particular element
	 * @param target
	 * @param driver
	 */
	public void rightClickOn(WebElement element ) {
		Actions a = new Actions(driver);
		a.contextClick(element).perform();
	}


	/**
	 * This method will perform double click over a page
	 * @param driver
	 */
	public void doubleClickOn()
	{
		Actions a = new Actions(driver);
		a.doubleClick().perform();
	}



	/**
	 * This method will accept the alert popup
	 * @param driver
	 * @throws InterruptedException 
	 */
	public void acceptAlert() throws InterruptedException {

		Alert ok = driver.switchTo().alert();
		ok.accept();
	}

	/**
	 * This method will dismiss the alert popup
	 * @param driver
	 */
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}


	/**
	 * This method will getText of the alert popup
	 * @param driver
	 */
	public String getTextOfAlert() {
		String value = driver.switchTo().alert().getText();
		return value;
	}

	/**
	 * This method is used for coverting external file into string
	 * @param path
	 * @return
	 */
	public String externalFiles(String path)
	{
		File doc = new File(path);
		String file = doc.getAbsolutePath();
		return file;
	}
	public void executerClick(WebElement element)
	{
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript("arguments[0].click();", element);
	}
	public void executerSendKeys(WebElement element, String text)
	{
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript("arguments[0].value='"+text+"';", element);
	}


	/**
	 * This method is used to switch the window
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void switchingTheWindows(String partialWindowTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String wID = it.next();
			driver.switchTo().window(wID);
			String currentWindowTitle = driver.getTitle();
			if(currentWindowTitle.contains(partialWindowTitle)) {
				break;
			}
		}
	}


}
