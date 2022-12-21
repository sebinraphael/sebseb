package com.flipkart.topOffer;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.flipkart.TopOfferRep.topOfferPage;
import com.flipkart.genericUtility.ConstantUtility;
import com.flipkart.genericUtility.ExcelSheetName;
import com.flipkart.genericUtility.PropertiesUtility;
import com.flipkart.genericUtility.PropertyFileKeys;
import com.flipkart.genericUtility.WebDriverUtility;

public class FlipkartFetchTopoffer10perOffTest {
	@Test
	public static void fetchPrice() throws EncryptedDocumentException, IOException{
		
		WebDriverUtility webdriver = new WebDriverUtility();
		PropertiesUtility prop= new PropertiesUtility();

		String browser = prop.getDataFromPropertyFile(ConstantUtility.PROPERTYFILE_PATH.toString(),PropertyFileKeys.BROWSER.convertToString());
		String url = prop.getDataFromPropertyFile(ConstantUtility.PROPERTYFILE_PATH.toString(),PropertyFileKeys.URL.convertToString());
		
	
		WebDriver driver = webdriver.launchApplication(browser, url);
		topOfferPage offer = new topOfferPage(driver);
	
		String excelPath = ConstantUtility.EXCEL_PATH.toString();
		String sheetName = ExcelSheetName.TopOffers.toString();
		offer.getThelist(excelPath, sheetName);
		System.out.println("exicuted sucessfully=====>");
		driver.quit();
		
	}
}
