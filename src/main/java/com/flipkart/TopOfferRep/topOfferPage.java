package com.flipkart.TopOfferRep;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.genericUtility.ExcelWorkBook;

public class topOfferPage {

	ExcelWorkBook excel = new ExcelWorkBook();
	@FindBy (xpath="//div[contains(text(),'10%')]/preceding-sibling::div[@class='_3LU4EM']") private List<WebElement> listByName;
	@FindBy (xpath="//preceding-sibling::div[@class='_3LU4EM']/following-sibling::div[contains(text(),'10%')]") private List<WebElement> listByPer;

	public topOfferPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	
	/**
	 * This method is used to write values in multiple columns
	 * @param path
	 * @param sheetName
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void getThelist(String path, String sheetName) throws EncryptedDocumentException, IOException
	{
		excel.writeInMultipleColumn(path, sheetName,listByName, listByPer);
	}
}
